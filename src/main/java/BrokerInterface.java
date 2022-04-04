import java.util.ArrayList;

/**
 * Interface for Broker.
 *
 * @author Meg Zhang
 *
 */
public interface BrokerInterface {
    /**
     * getName returns the name of the broker.
     *
     * @return the name of the broker
     */
    String getName();

    /**
     * getTradeStrategy returns the strategy attached to this broker
     *
     * @return the trade strategy associated with the broker
     */
    TradingStrategy getTradeStrategy();

    /**
     * getCoins returns the list of coins associated with this broker
     *
     * @return ArrayList of coin objects associated with this broker.
     */
    ArrayList<Coin> getCoins();

    /**
     * setName changes the name of the broker.
     *
     * @param newName new name of broker.
     */
    void setName(String newName);

    /**
     * setStrategy changes the strategy attached to this broker
     *
     * @param strategy the new strategy
     */
    void setStrategy(TradingStrategy strategy);

    /**
     * setCoins changes the coinList attached to this broker.
     *
     * @param coins new ArrayList of coins to attach to this broker.
     */
    void setCoins(ArrayList<Coin> coins);

    /**
     * finds and returns the specified coin within the coin list.
     *
     * @param coinName name of coin to find.
     * @return the coin object associated with the coinName. Otherwise returns null.
     */
    Coin findCoin(String coinName);

    /**
     * Updates coin prices associated with this broker.
     *
     */
    public void updateCoinPrices();
}
