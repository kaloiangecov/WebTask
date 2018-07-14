package com.example.WebTask.modules.academics.subject;

import com.example.WebTask.validator.ResponseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SubjectController {

    private SubjectService subjectService;
    private Map<String, String> errors;
    private static final int EMPTY = 0;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(value = "/subjects")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Subject> findAllSubjects() {
        return subjectService.findAll();
    }

    @GetMapping(value = "/subjects/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Subject findSubjectById(@PathVariable Long id) throws Exception {
        return subjectService.findById(id);
    }

    @PostMapping(value = "subjects")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createSubject(@RequestBody @Valid Subject subject,
                                                BindingResult bindingResult) {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(subjectService.create(subject), HttpStatus.OK);
    }

    @PutMapping(value = "subjects/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateSubject(@PathVariable Long id,
                                                @RequestBody @Valid Subject subject,
                                                BindingResult bindingResult) throws Exception {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(subjectService.update(subject, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "subjects/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Subject deleteSubject(@PathVariable Long id) throws Exception {
        return subjectService.delete(id);
    }

    @GetMapping(value = "/subjects/page")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Page<Subject> findSubjectsPage(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(name = "elements_per_page", defaultValue = "8") int elementsPerPage,
                                          @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm) {

        return subjectService.getSubjectsPage(page, elementsPerPage, searchTerm);
    }
}
