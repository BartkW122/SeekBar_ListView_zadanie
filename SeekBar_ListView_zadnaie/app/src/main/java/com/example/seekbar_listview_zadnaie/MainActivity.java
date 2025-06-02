package com.example.seekbar_listview_zadnaie;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> settingNames;
    private ArrayList<Integer> settingValues;
    private ArrayList<String> settingUnits;

    private  ArrayList<String> displayItemsForListVie;

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        settingNames=new ArrayList<String>();
        settingNames.add("przezroczystosc");
        settingNames.add("red");
        settingNames.add("gree");
        settingNames.add("blue");
        settingNames.add("jasnosc");
        System.out.println(settingNames);

        settingValues=new ArrayList<Integer>();
        settingValues.add(20);
        settingValues.add(255);
        settingValues.add(30);
        settingValues.add(0);
        settingValues.add(34);
        System.out.println(settingValues);

        settingUnits=new ArrayList<String>();
        settingUnits.add("%");
        settingUnits.add("%");
        settingUnits.add("%");
        settingUnits.add("%");
        settingUnits.add("%");
        System.out.println(settingUnits);

        displayItemsForListVie=new ArrayList<String>();

        for (int i = 0; i < settingNames.size(); i++) {
            String wiersz = settingNames.get(i) + " " + settingValues.get(i) + settingUnits.get(i);
            displayItemsForListVie.add(wiersz);
        }


        displayItemsForListVie.forEach(item->{
            System.out.println(item);
        });

        listView=findViewById(R.id.settingsListView);


        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, R.layout.list_item_setting, displayItemsForListVie);

    }
}