package com.seu.recen.refreshdemo.binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.tatarka.bindingcollectionadapter.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter.ItemViewArg;

/**
 * Created by zhangrenchen on 2016/12/11.
 */
public class HeaderViewRecyclerViewAdapter<T> extends BindingRecyclerViewAdapter<T> {
    public static final int VIEW_TYPE_HEADER = 1;
    public static final int VIEW_TYPE_FOOTER = 2;

    private View headerView;
    private View footerView;

    // 为了提升性能用该变量保存header和footer的数量，该变量只允许updateExtendCount函数进行修改
    private int extendCount = 0;

    public HeaderViewRecyclerViewAdapter(@NonNull ItemViewArg<T> arg) {
        super(arg);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + extendCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView != null && position == 0) {
            return VIEW_TYPE_HEADER;
        } else if (footerView != null && position == getItemCount()) {
            return VIEW_TYPE_FOOTER;
        }
        return super.getItemViewType(getRealPosition(position));
    }

    @Override
    public T getAdapterItem(int position) {
        if (headerView != null && position == 0) {
            return null;
        } else if (footerView != null && position == getItemCount()) {
            return null;
        }
        return super.getAdapterItem(getRealPosition(position));
    }

    @SuppressWarnings("ResourceType")
    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, int layoutId, ViewGroup viewGroup) {
        if (layoutId == VIEW_TYPE_HEADER) {
            return new HeaderViewDataBinding(headerView, VIEW_TYPE_HEADER);
        } else if (layoutId == VIEW_TYPE_FOOTER) {
            return new HeaderViewDataBinding(footerView, VIEW_TYPE_FOOTER);
        }
        return DataBindingUtil.inflate(inflater, layoutId, viewGroup, false);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewDataBinding binding) {
        if (binding instanceof HeaderViewDataBinding) {
            HeaderViewDataBinding headerBinding = (HeaderViewDataBinding) binding;
            if (headerBinding.ViewType == VIEW_TYPE_HEADER) {
                return new RecyclerView.ViewHolder(headerView) {
                };
            } else if (headerBinding.ViewType == VIEW_TYPE_FOOTER) {
                return new RecyclerView.ViewHolder(footerView) {
                };
            }
        }

        return super.onCreateViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if (headerView != null && position == 0) {
            return;
        }
        if (footerView != null && position == getItemCount()) {
            return;
        }
        super.onBindViewHolder(holder, getRealPosition(position), payloads);
    }

    @Override
    public long getItemId(int position) {
        if (headerView != null && position == 0) {
            return VIEW_TYPE_HEADER;
        }
        if (footerView != null && position == getItemCount()) {
            return VIEW_TYPE_FOOTER;
        }
        return super.getItemId(position);
    }

    public void setHeaderView(View view) {
        headerView = view;
        updateExtendCount();
    }

    public void setFooterView(View view) {
        footerView = view;
        updateExtendCount();
    }

    private void updateExtendCount() {
        extendCount = 0;
        extendCount += headerView != null ? 1 : 0;
        extendCount += footerView != null ? 1 : 0;
    }

    private int getRealPosition(int position) {
        if (headerView != null) {
            return position - 1;
        }
        return position;
    }

    public static class HeaderViewDataBinding extends ViewDataBinding {
        public final int ViewType;

        public HeaderViewDataBinding(View view, int viewType) {
            super(null, view, 0);
            ViewType = viewType;
        }

        @Override
        protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
            return false;
        }

        @Override
        public boolean setVariable(int variableId, Object value) {
            return false;
        }

        @Override
        protected void executeBindings() {

        }

        @Override
        public void invalidateAll() {

        }

        @Override
        public boolean hasPendingBindings() {
            return false;
        }
    }
}

