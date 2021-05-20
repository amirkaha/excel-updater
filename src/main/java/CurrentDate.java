import java.util.Calendar;
import java.text.SimpleDateFormat;

public class CurrentDate {
    public static final String DATE_FORMAT_NOW = "dd/MM/yyyy";

    public String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
}
