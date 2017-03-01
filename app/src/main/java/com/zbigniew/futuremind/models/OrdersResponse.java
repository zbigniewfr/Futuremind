package com.zbigniew.futuremind.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zbigniew on 28/02/2017.
 */

public class OrdersResponse {
    @SerializedName("data")
    private List<OrderModel> results;

    public List<OrderModel> getResults() {
        return results;
    }

    public void setResults(List<OrderModel> results) {
        this.results = results;
    }
}
