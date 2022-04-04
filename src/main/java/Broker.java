import java.util.ArrayList;
/**
 * This class models a Broker object that is stored by a BrokerList object.
 * A broker object contains its name, strategy, and a list of coins of interest attached to it.
 *
 * @author Meg Zhang
 *
 */
public class Broker implements BrokerInterface{
    private String brokerName;
    private TradingStrategy tradeStrategy;
    private ArrayList<Coin> coins;

    /**
     * Constructor for a new broker object.
     *
     * @param name broker name
     * @param strategy strategy associated with the broker
     * @param coins coins associated with the broker.
     */
    public Broker(String name, TradingStrategy strategy, ArrayList<Coin> coins) {
        brokerName = name;
        tradeStrategy = strategy;
        this.coins = coins;
    }

    /**
     * getName returns the name of the broker.
     *
     * @return the name of the broker
     */
    public String getName(){
        return brokerName;
    }

    /**
     * getTradeStrategy returns the strategy attached to this broker
     *
     * @return the trade strategy associated with the broker
     */
    public TradingStrategy getTradeStrategy() {
        return tradeStrategy;
    }

    /**
     * getCoins returns the list of coins associated with this broker
     *
     * @return ArrayList of coin objects associated with this broker.
     */
    public ArrayList<Coin> getCoins() {
        return coins;
    }

    /**
     * setName changes the name of the broker.
     *
     * @param newName new name of broker.
     */
    public void setName(String newName) {
        brokerName = newName;
    }

    /**
     * setStrategy changes the strategy attached to this broker
     *
     * @param strategy the new strategy
     */
    public void setStrategy(TradingStrategy strategy) {
        tradeStrategy = strategy;
    }

    /**
     * setCoins changes the coinList attached to this broker.
     *
     * @param coins new ArrayList of coins to attach to this broker.
     */
    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }

    /**
     * finds and returns the specified coin within the coin list.
     *
     * @param coinName name of coin to find.
     * @return the coin object associated with the coinName. Otherwise returns null.
     */
    public Coin findCoin(String coinName){
        for (int i = 0; i < getCoins().size(); i++) {
            // if coin is found, then return the coin.
            if (getCoins().get(i).getCoinName().equals(coinName)){
                return getCoins().get(i);
            }
        }
        return null; // otherwise, return null.
    }

    /**
     * Updates coin prices associated with this broker.
     *
     */
    public void updateCoinPrices(){
        for (Coin coin : coins) {
            try {
                coin.updateCoinPrice();
            } catch (BrokerException e) {
                System.out.println("Could not update coin price");
            }
        }
    }

    /**
     * returns a String[] of the coin list's names.
     *
     */
    public String[] coinNameList(){
        String[] names = new String[coins.size()];

        for (int i = 0; i < coins.size(); i++) {
            names[i] = coins.get(i).getCoinName();
        }
        return names;
    }

    /**
     * returns a double[] of the coin list's names.
     *
     */
    public double[] coinPriceList(){
        double[] prices = new double[coins.size()];

        for (int i = 0; i < coins.size(); i++) {
            prices[i] = coins.get(i).getCoinPrice();
        }
        return prices;
    }


}
