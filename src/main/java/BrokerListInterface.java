import java.util.ArrayList;

/**
 * Interface for BrokerList.
 *
 * @author Meg Zhang
 */
public interface BrokerListInterface {
    /**
     * addBroker adds a broker to brokerList.
     *
     * @param name the name of the broker, retrieved from user input into the table on the mainUI.
     * @param strategy the strategy of the broker, selected from the table in mainUI.
     * @param coins the list of coins of interest, which is also inputted into the table in mainUI.
     * @throws BrokerException if broker cannot be added because it already exists.
     */
    void addBroker(String name, TradingStrategy strategy, ArrayList<Coin> coins) throws BrokerException;

    /**
     * removeBroker removes a trading broker from the table.
     *
     * @param name the name of the broker to be removed.
     * @throws BrokerException if the broker to be removed doesn't exist.
     */
    void removeBroker(String name) throws BrokerException;

    /**
     * editBrokerName is a method to edit the broker name.
     *
     * @param brokerName the current name
     * @param newName the new name
     */
    void editBrokerName (String brokerName, String newName);

    /**
     * editBrokerStrategy applies a new tradingStrategy to the specified broker.
     *
     * @param name the name of the broker to apply a new strategy to
     * @param strategy the new strategy to apply
     */
    void editBrokerStrategy (String name, TradingStrategy strategy);

    /**
     * editCoins replaces the coinList with a new coinList depending on user input on the table in
     * mainUI.
     *
     * @param name the name of the broker to be updated
     * @param coins an arrayList of the updated coins.
     */
    void editCoins (String name, ArrayList<Coin> coins);

    /**
     * findBroker checks brokerList for an existing broker name on the table.
     *
     * @param theName to find
     * @return the broker associated with the inputted name.
     */
    Broker findBroker(String theName);

    /**
     * logTradingActivity returns the entire brokerList in an ArrayList of Broker objects.
     *
     * @return the brokerList.
     */
    ArrayList<Broker> logTradingActivity();

    /**
     * updateCoinPrices updates all coin prices on the brokerList.
     *
     */
    void updateCoinPrices();

}
