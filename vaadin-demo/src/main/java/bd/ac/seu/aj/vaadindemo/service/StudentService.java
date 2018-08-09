package bd.ac.seu.aj.vaadindemo.service;

import bd.ac.seu.aj.vaadindemo.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {
    private ObjectMapper objectMapper;

    public StudentService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Student> getAllStudents() {
        // HW: instead of working with fake data, make sure that
        // the data is read from a web service
        String message = "";
        Student[] students = null;
        try {
            URL url = new URL("http://localhost:8081/student/all");
            students = objectMapper.readValue(url, Student[].class);
            System.out.println("toString from object: " + Arrays.toString(students));

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Student> studentList = Arrays.asList(students);
        return studentList;
    }

    public void saveStudent(Student student) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/student/create";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Student> entity = new HttpEntity<>(student, httpHeaders);

        Student student1 = restTemplate.postForObject(url, entity, Student.class);
        System.out.println(student1);
    }
    public void updateStudent(Student student) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/student/update";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Student> entity = new HttpEntity<>(student, httpHeaders);

        Student student1 = restTemplate.postForObject(url, entity, Student.class);
        System.out.println(student1);
    }

    public void deleteStudent(long id) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/student/delete/"+id;

        restTemplate.delete(url);


    }
}
