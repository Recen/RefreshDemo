package com.seu.recen.refreshdemo.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seu.recen.refreshdemo.BR;
import com.seu.recen.refreshdemo.R;
import com.seu.recen.refreshdemo.databinding.FragmentRecyclerviewBinding;
import com.seu.recen.refreshdemo.model.RefreshViewModel;
import com.seu.recen.refreshdemo.ui.common.BaseFragment;

import me.tatarka.bindingcollectionadapter.BaseItemViewSelector;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

/**
 * Created by zhangrenchen on 2016/12/15.
 */

public class WithRecyclerView extends BaseFragment {

    private ItemViewSelector<String> itemViewSelector = new BaseItemViewSelector<String>() {
        @Override
        public void select(ItemView itemView, int position, String item) {
            itemView.set(BR.item,R.layout.view_item);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentRecyclerviewBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recyclerview,container,false);
        RefreshViewModel info = new RefreshViewModel();
        info.initData();
        binding.setItem(info);
        return binding.getRoot();
    }
}

