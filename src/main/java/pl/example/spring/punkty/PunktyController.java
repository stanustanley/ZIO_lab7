package pl.example.spring.punkty;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/punkty")
public class PunktyController {
    // private List<String> users=new CopyOnWriteArrayList<>();
    private StudentService service;// = new StudentService(repository);
    public PunktyController(StudentService service)
    {
        this.service = service;
    }

    /*@RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<String> getUsers() {
        return this.users;
    }*/
    @RequestMapping(value = "/students", method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Student> getUsers() {
        return this.service.getStudents().asJava();
    }

    /* @RequestMapping(value = "/users",method = RequestMethod.POST)
     public int addUser(@RequestBody String name) {
         this.users.add(name);
         return this.users.size();
     }*/
    @RequestMapping(value = "/students", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Student addUser(@RequestBody NewStudent student) {
        return this.service.addStudent(student);
    }
}
