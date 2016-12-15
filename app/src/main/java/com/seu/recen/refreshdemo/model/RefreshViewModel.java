package com.seu.recen.refreshdemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.seu.recen.refreshdemo.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangrenchen on 2016/12/15.
 */

public class RefreshViewModel extends BaseObservable {
    private ArrayList<ImageItem> imageList = new ArrayList<>();

    @Bindable
    public ArrayList<ImageItem> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<ImageItem> imageList) {
        this.imageList = imageList;
        notifyPropertyChanged(BR.imageList);
    }

    public void initData(){
        ArrayList<ImageItem> items = new ArrayList<>();
        for (int i = 0;i< Images.imageUrls.length;i++){
            ImageItem item = new ImageItem(Images.imageUrls[i],"第"+i+"张示例图片");
            items.add(item);
        }
        setImageList(items);
    }
}
