import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a trading strategy. It includes the name of the strategy, the coin in interest,
 * the current price of the coin, the target price of the coin, and the action it will take.
 *
 * @author Nicholas Lai
 * @author Samuel Catania
 */
public class TradingStrategy implements TradingStrategyInterface {

    private final String strategyName;
    private final String coinTarget;
    private final String action;
    private final int quantity;
    private final boolean quantityIsCount;
    private final String[] names;
    private final String[] symbols;
    private final int[] prices;

    /**
     * Constructor for TradingStrategy creates a new TradingStrategy
     *
     * @param strategyName name of strategy
     * @param coinTarget coin that will be traded
     * @param action buy/sell
     * @param quantity amount of coin will be bought/sold OR the total price of the amount of coin to be bought/sold
     * @param quantityIsCount true if quantity is the amount of coin, false if it is the price.
     * @param names String array of the names of the coins.
     * @param symbols String array of coin tickers (eg. BTC for bitcoin)
     * @param prices int array of current coin prices
     */
    public TradingStrategy(String strategyName, String coinTarget, String action, int quantity, boolean quantityIsCount, String[] names, String[] symbols, int[] prices) {
        this.strategyName = strategyName;
        this.coinTarget = coinTarget;
        this.action = action;
        this.quantity = quantity;
        this.quantityIsCount = quantityIsCount;
        this.names = names;
        this.symbols = symbols;
        this.prices = prices;

    }

    /**
     * getStrategyName returns strategy name
     *
     * @return strategyName- the name of the strategy.
     */
    public String getStrategyName() {
        return strategyName;
    }

    /**
     * getCoinTarget returns the coin that this strategy will trade if the conditions are met.
     *
     * @return coinTarget- coin that will be traded
     */
    public String getCoinTarget() {
        return coinTarget;
    }

    /**
     * Method trade executes a trade.
     *
     * @param brokerName name of broker
     * @param coinTargetPrice target price of coin
     * @param coinList list of coins attached to the broker
     * @param coinPriceList list of coin prices associated with the coins attached to the broker.
     * @return a String[] that contains details about the trade that was performed.
     */
    @Override
    public String[] trade(String brokerName, double coinTargetPrice, String[] coinList, double[] coinPriceList) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        Date date = new Date();

        for (int i = 0; i < names.length; i++) {
            int index = -1;

            for (int x = 0; x < coinList.length; x++) {
                if (coinList[x].equalsIgnoreCase(names[i])) {
                    index = x;
                }
            }

            if (index != -1) {
                boolean evaluation = true;

                switch (symbols[i]) {

                    case "<":

                        if (!(prices[i] < coinPriceList[index])) {
                            evaluation = false;
                        }
                        break;

                    case ">":
                        if (!(prices[i] > coinPriceList[index])) {
                            evaluation = false;
                        }
                        break;

                    case "=":
                        if (!(prices[i] == coinPriceList[index])) {
                            evaluation = false;
                        }
                        break;

                    case ">=":
                        if (!(prices[i] >= coinPriceList[index])) {
                            evaluation = false;
                        }
                        break;

                    case "<=":
                        if (!(prices[i] <= coinPriceList[index])) {
                            evaluation = false;
                        }
                        break;

                }

                if (!evaluation) {
                    return new String[]{brokerName, strategyName, coinTarget, "Fail", "Null", "Null", dateFormat.format(date), "1"};
                }

            } else {
                return new String[]{brokerName, strategyName, coinTarget, "Fail", "Null", "Null", dateFormat.format(date), "0"};
            }

        }

        if (quantityIsCount) {
            return new String[]{brokerName, strategyName, coinTarget, action, quantity + "", coinTargetPrice + "", dateFormat.format(date)};
        }

        return new String[]{brokerName, strategyName, coinTarget, action, (quantity / coinTargetPrice) + "", coinTargetPrice + "", dateFormat.format(date)};
    }
}
