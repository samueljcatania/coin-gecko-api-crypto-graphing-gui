public interface TradingStrategyInterface {

    String[] trade(String brokerName, double coinTargetPrice, String[] coinList, double[] coinPriceList);

}
