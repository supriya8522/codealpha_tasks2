public class Stock {
    private String symbol;
    private String companyName;
    private double price;
    private double change;   // % change today

    public Stock(String symbol, String companyName, double price, double change) {
        this.symbol      = symbol;
        this.companyName = companyName;
        this.price       = price;
        this.change      = change;
    }

    public String getSymbol()      { return symbol; }
    public String getCompanyName() { return companyName; }
    public double getPrice()       { return price; }
    public double getChange()      { return change; }

    public void setPrice(double price)   { this.price  = price; }
    public void setChange(double change) { this.change = change; }

    @Override
    public String toString() {
        String arrow = change >= 0 ? "▲" : "▼";
        return String.format("%-6s %-22s $%-9.2f %s %.2f%%",
                symbol, companyName, price, arrow, change);
    }
}
