package com.zbigniew.futuremind.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zbigniew.futuremind.R;
import com.zbigniew.futuremind.activities.MainActivity;
import com.zbigniew.futuremind.adapters.ListAdapter;
import com.zbigniew.futuremind.interfaces.OrdersView;
import com.zbigniew.futuremind.models.OrderModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @BindView(R.id.orders_list)
    RecyclerView ordersRecyclerView;

    private ListAdapter listAdapter;

    private Unbinder unbinder;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listAdapter = new ListAdapter((MainActivity)getActivity());
        ordersRecyclerView.setAdapter(listAdapter);
        return view;
    }

    public void setupView(List<OrderModel> orders) {
        listAdapter.setItems(orders);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
