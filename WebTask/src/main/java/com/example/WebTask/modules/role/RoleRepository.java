package com.example.WebTask.modules.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kaloi
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
