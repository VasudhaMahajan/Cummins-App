package com.s1.cumminsapp;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UploadFile extends AppCompatActivity implements View.OnClickListener{

    private static final int PICK_IMAGE_REQUEST = 234;
    private ImageView imageView;
    private EditText editText;
    Button buttonChoose,buttonUpload;
    private Uri filepath;
    private StorageReference storageReference;
    private DatabaseReference databasereference;
    public static final String FB_STORAGE_PATH ="image/";
    public static final String FB_DATABASE_PATH ="image";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_file);

        storageReference = FirebaseStorage.getInstance().getReference();
        databasereference= FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        imageView = (ImageView)findViewById(R.id.imgView);
        buttonChoose=(Button)findViewById(R.id.buttonChoose);
        buttonUpload=(Button)findViewById(R.id.buttonUpload);
        editText=(EditText)findViewById(R.id.editText);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);
    }
    private void showFileChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an Image"),PICK_IMAGE_REQUEST);
    }
    private void uploadFile() {
        if (filepath != null) {
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setTitle("Uploading..");
            pd.show();

            StorageReference riversRef = storageReference.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getImageExt(filepath));

            riversRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(),"file uploaded",Toast.LENGTH_LONG).show();
                            ImageUpload imageUpload = new ImageUpload(editText.getText().toString(),taskSnapshot.getDownloadUrl().toString());
                            String uploadId = databasereference.push().getKey();
                            databasereference.child(uploadId).setValue(imageUpload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(),"failure occured",Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            pd.setMessage(((int)progress)+"%uploaded");
                        }
                    });

        }
        else
        {
            Toast.makeText(this,"Please select Image..",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            filepath=data.getData();

            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getImageExt(Uri uri)
    {
        ContentResolver contenResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contenResolver.getType(uri));
    }

    @Override
    public void onClick(View v) {
        if(v==buttonChoose)
        {
            showFileChooser();
        }
        else if(v==buttonUpload)
        {
            uploadFile();
        }
    }
}
