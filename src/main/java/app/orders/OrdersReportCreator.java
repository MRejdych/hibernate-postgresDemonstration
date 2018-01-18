package app.orders;

import app.entities.Order;
import app.entities.OrderDetails;
import app.entities.Product;
import app.utils.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class OrdersReportCreator {

    public static List<OrderReportEntry> createReportFromOrders(List<Order> orders) {
        List<OrderDetails> details = orders.stream()
                .map(Order::getOrderDetails)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<Product> products = details.stream()
                .map(OrderDetails::getProduct)
                .distinct()
                .collect(Collectors.toList());

        Map<Short, Pair<Long, Double>> reportEntires = new HashMap<>();

        for(OrderDetails orderDetails : details) {
            Product p = orderDetails.getProduct();

            Double moneyEarned = orderDetails.getUnitPrice() * (1.0 - orderDetails.getDiscount()) * orderDetails.getQuantity();
            moneyEarned = BigDecimal.valueOf(moneyEarned)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();

            if(reportEntires.containsKey(p.getProductId())){
                Pair<Long, Double> pair = reportEntires.get(p.getProductId());
                Long amount = pair.getLeft();
                Double money = pair.getRight();
                amount += orderDetails.getQuantity();
                money += moneyEarned;
            } else {
                reportEntires.put(p.getProductId(), new Pair<>(Short.toUnsignedLong(orderDetails.getQuantity()), moneyEarned));
            }
        }
        List<OrderReportEntry> productReports = new LinkedList<>();

        for(Product product : products) {
            Short id = product.getProductId();
            String name = product.getProductName();
            Pair<Long, Double> pair = reportEntires.get(id);
            Long unitsSold = pair.getLeft();
            Double moneyEarned = pair.getRight();

            productReports.add(new OrderReportEntry(id, name, unitsSold, moneyEarned));
        }

        return productReports;
    }
}
