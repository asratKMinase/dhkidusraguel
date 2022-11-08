package com.kidusraguel.kidusraguel.address;

import com.kidusraguel.kidusraguel.exceptions.InvalidRequestException;
import com.kidusraguel.kidusraguel.exceptions.ResourcePersistanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class AddressServices {
    private AddressDao addressDao;
    @Autowired
    public AddressServices(AddressDao addressDao) {
        this.addressDao = addressDao;
    }
    public List<Address> findAll(){
        List<Address> address = (List<Address>) addressDao.findAll();
        return address;
    }
    public Address readById(Long id) {
        Address address= addressDao.findById(id).get();
        return address;
    }
    public Address update(Address updatedAddress) {
        addressDao.save(updatedAddress);
        return updatedAddress;
    }
    public boolean delete(Long address) {
        addressDao.deleteById(address);
        return true;
    }


    public Address create(Address newAddress){
        if(!validateInput(newAddress)){
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }

        Address persistedAddress = addressDao.save(newAddress);

        if(persistedAddress == null){
            throw new ResourcePersistanceException("Challenges was not persisted to the database.");
        }
        return persistedAddress;
    }

    public boolean validateInput(Address newAddress) {
        if(newAddress == null) return false;
        if(newAddress.getAddressid()== null || newAddress.getAddressid().equals("")) return false;
        if(newAddress.getAddress()== null || newAddress.getAddress().trim().equals("")) return false;
        if(newAddress.getAddress2()== null || newAddress.getAddress2().trim().equals("")) return false;
        if(newAddress.getCity()== null || newAddress.getCity().trim().equals("")) return false;
        if(newAddress.getState()== null || newAddress.getState().trim().equals("")) return false;
        if(newAddress.getZip()== null || newAddress.getZip().equals("")) return false;
        return newAddress.getId()!= null || !newAddress.getId().equals("");

    }
}

