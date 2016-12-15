package com.seu.recen.refreshdemo.ui.common;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

import me.tatarka.bindingcollectionadapter.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter.ItemViewArg;
import me.tatarka.bindingcollectionadapter.factories.BindingRecyclerViewAdapterFactory;

/**
 * Created by zhangrenchen on 2016/12/15.
 */

public class Adapter {
    @BindingAdapter("frescoUrl")
    public static void setFrescoUrl(SimpleDraweeView draweeView, String url) {
        if(!TextUtils.isEmpty(url)){
            if(!url.contains("://")){
                url = "file://" + url;
            }
            Uri uri = Uri.parse(url);

            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri).
                    setProgressiveRenderingEnabled(true).build();
            DraweeController controller = Fresco.newDraweeControllerBuilder().setImageRequest(request).build();
            draweeView.setController(controller);
        }
    }

    @BindingAdapter(value = {"itemView", "items", "adapter", "itemIds", "viewHolder", "header", "footer"}, requireAll = false)
    public static <T> void setAdapter(RecyclerView recyclerView, ItemViewArg<T> arg, List<T> items, BindingRecyclerViewAdapterFactory factory, BindingRecyclerViewAdapter.ItemIds<T> itemIds, BindingRecyclerViewAdapter.ViewHolderFactory viewHolderFactory, View header, View footer) {
        if (arg == null) {
            throw new IllegalArgumentException("itemView must not be null");
        }
        if (factory == null) {
            factory = BindingRecyclerViewAdapterFactory.DEFAULT;
        }
        BindingRecyclerViewAdapter adapter = (BindingRecyclerViewAdapter)recyclerView.getAdapter();
        if (adapter == null) {
//            adapter = factory.create(recyclerView, arg);
            adapter = new BindingRecyclerViewAdapter<>(arg);
            adapter.setItems(items);
            adapter.setItemIds(itemIds);
            adapter.setViewHolderFactory(viewHolderFactory);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
        }
    }
}
