package com.buccodev.contact_book.infrastructure.controllers.user_controller;

import com.buccodev.contact_book.application.gateway.contact_gateway.DeleteContactGateway;
import com.buccodev.contact_book.application.gateway.contact_gateway.GetContactGateway;
import com.buccodev.contact_book.application.gateway.contact_gateway.RegisterContactGateway;
import com.buccodev.contact_book.application.gateway.contact_gateway.UpdateContactGateway;
import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactPhotoUrlDto;
import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactRequestDto;
import com.buccodev.contact_book.application.utils.dtos.contact_dto.ContactResponseDto;
import com.buccodev.contact_book.application.utils.dtos.user_dtos.ContactUpdateDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("contact-book/api/contact")
public class ContactController {

    private final GetContactGateway getContactGateway;
    private final RegisterContactGateway registerContactGateway;
    private final UpdateContactGateway updateContactGateway;
    private final DeleteContactGateway deleteContactGateway;

    public ContactController(GetContactGateway getContactGateway, RegisterContactGateway registerContactGateway, UpdateContactGateway updateContactGateway, DeleteContactGateway deleteContactGateway) {
        this.getContactGateway = getContactGateway;
        this.registerContactGateway = registerContactGateway;
        this.updateContactGateway = updateContactGateway;
        this.deleteContactGateway = deleteContactGateway;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ContactResponseDto> registerContact( @PathVariable
                                                               Long userId,
                                                               @Valid @RequestBody
                                                               ContactRequestDto contactRequestDto) {

        var contact = registerContactGateway.registerContact(userId, contactRequestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contact.id())
                .toUri();

        return ResponseEntity.created(location).body(contact);
    }

    @PutMapping("/{idContact}")
    public ResponseEntity<Void> updateContact(@PathVariable Long idContact,
                                              @RequestBody ContactUpdateDto contactUpdateDto) {
        updateContactGateway.updateContact(idContact, contactUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idContact}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long idContact) {
        deleteContactGateway.deleteContact(idContact);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idContact}")
    public ResponseEntity<ContactResponseDto> getContactById(@PathVariable Long idContact) {
        var contact = getContactGateway.getContactById(idContact);
        return ResponseEntity.ok(contact);
    }

    @PutMapping("/photo/{idContact}")
    public ResponseEntity<Void> updateContactPhoto(@PathVariable Long idContact,
                                                   @RequestBody ContactPhotoUrlDto contactPhotoUrl) {
        updateContactGateway.updateContactPhoto(idContact, contactPhotoUrl);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ContactResponseDto>> getAllContactsByUser(@PathVariable Long id,
                                                                         @RequestParam(defaultValue = "0")
                                                                         Integer page,
                                                                         @RequestParam(defaultValue = "5")
                                                                         Integer size) {
        var contacts = getContactGateway.getAllContactsByUser(id, page, size);
        return ResponseEntity.ok().body(contacts);
    }
}
