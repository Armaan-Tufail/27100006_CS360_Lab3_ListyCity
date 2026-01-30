package com.example.listycity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        Button addCityButton = findViewById(R.id.add_city_button);
        Button removeCityButton = findViewById(R.id.remove_city_button);
        LinearLayout addCityLayout = findViewById(R.id.add_city_layout);
        EditText addCityEditText = findViewById(R.id.add_city_edittext);
        Button confirmButton = findViewById(R.id.confirm_button);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<String>(this, R.layout.content, dataList)
        {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
            {

                View view = super.getView(position, convertView, parent);

                if (position == selectedPosition)
                {
                    view.setBackgroundColor(Color.DKGRAY);
                }
                else
                {
                    view.setBackgroundColor(Color.TRANSPARENT);
                }
                return view;

            }

        };

        cityList.setAdapter(cityAdapter);

        addCityButton.setOnClickListener(v -> addCityLayout.setVisibility(View.VISIBLE));

        confirmButton.setOnClickListener(v ->
        {

            String newCity = addCityEditText.getText().toString();
            if (!newCity.isEmpty())
            {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                addCityEditText.setText("");
                addCityLayout.setVisibility(View.GONE);
            }

        });

        cityList.setOnItemClickListener((parent, view, position, id) ->
        {

            if (selectedPosition == position)
            {
                selectedPosition = -1;
            }
            else
            {
                selectedPosition = position;
            }
            cityAdapter.notifyDataSetChanged();

        });

        removeCityButton.setOnClickListener(v ->
        {

            if (selectedPosition != -1)
            {
                dataList.remove(selectedPosition);
                selectedPosition = -1;
                cityAdapter.notifyDataSetChanged();
            }

        });

    }

}
