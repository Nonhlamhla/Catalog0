package com.example.admin.catalog0;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CatalogAdmin extends AppCompatActivity {

    EditText editTextTitle ;
    Button btnAdd;
    ImageButton IBAdmin;


    //IMAGE
    Uri uri;
    private static final int GALLERY_INTENT = 1;

    //FIREBASE CONNECTION
    private DatabaseReference databaseLaptops;
    private StorageReference mStorageReference;

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog_admin);

        databaseLaptops = FirebaseDatabase.getInstance().getReference("Laptops");
        mStorageReference = FirebaseStorage.getInstance().getReference();

        IBAdmin = (ImageButton) findViewById(R.id.IBAdmin);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        mDialog = new ProgressDialog(this);


        IBAdmin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //IMAGE
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddAsset();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            uri = data.getData();
            IBAdmin.setImageURI(uri);

        }
    }


    private void AddAsset() {
        final String title = editTextTitle.getText().toString().trim();


        if (!TextUtils.isEmpty(title)) {

            mDialog.setMessage("please wait ...");
            mDialog.show();


            Log.i("T",uri.toString());
            StorageReference filePath = mStorageReference.child("LaptopsImages").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri imageUri = taskSnapshot.getDownloadUrl();


                    //CREATES A NEW UNIQUE ID IN DATABASE
                    String id = databaseLaptops.push().getKey();
                    Catalog restaurant = new Catalog(id, title, imageUri.toString());
                    databaseLaptops.child(id).setValue(restaurant);
                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Done Uploading ...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), CatalogActivity.class);
                    startActivity(intent);

                }
            });
        } else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }

    }
}



