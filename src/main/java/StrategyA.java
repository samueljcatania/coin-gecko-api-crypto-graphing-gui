public class StrategyA extends TradingStrategy{
    public StrategyA(String strategyName, String coin, float current, float target, String description) {
        super(strategyName, coin, current, target);
        this.strategyName = "Strategy A";
    }
}
