import java.util.ArrayList;
/**
 * BrokerList stores Broker objects in an array.
 *
 */
public class BrokerList {
    private final ArrayList<Broker> brokerList; // list of brokers

    public BrokerList(){
        // initialize brokerList with no items
        brokerList = new ArrayList<>();
    }

    // add a broker to brokerList
    public void addBroker(String name, TradingStrategy strategy, ArrayList<Coin> coins) throws BrokerException {
        // broker name already exists. Don't add
        if (findBroker(name) != null){
            throw new BrokerException("Broker name already exists. The Broker was not added");
        }
        else {
            brokerList.add(new Broker(name, strategy, coins));
        }
    }

    // remove trading broker
    public void removeBroker(String name) throws BrokerException{
        try {
            brokerList.remove(findBroker(name));
        } catch (Exception e){
            throw new BrokerException("Could not remove broker");
        }
    }

    //TODO Edit broker methods

    // checks brokerList for an existing name
    private Broker findBroker(String theName){
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
}
