package ru.tvs.app.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.tvs.app.R;
import ru.tvs.app.net.Stock;

/**
 * Created by vladimir on 17.01.18.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.value_name)
    TextView valueName;

    @BindView(R.id.value_volume)
    TextView valueVolume;

    @BindView(R.id.value_amount)
    TextView valueAmount;

    MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void init(Stock st) {

        valueName.setText(st.name.trim());
        valueVolume.setText(st.volume + "");
        valueAmount.setText(st.getAmount());
        if (!st.getIsPositiv())
            valueAmount.setTextColor(Color.RED);
        else
            valueAmount.setTextColor(Color.GRAY);
    }
}
