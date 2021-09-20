package com.s1.cumminsapp;
import android.app.ProgressDialog;
import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageListActivity extends AppCompatActivity {

    private DatabaseReference mdatabase;
    private List<ImageUpload> imglist;
    private ListView lv;

    PhotoViewAttacher photoViewAttacher;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        imglist = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listimgView);
//        photoViewAttacher = new PhotoViewAttacher(lv);

        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("pls wait loading list image...");
        progressDialog.show();

        mdatabase = FirebaseDatabase.getInstance().getReference(UploadFile.FB_DATABASE_PATH);
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ImageUpload img = snapshot.getValue(ImageUpload.class);
                    imglist.add(img);
                }

                adapter = new ImageListAdapter(ImageListActivity.this, R.layout.image_item, imglist);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }
}

