package com.projet.cours.controllers;

import com.projet.cours.entities.Certificat;
import com.projet.cours.services.ICerificatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CertificatController {
    @Autowired
    private ICerificatService iCertificatService;

    @GetMapping("/certificats/download/{etudId}/{examId}")
    public ResponseEntity<ByteArrayResource> downloadCertificate(@PathVariable("etudId") int etudId, @PathVariable("examId") int examId) {
        byte[] pdfBytes = iCertificatService.generateCertificatePdf(etudId, examId);

        if (pdfBytes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate.pdf")
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @PutMapping("/certificats/{certifId}")
    public Certificat updateCertificate(@RequestBody Certificat certificat, @PathVariable("certifId") int certifId) {
        return iCertificatService.updateCertificat(certifId, certificat);
    }

    @GetMapping("/certificats/byStudent/{etudId}")
    public List<Certificat> getCertificatesByStudent(@PathVariable("etudId") int etudId) {
        return iCertificatService.getCertificatesByStudent(etudId);
    }
}
