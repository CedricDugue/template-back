//package br.com.trackertemplateback.controller.v1;
//
//import br.com.trackertemplateback.error.handler.RestResponseExceptionHandler;
//import br.com.trackertemplateback.persistence.model.Student;
//import br.com.trackertemplateback.persistence.repository.StudentRepository;
//import org.json.JSONException;
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
//import org.springframework.http.*;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Objects;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * @author Cedric Christian on 30/04/2021
// */
////@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class StudentEndPointTokenTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @MockBean
//    private StudentRepository studentDAO;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private HttpHeaders headersToken;
//
//    @TestConfiguration
//    static class Config {
//        @Bean
//        public RestTemplateBuilder restTemplateBuilder() {
//            return new RestTemplateBuilder()
//                    .errorHandler(new RestResponseExceptionHandler());
//        }
//    }
//
//    @Before
//    public void getToken() throws JSONException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        JSONObject body = new JSONObject()
//                .put("username", "admin1")
//                .put("password", "go#o$%0d#$98yl-?8fe-w6k!a%m7moyy4lars-e@");
//
//        HttpEntity<String> httpEntity = new HttpEntity<>(body.toString(), headers);
//
//        this.headersToken = restTemplate.postForEntity("/login", httpEntity, String.class).getHeaders();
//    }
//
//    @Before
//    public void generateDefaultRecordMockito() {
//        Student student = new Student(1, "Alexios", "alexios@odissey.com");
//        BDDMockito.when(studentDAO.findById(student.getId())).thenReturn(Optional.of(student));
//    }
//
////    @Test
//    public void listStudentsWhenTokenIncorrectShouldReturnStatusCode403() {
//        ResponseEntity<String> response = restTemplate.exchange("/v1/trackertemplateback/students/", HttpMethod.GET, null, String.class);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(403);
//    }
//
////    @Test
//    public void listStudentsWhenTokenIsCorrectShouldReturnStatusCode200() {
//        ResponseEntity<String> response = restTemplate.exchange("/v1/trackertemplateback/students/", HttpMethod.GET, new HttpEntity<>(headersToken), String.class);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//    }
//
////    @Test
//    public void getStudentsByIdWhenTokenIsCorrectShouldReturnStatusCode200() {
//        ResponseEntity<Student> response = restTemplate.exchange("/v1/trackertemplateback/students/1", HttpMethod.GET, new HttpEntity<>(headersToken), Student.class);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//    }
//
////    @Test
//    public void getStudentsByIdWhenTokenIsCorrectAndStudentDoesNotExistShouldReturnStatusCode404() {
//        ResponseEntity<Student> response = restTemplate.exchange("/v1/trackertemplateback/students/-1", HttpMethod.GET, new HttpEntity<>(headersToken), Student.class);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(404);
//    }
//
////    @Test
//    public void deleteWhenUserHasRoleAdminAndStudentExistsShouldReturnStatusCode200() {
//        BDDMockito.doNothing().when(studentDAO).deleteById(1L);
//
//        ResponseEntity<String> response = restTemplate.exchange("/v1/trackertemplateback/students/1", HttpMethod.DELETE, new HttpEntity<>(headersToken), String.class);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//    }
//
////    @Test
//    public void deleteWhenUserHasRoleAdminAndStudentDoesNotExistShouldReturnStatusCode404() throws Exception {
//        String token = Objects.requireNonNull(headersToken.get("Authorization")).get(0);
//        BDDMockito.doNothing().when(studentDAO).deleteById(1L);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/v1/trackertemplateback/students/{id}", -1L).header("Authorization", token))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
////    @Test
//    public void createWhenNameIsNullShouldReturnStatusCode400BadRequest() {
//        Student student = new Student(null, "barnabas@odissey.com");
//
//        BDDMockito.when(studentDAO.save(student)).thenReturn(student);
//
//        ResponseEntity<String> response = restTemplate.exchange("/v1/trackertemplateback/students/", HttpMethod.POST, new HttpEntity<>(student, headersToken), String.class);
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
//        ResponseEntity<Student> response = restTemplate.exchange("/v1/trackertemplateback/students/", HttpMethod.POST, new HttpEntity<>(student, headersToken), Student.class);
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(201);
//        assertThat(Objects.requireNonNull(response.getBody()).getId()).isNotNull();
//    }
//}
