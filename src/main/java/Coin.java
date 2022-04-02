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

    /**
     * Constructor for a new Coin object.
     *
     * @param name the name of the cryptocoin to add.
     */
    public Coin(String name){
        coinName = name; // set the coin's name as the parameter passed.
    }

    /**
     * setCoinPrice sets coin price
     *
     * @param coinPrice the new price of the coin.
     */
    private void setCoinPrice(double coinPrice) {
        this.coinPrice = coinPrice;
    }

    /**
     * updateCoinPrice updates coin price with most up to date prices.
     *
     * @throws BrokerException if a coin cannot be found or price cannot be fetched.
     */
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

    /**
     * getCoinPrice returns coin price
     *
     * @return the current price associated with the coin.
     */
    public double getCoinPrice(){
        return coinPrice;
    }


    /**
     * getCoinName returns coin name
     *
     * @return the coin's name
     */
    public String getCoinName() {
        return coinName;
    }
}
