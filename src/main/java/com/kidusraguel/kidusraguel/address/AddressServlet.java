package com.kidusraguel.kidusraguel.address;

import com.kidusraguel.kidusraguel.users.Users;
import com.kidusraguel.kidusraguel.users.UsersServices;
import com.kidusraguel.kidusraguel.util.web.dto.AddressInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressServlet {
    private final AddressServices addressServices;
    private final UsersServices usersServices;

    @Autowired
    public AddressServlet(AddressServices addressServices, UsersServices usersServices) {
        this.addressServices = addressServices;
        this.usersServices = usersServices;
    }

    @GetMapping("/findAllAddress")
    public ResponseEntity<List> findAllAddress() {
        return new ResponseEntity<>(addressServices.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/findAddress")
    public ResponseEntity<Address> findAddress(@RequestParam Long addressid) {
        Address address = addressServices.readById(addressid);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Address> CreateAddress(@RequestBody AddressInitializer newAddressi, HttpSession req) {

        Address newAddress = new Address();
        Users authAddress = (Users) req.getAttribute("authUClasses");
        newAddress.setAddressid(newAddressi.getAddressid());
        newAddress.setAddress(newAddressi.getAddress());
        newAddress.setAddress2(newAddressi.getAddress2());
        newAddress.setCity(newAddressi.getCity());
        newAddress.setState(newAddressi.getState());
        newAddress.setZip(newAddressi.getZip());
        newAddress.setId(usersServices.readById(newAddressi.getId()));

        Address address= addressServices.create(newAddress);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Address> updateAddress(@RequestBody AddressInitializer newAddressi, HttpSession req) {

        Address newAddress = new Address();
        Users authAddress = (Users) req.getAttribute("authUClasses");
        newAddress.setAddressid(newAddressi.getAddressid());
        newAddress.setAddress(newAddressi.getAddress());
        newAddress.setAddress2(newAddressi.getAddress2());
        newAddress.setCity(newAddressi.getCity());
        newAddress.setState(newAddressi.getState());
        newAddress.setZip(newAddressi.getZip());
        newAddress.setId(usersServices.readById(newAddressi.getId()));

        Address address= addressServices.create(newAddress);
        return new ResponseEntity<>(address, HttpStatus.CREATED);

    }
    @DeleteMapping("/delete")
    public void deleteAddress(@RequestParam Long addessid) {
        boolean newChallenge= addressServices.delete(addessid);
    }

}
