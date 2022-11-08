package com.kidusraguel.kidusraguel.util.web.servlet;

import com.kidusraguel.kidusraguel.users.Users;
import com.kidusraguel.kidusraguel.users.UsersServices;
import com.kidusraguel.kidusraguel.util.interfacee.Authable;
import com.kidusraguel.kidusraguel.util.web.dto.LoginCreds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthServlet implements Authable {

    private final UsersServices usersServices;

    @Autowired
    public AuthServlet(UsersServices usersServices){
        this.usersServices = usersServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authorizeUsers(@RequestBody LoginCreds loginCreds, HttpSession httpSession) throws AuthenticationException {
        Users authUsers = usersServices.authenticateUsers(loginCreds.getUsername(), loginCreds.getPassword());
        httpSession.setAttribute("authUsers", authUsers);
        System.out.println("Successfully logged in.");
    }

    @DeleteMapping
    public void logout(HttpSession session){
        session.invalidate();
        System.out.println("Successfully logged out");
    }

}
