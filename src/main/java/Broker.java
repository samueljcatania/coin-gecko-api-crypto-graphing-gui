/**
 * This class models a Broker object that is stored by a BrokerList object.
 * A broker object contains its name, strategy, and a list of coins of interest attached to it.
 *
 * @author Meg Zhang
 * @author Samuel Catania
 */
public class Broker {
    private String brokerName;
    private TradingStrategy tradeStrategy;
    private String[] coinNames;
    private double[] coinPrices;

    /**
     * Constructor for a new broker object.
     *
     * @param name      broker name
     * @param strategy  strategy associated with the broker
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
    public String getName() {
        return brokerName;
    }

    /**
     * getTradeStrategy returns the strategy attached to this broker
     *
     * @return The trade strategy associated with the broker
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
     * setCoinPrices sets a new array of coin prices.
     *
     * @param coinPrices double[] containing new coin prices.
     */
    public void setCoinPrices(double[] coinPrices) {
        this.coinPrices = coinPrices;
    }

}
