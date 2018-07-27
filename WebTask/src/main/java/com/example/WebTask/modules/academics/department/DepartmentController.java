package com.example.WebTask.modules.academics.department;

import com.example.WebTask.modules.academics.Academics;
import com.example.WebTask.modules.academics.AcademicsService;
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
public class DepartmentController {

    private DepartmentService departmentService;
    private AcademicsService academicsService;
    private Map<String, String> errors;
    private static final int EMPTY = 0;
    private static final long NEW_DEPARTMENT_ID = 0L;

    @Autowired
    public DepartmentController(DepartmentService departmentService, AcademicsService academicsService) {
        this.departmentService = departmentService;
        this.academicsService = academicsService;
    }

    @GetMapping(value = "/departments")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Department> findAllDepartments() {
        return departmentService.findAll();
    }

    @GetMapping(value = "/departments/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Department findDepartmentById(@PathVariable Long id) throws Exception {
        return departmentService.findById(id);
    }

    @PostMapping(value = "departments")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> createDepartment(@RequestBody @Valid Department department,
                                       BindingResult bindingResult) {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        errors = ResponseGenerator.codeExists(department, academicsService, errors);
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(departmentService.create(department), HttpStatus.OK);
    }

    @PutMapping(value = "departments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> updateDepartment(@PathVariable Long id,
                                       @RequestBody @Valid Department department,
                                       BindingResult bindingResult) throws Exception {
        errors = new HashMap<>();

        if (bindingResult.hasErrors()) {
            errors = ResponseGenerator.setErrors(bindingResult);
        }
        errors = ResponseGenerator.codeExists(department, academicsService, errors, id);
        if (errors.size() != EMPTY) {
            return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(departmentService.update(department, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "departments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Department deleteDepartment(@PathVariable Long id) throws Exception {
        return departmentService.delete(id);
    }

    @GetMapping(value = "/departments/page")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Page<Department> findDepartmentsPage(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(name = "elements_per_page", defaultValue = "8") int elementsPerPage,
                                                @RequestParam(name = "searchTerm", defaultValue = "") String searchTerm) {

        return departmentService.getDepartmentsPage(page, elementsPerPage, searchTerm);
    }

}
