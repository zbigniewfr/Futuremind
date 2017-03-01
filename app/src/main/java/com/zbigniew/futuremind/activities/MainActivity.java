package com.zbigniew.futuremind.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.zbigniew.futuremind.MyApplication;
import com.zbigniew.futuremind.R;
import com.zbigniew.futuremind.adapters.ListAdapter;
import com.zbigniew.futuremind.fragments.MainActivityFragment;
import com.zbigniew.futuremind.fragments.WebViewActivityFragment;
import com.zbigniew.futuremind.interfaces.OrdersView;
import com.zbigniew.futuremind.models.OrderModel;
import com.zbigniew.futuremind.presenters.OrdersPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements OrdersView, ListAdapter.ActivityCallback {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    OrdersPresenter ordersPresenter;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.fab_progressbar)
    ProgressBar fabProgressBar;

    private MainActivityFragment mainActivityFragment;
    private WebViewActivityFragment webViewFragment;


    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        unbinder = ButterKnife.bind(this);

        ((MyApplication) getApplication()).getOrdersComponent().inject(this);

        mainActivityFragment = (MainActivityFragment)
                getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        webViewFragment = (WebViewActivityFragment) getSupportFragmentManager().findFragmentById(R.id.web_view_fragment);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });

        ordersPresenter.setView(this);

        ordersPresenter.getOrdersFromDatabase();
    }

    private void refresh(){
        fabProgressBar.setVisibility(View.VISIBLE);
        ordersPresenter.getOrders();
    }

    private void onItemsLoadComplete() {
        fabProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setupView(List<OrderModel> orders) {
        onItemsLoadComplete();
        if (!orders.isEmpty()) {
            OrderModel.deleteAll(OrderModel.class);

            for (OrderModel orderModel : orders) {
                orderModel.save();
            }
            setupFromDatabse(orders);
        }
    }

    @Override
    public void setupFromDatabse(List<OrderModel> orders) {
        if (!orders.isEmpty()) {
            mainActivityFragment.setupView(orders);
            if(webViewFragment != null) {
                webViewFragment.setupWebView(orders.get(0).getURL());
            }
        } else {
            refresh();
        }
    }

    @Override
    public void openWebView(String url) {
        if (webViewFragment != null) {
            webViewFragment.setupWebView(url);
        } else {
            Intent i = new Intent(this, WebViewActivity.class);
            i.putExtra("url", url);
            startActivity(i);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ordersPresenter.setView(null);
    }
}
