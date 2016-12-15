package com.seu.recen.refreshdemo.ui.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

/**
 * Created by zhangrenchen on 2016/12/15.
 */

public class GenericFragmentActivity extends FragmentActivity{
    private static final String KEY_FRAGMENT_CLASS = "KEY_FRAGMENT_CLASS";
    private static final String KEY_FRAGMENT_ARGS = "KEY_FRAGMENT_ARGS";

    private Fragment currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle args = getIntent().getExtras();

        super.onCreate(savedInstanceState);
        if(isFinishing()){
            return;
        }

        String fragmentClassName = args.getString(KEY_FRAGMENT_CLASS);

        try {
            Fragment fragment = (Fragment) Class.forName(fragmentClassName).newInstance();
            Bundle argument = args.getBundle(KEY_FRAGMENT_ARGS);
            fragment.setArguments(argument);

            attachFragment(getSupportFragmentManager(), fragment);
        } catch (Exception e) {
            throw new IllegalStateException("Has error in new instance of fragment");
        }
    }

    private void attachFragment(FragmentManager supportFragmentManager, Fragment fragment) {
        currentFragment = fragment;
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.add(android.R.id.content, fragment);
        transaction.commitAllowingStateLoss();
    }

    public static void start(Activity from, Class<?> fragmentClass, Bundle args) {
        Intent intent = new Intent(from, GenericFragmentActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass.getCanonicalName());
        intent.putExtra(KEY_FRAGMENT_ARGS, args);
        from.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
