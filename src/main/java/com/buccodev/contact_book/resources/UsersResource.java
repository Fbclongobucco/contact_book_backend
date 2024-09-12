package com.buccodev.contact_book.resources;

import com.buccodev.contact_book.dto.*;
import com.buccodev.contact_book.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UsersResource {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<List<UserResponseDTO>> getAllusers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){

        var userDTOS = userService.getAllUsers(page, size);


        return ResponseEntity.ok(userDTOS);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id, JwtAuthenticationToken token){

        var user = userService.findUsersById(Long.parseLong(token.getName()));

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO){

        var idSalved = userService.saveUser(userDTO);

        var resposnse = "new user saved with id: "+idSalved;

        return ResponseEntity.ok(resposnse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO){

        userService.updateUser(id, userUpdateDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){

        userService.deleteUsersById(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginValidate(@RequestBody LoginDTO loginDTO){

        var validate = userService.validateUser(loginDTO);


        return ResponseEntity.ok(validate);

    }

    @PostMapping("/{id}/contact")
    public ResponseEntity<String> createContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO){

        var contact = userService.createContact(id, contactDTO);

        var msg = "contact "+contact.name()+" salved!";

        return ResponseEntity.ok(msg);

    }

    @GetMapping("/{idUser}/contact/{idContact}")
    public ResponseEntity<ContactDTO> findContactById(@PathVariable Long idUser, @PathVariable Long idContact){

        var contact = userService.findContactById(idUser, idContact);

        return  ResponseEntity.ok(contact);
    }

    @PutMapping("/{idUser}/contact/{idContact}")
    public ResponseEntity<Void> updateContact(@PathVariable Long idUser, @PathVariable Long idContact, @RequestBody ContactDTO contactDTO){

        userService.updateContact(idUser, idContact, contactDTO);

        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idUser}/contact/{idContact}")
    public ResponseEntity<Void> deleteConatct(@PathVariable Long idUser, @PathVariable Long idContact){

        userService.deleteContact(idUser,idContact);

        return ResponseEntity.ok().build();
    }

}
