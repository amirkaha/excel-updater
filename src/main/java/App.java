public class App {
    public static void main( String[] args ) throws Exception {
        PDFReader pdfReader = new PDFReader();
        pdfReader.ReadPDFFile();

        WordModifier wordModifier = new WordModifier();
        wordModifier.replaceAndCreate();

        System.out.println(" main method execution.........");

        EmailDan emailDan = new EmailDan();

        emailDan.loadPage().enterEmail().clickEmailNext().enterPassword().clickNext()
                .clickCompose().typeEmailAddress()
                        .typeSubject(pdfReader.getCdNumber(), wordModifier.getGgOrderNumber(),pdfReader.getRiceTypeFull())
                             .attachFile(wordModifier.getGgOrderNumber(), pdfReader.getCdNumber());

    }
}
