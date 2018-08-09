package bd.ac.seu.aj.springbootdemo.controller;

import bd.ac.seu.aj.springbootdemo.model.Student;
import bd.ac.seu.aj.springbootdemo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "all")
    @ResponseBody
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentService.getStudents().forEach(studentList::add);
        return studentList;
    }

    @GetMapping(value = "{id}")
    @ResponseBody
    public Student getStudents(@PathVariable long id) {
        return studentService.getStudent(id).get();
    }

    @DeleteMapping(value = "delete/{id}")
    @ResponseBody
    public void deleteStudent(@PathVariable long id) {

        studentService.deleteStudent(id);
    }

    @PostMapping(value = "update")
    @ResponseBody
    public Student updateStudent(@RequestBody Student student) {

        studentService.updateStudent(student);

        return student;
    }

    @PostMapping(value = "create")
    @ResponseBody
    public Student saveStudent(@RequestBody Student student) {

        studentService.createStudent(student);

        return student;
    }


}
