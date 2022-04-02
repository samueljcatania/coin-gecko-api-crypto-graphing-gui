import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private void setCoinPrice(double coinPrice) {
        this.coinPrice = coinPrice;
    }

    //update coin price with most up to date prices.
    public void updateCoinPrice() throws BrokerException {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        DataFetcher fetcher = new DataFetcher();
        try {
            setCoinPrice(fetcher.getPriceForCoin(coinName, dateFormat.format(date)));
        } catch (Exception e){
            throw new BrokerException("Could not update coin price");
        }
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
