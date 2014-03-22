package com.github.espiandev.navigator.sample;

import android.content.Context;
import android.widget.Toast;

import com.github.espiandev.navigator.Navigator;

public class ToastNavigator implements Navigator {

    private Context context;

    public ToastNavigator(Context context) {
        this.context = context;
    }

    @Override
    public void onPageSelected(int selectedPage) {
        String toast = context.getString(R.string.toast_template, selectedPage);
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}
