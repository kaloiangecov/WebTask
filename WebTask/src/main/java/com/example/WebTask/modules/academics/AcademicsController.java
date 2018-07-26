package com.example.WebTask.modules.academics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AcademicsController {

    private AcademicsService academicsService;

    @Autowired
    public AcademicsController(AcademicsService academicsService) {
        this.academicsService = academicsService;
    }

    @GetMapping(value = "/academics/exists/code")
    public Boolean emailExists(@RequestParam(name = "code") String code,
                               @RequestParam(name = "id") long id) {
        if (academicsService.codeExists(code, id) != null) {
            return true;
        }
        return false;
    }
}
