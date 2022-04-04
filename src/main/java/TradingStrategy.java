/**
 * This class represents a trading strategy. It includes the name of the strategy, the coin in interest,
 * the current price of the coin, the target price of the coin, and the action it will take.
 */
public class TradingStrategy {

	private String strategyName;
	private String coin;
	private float current;
	private float target;

	public TradingStrategy(String strategyName, String coin, float current, float target) {
		this.strategyName = strategyName;
		this.coin = coin;
		this.current = current;
		this.target = target;
	}


	/**
	 * Get method returns the name of the trading strategy object
	 * @param strategy Trading strategy object in question
	 * @return the name of the trading strategy object
	 */
	public String getStrategyName (TradingStrategy strategy){
		return strategy.strategyName;
	}


	public Boolean evaluateStrategy(TradingStrategy strategy) {
		if (strategy.equals("StrategyA")){
			if ()
		} else if (strategy.equals("StrategyB")){

		} else if (strategy.equals("StrategyC")){

		} else if (strategy.equals("StrategyD")){

		} else if (strategy.equals("StrategyE")){

		}
		return null;
	}

}
