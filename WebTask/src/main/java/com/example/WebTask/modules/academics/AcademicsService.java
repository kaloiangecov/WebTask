package com.example.WebTask.modules.academics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicsService {

    private AcademicsRepository academicsRepository;

    @Autowired
    public AcademicsService(AcademicsRepository academicsRepository) {
        this.academicsRepository = academicsRepository;
    }

    public Academics codeExists(String code, long id) {
        Academics academics = academicsRepository.findByCode(code);
        if (academics != null && academics.getId() != id) {
            return academics;
        }
        return null;
    }
}
