import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.Set;


/**
 * This class communicates with the CoinGecko API to fetch all selected cryptocoin prices in one http request
 *
 * @author Samuel Catania
 */
public class DataFetcher {

    /**
     * private helper method getDataForCrypto creates a JsonArray using CoinGecko API data,
     * which contains data which is then parsed by getPricesForCoins and getTargetCoinPrice.
     *
     * @return JsonArray containing information about all coins on CoinGecko.
     */
    private JsonArray getDataForCrypto() {

        try {
            URL url = new URL("https://api.coingecko.com/api/v3/coins/markets?vs_currency=cad");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode == 200) {
                StringBuilder inline = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline.append(sc.nextLine());
                }
                sc.close();
                return new JsonParser().parse(inline.toString()).getAsJsonArray();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with the API call.");
        }
        return null;
    }

    /**
     * getPricesForCoins fetches the current price for all coins on CoinGecko.
     *
     * @param allCoins a set of all the coins that the brokers are interested in.
     * @return a double[] containing the prices of all coins that brokers are interested in.
     */
    public double[] getPricesForCoins(Set<String> allCoins) {

        JsonArray jsonArray = getDataForCrypto();

        Object[] allCoinsArray = allCoins.toArray();
        double[] allPrices = new double[allCoinsArray.length];

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = (JsonObject) jsonArray.get(i);

                for (int x = 0; x < allCoinsArray.length; x++) {
                    if (jsonObject.get("symbol").getAsString().equalsIgnoreCase(allCoinsArray[x].toString())) {
                        allPrices[x] = jsonObject.get("current_price").getAsDouble();
                    }
                }
            }
        }
        return allPrices;
    }

    /**
     * getTargetCoinPrice fetches the current price of the targetCoin (the coin to be traded)
     *
     * @param targetCoin the name of the coin to be traded.
     * @return the price of targetCoin.
     */
    public double getTargetCoinPrice(String targetCoin) {

        JsonArray jsonArray = getDataForCrypto();

        double price = 0.0;

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = (JsonObject) jsonArray.get(i);

                if (jsonObject.get("symbol").getAsString().equalsIgnoreCase(targetCoin)){
                    price = jsonObject.get("current_price").getAsDouble();

                }
            }
        }
        return price;
    }
}