package com.example.WebTask.modules.academics.department;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id) throws Exception{
        Department department = validateId(id);
        return department;
    }

    public Department create(Department department){
        return departmentRepository.save(department);
    }

    public Department update(Department department, Long id) throws Exception{
        Department dbDepartment = validateId(id);

        dbDepartment.setCode(department.getCode());
        dbDepartment.setDescription(department.getDescription());
        dbDepartment.setFaculty(department.getFaculty());
        dbDepartment.setName(department.getName());

        return departmentRepository.save(dbDepartment);
    }

    public Department delete(Long id) throws Exception{
        Department department = validateId(id);
        departmentRepository.delete(department);
        return department;
    }


    private Department validateId(Long id) throws Exception {
        Department department = departmentRepository.findOne(id);
        if(department != null) {
            return department;
        }
        throw new Exception();
    }

    public Page<Department> getDepartmentsPage(int page, int elementsPerPage, String searchTerm) {
        PageRequest pageRequest = new PageRequest(page, elementsPerPage);
        Predicate predicate = DepartmentPredicates.searchFields(searchTerm);
        return departmentRepository.findAll(predicate, pageRequest);
    }
}
