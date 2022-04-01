/**
 * This class represents the collection of Trading Strategies
 */

import java.util.LinkedList;

/**
 * Constructor creates a LinkedList to store all the trading strategies
 */
public class TradingStrategies {
    LinkedList<TradingStrategy> strategies = new LinkedList<TradingStrategy>();
    TradingStrategy buyIfGreater = new TradingStrategy();
    TradingStrategy buyIfLesser = new TradingStrategy();
    TradingStrategy sellIfGreater = new TradingStrategy();
    TradingStrategy sellIfLesser = new TradingStrategy();
    TradingStrategy buyIfEquals = new TradingStrategy();


    /**
     * Getter method retrieves the trading strategy object with the desired name
     * @param strategyName Name of the desired trading strategy
     * @return The trading strategy object with the desired name
     */
    public TradingStrategy getStrategy(String strategyName) {
        for (int i = 0; i < strategies.size(); i++){
            if (strategyName.equals(strategies.get(i).getStrategyName(strategies.get(i)))){
                return strategies.get(i);
            }
        }
        return null;
    }
}
