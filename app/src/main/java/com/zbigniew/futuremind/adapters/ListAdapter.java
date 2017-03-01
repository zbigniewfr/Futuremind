package com.zbigniew.futuremind.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zbigniew.futuremind.R;
import com.zbigniew.futuremind.models.OrderModel;
import com.zbigniew.futuremind.utils.PicassoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ossek on 2016-10-16.
 */


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.OrderViewHolder> {

    public interface ActivityCallback {
        public void openWebView(String url);
    }

    private ActivityCallback activityCallback;
    private List<OrderModel> orders = new ArrayList<>();


    public ListAdapter(ActivityCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_template, null);

        OrderViewHolder viewHolder = new OrderViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        final OrderModel order = orders.get(position);
        holder.title.setText(order.getTitle());
        holder.description.setText(order.getDescription());
        holder.date.setText(order.getDateString());
        PicassoUtil.loadPicture(holder.picture.getContext(), order.getImageUrl(), holder.picture);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityCallback.openWebView(order.getURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setItems(List<OrderModel> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.container)
        LinearLayout container;
        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.description)
        public TextView description;
        @BindView(R.id.date)
        public TextView date;
        @BindView(R.id.picture)
        public ImageView picture;

        public OrderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
