import java.util.ArrayList;
/**
 * This class models a Broker object that is stored by a BrokerList object.
 * A broker object contains its name, strategy, and a list of coins of interest attached to it.
 *
 * @author Meg Zhang
 *
 */
public class Broker {
    private String brokerName;
    private TradingStrategy tradeStrategy;
    private ArrayList<Coin> coins;

    public Broker(String name, TradingStrategy strategy, ArrayList<Coin> coins) {
        brokerName = name;
        tradeStrategy = strategy;
        this.coins = coins;
    }

    public String getName(){
        return brokerName;
    }

    public TradingStrategy getTradeStrategy() {
        return tradeStrategy;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
}
