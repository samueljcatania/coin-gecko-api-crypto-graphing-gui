import java.util.ArrayList;
/**
 * BrokerList stores Broker objects in an array.
 *
 * @author Meg Zhang
 *
 */
public class BrokerList implements BrokerListInterface {
    private final ArrayList<Broker> brokerList; // list of brokers

    /**
     * Constructor for BrokerList. Creates a BrokerList object.
     */
    public BrokerList(){
        // initialize brokerList with no items
        brokerList = new ArrayList<>();
    }

    /**
     * addBroker adds a broker to brokerList.
     *
     * @param name the name of the broker, retrieved from user input into the table on the mainUI.
     * @param strategy the strategy of the broker, selected from the table in mainUI.
     * @param coins the list of coins of interest, which is also inputted into the table in mainUI.
     * @throws BrokerException if broker cannot be added because it already exists.
     */
    public void addBroker(String name, TradingStrategy strategy, ArrayList<Coin> coins) throws BrokerException {
        // broker name already exists. Don't add
        if (findBroker(name) != null){
            throw new BrokerException("Broker name already exists. The Broker was not added");
        }
        else {
            brokerList.add(new Broker(name, strategy, coins));
        }
    }

    /**
     * removeBroker removes a trading broker from the table.
     *
     * @param name the name of the broker to be removed.
     * @throws BrokerException if the broker to be removed doesn't exist.
     */
    public void removeBroker(String name) throws BrokerException{
        try {
            brokerList.remove(findBroker(name));
        } catch (Exception e){
            throw new BrokerException("Could not remove broker");
        }
    }

    /**
     * editBrokerName is a method to edit the broker name.
     *
     * @param brokerName the current name
     * @param newName the new name
     * @throws BrokerException throw exception if the current name cannot be found on the table.
     */
    public void editBrokerName (String brokerName, String newName) throws BrokerException{
        Broker broker = findBroker(brokerName);
        if (broker != null){
            broker.setName(brokerName);
        }
    }

    /**
     * editBrokerStrategy applies a new tradingStrategy to the specified broker.
     *
     * @param name the name of the broker to apply a new strategy to
     * @param strategy the new strategy to apply
     * @throws BrokerException throws exception if the broker cannot be found on the table.
     */
    public void editBrokerStrategy (String name, TradingStrategy strategy) throws BrokerException{
        Broker broker = findBroker(name);
        if (broker != null){
            broker.setStrategy(strategy);
        }
    }

    /**
     * editCoins replaces the coinList with a new coinList depending on user input on the table in
     * mainUI.
     *
     * @param name the name of the broker to be updated
     * @param coins an arrayList of the updated coins.
     * @throws BrokerException throws exception if the broker cannot be found.
     */
    public void editCoins (String name, ArrayList<Coin> coins) throws BrokerException{
        Broker broker = findBroker(name);
        if (broker != null){
            broker.setCoins(coins);
        }
    }

    // checks brokerList for an existing name
    public Broker findBroker(String theName){
        for (Broker broker : brokerList) {
            if (broker.getName().equals(theName)) {
                return broker;
            }
        }
        return null;
    }

    // returns the brokerlist
    public ArrayList<Broker> logTradingActivity() {
        return brokerList;
    }

    // update all coin prices on the brokerList.
    public void updateCoinPrices() {
        for (Broker broker : brokerList) {
            for (int j = 0; j < broker.getCoins().size(); j++) {
                try {
                    broker.getCoins().get(j).updateCoinPrice();
                } catch (BrokerException ignored) {
                    System.out.println("Could not update coin price for " + broker.getCoins().get(j).getCoinName());
                }
            }
        }
    }
}
