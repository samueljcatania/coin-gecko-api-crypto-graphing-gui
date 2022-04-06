/**
 * This class models a Broker object that is stored by a BrokerList object.
 * A broker object contains its name, strategy, and a list of coins of interest attached to it.
 *
 * @author Meg Zhang
 *
 */
public class Broker{
    private String brokerName;
    private TradingStrategy tradeStrategy;
    private String[] coinNames;
    private double[] coinPrices;

    /**
     * Constructor for a new broker object.
     *
     * @param name broker name
     * @param strategy strategy associated with the broker
     * @param coinNames coins associated with the broker.
     */
    public Broker(String name, TradingStrategy strategy, String[] coinNames) {
        brokerName = name;
        tradeStrategy = strategy;
        this.coinNames = coinNames;
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

    public double[] getCoinPrices() {
        return coinPrices;
    }

    /**
     * getCoins returns the list of coins associated with this broker
     *
     * @return ArrayList of coin objects associated with this broker.
     */
    public String[] getCoins() {
        return coinNames;
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
    public void setCoinNames(String[] coins) {
        this.coinNames = coinNames;
    }

    public void setCoinPrices(double[] coinPrices){
        this.coinPrices = coinPrices;
    }

}
