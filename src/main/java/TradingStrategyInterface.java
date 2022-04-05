public interface TradingStrategyInterface {

    String[] trade(String brokerName, String[] coinList, double[] coinPriceList);

}
