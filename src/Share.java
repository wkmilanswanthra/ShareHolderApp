public class Share {
    private String shareCode;
    private String company;
    private double price;

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Share(String shareCode, String company, double price) {
        this.shareCode = shareCode;
        this.company = company;
        this.price = price;
    }
}
