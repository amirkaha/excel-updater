import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.util.SystemOutLogger;

public class PDFReader {
    private String cdNumber;
    private String beforeDate;
    private String afterDate;
    private String portOfLoading;
    private String riceType;
    private String riceTypeFull;
    private String fileName;

    public String getCdNumber() {
        return this.cdNumber;
    }

    public String getBeforeDate() {
        return this.beforeDate;
    }

    public String getAfterDate() {
        return this.afterDate;
    }

    public String getPortOfLoading() {
        return this.portOfLoading;
    }

    public String getRiceType() {
        return this.riceType;
    }

    public String getRiceTypeFull() {
        return this.riceTypeFull;
    }

    public String getFileName () { return this.fileName; }


    public void ReadPDFFile() throws Exception {
        String[] pathnames;
        File f = new File("C:\\Users\\Essam\\Desktop\\Animation");
        // Populates the array with names of files and directories
        pathnames = f.list();

        String[] pathNamesCountdown;
        File g = new File("C:\\Users\\Essam\\Documents\\Countdown Real Rice Orders");
        pathNamesCountdown = g.list();

        for (String pathname : pathnames) {
            // Print the names of files and directories
            System.out.println(pathname);
            if (pathname.contains("Purchase Order")) {
                 fileName = pathname;
            }
        }

        // if the file is available in local machine
        File file = new File("C:\\Users\\Essam\\Desktop\\Animation\\" + fileName);
        FileInputStream fis = new FileInputStream(file);
        PDDocument pdfDocument = PDDocument.load(fis);
       // System.out.println("Number of Pages: " + pdfDocument.getPages().getCount());

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        pdfTextStripper.setStartPage(1); // comment this line if you want to read the entire document
        pdfTextStripper.setEndPage(3); // comment this line if you want to read the entire document
        String docText = pdfTextStripper.getText(pdfDocument);



        String lines[] = docText.split("\\r?\\n");

         cdNumber = textModifier(lines[6]);
         beforeDate = textModifier(lines[7]);
         afterDate = textModifier(lines[8]);
         portOfLoading = textModifier(lines[27]);

        for (String pathNameCountdown : pathNamesCountdown) {
            // Print the names of files and directories
            System.out.println(pathNameCountdown);
            if (pathNameCountdown.contains(cdNumber)) {
                System.out.println("Error, this order number (" + cdNumber + " ) already exists");
                System.exit(0);
            }
        }


        if (docText.contains("Real Rice Short Grain 5kg")) {
            riceType = "Short Grain";
            riceTypeFull = "Real Rice Short Grain 5kg";
        } else if (docText.contains("Real Rice Jasmine Brown 5kg")) {
            riceType = "Jasmine Brown";
            riceTypeFull = "Real Rice Jasmine Brown 5kg";
        } else if (docText.contains("Real Rice Jasmine 5kg")) {
            riceType = "Jasmine";
            riceTypeFull = "Real Rice Jasmine 5kg";
        }

        if (docText.contains("THBKK")) {
            System.out.println("Error! Port of loading is Thailand, please request to change to Vietnam");
        }

        for(String line: lines) {
           // System.out.println(line);

        }

        System.out.println(riceType);

        pdfDocument.close();
        fis.close();
    }

    public String textModifier (String text) {
        text = text.substring(text.indexOf(":")+1);
        text.trim();
        return text;
    }

}


