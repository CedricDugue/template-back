package br.com.trackertemplateback.persistence.model;

import br.com.trackertemplateback.persistence.model.generic.AbstractEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Cedric Christian on 30/04/2021
 */
@Entity
@Table(name = "student")
@Data
public class Student extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Column(unique = true, nullable = false, length = 100)
    @ApiModelProperty(notes = "Student name")
    private String name;

    @NotBlank(message = "Invalid email")
    @Email(message = "Invalid email")
    @ApiModelProperty(notes = "Student email")
    private String email;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(@NotBlank(message = "Name is required") String name, @NotBlank(message = "Invalid email") @Email(message = "Invalid email") String email) {
        this.name = name;
        this.email = email;
    }

    public Student(long id, @NotBlank(message = "Name is required") String name, @NotBlank(message = "Invalid email") @Email(message = "Invalid email") String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
