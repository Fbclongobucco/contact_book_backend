package com.buccodev.contact_book.resources;

import com.buccodev.contact_book.dto.*;
import com.buccodev.contact_book.services.UserService;
import org.apache.catalina.User;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        var user = userService.findUsersById(id, token);

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO){

        var idSalved = userService.createUser(userDTO);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()  
                .path("/{id}")          
                .buildAndExpand(idSalved) 
                .toUri();

        return ResponseEntity.created(location).body("user created in resource "+location);

    }

    @PostMapping("/create_admin")
    @PreAuthorize("hasAuthority('SCOPE_admin')")
    public ResponseEntity<String> createAdminUser(@RequestBody UserDTO userDTO){

        var idSalved = userService.createAdminUser(userDTO);

        var resposnse = "new admin user saved with id: "+idSalved;

        return ResponseEntity.ok(resposnse);

    }



    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO, JwtAuthenticationToken token){

        userService.updateUser(id, userUpdateDTO, token);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id, JwtAuthenticationToken token){

        userService.deleteUsersById(id, token);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginValidate(@RequestBody LoginDTO loginDTO){

        var validate = userService.validateUser(loginDTO);


        return ResponseEntity.ok(validate);

    }

    @GetMapping("/{idUser}/contacts")
    public ResponseEntity<List<ContactDTO>> getContactsByUsers(
            @PathVariable Long idUser,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            JwtAuthenticationToken token
    ){
        var contacts = userService.getContactsByUsersId(idUser, page, size, token);

        return ResponseEntity.ok(contacts);
    }


}
