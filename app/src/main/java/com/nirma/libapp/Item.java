package com.nirma.libapp;

import android.graphics.Bitmap;

/**
 * Created by Mohit on 30-08-2016.
 */
public class Item {
    Bitmap res;
    String title;

    public Item(){

    }

    public Item(Bitmap res, String title) {
        this.res = res;
        this.title = title;
    }

    public Bitmap getRes() {
        return res;
    }

    public String getTitle() {
        return title;
    }

    public void setRes(Bitmap res) {
        this.res = res;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
