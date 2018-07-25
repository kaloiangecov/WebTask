package com.example.WebTask.modules.academics.discipline;

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
public class DisciplineController {

    private DisciplineService disciplineService;
    private Map<String, String> errors;
    private static final int EMPTY = 0;

    @Autowired
    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping(value = "/disciplines")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<DisciplineDTO> findAllDisciplines() {
        return disciplineService.findAll();
    }

    @GetMapping(value = "/disciplines/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public DisciplineDTO findDisciplineById(@PathVariable Long id) throws Exception {
        return disciplineService.findById(id);
    }

    @PostMapping(value = "disciplines")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createDiscipline(
                                        @RequestBody @Valid DisciplineDTO discipline,
                                        BindingResult bindingResult) {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(disciplineService.create(discipline), HttpStatus.OK);
    }

    @PutMapping(value = "disciplines/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateDiscipline(@PathVariable Long id,
                                                   @RequestBody @Valid DisciplineDTO discipline,
                                                   BindingResult bindingResult) throws Exception {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(disciplineService.update(discipline, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "disciplines/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Discipline deleteDiscipline(@PathVariable Long id) throws Exception {
        return disciplineService.delete(id);
    }

    @GetMapping(value = "/disciplines/page")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Page<Discipline> findDisciplinesPage(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(name = "elements_per_page", defaultValue = "8") int elementsPerPage,
                                                @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm) {

        return disciplineService.getDisciplinesPage(page, elementsPerPage, searchTerm);
    }
}
