package testdb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import testdb.demo.entities.Teacher;
import testdb.demo.repositories.TeacherRepositories;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/teacher")
public class DatabaseController {
    @Autowired
    private TeacherRepositories teacherRepositories;

    @GetMapping("/get")
    public ResponseEntity<List<Teacher>> getAll(){
        List<Teacher> getAll = teacherRepositories.findAll();
        return ResponseEntity.ok().body(getAll);
    }

    @PostMapping("/set")
    public ResponseEntity<Teacher> set(@Validated @RequestBody Teacher obj) throws Exception{
        obj = teacherRepositories.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
