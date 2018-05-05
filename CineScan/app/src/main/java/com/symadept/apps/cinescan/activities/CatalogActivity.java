package com.symadept.apps.cinescan.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.symadept.apps.cinescan.R;
import com.symadept.apps.cinescan.adapters.CatalogAdapter;

public class CatalogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        GridView gridview = findViewById(R.id.catalog_gv);
        gridview.setAdapter(new CatalogAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(CatalogActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
