import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WordModifier {
    public String ggOrderNumber;
    public String getGgOrderNumber() {
        return this.ggOrderNumber;
    }

    public  void replaceAndCreate() throws Exception {
        PDFReader pdfReader = new PDFReader();
        pdfReader.ReadPDFFile();

        ExcelUpdateService excelUpdateService = new ExcelUpdateService();
        excelUpdateService.updateExcelFile();
         ggOrderNumber = excelUpdateService.getGgOrderNumber();

        File file = new File("C:\\Users\\Essam\\Desktop\\Animation\\Template.docx");
        XWPFDocument docx = new XWPFDocument(new FileInputStream(file));

       CurrentDate currentDate = new CurrentDate();
       String date = currentDate.now();
       System.out.println(date);

        for (XWPFParagraph p : docx.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("ggNumber")) {
                        text = text.replace("ggNumber", ggOrderNumber);
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("todaysDate")) {
                        text = text.replace("todaysDate", date);
                        r.setText(text, 0);
                    }
                    if (text != null && text.contains("shipBeforeDate")) {
                        text = text.replace("shipBeforeDate", pdfReader.getBeforeDate());
                        r.setText(text, 0);
                    }

                    if (text != null && text.contains("shipAfterDate")) {
                        text = text.replace("shipAfterDate", pdfReader.getAfterDate());
                        r.setText(text, 0);
                    }

                    if (text != null && text.contains("cdNumber")) {
                        text = text.replace("cdNumber", pdfReader.getCdNumber());
                        r.setText(text, 0);
                    }

                    if (text != null && text.contains("riceType")) {
                        text = text.replace("riceType", pdfReader.getRiceType());
                        r.setText(text, 0);
                    }

                }
            }


        }
        new File("C:\\Users\\Essam\\Documents\\Countdown Real Rice Orders\\" + ggOrderNumber + pdfReader.getCdNumber()).mkdirs();
        Files.move(Paths.get("C:\\Users\\Essam\\Desktop\\Animation\\" + pdfReader.getFileName()), Paths.get("C:\\Users\\Essam\\Documents\\Countdown Real Rice Orders\\" + ggOrderNumber + pdfReader.getCdNumber() + "\\Purchase Order " + pdfReader.getCdNumber()+ ".pdf"), StandardCopyOption.REPLACE_EXISTING);

        docx.write(new FileOutputStream("C:\\Users\\Essam\\Documents\\Countdown Real Rice Orders\\" + ggOrderNumber + pdfReader.getCdNumber() + "\\WW Order Number " + ggOrderNumber + "" + pdfReader.getCdNumber() + ".docx"));

    }

}





