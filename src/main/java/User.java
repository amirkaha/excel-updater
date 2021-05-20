

public class User {
    private String ggOrderNumber;
    private String nameOfRice;
    private String cdOrderNumber;

    public String getGgOrderNumber() {
        return ggOrderNumber;
    }
    public void setGgOrderNumber(String ggOrderNumber) {
        this.ggOrderNumber = ggOrderNumber;
    }
    public String getNameOfRice() {
        return nameOfRice;
    }

    public void setNameOfRice(String nameOfRice) {
        this.nameOfRice = nameOfRice;
    }
    public String getCdOrderNumber() {
        return cdOrderNumber;
    }
    public void setCdOrderNumber(String ggOrderNumber) {
        this.cdOrderNumber = cdOrderNumber;
    }


    public User() {
        super();
    }
    public User(String ggOrderNumber, String nameOfRice, String cdOrderNumber) {
        super();
        this.ggOrderNumber = ggOrderNumber;
        this.nameOfRice = nameOfRice;
        this.cdOrderNumber = cdOrderNumber;

    }


    @Override
    public String toString() {
        return "User [userId=" + ggOrderNumber + ", userName=" + nameOfRice + "]";
    }
}
