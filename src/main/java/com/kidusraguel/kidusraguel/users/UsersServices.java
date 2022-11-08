package com.kidusraguel.kidusraguel.users;


import com.kidusraguel.kidusraguel.exceptions.InvalidRequestException;
import com.kidusraguel.kidusraguel.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import javax.naming.AuthenticationException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsersServices  {
    private UsersDao usersDao;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServices(UsersDao usersDao) {
        this.usersDao = usersDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Users> findAll(){
        List<Users> customers = (List<Users>) usersDao.findAll();
        return customers;
    }
    public Users readById(long id) {
        Users users= usersDao.findById(id).get();
        return users;
    }
    public Users update(Users updatedCustomer) {
        usersDao.save(updatedCustomer);
        return updatedCustomer;
    }
    public boolean delete(long username) {
        usersDao.deleteById(username);
        return true;
    }
    public Users create(Users newUsers){
        if(!validateInput(newUsers)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }
        String encodedPassword = this.passwordEncoder.encode(newUsers.getPassword());
        newUsers.setPassword(encodedPassword);
        Users persistedUsers= usersDao.save(newUsers);

        if(persistedUsers == null){
            throw new ResourcePersistanceException("User was not persisted to the database upon registration");
        }
        return persistedUsers;
    }
    public boolean validateInput(Users newUsers) {
        if(newUsers == null) return false;
        if(newUsers.getUsername()== null || newUsers.getUsername().trim().equals("")) return false;
        if(newUsers.getPassword() == null || newUsers.getPassword().trim().equals("")) return false;
        if(newUsers.getFname() == null || newUsers.getFname().trim().equals("")) return false;
        if(newUsers.getMitial() == null || newUsers.getMitial().trim().equals("")) return false;
        if(newUsers.getLname() == null || newUsers.getLname().trim().equals("")) return false;
        if(newUsers.getKname() == null || newUsers.getKname().trim().equals("")) return false;
        if(newUsers.getGender() == null || newUsers.getGender().trim().equals("")) return false;
        if(newUsers.getPhone() == null || newUsers.getPhone().trim().equals("")) return false;
        if(newUsers.getDob() == null || newUsers.getDob().trim().equals("")) return false;
        if(newUsers.getRdate() == null || newUsers.getRdate().trim().equals("")) return false;
        return newUsers.getEmail() != null || !newUsers.getEmail().trim().equals("");
    }

    public Users authenticateUsers(String username, String password) throws AuthenticationException {
        if(password == null || password.trim().equals("") || username == null || username.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid entry. Please try logging in again");
        }
        Users authenticatedUsers = usersDao.authenticateUsers(username, password );
        if (authenticatedUsers == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }
        return authenticatedUsers;
    }
}
