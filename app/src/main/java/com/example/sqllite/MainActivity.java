package com.example.sqllite;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView pop_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pop_list = (ListView) findViewById(R.id.list);
        ArrayList<String> uri = new ArrayList<>();
        ArrayList<String> course = new ArrayList<>();


        SQLiteDatabase base = this.openOrCreateDatabase("assignmentdb", MODE_PRIVATE, null);
        base.execSQL("CREATE Table if not exists links (course VARCHAR, uri VARCHAR)");
        base.execSQL("DELETE from links");
        base.execSQL("INSERT INTO links(course, uri) VALUES ('Operating Systems', 'https://elearn.lau.edu.lb/ultra/courses/_48696_1/cl/outline'), ('Software Engineering', 'https://elearn.lau.edu.lb/ultra/courses/_50526_1/cl/outline'),('Discrete Structures 2','https://elearn.lau.edu.lb/ultra/courses/_50201_1/cl/outline')");
        Cursor c = base.rawQuery("SELECT * FROM links", null);
        int i1 = c.getColumnIndex("course");
        int i2 = c.getColumnIndex("uri");
        while (c.moveToNext()) {
            course.add(c.getString(i1));
            uri.add(c.getString(i2));
        }
        ArrayAdapter adapt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,course);
        pop_list.setAdapter(adapt);
        pop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(adapterView.getContext(), WebActivity.class);
                intent.putExtra("uri", uri.get(i));
                startActivity(intent);
            }
        });

    }


}