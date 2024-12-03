package com.test.COCONSULT.Services;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import com.test.COCONSULT.Entity.Contract;


import com.test.COCONSULT.Interfaces.IpdfContarct;
import com.test.COCONSULT.Reposotories.ContractRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class PDFGenerationService implements IpdfContarct {


    @Autowired
    private ContractRepository contractRepository ;


    @Value("${file.upload-dir}/")
    private String uploadDir;


    public byte[] generatePdf(Long contractId) throws IOException {
        Date currentDate = new Date();

        // Format the date as yyyy-MM-dd
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        // Get nanoseconds
        long nanoTime = System.nanoTime();



        Optional<Contract> contract = contractRepository.findById(contractId) ;
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true)) {
                // Positionner le logo en haut à droite de la page
                // Charger le logo
                //     PDImageXObject logoImageRight = PDImageXObject.createFromFile("C:/xampp/htdocs/img/Capture d'écran 2024-03-24 194426.png", document);


// Obtenir la largeur de la page
                float pageWidth = page.getMediaBox().getWidth();

// Obtenir la hauteur de la page
                float pageHeight = page.getMediaBox().getHeight();

// Définir les coordonnées de positionnement du logo
                float logoX = pageWidth - 100; // Décalage de 100 unités vers la gauche par rapport à l'extrémité droite de la page
                float logoY = pageHeight - 50; // Décalage de 50 unités vers le bas par rapport à l'extrémité supérieure de la page

// Dessiner le logo sur la page
                // contentStream.drawImage(logoImageRight, logoX, logoY, 100, 50); // 100 est la largeur du logo, 50 est la hauteur du logo

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                float titleWidth = PDType1Font.HELVETICA_BOLD.getStringWidth("Contrat de Location") / 1000f * 16f;
                float titleX = (page.getMediaBox().getWidth() - titleWidth) / 2f;
                contentStream.newLineAtOffset(titleX, 780);
                contentStream.showText("Contrat de Location");
                contentStream.endText();

                // Calculer la hauteur du titre
                float titleHeight = 16f; // Taille de la police du titre
                float spaceAfterTitle = 30; // Espace vertical souhaité entre le titre et le reste du contenu
                float contentY = 780 - titleHeight - spaceAfterTitle; // Calculer la nouvelle position Y du premier élément de contenu après le titre

                contentStream.beginText(); // <-- Ajoutez ceci pour commencer un nouveau bloc de texte
                contentStream.newLine();
                contentStream.endText(); // <-- Ajoutez ceci pour terminer le bloc de texte
                contentStream.beginText(); // <-- Ajoutez ceci pour commencer un nouveau bloc de texte
                contentStream.newLine();
                contentStream.endText(); // <-- Ajoutez ceci pour terminer le bloc de texte


                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.newLineAtOffset(50, 740);
                contentStream.showText("Règles à respecter entre locataires et propriétaires :");
                contentStream.endText();

                contentStream.beginText(); // <-- Ajoutez ceci pour commencer un nouveau bloc de texte
                contentStream.newLine();
                contentStream.endText(); // <-- Ajoutez ceci pour terminer le bloc de texte
                contentStream.beginText(); // <-- Ajoutez ceci pour commencer un nouveau bloc de texte
                contentStream.newLine();
                contentStream.endText(); // <-- Ajoutez ceci pour terminer le bloc de texte


                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(50, 720);
                contentStream.showText("1) Contrat de location : Les deux parties doivent respecter les termes du contrat de location");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("2) Entretien et réparations : Le propriétaire est responsable des réparations majeures, tandis que le locataire doit maintenir ");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(50, 680);
                contentStream.showText("la propriété en bon état");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(50, 660);
                contentStream.showText("3) Respect de la propriété : Le locataire doit respecter la propriété du propriétaire et éviter tout dommage délibéré ");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(50, 640);
                contentStream.showText("ou négligence");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(50, 620);
                contentStream.showText("4) Le propriétaire doit respecter la vie privée du locataire et ne peut accéder à la propriété qu'après avoir donné un préavis .");
                contentStream.endText();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.newLineAtOffset(50, 600);
                contentStream.showText("raisonnable, sauf en cas d'urgence");
                contentStream.endText();


                // Tableau
                String[] headers = {" RepertoireContact ", " Montant ", " NbreTranche ", " DateContract ", " Etape " };
                String[] values = {
                        contract.get().getRepertoireContact(),
                        String.valueOf(contract.get().getMontant()),
                        String.valueOf(contract.get().getNbreTranche()),
                        String.valueOf(contract.get().getDateContract()),
                        String.valueOf(contract.get().getEtape())
                };

                drawTable(contentStream, 400, 500, headers, values, page.getMediaBox().getWidth());

                // Images de signature
                /*PDImageXObject signatureImageLeft = PDImageXObject.createFromFile("C:\\xampp\\htdocs\\img\\téléchargement.png", document);
                PDImageXObject signatureImageRight = PDImageXObject.createFromFile("C:\\xampp\\htdocs\\img\\télécharg.png", document);
                contentStream.drawImage(signatureImageLeft, 50, 50, 100, 50);
                contentStream.drawImage(signatureImageRight, page.getMediaBox().getWidth() - 150, 50, 100, 50);*/
            }
            //Optional<Contract> contractOptional = contractRepository.findById(contractId);
            // Saving the pdf as a physical file that can be accessed later in path: filePAth
            String filename = "contract_" + formattedDate + "_" + nanoTime + ".pdf";
            //String filePath = "C:/Users/MSI/Desktop/4éme SE (Esprit)/SEM2/PI/COCONSULTERP-BACK/src/main/resources/uploads/" + filename;
            String filePath = uploadDir + filename;

            File outputFile = new File(filePath);
            System.out.println("outputFile : " + outputFile);

            //  if (contract.isPresent()) {
            Contract contract1 = contract.get();

            // Setting the description
            contract1.setDescription(filename);
            contractRepository.save(contract1);
            //}

            try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                document.save(outputStream);
            }


            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                document.save(byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private static void drawTable(PDPageContentStream contentStream, float yStart, float tableWidth, String[] headers, String[] values, float pageWidth) throws IOException {
        float margin = 50;
        float yPosition = yStart;

        float rowHeight = 25f; // Réduire légèrement la hauteur des lignes
        float tableHeight = rowHeight * 2; // Deux lignes : une pour les titres et une pour les valeurs

        float[] columnWidths = {tableWidth * 0.2f, tableWidth * 0.2f, tableWidth * 0.2f, tableWidth * 0.2f, tableWidth * 0.2f}; // Taille des colonnes ajustée

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10); // Réduire la taille de la police des titres

        // Dessiner les titres dans la première ligne
        float textY = yPosition - 10; // Réduire l'écart entre les titres et les cellules
        float textX = margin;
        for (int i = 0; i < headers.length; i++) {
            drawCell(contentStream, textX, textY, columnWidths[i], rowHeight, headers[i], 10f); // Utiliser une taille de police plus petite pour les titres
            textX += columnWidths[i];
        }

        // Dessiner les valeurs dans la deuxième ligne
        textY -= rowHeight; // Déplacer vers le bas pour la deuxième ligne
        textX = margin;
        for (int i = 0; i < values.length; i++) {
            if (i < 2) { // Si c'est une case ID, utiliser une taille de police plus petite
                drawCell(contentStream, textX, textY, columnWidths[i], rowHeight, values[i], 8f); // Utiliser une taille de police encore plus petite pour les ID
            } else {
                drawCell(contentStream, textX, textY, columnWidths[i], rowHeight, values[i], 10f); // Utiliser la même taille de police pour les autres cellules
            }
            textX += columnWidths[i];
        }
    }


    private static void drawCell(PDPageContentStream contentStream, float x, float y, float width, float height, String text, float fontSize) throws IOException {
        contentStream.setLineWidth(1f);
        contentStream.setNonStrokingColor(0, 0, 0); // Couleur du texte
        contentStream.addRect(x, y, width, height);
        contentStream.stroke();
        contentStream.beginText();

        float lineHeight = height - 2 * 2f;

        float textWidth = PDType1Font.HELVETICA.getStringWidth(text) / 1000f * fontSize;
        float textHeight = lineHeight; // Margin haut et bas

        // Centrer le texte horizontalement et verticalement dans la cellule
        float offsetX = (width - textWidth) / 2;
        float offsetY = (textHeight - fontSize) / 2;
        contentStream.newLineAtOffset(x + offsetX, y + offsetY);
        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.showText(text);
        contentStream.endText();
    }




    /*------------------------------------------------------------------------------------------------------------------------------*/

    /*public byte[] generatePDF() throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document doc = new Document();
        PdfWriter.getInstance(doc, outputStream);
        doc.open();

        // Add text content
        Paragraph paragraph = new Paragraph("Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image.\n");
        doc.add(paragraph);

        // Add table
        PdfPTable table = new PdfPTable(3);
        addTableContent(table);
        doc.add(table);

        // Close document
        doc.close();

        return outputStream.toByteArray();
    }

    private void addTableContent(PdfPTable table) throws DocumentException, IOException {
        tableHeader(table);
        addRow(table);

    }

    private void tableHeader(PdfPTable table) {
        Stream.of("Id", "First Name", "Last Name").forEach(title -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.CYAN);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(title));
            table.addCell(header);
        });
    }

    private void addRow(PdfPTable table) {
        table.addCell("1");
        table.addCell("PDF");
        table.addCell("Generation");
    }*/
}

