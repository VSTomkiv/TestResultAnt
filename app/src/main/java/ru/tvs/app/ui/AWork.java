package ru.tvs.app.ui;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.tvs.app.App;
import ru.tvs.app.Injector;
import ru.tvs.app.R;
import ru.tvs.app.adapter.DataRecyclerViewAdapter;
import ru.tvs.app.net.ResponseData;
import ru.tvs.app.net.Stock;

public class AWork extends AppCompatActivity {
    @BindView(R.id.work_view)
    View workView;

    @BindView(R.id.upd_data)
    ImageView icUpdData;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private Timer timerRefresher;
    private Unbinder unbinder;
    private ViewPropertyAnimatorCompat animateIcUpdData;
    public List<Stock> stock;
    private DataRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.app.activate();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_work);
        unbinder = ButterKnife.bind(this);
     //   showWorkProgress();
        if (stock == null)
            stock = new ArrayList<>();

        adapter = new DataRecyclerViewAdapter(stock);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(itemAnimator);

    }

    @OnClick(R.id.upd_data)
    public void updDataTables() {
        initAnimeIcUpdData();
        getNetData();
    }

    @BindView(R.id.heder_tables)
    View hederTables;
    @BindView(R.id.work_progress)
    View workProgress;

    private void showWorkProgress(){
        workProgress.setVisibility(View.VISIBLE);
    }
    private void hideWorkProgress(){
        workProgress.setVisibility(View.GONE);
    }
    private void getNetData() {
        Injector.getRestApi().findResponseData(new Callback<ResponseData>() {
            @Override
            public void success(ResponseData responseData, Response response) {
                if (isFinishing())
                    return;

                initUIList(responseData.stock);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void initUIList(List<Stock> stock) {
        if (stock == null)
            return;
        workView.setVisibility(View.VISIBLE);
        hederTables.setVisibility(View.VISIBLE);
        adapter.stock = stock;
        adapter.notifyDataSetChanged();
     //   hideWorkProgress();
    }

    private void initAnimeIcUpdData() {
        animateIcUpdData = ViewCompat.animate(icUpdData);
        icUpdData.setRotation(0);
        animateIcUpdData.setDuration(500).rotation(360f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updDataTables();
        timerUpd();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timerRefresher == null)
            return;
        if (animateIcUpdData != null)
            animateIcUpdData.cancel();
        timerRefresher.cancel();
     //   hideWorkProgress();
    }

    @Override
    protected void onDestroy() {
        App.app.finish();
        if (unbinder != null)
            unbinder.unbind();
        super.onDestroy();
    }

    private void timerUpd() {
        timerRefresher = new Timer();
        timerRefresher.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updDataTables();
                    }
                });
            }
        }, 15000, 15000);
    }
}
