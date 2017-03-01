package com.zbigniew.futuremind;

import android.app.Application;

import com.orm.SugarContext;
import com.zbigniew.futuremind.components.DaggerOrdersComponent;
import com.zbigniew.futuremind.components.OrdersComponent;
import com.zbigniew.futuremind.modules.NetModule;

/**
 * Created by zbigniew on 28/02/2017.
 */

public class MyApplication extends Application {

    private OrdersComponent ordersComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        SugarContext.init(getApplicationContext());

        ordersComponent = DaggerOrdersComponent.builder()
                .netModule(new NetModule())
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public OrdersComponent getOrdersComponent() {
        return ordersComponent;
    }
}
