public class Shareholder {

    private int customerId;
    private String firstname;
    private String surname;
    private String address;
    private String portfolioId;
    private String phone;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Shareholder(int customerId, String firstname, String surname, String address, String phone, String portfolioId) {
        this.customerId = customerId;
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.portfolioId = portfolioId;
    }
}
