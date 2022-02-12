package com.prajjwal.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.userEdit);
        Button button = findViewById(R.id.userButton);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("data", "Buy groceries");
        editor.putString("data2", "Buy candy");
        editor.putString("data3", "Buy groceries");
        editor.putString("data4", "Buy candy");

        editor.commit();

        data.add(sharedPreferences.getString("data", null));
        data.add(sharedPreferences.getString("data2", null));
        data.add(sharedPreferences.getString("data3", null));
        data.add(sharedPreferences.getString("data4", null));
        
        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = editText.getText().toString();

                if(message.isEmpty()) {

                    Toast.makeText(MainActivity.this, "Please input some text", Toast.LENGTH_SHORT).show();

                } else {

                    data.add(message);
                    editor.putString("data5", message).commit();
                    adapter.notifyDataSetChanged();
                    editText.setText("");

                }


            }
        });

    }
}