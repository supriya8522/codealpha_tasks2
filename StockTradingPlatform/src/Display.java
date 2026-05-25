import java.util.Map;

public class Display {
    private static final String BORDER  = "=".repeat(65);
    private static final String DIVIDER = "-".repeat(65);

    public static void header() {
        System.out.println("\n" + BORDER);
        System.out.println("          STOCK TRADING PLATFORM  (Simulated)");
        System.out.println(BORDER);
    }

    public static void menu() {
        System.out.println("\n" + DIVIDER);
        System.out.println("  MENU");
        System.out.println(DIVIDER);
        System.out.println("  1. View Market Data");
        System.out.println("  2. Buy Stock");
        System.out.println("  3. Sell Stock");
        System.out.println("  4. View Portfolio");
        System.out.println("  5. Refresh Market Prices");
        System.out.println("  6. Exit");
        System.out.println(DIVIDER);
        System.out.print("  Enter choice: ");
    }

    public static void marketData(Market market) {
        System.out.println("\n" + BORDER);
        System.out.printf("  %-6s %-22s %-11s %s%n", "SYMBOL", "COMPANY", "PRICE", "CHANGE");
        System.out.println(DIVIDER);
        for (Stock s : market.getStocks().values()) {
            System.out.println("  " + s);
        }
        System.out.println(BORDER);
    }

    public static void portfolio(Portfolio portfolio, Market market) {
        System.out.println("\n" + BORDER);
        System.out.println("  PORTFOLIO  —  " + portfolio.getOwnerName());
        System.out.println(DIVIDER);
        System.out.printf("  Available Cash : $%.2f%n", portfolio.getCash());
        System.out.println(DIVIDER);

        Map<String, Integer> holdings = portfolio.getHoldings();
        if (holdings.isEmpty()) {
            System.out.println("  No stocks owned yet.");
        } else {
            System.out.printf("  %-8s %-6s %-12s %-12s%n", "SYMBOL", "QTY", "PRICE", "VALUE");
            System.out.println(DIVIDER);
            for (Map.Entry<String, Integer> e : holdings.entrySet()) {
                Stock s = market.getStock(e.getKey());
                double value = s.getPrice() * e.getValue();
                System.out.printf("  %-8s %-6d $%-11.2f $%-11.2f%n",
                        e.getKey(), e.getValue(), s.getPrice(), value);
            }
        }

        System.out.println(DIVIDER);
        double totalVal = portfolio.getPortfolioValue(market.getStocks());
        System.out.printf("  Total Portfolio Value : $%.2f%n", totalVal);
        System.out.println(BORDER);
    }
}
