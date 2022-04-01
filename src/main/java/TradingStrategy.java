/**
 * This class represents a trading strategy. It includes the name of the strategy, the coin in interest,
 * the current price of the coin, the target price of the coin, and the action it will take.
 */
public class TradingStrategy {

	private String name;
	private String coin;
	private float current;
	private float target;
	private String description;

	public TradingStrategy(String name, String coin, float current, float target, String description) {
		this.name = name;
		this.coin = coin;
		this.current = current;
		this.target = target;
		this.description = description;
	}

	/**
	 * Gett method returns the name of the trading strategy object
	 * @param strategy Trading strategy object in question
	 * @return the name of the trading strategy object
	 */
	public String getStrategyName (TradingStrategy strategy){
		return strategy.name;
	}


}
