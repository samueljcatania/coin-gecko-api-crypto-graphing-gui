public class Main {

    public static void main(String[] args){

        Login login = new Login();

        //Create trading strategies
        TradingStrategy strategyA = new TradingStrategy("Strategy-A", "ADA","buy",10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});
        TradingStrategy strategyB = new TradingStrategy("Strategy-B", "ETH","buy",10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});
        TradingStrategy strategyC = new TradingStrategy("Strategy-C", "BTC","buy",10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});
        TradingStrategy strategyD = new TradingStrategy("Strategy-D", "ADA","buy",10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});
        TradingStrategy strategyE = new TradingStrategy("Strategy-E", "ADA","buy",10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});

        BrokerList brokerList = new BrokerList();

        Broker brokerOne = new Broker("BrokerOne", strategyA, new String[]{"BTC", "ADA"});

        DataFetcher dataFetcher = new DataFetcher();

        brokerOne.updateCoinPrices();
        String[] array = brokerOne.getTradeStrategy().trade(brokerOne.coinNameList(), brokerOne.coinPriceList());

    }
}
