import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Market market = new Market();

        System.out.print("\n  Enter your name: ");
        String name = sc.nextLine().trim();
        Portfolio portfolio = new Portfolio(name.isEmpty() ? "Trader" : name, 10000.00);

        Display.header();
        System.out.printf("  Welcome, %s! Starting cash: $10,000.00%n", portfolio.getOwnerName());

        boolean running = true;
        while (running) {
            Display.menu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    Display.marketData(market);
                    break;

                case "2":
                    Display.marketData(market);
                    System.out.print("  Enter stock symbol to BUY: ");
                    String buySymbol = sc.nextLine().trim().toUpperCase();
                    Stock buyStock = market.getStock(buySymbol);
                    if (buyStock == null) { System.out.println("  Symbol not found."); break; }
                    System.out.printf("  Price: $%.2f  |  Your cash: $%.2f%n",
                            buyStock.getPrice(), portfolio.getCash());
                    System.out.print("  Enter quantity: ");
                    try {
                        int qty = Integer.parseInt(sc.nextLine().trim());
                        if (qty <= 0) { System.out.println("  Quantity must be positive."); break; }
                        if (portfolio.buyStock(buyStock, qty)) {
                            System.out.printf("  Bought %d shares of %s. Remaining cash: $%.2f%n",
                                    qty, buySymbol, portfolio.getCash());
                        } else {
                            System.out.println("  Insufficient funds!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("  Invalid quantity.");
                    }
                    break;

                case "3":
                    Display.portfolio(portfolio, market);
                    System.out.print("  Enter stock symbol to SELL: ");
                    String sellSymbol = sc.nextLine().trim().toUpperCase();
                    Stock sellStock = market.getStock(sellSymbol);
                    if (sellStock == null) { System.out.println("  Symbol not found."); break; }
                    System.out.print("  Enter quantity: ");
                    try {
                        int qty = Integer.parseInt(sc.nextLine().trim());
                        if (qty <= 0) { System.out.println("  Quantity must be positive."); break; }
                        if (portfolio.sellStock(sellStock, qty)) {
                            System.out.printf("  Sold %d shares of %s. Cash: $%.2f%n",
                                    qty, sellSymbol, portfolio.getCash());
                        } else {
                            System.out.println("  Not enough shares to sell!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("  Invalid quantity.");
                    }
                    break;

                case "4":
                    Display.portfolio(portfolio, market);
                    break;

                case "5":
                    market.simulateMarket();
                    System.out.println("  Market prices refreshed!");
                    Display.marketData(market);
                    break;

                case "6":
                    running = false;
                    System.out.println("\n  Goodbye, " + portfolio.getOwnerName() + "! Happy trading!\n");
                    break;

                default:
                    System.out.println("  Invalid choice. Enter 1-6.");
            }
        }
        sc.close();
    }
}
