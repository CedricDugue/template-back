package br.com.trackertemplateback.controller.v1;

import br.com.trackertemplateback.persistence.model.Student;
import br.com.trackertemplateback.persistence.repository.StudentRepository;
import br.com.trackertemplateback.util.EndpointUtil;
import br.com.trackertemplateback.util.crypt.PasswordEncoder;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Cedric Christian on 30/04/2021
 */
@SwaggerDefinition(
        tags = {
                @Tag(name = "student", description = "Description for Students")
        }
)

@RestController
@RequestMapping("/v1/trackertemplateback/students")
@Api(tags = {"student"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentEndPoint {

    private final StudentRepository studentDAO;
    private final EndpointUtil endpointUtil;

    /**
     * Consulta todos os registro como parametro opcional o nome.
     * Exemplo: http://localhost:8080/v1/trackertemplateback/students?sort=name,desc&name=a
     */
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Return a list with all students.", notes = "Return a list with all students.</br>Optional name parameter.", response = Student[].class)
    public ResponseEntity<?> findAll(@ApiParam("Student name") @RequestParam(value = "name", defaultValue = "") String name,
                                     Pageable pageable, Authentication authentication) {
        return endpointUtil.returnObjectOrNotFound(studentDAO.findByName(pageable, name));
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "Return the student by id.", notes = "Return the student by id.", response = Student.class)
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        return endpointUtil.returnObjectOrNotFound(studentDAO.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @ApiOperation(value = "Insert the student.", notes = "Insert the student.", response = Student.class)
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @ApiOperation(value = "Update the student.", notes = "Update the student.")
    public ResponseEntity<?> update(@Valid @RequestBody Student student) {
        endpointUtil.verifyIfRecordExists(student.getId(), studentDAO);
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @ApiOperation(value = "Delete the student.", notes = "Delete the student.")
    public ResponseEntity<?> delete(@PathVariable long id) {
        endpointUtil.verifyIfRecordExists(id, studentDAO);
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}