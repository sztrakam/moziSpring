package com.example.mozispring.Service;

import com.example.mozispring.Controller.ContentController;
import com.example.mozispring.Model.MyAppContact;
import com.example.mozispring.Model.MyAppContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private MyAppContactRepository contactRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    public MyAppContact createContact(MyAppContact contact) {
        logger.info("Contact mentve");
        return contactRepository.save(contact);

    }

}
