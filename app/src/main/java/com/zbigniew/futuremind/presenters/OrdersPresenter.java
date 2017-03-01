package com.zbigniew.futuremind.presenters;

import android.util.Log;

import com.zbigniew.futuremind.interfaces.OrdersView;
import com.zbigniew.futuremind.models.OrderModel;
import com.zbigniew.futuremind.models.OrdersResponse;
import com.zbigniew.futuremind.rest.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by zbigniew on 28/02/2017.
 */

public class OrdersPresenter implements Presenter<OrdersView> {
    private static final String TAG = OrdersPresenter.class.getSimpleName();

    private OrdersView ordersView;

    private final Retrofit retrofit;


    @Inject
    public OrdersPresenter(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void setView(OrdersView view) {
        ordersView = view;
    }

    public void getOrders(){
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<OrdersResponse> call = apiService.getOrders();
        call.enqueue(new Callback<OrdersResponse>() {
            @Override
            public void onResponse(Call<OrdersResponse>call, Response<OrdersResponse> response) {
                List<OrderModel> orders = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + orders.size());
                ordersView.setupView(orders);
            }

            @Override
            public void onFailure(Call<OrdersResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("", t.toString());
            }
        });
    }

    public void getOrdersFromDatabase() {
        List<OrderModel> orders = OrderModel.listAll(OrderModel.class);
        ordersView.setupFromDatabse(orders);
    }
}
