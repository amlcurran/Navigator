package com.github.espiandev.navigator.sample;

import android.content.Context;
import android.widget.Toast;

import com.github.espiandev.navigator.Navigator;

import java.util.List;

public class ToastNavigator extends Navigator {

    private Context context;

    public ToastNavigator(Context context) {
        this.context = context;
    }

    @Override
    protected void onHostNavigated(int selectedPage) {
        String toast = context.getString(R.string.toast_template, selectedPage);
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindNavigationItems(List<CharSequence> labels) {
        Toast.makeText(context, labels.toString(), Toast.LENGTH_SHORT).show();
    }
}
