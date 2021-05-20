import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelUpdateService {

    private String ggOrderNumber;
    public String getGgOrderNumber() {
        return this.ggOrderNumber;
    }
    public void updateExcelFile() throws Exception {
        PDFReader pdfReader = new PDFReader();
        pdfReader.ReadPDFFile();
        String riceType = pdfReader.getRiceType();
        String cdOrderNumber = pdfReader.getCdNumber();

        if (riceType.contains("Short Grain")) {
                riceType = "Real Rice Short Grain 5kg";
        } else if (riceType.contains("Jasmine Brown")) {
                riceType = "Real Rice Jasmine Brown 5kg";
        } else if (riceType.contains("Jasmine")) {
                riceType = "Real Rice Jasmine White 5kg";
        }

        String excelFilePath = "C:\\Users\\Essam\\Desktop\\Animation\\Golden Gate Shipments Automated.xlsx";

        try {

            FileInputStream fileInputStream = new FileInputStream(excelFilePath);

            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(sheet.getLastRowNum());
            Cell cell = row.getCell(0);

            double idc = cell.getNumericCellValue();
            idc = idc + 1;

            String str = String.valueOf(idc);
            String correctString[] = str.split("\\.");

            ggOrderNumber = correctString[0];

            int lastRowCount = sheet.getLastRowNum();

            Row dataRow = sheet.createRow(++lastRowCount);
                dataRow.createCell(0).setCellValue(idc);
                dataRow.createCell(1).setCellValue(riceType);
                dataRow.createCell(2).setCellValue(cdOrderNumber);
                dataRow.createCell(3).setCellValue("Created");
                dataRow.createCell(4).setCellValue(pdfReader.getBeforeDate());
                dataRow.createCell(5).setCellValue(pdfReader.getAfterDate());

            fileInputStream.close();

            FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath);
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            System.out.println("excel sheet updated successfully........");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}