public class Main {

    public static void main(String[] args){

        //Create trading strategies
        TradingStrategy strategyA = new TradingStrategy("Strategy-A", "ADA","buy",10, new String[]{"BTC", "ADA"}, new String[]{"<=", ">"}, new int[]{50000, 2});

    }
}
