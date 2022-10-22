/*
*
* Portfolio share class used in portfolio class as an object
* */

public class PortfolioShare {
    private String shareCode;
    private int numOfShares;

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public int getNumOfShares() {
        return numOfShares;
    }

    public void setNumOfShares(int numOfShares) {
        this.numOfShares = numOfShares;
    }

    public PortfolioShare(String shareCode, int numOfShares) {
        this.shareCode = shareCode;
        this.numOfShares = numOfShares;
    }
}
