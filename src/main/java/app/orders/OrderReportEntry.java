package app.orders;


public class OrderReportEntry {
    public final Short productId;
    public final String productName;
    public final Long unitsSold;
    public final Double moneyEarned;

    public OrderReportEntry(Short productId, String productName, Long unitsSold, Double moneyEarned) {
        this.productId = productId;
        this.productName = productName;
        this.unitsSold = unitsSold;
        this.moneyEarned = moneyEarned;
    }
}
