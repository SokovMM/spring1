package com.example.spring1.controller;

import com.example.spring1.model.Student;
import com.example.spring1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }




   /* @RestController
    @RequestMapping("/response")
    public class HomeController {

        @PostMapping("/postbody")
        public String postBody(@RequestBody String fullName) {
            return "Hello " + fullName;

        }

    }
/*/



    @PostMapping(value = "/students/new")
    public ResponseEntity<?> create(@RequestBody Student student) {
        studentService.create(student);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/students/del/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = studentService.delete(id);
        if (deleted) {
            System.out.println("del_ok");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println("del_notOK");
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

        }
    }


   /** @GetMapping(value = "/students/read/id/{id_student}")

    public ResponseEntity<Student> read2(@PathVariable(name = "id_student") String id_student) {
        final Student student = studentService.read(id_student));

        System.out.println("Запрос по id "+id_student);
        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }*/


    @GetMapping(value = "/students/read/id/{id_student}")
    public ResponseEntity<Student> read_by_id(@PathVariable(name = "id_student") int id) {
        final Student student = studentService.read_by_id(id);
        System.out.println("Запрос по id "+id);

        return student != null
                ? new ResponseEntity<>(student, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/students/readAll")
    public ResponseEntity<List<Student>> read() {
        System.out.println("Запрос на всех учеников");
        final List<Student> students = studentService.readAll();

        //странная запись.
        return students != null && !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/students/name/{name_studnet}")
    public ResponseEntity<List<Student>> read(@PathVariable(name = "name_studnet") String name_studnet ) {

        final List<Student> students = studentService.readGroup(name_studnet);
        //странная запись.
        System.out.println("Выполнене "+name_studnet);
        return students != null &&  !students.isEmpty()
                ? new ResponseEntity<>(students, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    }



