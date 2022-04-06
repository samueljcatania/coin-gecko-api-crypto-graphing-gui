import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents a trading strategy. It includes the name of the strategy, the coin in interest,
 * the current price of the coin, the target price of the coin, and the action it will take.
 */
public class TradingStrategy implements TradingStrategyInterface {

    private String strategyName;
    private String coinTarget;
    private String action;
    private int quantity;
    private boolean quantityIsCount;
    private String[] names;
    private String[] symbols;
    private int[] prices;


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

    public String getStrategyName() {
        return strategyName;
    }

    public String getCoinTarget() {
        return coinTarget;
    }

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
                    return new String[]{brokerName, strategyName, coinTarget, "Fail", "Null", "Null", dateFormat.format(date)};
                }

            } else {
                return new String[]{brokerName, strategyName, coinTarget, "Fail", "Null", "Null", dateFormat.format(date)};
            }

        }

        if (quantityIsCount) {
            return new String[]{brokerName, strategyName, coinTarget, action, quantity + "", coinTargetPrice + "", dateFormat.format(date)};
        }

        return new String[]{brokerName, strategyName, coinTarget, action, (quantity / coinTargetPrice) + "", coinTargetPrice + "", dateFormat.format(date)};
    }
}
