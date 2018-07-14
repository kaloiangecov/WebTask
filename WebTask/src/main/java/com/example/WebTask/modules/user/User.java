package com.example.WebTask.modules.user;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Size;

import com.example.WebTask.modules.role.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity(name="T_USER")
@Data
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6622252715206156181L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

	@NotBlank(message = "Enter username")
	@Size(min = 3, max = 40, message = "Username size must be between 8 and 40 characters")
    private String username;
	@NotBlank(message = "Enter email")
	@Size(min = 8, max = 40, message = "Email size must be between 8 and 40 characters")
	@Email(message = "Invalid email address")
    private String email;
	@Size(min = 6, message = "Password size must be between 6 and 20 characters")
	@NotBlank(message = "Enter password")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	private Role role;

}
