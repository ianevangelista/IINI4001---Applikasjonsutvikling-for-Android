package com.example.oving4;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TeleListFragment extends ListFragment{

    private TeleListListener listener;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (TeleListListener) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(listener != null){
            listener.itemClicked(id);
        }
        super.onListItemClick(l, v, position, id);
    }

    public TeleListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] teletubbies = getResources().getStringArray(R.array.NTNU);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, teletubbies);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    static interface TeleListListener{
        void itemClicked(long id);
    }




}