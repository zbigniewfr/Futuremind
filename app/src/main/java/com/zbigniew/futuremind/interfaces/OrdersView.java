package com.zbigniew.futuremind.interfaces;

import com.zbigniew.futuremind.models.OrderModel;

import java.util.List;

/**
 * Created by zbigniew on 28/02/2017.
 */

public interface OrdersView {
    void setupView(List<OrderModel> orders);
    void setupFromDatabse(List<OrderModel> orders);
}
