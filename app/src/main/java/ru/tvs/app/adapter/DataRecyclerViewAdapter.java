package ru.tvs.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.tvs.app.R;
import ru.tvs.app.net.Stock;

/**
 * Created by vladimir on 17.01.18.
 */

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    public List<Stock> stock;

    public DataRecyclerViewAdapter(List<Stock> stock) {
        this.stock = stock;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.init(stock.get(position));
    }

    @Override
    public int getItemCount() {
        return stock.size();
    }


}
