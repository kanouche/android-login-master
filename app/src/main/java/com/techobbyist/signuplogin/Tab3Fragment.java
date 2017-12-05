package com.techobbyist.HouseHold;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ListAdapter;
import android.support.v7.app.AppCompatActivity;




import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ramzy on 2017-11-26.
 */

public class Tab3Fragment extends Fragment  {
    private static final String TAG = "Tab3Fragment";

    ArrayList<String> menuProfiles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);




        fillList();


        ListView listview = (ListView) view.findViewById(R.id.peopleListView);

        ArrayAdapter<String> listViewAdapter= new ArrayAdapter<String> (getActivity(), android.R.layout.simple_list_item_1, menuProfiles);
        listview.setAdapter(listViewAdapter);
        return view;

    }

    public void fillList(){
        DataBaseHelper db = new DataBaseHelper(getActivity());
        menuProfiles=db.getAllUserNames();

    }


}
