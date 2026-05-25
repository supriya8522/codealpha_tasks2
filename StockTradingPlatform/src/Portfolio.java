import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private String ownerName;
    private double cash;
    private Map<String, Integer> holdings = new HashMap<>();  // symbol -> quantity
    private double totalInvested = 0;

    public Portfolio(String ownerName, double startingCash) {
        this.ownerName = ownerName;
        this.cash      = startingCash;
    }

    public String getOwnerName() { return ownerName; }
    public double getCash()      { return cash; }
    public Map<String, Integer> getHoldings() { return holdings; }

    public boolean buyStock(Stock stock, int qty) {
        double cost = stock.getPrice() * qty;
        if (cost > cash) return false;
        cash -= cost;
        totalInvested += cost;
        holdings.merge(stock.getSymbol(), qty, Integer::sum);
        return true;
    }

    public boolean sellStock(Stock stock, int qty) {
        int owned = holdings.getOrDefault(stock.getSymbol(), 0);
        if (qty > owned) return false;
        cash += stock.getPrice() * qty;
        if (owned - qty == 0) holdings.remove(stock.getSymbol());
        else holdings.put(stock.getSymbol(), owned - qty);
        return true;
    }

    public double getPortfolioValue(Map<String, Stock> market) {
        double val = cash;
        for (Map.Entry<String, Integer> e : holdings.entrySet()) {
            Stock s = market.get(e.getKey());
            if (s != null) val += s.getPrice() * e.getValue();
        }
        return val;
    }

    public double getTotalInvested() { return totalInvested; }
}
