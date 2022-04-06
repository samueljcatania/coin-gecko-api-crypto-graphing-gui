/**
 * This class represents a trading strategy. It includes the name of the strategy, the coin in interest,
 * the current price of the coin, the target price of the coin, and the action it will take.
 */
public interface TradingStrategyInterface {

    /**
     * Method trade executes a trade.
     *
     * @param brokerName name of broker
     * @param coinTargetPrice target price of coin
     * @param coinList list of coins attached to the broker
     * @param coinPriceList list of coin prices associated with the coins attached to the broker.
     * @return a String[] that contains details about the trade that was performed.
     */
    String[] trade(String brokerName, double coinTargetPrice, String[] coinList, double[] coinPriceList);

}
