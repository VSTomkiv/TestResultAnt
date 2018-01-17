package ru.tvs.app.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vladimir on 17.01.18.
 */

public class ResponseData {
    @SerializedName("stock")
    public List<Stock> stock;

}
