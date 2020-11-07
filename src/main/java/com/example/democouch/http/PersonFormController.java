package com.example.democouch.http;

import com.example.democouch.config.DBConfig;
import com.example.democouch.data.PersonForm;
import org.ektorp.CouchDbConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@Validated
public class PersonFormController {

    @Autowired
    private DBConfig dbConfig;

    @GetMapping("/")
    public String showForm(PersonForm personForm) {
        return "form";
    }

    @PostMapping("/")
    public String greetingMessage(PersonForm personForm, Model model) {
        CouchDbConnector db = dbConfig.couchDbConnector();
        String uniqueId = UUID.randomUUID().toString();
        db.create(uniqueId, personForm);
        model.addAttribute("visitorId", uniqueId);
        return "results";
    }
}
