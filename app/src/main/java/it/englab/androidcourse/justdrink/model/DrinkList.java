package it.englab.androidcourse.justdrink.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Peppe on 07/02/2017.
 */

public class DrinkList {

    @SerializedName("drinks")
    @Expose
    private List<Drink> drinks;

    public List<Drink> getDrinks() {
        return drinks;
    }
}