package ru.tvs.app.net;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by vladimir on 17.01.18.
 */

public class Stock {
    @SerializedName("name")
    public String name;
    @SerializedName("volume")
    public int volume;
    @SerializedName("price")
    public Price price;

    public String getAmount() {

        BigDecimal result = new BigDecimal(price.amount).setScale(2, RoundingMode.HALF_UP);
        return  result.toString();
    }

    public boolean getIsPositiv() {
        BigDecimal result = new BigDecimal(price.amount).setScale(2, RoundingMode.HALF_UP);
        int val = result.compareTo(BigDecimal.ZERO);
       return  val == 1;
    }
}
