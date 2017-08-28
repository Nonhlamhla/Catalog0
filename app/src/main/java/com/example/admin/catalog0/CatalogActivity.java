package com.example.admin.catalog0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    ListView ListViewCatalog;
    List<Catalog> catalogList;

    DatabaseReference databaseCatalog;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        databaseCatalog = FirebaseDatabase.getInstance().getReference("Laptops");
        ListViewCatalog = (ListView)findViewById(R.id.ListViewCatalog);

        catalogList = new ArrayList<>();




        databaseCatalog.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                catalogList.clear();
                for (DataSnapshot catalogSnapshot : dataSnapshot.getChildren()){
                    Catalog catalog = catalogSnapshot.getValue(Catalog.class);
                    catalogList.add(catalog);
                }
                CatalogList adapter = new CatalogList(CatalogActivity.this,catalogList);
                ListViewCatalog.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
}
 }

