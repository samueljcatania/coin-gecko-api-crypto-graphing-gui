public class Main {

    public static void main(String[] args){



        //Create trading strategies
        TradingStrategy strategyA = new TradingStrategy("Strategy-A", "ADA","buy",10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});

        BrokerList brokerList = new BrokerList();

        Broker brokerOne = new Broker("BrokerOne", strategyA, coins);

        DataFetcher dataFetcher = new DataFetcher();

        String[] array = brokerOne.getTradeStrategy().trade(brokerOne.coinNameList(), dataFetcher.getPriceForCoin())

    }
}
