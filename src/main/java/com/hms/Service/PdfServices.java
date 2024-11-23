package com.hms.Service;

import com.hms.entity.Property;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;

@Service
public class PdfServices {
    public void generateBookingPdf(
            String filePath,
            Property property) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            PdfPTable table = new PdfPTable(2);

                table.addCell("Id");
                table.addCell(property.getId().toString());

            table.addCell("Name");
            table.addCell(property.getName());

            table.addCell("No.Of Guest");
            table.addCell(property.getNo_of_guests().toString());

            document.add(table);
            document.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

