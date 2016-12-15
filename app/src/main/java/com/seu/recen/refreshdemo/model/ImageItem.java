package com.seu.recen.refreshdemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.seu.recen.refreshdemo.BR;

/**
 * Created by zhangrenchen on 2016/12/15.
 */

public class ImageItem extends BaseObservable {
    public String imageUrl;
    public String imageDesc;

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @Bindable
    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
        notifyPropertyChanged(BR.imageDesc);
    }

    public ImageItem(String imageUrl, String imageDesc) {
        this.imageUrl = imageUrl;
        this.imageDesc = imageDesc;
    }
}
