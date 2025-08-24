package com.example.impati.model.client;

import com.example.impati.model.OrderDetail;

public interface OrderClient {

    boolean hasOrderHistoryB(String memberNumber);

    OrderDetail getOrderDetailB(String orderNumber);
}
