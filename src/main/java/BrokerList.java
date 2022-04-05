//import java.util.ArrayList;
//
///**
// * BrokerList stores Broker objects in an array.
// *
// * @author Meg Zhang
// *
// */
//public class BrokerList implements BrokerListInterface {
//    private final ArrayList<Broker> brokerList; // list of brokers
//
//    /**
//     * Constructor for BrokerList. Creates a BrokerList object.
//     */
//    public BrokerList(){
//        // initialize brokerList with no items
//        brokerList = new ArrayList<>();
//    }
//
//    /**
//     * addBroker adds a broker to brokerList.
//     *
//     * @param name the name of the broker, retrieved from user input into the table on the mainUI.
//     * @param strategy the strategy of the broker, selected from the table in mainUI.
//     * @param coins the list of coins of interest, which is also inputted into the table in mainUI.
//     * @throws BrokerException if broker cannot be added because it already exists.
//     */
//    public void addBroker(String name, TradingStrategy strategy, ArrayList<Coin> coins) throws BrokerException {
//        // broker name already exists. Don't add
//        if (findBroker(name) != null){
//            throw new BrokerException("Broker name already exists. The Broker was not added");
//        }
//        else {
//            brokerList.add(new Broker(name, strategy, coins));
//        }
//    }
//
//    /**
//     * addBroker adds a broker to brokerList.
//     *
//     * @param name the name of the broker, retrieved from user input into the table on the mainUI.
//     * @param strategy the strategy of the broker, selected from the table in mainUI.
//     * @param coins the list of coins of interest, which is also inputted into the table in mainUI.
//     * @throws BrokerException if broker cannot be added because it already exists.
//     */
//    public void addBroker(String name, TradingStrategy strategy, String[] coins) throws BrokerException {
//        // broker name already exists. Don't add
//        if (findBroker(name) != null){
//            throw new BrokerException("Broker name already exists. The Broker was not added");
//        }
//        else {
//            brokerList.add(new Broker(name, strategy, coins));
//        }
//    }
//
//    /**
//     * removeBroker removes a trading broker from the table.
//     *
//     * @param name the name of the broker to be removed.
//     * @throws BrokerException if the broker to be removed doesn't exist.
//     */
//    public void removeBroker(String name) throws BrokerException{
//        try {
//            brokerList.remove(findBroker(name));
//        } catch (Exception e){
//            throw new BrokerException("Could not remove broker");
//        }
//    }
//
//    /**
//     * editBrokerName is a method to edit the broker name.
//     *
//     * @param brokerName the current name
//     * @param newName the new name
//     */
//    public void editBrokerName (String brokerName, String newName){
//        Broker broker = findBroker(brokerName);
//        if (broker != null){
//            broker.setName(brokerName);
//        }
//    }
//
//    /**
//     * editBrokerStrategy applies a new tradingStrategy to the specified broker.
//     *
//     * @param name the name of the broker to apply a new strategy to
//     * @param strategy the new strategy to apply
//     */
//    public void editBrokerStrategy (String name, TradingStrategy strategy){
//        Broker broker = findBroker(name);
//        if (broker != null){
//            broker.setStrategy(strategy);
//        }
//    }
//
//    /**
//     * editCoins replaces the coinList with a new coinList depending on user input on the table in
//     * mainUI.
//     *
//     * @param name the name of the broker to be updated
//     * @param coins an arrayList of the updated coins.
//     */
//    public void editCoins (String name, ArrayList<Coin> coins){
//        Broker broker = findBroker(name);
//        if (broker != null){
//            broker.setCoins(coins);
//        }
//    }
//
//    /**
//     * editCoins replaces the coinList with a new coinList depending on user input on the table in
//     * mainUI.
//     *
//     * @param name the name of the broker to be updated
//     * @param coins a String[] of the updated coins.
//     */
//    public void editCoins (String name, String[] coins){
//        Broker broker = findBroker(name);
//        if (broker != null){
//            broker.setCoins(coins);
//        }
//    }
//
//    /**
//     * findBroker checks brokerList for an existing broker name on the table.
//     *
//     * @param theName to find
//     * @return the broker associated with the inputted name.
//     */
//    public Broker findBroker(String theName){
//        for (Broker broker : brokerList) {
//            if (broker.getName().equals(theName)) {
//                return broker;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * logTradingActivity returns the entire brokerList in an ArrayList of Broker objects.
//     *
//     * @return the brokerList.
//     */
//    public ArrayList<Broker> logTradingActivity() {
//        return brokerList;
//    }
//
//    /**
//     * updateCoinPrices updates all coin prices on the brokerList.
//     *
//     */
//    public void updateCoinPrices() {
//        for (Broker broker : brokerList) {
//            broker.updateCoinPrices();
//        }
//    }
//}
