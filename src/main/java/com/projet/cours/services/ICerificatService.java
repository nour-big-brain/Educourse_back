package com.projet.cours.services;

import com.projet.cours.entities.Certificat;

import java.util.List;

public interface ICerificatService {
    public byte[] generateCertificatePdf(int etudId, int examId);
    public Certificat updateCertificat(int certifId, Certificat certificats);
    public List<Certificat> getCertificatesByStudent(int etudId);

}
