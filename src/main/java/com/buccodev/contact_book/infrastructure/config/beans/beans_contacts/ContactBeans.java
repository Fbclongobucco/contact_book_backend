package com.buccodev.contact_book.infrastructure.config.beans.beans_contacts;

import com.buccodev.contact_book.application.gateway.contact_gateway.DeleteContactGateway;
import com.buccodev.contact_book.application.gateway.contact_gateway.GetContactGateway;
import com.buccodev.contact_book.application.gateway.contact_gateway.RegisterContactGateway;
import com.buccodev.contact_book.application.gateway.contact_gateway.UpdateContactGateway;
import com.buccodev.contact_book.application.usecases.contacts_usecases.DeleteContact;
import com.buccodev.contact_book.application.usecases.contacts_usecases.GetContact;
import com.buccodev.contact_book.application.usecases.contacts_usecases.RegisterContact;
import com.buccodev.contact_book.application.usecases.contacts_usecases.UpdateContact;
import com.buccodev.contact_book.application.usecases.user_usecases.GetUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactBeans {

    @Bean
    RegisterContactGateway registerContactGateway(RegisterContact registerContact, GetContact getContact) {
        return new RegisterContactGateway(registerContact, getContact);
    }

    @Bean
    UpdateContactGateway updateContactGateway(UpdateContact updateContact, GetContact getContact) {
        return new UpdateContactGateway(updateContact, getContact);
    }

    @Bean
    DeleteContactGateway deleteContactGateway(DeleteContact deleteContact, GetContact getContact) {
        return new DeleteContactGateway(deleteContact, getContact);
    }

    @Bean
    GetContactGateway getContactGateway(GetContact getContact, GetUser getUser) {
        return new GetContactGateway(getContact, getUser);
    }
}
