/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package devops.gradleproject.vintonat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;


import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.poi.xwpf.usermodel.*;


public class App {
    //Abrufen der Loger instanz der App logs zb Fehler oder Info Daten
    private static final Logger logger = LogManager.getLogger(App.class);
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new App().getGreeting());

        IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		builder.width(120).height(20);
		builder.element(new PseudoText("Natalie"));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		System.out.println(s);

        PDDocument helloPdf = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        helloPdf.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(helloPdf, page);
        contentStream.beginText();
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 25);
        contentStream.newLineAtOffset(10, 100);
        contentStream.showText("Dies ist ein Test um nachzusehen ob dies nun wirklich funktionieren würde");
        contentStream.endText();
        contentStream.close();

        helloPdf.save(new File("/Users/natalievintonjak/Documents/Devops-GradleProject-vintonat/simple.pdf"));
        helloPdf.close();

        
         //Erstellen eines neuen Word Dokuments
            XWPFDocument document = new XWPFDocument();

         //Hinzufügen eines initalen Paragraphens´
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("This is a Word document created to save logging information.");

         //Hinzufügen des Datum und der Urhzeit der Erstellung des Dokuments
            LocalDateTime creationTime = LocalDateTime.now();
            XWPFParagraph timestampParagraph = document.createParagraph();
            XWPFRun timestampRun = timestampParagraph.createRun();
            timestampRun.setText("Document created: " + creationTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));

            //Paragraphen Anzahl zum Dokument hinzufügen
            XWPFParagraph contentDetailsParagraph = document.createParagraph();
            XWPFRun contentDetailsRun = contentDetailsParagraph.createRun();
            contentDetailsRun.setText("- Number of paragraphs: " + document.getParagraphs().size());

            //Das Dokument wird in einem docx file abespeichert. Der Pfad bleibt der jetzige Pfad
           FileOutputStream out = new FileOutputStream("LogInformationen.docx");
                document.write(out);
                logger.info("Das Dokument wurde gespeichert.");
    

    }
}
