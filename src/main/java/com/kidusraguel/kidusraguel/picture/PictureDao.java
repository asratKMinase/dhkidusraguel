package com.kidusraguel.kidusraguel.picture;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PictureDao extends JpaRepository<Picture,Long> {
    Optional<Picture> findByName(String fileName);
}
