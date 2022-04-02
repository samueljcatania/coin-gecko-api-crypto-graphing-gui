import java.util.ArrayList;

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
}
