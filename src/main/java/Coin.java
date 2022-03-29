/**
 * This class models a coin, containing a cryptocoin's name, and current price.
 *
 * @author Meg Zhang
 *
 */
public class Coin {
    private String coinName;
    private double coinPrice;

    public Coin(String name){
        coinName = name; // set the coin's name as the parameter passed.
    }

    // set coin price
    public void setCoinPrice(double coinPrice) {
        this.coinPrice = coinPrice;
    }

    // return coin price
    public double getCoinPrice(){
        return coinPrice;
    }


    // return coin name
    public String getCoinName() {
        return coinName;
    }
}
