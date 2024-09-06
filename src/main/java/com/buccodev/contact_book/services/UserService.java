package com.buccodev.contact_book.services;

import com.buccodev.contact_book.dto.*;
import com.buccodev.contact_book.entities.Users;
import com.buccodev.contact_book.repository.ContactRepository;
import com.buccodev.contact_book.repository.UserRepository;
import com.buccodev.contact_book.services.exceptions.DataBaseExceptcion;
import com.buccodev.contact_book.services.exceptions.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;


    public Long saveUser(UserDTO userDTO) {

        try {

            var user = new Users(null, userDTO.name(), passwordEncoder.encode(userDTO.password()), userDTO.email());

            return userRepository.save(user).getId();

        } catch (DataIntegrityViolationException | ConstraintViolationException e) {

            throw new DataBaseExceptcion(e.getMessage());

        }

    }

    public UserResponseDTO findUsersById(Long id){

            var user =  userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));

           List<ContactDTO> contactDTOS = user.getContacts().stream().map(x -> new ContactDTO(x.getId(), x.getName(), x.getNumber())).toList();

        return new UserResponseDTO(user.getId(),user.getName(), user.getEmail(), contactDTOS);
    }

    public void updateUser(Long id, UserUpdateDTO userUpdateDTO){

        var user =  userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));

        user.setName(userUpdateDTO.name());
        user.setEmail(userUpdateDTO.email());

        try{

            userRepository.save(user);

        } catch (DataIntegrityViolationException | ConstraintViolationException e){

            throw new DataBaseExceptcion(e.getMessage());

        }

    }

    public void deleteUsersById(Long id){

        try {
            userRepository.deleteById(id);

        } catch (DataIntegrityViolationException e){

            throw  new DataBaseExceptcion(e.getMessage());

        }

    }

    public List<UserResponseDTO> getAllUsers(Integer page, Integer size){

        Pageable pageable = PageRequest.of(page, size);

        Page<Users> users = userRepository.findAll(pageable);

        return users.stream().map(x -> new UserResponseDTO(x.getId(), x.getName(), x.getEmail(), x.getContacts().stream()
                .map(c -> new ContactDTO(c.getId(), c.getName(), c.getNumber())).toList())).toList();

    }

    public LoginResponseDTO validateUser(LoginDTO loginDTO){

           try {
               var user = userRepository.findByEmail(loginDTO.email()).orElseThrow(() -> new ResourceNotFoundException(loginDTO.email()));

               var sucess = passwordEncoder.matches(loginDTO.password(), user.getPassword());

               return new LoginResponseDTO(sucess, user.getId());

           } catch (Exception e){

               throw new ResourceNotFoundException(loginDTO.email());

           }
    }

    public ContactDTO createContact(Long id, ContactDTO contactDTO){

        var user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));

        var newContact = contactService.saveContact(contactDTO);


        newContact.setUsers(user);

        contactRepository.save(newContact);

        user.getContacts().add(newContact);

        userRepository.save(user);

        return contactDTO;
    }

    public ContactDTO findContactById(Long idUser, Long idContact){

        var user = userRepository.findById(idUser).orElseThrow(()-> new ResourceNotFoundException(idUser));

        var contact = user.getContacts().stream().filter(c -> c.getId().equals(idContact)).findFirst().orElseThrow(()-> new ResourceNotFoundException(idContact));

        return new ContactDTO(null, contact.getName(), contact.getNumber());
    }

    public void updateContact(Long idUser, Long idcontact, ContactDTO contactDTO){

        var user = userRepository.findById(idUser).orElseThrow(()-> new ResourceNotFoundException(idUser));

        var contact = user.getContacts().stream().filter(c->c.getId().equals(idcontact)).
                findFirst().orElseThrow(()->new ResourceNotFoundException(idcontact));

        contact.setName(contactDTO.name());
        contact.setNumber(contactDTO.number());

        contactRepository.save(contact);
    }

    public void deleteContact(Long idUser, Long idContact){

        var user = userRepository.findById(idUser).orElseThrow(()-> new ResourceNotFoundException(idUser));

        var contact = user.getContacts().stream().filter(c -> c.getId().equals(idContact))
                .findFirst().orElseThrow(()-> new ResourceNotFoundException(idContact));
        user.getContacts().remove(contact);

       try {

           contactRepository.deleteById(contact.getId());

           userRepository.save(user);

       } catch (DataIntegrityViolationException | ResourceNotFoundException e){

           throw  new DataBaseExceptcion(e.getMessage());

       }

    }
}
