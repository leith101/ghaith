package com.example.testeditions.Repositories;


import com.example.testeditions.Entites.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CertREpo extends JpaRepository<Certification,Long> {

    Certification findByUserId(Long userId);

    @Query("SELECT COUNT(c) FROM Certification c WHERE c.etat = 'refused'")
    long countRefusedCertifications();

    @Query("SELECT COUNT(c) FROM Certification c WHERE c.etat = 'accepted'")
    long countAcceptedCertifications();
}
