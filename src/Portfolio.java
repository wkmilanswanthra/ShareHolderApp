import java.util.ArrayList;
/*
 * portfolio class
 * */

public class Portfolio {
    private String pId;
    private ArrayList<PortfolioShare> shares;


    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public ArrayList<PortfolioShare> getShares() {
        return shares;
    }

    public void setShares(ArrayList<PortfolioShare> shares) {
        this.shares = shares;
    }

    public Portfolio(String pId, ArrayList<PortfolioShare> shares) {
        this.pId = pId;
        this.shares = shares;
    }
}
