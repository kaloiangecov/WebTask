package com.example.WebTask.modules.academics.faculty;

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
public class FacultyController {

    private FacultyService facultyService;
    private Map<String, String> errors;
    private static final int EMPTY = 0;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping(value = "/faculties")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Faculty> findAllFaculties() {
        return facultyService.findAll();
    }

    @GetMapping(value = "/faculties/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Faculty findFacultyById(@PathVariable Long id) throws Exception {
        return facultyService.findById(id);
    }

    @PostMapping(value = "faculties")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createFaculty(@RequestBody @Valid Faculty faculty,
                                                BindingResult bindingResult) {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(facultyService.create(faculty), HttpStatus.OK);
    }

    @PutMapping(value = "faculties/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateFaculty(@PathVariable Long id,
                                                @RequestBody @Valid Faculty faculty,
                                                BindingResult bindingResult) throws Exception {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(facultyService.update(faculty, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "faculties/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Faculty deleteFaculty(@PathVariable Long id) throws Exception {
        return facultyService.delete(id);
    }

    @GetMapping(value = "/faculties/page")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Page<Faculty> findFacultiesPage(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(name = "elements_per_page", defaultValue = "8") int elementsPerPage,
                                           @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm) {

        return facultyService.getFacultisPage(page, elementsPerPage, searchTerm);
    }
}
