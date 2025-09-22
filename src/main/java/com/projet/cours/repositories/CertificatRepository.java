package com.projet.cours.repositories;

import com.projet.cours.entities.Certificat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificatRepository extends JpaRepository<Certificat, Integer> {
    List<Certificat> findByEtudiant_IdEtudAndExamen_IdEx(int etudiantId, int examenId);
    List<Certificat> findByEtudiant_IdEtud(int etudId);

}
