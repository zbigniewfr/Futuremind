package com.zbigniew.futuremind.rest;

import com.zbigniew.futuremind.models.OrdersResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zbigniew on 28/02/2017.
 */

public interface ApiInterface {

    @GET("test35/")
    Call<OrdersResponse> getOrders();
}
