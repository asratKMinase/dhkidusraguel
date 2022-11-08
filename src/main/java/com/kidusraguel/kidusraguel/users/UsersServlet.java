package com.kidusraguel.kidusraguel.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UsersServlet {
    private final UsersServices usersServices;
    @Autowired
    public UsersServlet(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @GetMapping("/welcome")
    public @ResponseBody String welcomePage(){
        return "Welcome to Bailey Barista Training Academy";
    }


    @GetMapping("/findAllUsers")
    public ResponseEntity<List> findAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return new ResponseEntity<>(usersServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findUsers")

    public ResponseEntity<Users> findUsers(@RequestParam long id){
        Users users = usersServices.readById(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Users> saveUsers(@RequestBody Users users) {
        Users newUsers = (Users)usersServices.create(users);
        return new ResponseEntity<>(newUsers, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Users> updateUsers(@RequestBody Users users) {
        Users newUsers = usersServices.update(users);
        return new ResponseEntity<>(newUsers, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public void deleteUsers(@RequestParam long username) {
        boolean newUsers = usersServices.delete(username);
    }

}