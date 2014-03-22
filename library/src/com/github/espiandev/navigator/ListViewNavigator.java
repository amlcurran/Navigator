package com.github.espiandev.navigator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ListViewNavigator extends Navigator {

    private final NavigatorAdapter listAdapter;
    private ListView listView;

    public ListViewNavigator(Context context, ListView listView) {
        this.listAdapter = new NavigatorAdapter(context, android.R.layout.simple_list_item_activated_1);
        this.listView = listView;
        this.listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        this.listView.setAdapter(listAdapter);
        this.listView.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onPageSelected(int selectedPage) {
        this.listView.setItemChecked(selectedPage, true);
    }

    @Override
    public void bindNavigationItems(List<CharSequence> labels) {
        listView.setAdapter(listAdapter);
        listAdapter.clear();
        listAdapter.addAll(labels);
    }

    public static class NavigatorAdapter extends ArrayAdapter<CharSequence> {

        public NavigatorAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_activated_1, parent, false);
            }

            ((TextView) convertView).setText(getItem(position));

            return convertView;
        }
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onSelectedItem(position);
        }
    };

}
