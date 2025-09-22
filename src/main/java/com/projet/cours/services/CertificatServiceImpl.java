package com.projet.cours.services;

import com.projet.cours.entities.Certificat;
import com.projet.cours.entities.Cours;
import com.projet.cours.entities.Etudiant;
import com.projet.cours.entities.Examen;
import com.projet.cours.repositories.CertificatRepository;
import com.projet.cours.repositories.EtudiantRepository;
import com.projet.cours.repositories.ExamenRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class CertificatServiceImpl implements ICerificatService{
    @Autowired
    private CertificatRepository certificatRepository;
    @Autowired
    private ExamenRepository examenRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EtudiantServiceImpl etudiantService;
    @Override
    public byte[] generateCertificatePdf(int etudId, int examId) {
        // Fetch the student and exam details
        Optional<Etudiant> etudiantOpt = etudiantRepository.findById(etudId);
        Optional<Examen> examenOpt = examenRepository.findById(examId);

        if (etudiantOpt.isEmpty() || examenOpt.isEmpty()) {
            System.out.println("Student or Exam not found for etudId: " + etudId + ", examId: " + examId);
            return null;
        }

        Etudiant etudiant = etudiantOpt.get();
        Examen examen = examenOpt.get();

        // Fetch the course title from the associated Cours entity
        Cours cours = examen.getCours();
        if (cours == null) {
            System.out.println("Course not found for examId: " + examId);
            return null;
        }
        String titreCours = cours.getTitre_cours();

        // Check if the student passed the exam
        if (examen.getNote() < 50) {
            System.out.println("Student did not pass the exam. Certificate cannot be generated.");
            return null;
        }

        try (PDDocument document = new PDDocument(); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            // Create a landscape-oriented page
            PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Draw a gold and black border
                contentStream.setLineWidth(5);
                contentStream.setStrokingColor(255, 215, 0); // Gold color
                contentStream.addRect(30, 30, page.getMediaBox().getWidth() - 60, page.getMediaBox().getHeight() - 60);
                contentStream.stroke();

                contentStream.setLineWidth(2);
                contentStream.setStrokingColor(0, 0, 0); // Black color
                contentStream.addRect(40, 40, page.getMediaBox().getWidth() - 80, page.getMediaBox().getHeight() - 80);
                contentStream.stroke();

                // Add the logo in the top-left corner inside the frame
                InputStream logoStream = getClass().getResourceAsStream("/logo/1.png");
                if (logoStream != null) {
                    PDImageXObject logo = PDImageXObject.createFromByteArray(document, logoStream.readAllBytes(), "logo");
                    contentStream.drawImage(logo, 50, page.getMediaBox().getHeight() - 150, 100, 100); // Adjust position and size
                } else {
                    System.out.println("Logo not found at /logo/1.png");
                }

                // Add the title
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 30);
                contentStream.newLineAtOffset(200, 450);
                contentStream.showText("Certificate of Achievement");
                contentStream.endText();

                // Add the student name
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 18);
                contentStream.newLineAtOffset(100, 350);
                contentStream.showText("This is to certify that:");
                contentStream.newLineAtOffset(0, -30);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
                contentStream.showText(etudiant.getNomEtud() );
                contentStream.endText();

                // Add the course and exam details
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 16);
                contentStream.newLineAtOffset(100, 250);
                contentStream.showText("Has successfully completed the course:");
                contentStream.newLineAtOffset(0, -30);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.showText(titreCours);
                contentStream.newLineAtOffset(0, -30);
                contentStream.setFont(PDType1Font.HELVETICA, 16);
                contentStream.showText("Final Score: " + examen.getNote());
                contentStream.newLineAtOffset(0, -30);
                contentStream.showText("Date: " + new Date());
                contentStream.endText();

                // Add a signature placeholder
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.newLineAtOffset(100, 100);
                contentStream.showText("_________________________");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Authorized Signature");
                contentStream.endText();
            }

            document.save(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

            @Override
    public Certificat updateCertificat(int certifId, Certificat certificat) {
        Optional<Certificat> c=certificatRepository.findById(certifId);
        if (c.isPresent()){
            Certificat certificat1=c.get();
            certificat1.setDate_certif(certificat.getDate_certif());
            certificat1.setNote_finale(certificat.getNote_finale());
            certificat1.setExamen(certificat.getExamen());
            certificat1.setEtudiant(certificat.getEtudiant());
            return certificatRepository.save(certificat1);
        }
    return null;
    }

    @Override
    public List<Certificat> getCertificatesByStudent(int etudId) {
        return certificatRepository.findByEtudiant_IdEtud(etudId);
    }
    }


