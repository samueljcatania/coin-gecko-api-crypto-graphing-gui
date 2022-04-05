public class Main {

    public static void main(String[] args){



        //Create trading strategies
        TradingStrategy strategyA = new TradingStrategy("Strategy-A", "ADA","buy",10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});

        BrokerList brokerList = new BrokerList();

        Broker brokerOne = new Broker("BrokerOne", strategyA, new String[]{"BTC", "ADA"});

        DataFetcher dataFetcher = new DataFetcher();

        brokerOne.updateCoinPrices();
        String[] array = brokerOne.getTradeStrategy().trade(brokerOne.coinNameList(), brokerOne.coinPriceList());

    }
}
