package com.example.dhuro;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

@SuppressWarnings("ALL")
public class Blogs extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);

        initInstances();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.blogs);

        bottomNavigationView.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.home:
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.quickPlay:
                            startActivity(new Intent(getApplicationContext(), QuickPlay.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.puzzles:
                            startActivity(new Intent(getApplicationContext(), Puzzles.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.news:
                            startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.blogs:
                            return true;
                    }
                    return true;
                });

        Button mButtonChooseImage = findViewById(R.id.button_choose_image);
        Button mButtonUpload = findViewById(R.id.button_upload);
        TextView mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);

        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);
        mEditTextFileName = findViewById(R.id.edit_text_file_name);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mButtonChooseImage.setOnClickListener(v -> openFileChooser());

        mButtonUpload.setOnClickListener(v -> {
            if (mUploadTask != null && mUploadTask.isInProgress()) {
                Toast.makeText(Blogs.this, "Upload in progress", Toast.LENGTH_SHORT).show();
            } else {
                uploadFile();
            }
        });

        mTextViewShowUploads.setOnClickListener(v -> openImagesActivity());
    }

    private void openImagesActivity() {
        Intent intent = new Intent(Blogs.this, ImagesActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    private void initInstances() {
        drawerLayout = findViewById(R.id.my_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.nav_settings:
                startActivity(new Intent(getApplicationContext(), Settings.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.nav_about:
                startActivity(new Intent(getApplicationContext(), About.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
        }
        return true;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Glide.with(this)
                    .load(mImageUri)
                    .fitCenter()
                    .centerCrop()
                    .into(mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                        handler.postDelayed(() -> mProgressBar.setProgress(0), 500);

                        Toast.makeText(Blogs.this, "Upload successful", Toast.LENGTH_LONG).show();

                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {


                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            Upload upload = new Upload(mEditTextFileName.getText().toString().trim(),downloadUrl.toString());
                        String uploadId = mDatabaseRef.push().getKey();
                        assert uploadId != null;
                        mDatabaseRef.child(uploadId).setValue(upload);
                        }
                    });
                }
            }).addOnFailureListener(e -> Toast.makeText(Blogs.this, e.getMessage(), Toast.LENGTH_SHORT).show())
                    .addOnProgressListener(taskSnapshot -> {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        mProgressBar.setProgress((int) progress);
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        };}}