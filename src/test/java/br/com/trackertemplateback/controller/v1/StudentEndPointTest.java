//package br.com.trackertemplateback.controller.v1;
//
//import br.com.trackertemplateback.persistence.model.Student;
//import br.com.trackertemplateback.persistence.repository.StudentRepository;
//import org.json.JSONObject;
//import org.junit.Before;
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//import static java.util.Arrays.asList;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.http.HttpMethod.DELETE;
//
///**
// * @author Cedric Christian on 30/04/2021
// */
////@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class StudentEndPointTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private HttpEntity<String> httpEntity;
//
//    @MockBean
//    private StudentRepository studentDAO;
//
//    @Autowired
//    private MockMvc mockMvc;
//
////    @LocalServerPort
////    private int port;
//
//    @TestConfiguration
//    static class Config {
//        /**
//         * Utilizando Basic authentication.
//         *
//         * @return Objeto com autenticacao
//         */
//        @Bean
//        public RestTemplateBuilder restTemplateBuilder() {
//            final String USER = "admin1";
//            final String PASSWORD = "go#o$%0d#$98yl-?8fe-w6k!a%m7moyy4lars-e@";
//
//            return new RestTemplateBuilder()
//                    .basicAuthentication(USER, PASSWORD);
//        }
//
//        @Bean
//        public HttpEntity<String> httpEntity() {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            JSONObject body = new JSONObject();
//
//            return new HttpEntity<>(body.toString(), headers);
//        }
//    }
//
//    @Before
//    public void generateDefaultRecordMockito() {
//        Student student = new Student(1, "Alexios", "alexios@odissey.com");
//        BDDMockito.when(studentDAO.findById(student.getId())).thenReturn(Optional.of(student));
//    }
//
////    @Test
//    public void listStudentsWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
//        restTemplate = restTemplate.withBasicAuth("1", "1");
//        ResponseEntity<String> response = restTemplate.getForEntity("/v1/trackertemplateback/students/", String.class, httpEntity);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(401);
//    }
//
////    @Test
//    public void listStudentsWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
//        List<Student> studentList = asList(new Student(1, "Alexios", "alexios@odissey.com"),
//                new Student(2, "Kassandra", "kassandra@odissey.com"));
//        BDDMockito.when(studentDAO.findAll()).thenReturn(studentList);
//
//        ResponseEntity<String> response = restTemplate.getForEntity("/v1/trackertemplateback/students/", String.class, httpEntity);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//    }
//
////    @Test
//    public void getStudentsByIdWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
//        ResponseEntity<Student> response = restTemplate.getForEntity("/v1/trackertemplateback/students/{id}", Student.class, 1L, httpEntity);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//    }
//
////    @Test
//    public void getStudentsByIdWhenUsernameAndPasswordAreCorrectAndStudentDoesNotExistShouldReturnStatusCode404() {
//        ResponseEntity<Student> response = restTemplate.getForEntity("/v1/trackertemplateback/students/{id}", Student.class, -1, httpEntity);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(404);
//    }
//
////    @Test
//    public void deleteWhenUserHasRoleAdminAndStudentExistsShouldReturnStatusCode200() {
//        BDDMockito.doNothing().when(studentDAO).deleteById(1L);
//
//        ResponseEntity<String> response = restTemplate.exchange("/v1/trackertemplateback/students/{id}", DELETE, httpEntity, String.class, 1L);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//    }
//
////    @Test
//    @WithMockUser(username = "xx", password = "xx", roles = {"USER", "ADMIN"})
//    public void deleteWhenUserHasRoleAdminAndStudentDoesNotExistShouldReturnStatusCode404() throws Exception {
//        BDDMockito.doNothing().when(studentDAO).deleteById(1L);
//
////        ResponseEntity<String> response = restTemplate.exchange("/v1/trackertemplateback/students/{id}", DELETE, httpEntity, String.class, -1);
////        assertThat(response.getStatusCodeValue()).isEqualTo(404);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/v1/trackertemplateback/students/{id}", -1L))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
////    @Test
//    @WithMockUser(username = "xx", password = "xx")
//    public void deleteWhenUserDoesNotHaveRoleAdminShouldReturnStatusCode403() throws Exception {
//        BDDMockito.doNothing().when(studentDAO).deleteById(1L);
//
////        ResponseEntity<String> response = restTemplate.exchange("/v1/trackertemplateback/students/{id}", DELETE, httpEntity, String.class, -1);
////        assertThat(response.getStatusCodeValue()).isEqualTo(403);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/v1/trackertemplateback/students/{id}", -1L))
//                .andExpect(MockMvcResultMatchers.status().isForbidden());
//    }
//
////    @Test
//    public void createWhenNameIsNullShouldReturnStatusCode400BadRequest() {
//        Student student = new Student(null, "barnabas@odissey.com");
//
//        BDDMockito.when(studentDAO.save(student)).thenReturn(student);
//
//        ResponseEntity<String> response = restTemplate.postForEntity("/v1/trackertemplateback/students/", student, String.class);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(400);
//        assertThat(response.getBody()).contains("fieldMessage", "Name is required");
//    }
//
////    @Test
//    public void createShouldPersistDataAndReturnStatusCode201() {
//        Student student = new Student("Barnabas", "barnabas@odissey.com");
//
//        BDDMockito.when(studentDAO.save(student)).thenReturn(student);
//
//        ResponseEntity<Student> response = restTemplate.postForEntity("/v1/trackertemplateback/students/", student, Student.class);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(201);
//        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();
//    }
//}
