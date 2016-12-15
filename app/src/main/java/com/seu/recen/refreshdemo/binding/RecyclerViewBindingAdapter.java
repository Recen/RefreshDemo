package com.seu.recen.refreshdemo.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

import me.tatarka.bindingcollectionadapter.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter.ItemViewArg;
import me.tatarka.bindingcollectionadapter.factories.BindingRecyclerViewAdapterFactory;

/**
 * Created by zhangrenchen on 2016/12/11.
 */

public class RecyclerViewBindingAdapter {
    @BindingAdapter("scrollToPosition")
    public static void scrollToPosition(RecyclerView recyclerView, int position){
        recyclerView.scrollToPosition(position);
    }

    @BindingAdapter(value = {"itemView", "items", "adapter", "itemIds", "viewHolder", "header", "footer"}, requireAll = false)
    public static <T> void setAdapter(RecyclerView recyclerView, ItemViewArg<T> arg, List<T> items, BindingRecyclerViewAdapterFactory factory, BindingRecyclerViewAdapter.ItemIds<T> itemIds, BindingRecyclerViewAdapter.ViewHolderFactory viewHolderFactory, View header, View footer) {
        if (arg == null) {
            throw new IllegalArgumentException("itemView must not be null");
        }
        if (factory == null) {
            factory = BindingRecyclerViewAdapterFactory.DEFAULT;
        }

        HeaderViewRecyclerViewAdapter<T> adapter = (HeaderViewRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter == null) {
//            adapter = factory.create(recyclerView, arg);
            adapter = new HeaderViewRecyclerViewAdapter<>(arg);
            adapter.setItems(items);
            adapter.setItemIds(itemIds);
            adapter.setViewHolderFactory(viewHolderFactory);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
        }
        if (header != null) {
            ViewParent parent = header.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(header);
            }
            adapter.setHeaderView(header);
        }
        if (footer != null) {
            ViewParent parent = footer.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(footer);
            }
            adapter.setFooterView(footer);
        }
    }
}
