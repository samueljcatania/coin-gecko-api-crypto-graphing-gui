import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/**
 * This class communicates with the CoinGecko API to fetch cryptocoin prices
 *
 * @author Provided in CS2212 Project Description
 */
public class DataFetcher {
    private JsonObject getDataForCrypto(String id, String date) {
        String urlString = String.format(
                "https://api.coingecko.com/api/v3/coins/%s/history?date=%s", id, date);
        try {
            URL url = new URL(urlString);
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
                return new JsonParser().parse(inline.toString()).getAsJsonObject();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with the API call.");
        }
        return null;
    }

    public double getPriceForCoin(String id, String date) {
        double price = 0.0;
        JsonObject jsonObject = getDataForCrypto(id, date);
        if (jsonObject != null) {
            JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
            JsonObject currentPrice = marketData.get("current_price").getAsJsonObject();
            price = currentPrice.get("cad").getAsDouble();
        }
        return price;
    }

    public double getMarketCapForCoin(String id, String date) {
        double marketCap = 0.0;
        JsonObject jsonObject = getDataForCrypto(id, date);
        if (jsonObject != null) {
            JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
            JsonObject currentPrice = marketData.get("market_cap").getAsJsonObject();
            marketCap = currentPrice.get("cad").getAsDouble();
        }
        return marketCap;
    }

    public double getVolumeForCoin(String id, String date) {
        double volume = 0.0;
        JsonObject jsonObject = getDataForCrypto(id, date);
        if (jsonObject != null) {
            JsonObject marketData = jsonObject.get("market_data").getAsJsonObject();
            JsonObject currentPrice = marketData.get("total_volume").getAsJsonObject();
            volume = currentPrice.get("cad").getAsDouble();
        }
        return volume;
    }

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        DataFetcher fetcher = new DataFetcher();
        double price = fetcher.getPriceForCoin("ethereum", dateFormat.format(date));
        double marketCap = fetcher.getMarketCapForCoin("ethereum", dateFormat.format(date));
        double volume = fetcher.getVolumeForCoin("ethereum", dateFormat.format(date));
        System.out.println("ethereum=>\tPrice: " + price +
                "\n\t\tMarket Cap: " + marketCap +
                "\n\t\tVolume: " + volume);
    }
}