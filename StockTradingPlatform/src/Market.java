import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Market {
    private Map<String, Stock> stocks = new LinkedHashMap<>();
    private Random rand = new Random();

    public Market() {
        stocks.put("AAPL",  new Stock("AAPL",  "Apple Inc.",           178.50,  1.25));
        stocks.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.",        140.20, -0.80));
        stocks.put("MSFT",  new Stock("MSFT",  "Microsoft Corp.",      415.00,  0.60));
        stocks.put("TSLA",  new Stock("TSLA",  "Tesla Inc.",           175.30, -2.10));
        stocks.put("AMZN",  new Stock("AMZN",  "Amazon.com Inc.",      185.90,  1.50));
        stocks.put("NVDA",  new Stock("NVDA",  "NVIDIA Corporation",   875.00,  3.20));
        stocks.put("META",  new Stock("META",  "Meta Platforms Inc.",  490.00, -0.40));
        stocks.put("NFLX",  new Stock("NFLX",  "Netflix Inc.",         610.00,  0.90));
    }

    public Map<String, Stock> getStocks() { return stocks; }

    public Stock getStock(String symbol) { return stocks.get(symbol.toUpperCase()); }

    // Simulate price fluctuation
    public void simulateMarket() {
        for (Stock s : stocks.values()) {
            double fluctuation = (rand.nextDouble() * 6) - 3; // -3% to +3%
            double newPrice = s.getPrice() * (1 + fluctuation / 100);
            newPrice = Math.max(newPrice, 1.0);
            s.setChange(Math.round(fluctuation * 100.0) / 100.0);
            s.setPrice(Math.round(newPrice * 100.0) / 100.0);
        }
    }
}
