//package br.com.trackertemplateback.persistence.repository;
//
//import br.com.trackertemplateback.persistence.model.Student;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Rule;
//import org.junit.rules.ExpectedException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import javax.validation.ConstraintViolationException;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * @author Cedric Christian on 30/04/2021
// */
//@Slf4j
////@RunWith(SpringRunner.class)
//@DataJpaTest
////@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Anotacao para teste no banco de dados fisico. Remover para teste logico.
//public class StudentRepositoryTest {
//
//    @Autowired
//    private StudentRepository studentDAO;
//
//    @Rule
//    public ExpectedException thrown = ExpectedException.none();
//
//    //    @Test
//    public void createShouldPersistData() {
//        Student student = new Student("Cedric", "cdugue@gmail.com");
//        this.studentDAO.save(student);
//
//        assertThat(student.getId()).isNotNull();
//        assertThat(student.getName()).isEqualTo("Cedric");
//        assertThat(student.getEmail()).isEqualTo("cdugue@gmail.com");
//    }
//
//    //    @Test
//    public void deleteShouldRemoveData() {
//        Student student = new Student("Cedric", "cdugue@gmail.com");
//        this.studentDAO.save(student);
//        this.studentDAO.delete(student);
//
//        assertThat(studentDAO.findById(student.getId())).isNotPresent();
//    }
//
//    //    @Test
//    public void updateShouldChangeAndPersistData() {
//        Student student = new Student("Cedric", "cdugue@gmail.com");
//        this.studentDAO.save(student);
//
//        student.setName("Cedric2");
//        student.setEmail("cdugue2@gmail.com");
//        student = this.studentDAO.save(student);
//
//        Optional<Student> studentFindById = this.studentDAO.findById(student.getId());
//        log.info("studentFindById {}", studentFindById);
//
//        assertThat(student.getName()).isEqualTo("Cedric2");
//        assertThat(student.getEmail()).isEqualTo("cdugue2@gmail.com");
//    }
//
//    //    @Test
//    public void findByNameIgnoreCaseContainingShouldIgnoreCase() {
//        Student student = new Student("Cedric", "cdugue@gmail.com");
//        Student student2 = new Student("cedric", "cdugue2@gmail.com");
//        this.studentDAO.save(student);
//        this.studentDAO.save(student2);
//
//        List<Student> studentList = this.studentDAO.findByNameIgnoreCaseContaining("cedric");
////        List<Student> studentList2 = this.studentDAO.findByName(PageRequest.of(0, 5), "edri");
//
//        assertThat(studentList.size()).isEqualTo(2);
////        assertThat(studentList2.size());
//    }
//
//    //    @Test
//    public void createWhenNameIsNullShouldThrowConstraintViolationException() {
//        this.thrown.expect(ConstraintViolationException.class);
//        this.thrown.expectMessage("Name is required");
//
//        this.studentDAO.save(new Student());
//    }
//
//    //    @Test
//    public void createWhenEmailIsNullShouldThrowConstraintViolationException() {
//        this.thrown.expect(ConstraintViolationException.class);
//        Student student = new Student("Cedric", null);
//
//        this.studentDAO.save(student);
//    }
//}
