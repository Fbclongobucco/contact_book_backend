package com.buccodev.contact_book.services;

import com.buccodev.contact_book.dto.*;
import com.buccodev.contact_book.entities.Contact;
import com.buccodev.contact_book.entities.Roles;
import com.buccodev.contact_book.entities.Users;
import com.buccodev.contact_book.repository.ContactRepository;
import com.buccodev.contact_book.repository.RolesRepository;
import com.buccodev.contact_book.repository.UserRepository;
import com.buccodev.contact_book.services.exceptions.DataBaseExceptcion;
import com.buccodev.contact_book.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private JwtEncoder jwtEncoder;

	@Transactional
	public Long createUser(UserDTO userDTO) {

		try {

			var user = new Users(null, userDTO.name(), passwordEncoder.encode(userDTO.password()), userDTO.email());

			var roleBasic = rolesRepository.findByName(Roles.Values.BASIC.name().toLowerCase());

			user.setRoles(Set.of(roleBasic));

			var contacts = userDTO.contacts().stream().map(c -> {
				var contact = new Contact(null, c.name(), c.number());
				contact.setUsers(user);
				return contact;
			}).collect(Collectors.toList());

			contactRepository.saveAll(contacts);

			
			user.getContacts().addAll(contacts);

			return userRepository.save(user).getId();

		} catch (DataIntegrityViolationException | ConstraintViolationException e) {

			throw new DataBaseExceptcion(e.getMessage());

		}

	}

	@Transactional
	public Long createAdminUser(UserDTO userDTO) {

		try {

			var user = new Users(null, userDTO.name(), passwordEncoder.encode(userDTO.password()), userDTO.email());

			var roleAdmin = rolesRepository.findByName(Roles.Values.ADMIN.name().toLowerCase());

			user.setRoles(Set.of(roleAdmin));

			return userRepository.save(user).getId();

		} catch (DataIntegrityViolationException | ConstraintViolationException e) {

			throw new DataBaseExceptcion(e.getMessage());

		}

	}

	public UserResponseDTO findUsersById(Long id, JwtAuthenticationToken token) {

		var userIdFromToken = extractUserIdFromToken(token);

		var requestingUser = getUserById(userIdFromToken);

		var targetUser = getUserById(id);

		if (isAuthorized(requestingUser, targetUser)) {

			List<ContactDTO> contactDTOS = convertToContactDTOs(targetUser.getContacts());

			return new UserResponseDTO(targetUser.getId(), targetUser.getName(), targetUser.getEmail(), contactDTOS);

		} else {

			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied!");

		}
	}

	@Transactional
	public void updateUser(Long id, UserUpdateDTO userUpdateDTO, JwtAuthenticationToken token) {

		var idToken = extractUserIdFromToken(token);

		var userFromToken = getUserById(idToken);

		var user = getUserById(id);

		if (isAuthorized(userFromToken, user)) {

			user.setName(userUpdateDTO.name());
			user.setEmail(userUpdateDTO.email());
			userRepository.save(user);

		} else {

			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied!");

		}

	}

	public void deleteUsersById(Long id, JwtAuthenticationToken token) {

		var idToken = extractUserIdFromToken(token);

		var userFromToken = getUserById(idToken);

		var user = getUserById(id);

		if (isAuthorized(userFromToken, user)) {

			userRepository.deleteById(id);

		} else {

			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied!");

		}

	}

	public List<UserResponseDTO> getAllUsers(Integer page, Integer size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<Users> users = userRepository.findAll(pageable);

		return users.stream().map(x -> new UserResponseDTO(x.getId(), x.getName(), x.getEmail(),
				x.getContacts().stream().map(c -> new ContactDTO(c.getId(), c.getName(), c.getNumber())).toList()))
				.toList();

	}

	public LoginResponseDTO validateUser(LoginDTO loginDTO) {

		var user = userRepository.findByEmail(loginDTO.email())
				.orElseThrow(() -> new ResourceNotFoundException(loginDTO.email()));

		if (!user.isLoginCorrect(loginDTO, passwordEncoder)) {
			throw new BadCredentialsException("user or password is invalid");
		}

		var now = Instant.now();
		var expiresIn = 500L;

		var scopes = user.getRoles().stream().map(Roles::getName).collect(Collectors.joining(" "));

		var claims = JwtClaimsSet.builder().issuer("mybackend").subject(user.getId().toString()).issuedAt(now)
				.expiresAt(now.plusSeconds(expiresIn)).claim("scope", scopes).build();

		var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

		return new LoginResponseDTO(jwtValue, expiresIn);
	}

	@Transactional
	public ContactDTO createContact(Long id, ContactDTO contactDTO, JwtAuthenticationToken token) {

		var userIdFromToken = extractUserIdFromToken(token);
		var userRequesting = getUserById(userIdFromToken);
		var targetUser = getUserById(id);

		if (isAuthorized(userRequesting, targetUser)) {
			Contact newContact = contactRepository.save(new Contact(null, contactDTO.name(), contactDTO.number()));
			newContact.setUsers(targetUser);
			contactRepository.save(newContact);

			targetUser.getContacts().add(newContact);
			userRepository.save(targetUser);

			return contactDTO;
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied!");
		}
	}

	public ContactDTO findContactById(Long idUser, Long idContact, JwtAuthenticationToken token) {

		var userIdFromToken = extractUserIdFromToken(token);
		var userRequesting = getUserById(userIdFromToken);
		var targetUser = getUserById(idUser);

		if (isAuthorized(userRequesting, targetUser)) {

			var contact = targetUser.getContacts().stream().filter(c -> c.getId().equals(idContact)).findFirst()
					.orElseThrow(() -> new ResourceNotFoundException(idContact));

			return new ContactDTO(contact.getId(), contact.getName(), contact.getNumber());

		} else {

			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied!");

		}
	}

	public List<ContactDTO> getContactsByUsersId(Long userId, Integer page, Integer size,
			JwtAuthenticationToken token) {

		Long userIdFromToken = extractUserIdFromToken(token);

		if (!userIdFromToken.equals(userId)) {
			throw new BadCredentialsException("you are not allowed to access these contacts.");
		}

		Pageable pageable = PageRequest.of(page, size);

		Page<ContactDTO> contactsDTO = contactRepository.findByUsersId(userId, pageable)
				.map(contact -> new ContactDTO(contact.getId(), contact.getName(), contact.getNumber()));

		return contactsDTO.getContent();
	}

	public void updateContact(Long idUser, Long idcontact, ContactDTO contactDTO, JwtAuthenticationToken token) {

		var userIdFromToken = extractUserIdFromToken(token);
		var userRequesting = getUserById(userIdFromToken);
		var targetUser = getUserById(idUser);

		if (isAuthorized(userRequesting, targetUser)) {

			var contact = targetUser.getContacts().stream().filter(c -> c.getId().equals(idcontact)).findFirst()
					.orElseThrow(() -> new ResourceNotFoundException(idcontact));

			contact.setName(contactDTO.name());
			contact.setNumber(contactDTO.number());

			contactRepository.save(contact);

		} else {

			throw new BadCredentialsException("Access denied!");

		}
	}

	public void deleteContact(Long idUser, Long idContact, JwtAuthenticationToken token) {

		var userIdFromToken = extractUserIdFromToken(token);
		var userRequesting = getUserById(userIdFromToken);
		var targetUser = getUserById(idUser);

		if (isAuthorized(userRequesting, targetUser)) {

			var contact = targetUser.getContacts().stream().filter(c -> c.getId().equals(idContact)).findFirst()
					.orElseThrow(() -> new ResourceNotFoundException(idContact));
			targetUser.getContacts().remove(contact);

			contactRepository.deleteById(contact.getId());

			userRepository.save(targetUser);

		} else {

			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied!");

		}

	}

	private Long extractUserIdFromToken(JwtAuthenticationToken token) {
		return Long.parseLong(token.getName());
	}

	private Users getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	private boolean isAuthorized(Users requestingUser, Users targetUser) {

		boolean isAdmin = requestingUser.getRoles().stream()
				.anyMatch(role -> role.getName().equalsIgnoreCase(Roles.Values.ADMIN.name()));

		boolean isOwner = requestingUser.getId().equals(targetUser.getId());

		return isAdmin || isOwner;
	}

	private List<ContactDTO> convertToContactDTOs(List<Contact> contacts) {
		return contacts.stream().map(contact -> new ContactDTO(contact.getId(), contact.getName(), contact.getNumber()))
				.toList();
	}

}
