package com.kidusraguel.kidusraguel.picture;


import com.kidusraguel.kidusraguel.address.Address;
import com.kidusraguel.kidusraguel.address.AddressDao;
import com.kidusraguel.kidusraguel.exceptions.InvalidRequestException;
import com.kidusraguel.kidusraguel.exceptions.ResourcePersistanceException;
import com.kidusraguel.kidusraguel.util.web.servlet.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class PictureService {
    @Autowired
    private PictureDao pictureDao;

    public String uploadImage(MultipartFile file) throws IOException {

        Picture imageData = pictureDao.save(Picture.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<Picture> dbImageData = pictureDao.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public Picture create(Picture newPicture){

        Picture persistedPicture = pictureDao.save(newPicture);

        if(persistedPicture == null){
            throw new ResourcePersistanceException("Picture was not persisted to the database.");
        }
        return persistedPicture;
    }
}