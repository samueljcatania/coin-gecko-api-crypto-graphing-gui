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
}