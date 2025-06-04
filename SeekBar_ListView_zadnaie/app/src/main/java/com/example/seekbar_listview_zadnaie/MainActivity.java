package com.example.seekbar_listview_zadnaie;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> settingNames;
    private ArrayList<Integer> settingValues;
    private ArrayList<String> settingUnits;

    private  ArrayList<String> displayItemsForListVie;

    private int selectedItemPosition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView settingsListView=findViewById(R.id.settingsListView);
        TextView editingLabelTextView=findViewById(R.id.editingLabelTextView);
        SeekBar valueSeekBar=findViewById(R.id.valueSeekBar);
        TextView seekBarValueTextView=findViewById(R.id.seekBarValueTextView);

        settingNames=new ArrayList<String>();
        settingNames.add("przezroczystosc");
        settingNames.add("red");
        settingNames.add("green");
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayItemsForListVie);
        settingsListView.setAdapter(adapter);

        settingsListView.setOnItemClickListener((parent, view, position, id) -> {

            selectedItemPosition = position;

            String selectedOptionName = settingNames.get(selectedItemPosition);

            editingLabelTextView.setText("Edytujesz: " + selectedOptionName);


            int selectedValue = settingValues.get(selectedItemPosition);


            valueSeekBar.setProgress(selectedValue);


            seekBarValueTextView.setText("Wartość: " + selectedValue);


            valueSeekBar.setEnabled(true);
        });

        valueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekBarValueTextView.setText("Wartość: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (selectedItemPosition != -1) {

                    int newValue = seekBar.getProgress();


                    settingValues.set(selectedItemPosition, newValue);


                    String updatedText = settingNames.get(selectedItemPosition) + ": " + newValue + settingUnits.get(selectedItemPosition);


                    displayItemsForListVie.set(selectedItemPosition, updatedText);


                    adapter.notifyDataSetChanged();


                    seekBarValueTextView.setText("Wartość: " + newValue);


                    Toast.makeText(getApplicationContext(), "Zmieniono wartość: " + newValue, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}