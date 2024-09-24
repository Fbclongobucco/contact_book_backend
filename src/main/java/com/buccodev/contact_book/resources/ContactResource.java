package com.buccodev.contact_book.resources;

import com.buccodev.contact_book.dto.ContactDTO;
import com.buccodev.contact_book.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contacts")
public class ContactResource {

    private final UserService userService;

    public ContactResource(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/{id}/contact")
    public ResponseEntity<String> createContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO, JwtAuthenticationToken token){

        var contact = userService.createContact(id, contactDTO, token);

        var msg = "contact "+contact.name()+" salved!";

        return ResponseEntity.ok(msg);

    }

    @GetMapping("/{idUser}/contact/{idContact}")
    public ResponseEntity<ContactDTO> findContactById(@PathVariable Long idUser, @PathVariable Long idContact, JwtAuthenticationToken token){

        var contact = userService.findContactById(idUser, idContact, token);

        return  ResponseEntity.ok(contact);
    }

    @PutMapping("/{idUser}/contact/{idContact}")
    public ResponseEntity<Void> updateContact(@PathVariable Long idUser, @PathVariable Long idContact, @RequestBody ContactDTO contactDTO, JwtAuthenticationToken token){

        userService.updateContact(idUser, idContact, contactDTO, token);

        return  ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idUser}/contact/{idContact}")
    public ResponseEntity<Void> deleteConatct(@PathVariable Long idUser, @PathVariable Long idContact, JwtAuthenticationToken token){

        userService.deleteContact(idUser,idContact, token);

        return ResponseEntity.ok().build();
    }
}
