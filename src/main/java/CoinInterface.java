/**
 * Interface for Coin
 *
 * @author Meg Zhang
 *
 */
public interface CoinInterface {
    /**
     * updateCoinPrice updates coin price with most up to date prices.
     *
     * @throws BrokerException if a coin cannot be found or price cannot be fetched.
     */
    void updateCoinPrice() throws BrokerException;

    /**
     * getCoinPrice returns coin price
     *
     * @return the current price associated with the coin.
     */
    double getCoinPrice();

    /**
     * getCoinName returns coin name
     *
     * @return the coin's name
     */
    String getCoinName();



}
