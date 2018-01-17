package app.service;

import app.dao.OrdersDAO;
import app.entities.Order;
import app.utils.DatabaseUtils;

public class OrdersService {

    public void getDetailsOfOrder(short orderId){
        OrdersDAO dao = new OrdersDAO(new DatabaseUtils());

    }
}
