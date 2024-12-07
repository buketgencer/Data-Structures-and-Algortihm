public class Stock { // Stock class holds the stock information such as symbol, price, volume, and market cap
    private String symbol; 
    private double price;
    private long volume;
    private long marketCap;

    // Constructor for Stock
    public Stock(String symbol, double price, long volume, long marketCap) { 
        this.symbol = symbol;
        this.price = price;
        this.volume = volume;
        this.marketCap = marketCap;
    }

    // Getters and Setters

    //get symbol of stock
    public String getSymbol() {
        return symbol;
    }

    //set symbol of stock
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    //get price of stock
    public double getPrice() {
        return price;
    }

    //set price of stock
    public void setPrice(double price) {
        this.price = price;
    }

    //get volume of stock
    public long getVolume() {
        return volume;
    }

    //set volume of stock
    public void setVolume(long volume) {
        this.volume = volume;
    }

    //get market cap of stock
    public long getMarketCap() {
        return marketCap;
    }

    //set market cap of stock
    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    @Override // Override the toString method to print out the stock information in a readable format
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", marketCap=" + marketCap +
                '}';
    }
}
