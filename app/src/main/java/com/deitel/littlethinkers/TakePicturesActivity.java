package com.deitel.littlethinkers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class TakePicturesActivity extends AppCompatActivity {

    Button btnCapture, btnBack, btnSave;
    ImageView imgCard;

    private static final int REQUEST_PERM_WRITE_STORAGE = 102;
    private static final int CAPTURE_PHOTO = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pictures);

        btnCapture = (Button) findViewById(R.id.btnCapture);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);

        imgCard = (ImageView) findViewById(R.id.imgCard);


        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(TakePicturesActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
                    }
                }

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(TakePicturesActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERM_WRITE_STORAGE);
                }
                else{
                    takePicture();
                }

            }
        });

        MainMenu();
        SaveImage();

    }

    public void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_PHOTO);

    }


    public void onActivityResult(int requestCode, int resultCode, Intent returnIntent){
        super.onActivityResult(requestCode, resultCode, returnIntent);
        if(resultCode == RESULT_OK){
            switch(requestCode){

                case CAPTURE_PHOTO:

                    Bitmap bitmap = (Bitmap) returnIntent.getExtras().get("data");

                    imgCard.setImageBitmap(bitmap);
                    saveImageToGallery(bitmap);

                    break;
                default:
                    break;
            }
        }

    }



    private void saveImageToGallery(Bitmap finalBitmap){
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saveImage");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;

        n = generator.nextInt(n);
        String imageName = "image-" + n + ".jpg";
        File file = new File(myDir, imageName);
        if (file.exists()) file.delete();
        try{
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            String resizeImage = file.getAbsolutePath();
            out.flush();
            out.close();

            Toast.makeText(TakePicturesActivity.this, "Your photo has been saved", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(TakePicturesActivity.this, "Image captured", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(TakePicturesActivity.this);

            builder.setMessage("Image captured");


            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();

        }
    }


    public void MainMenu(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(TakePicturesActivity.this, StudentHomeActivity.class);
                startActivity(menu);
            }
        });
    }

    public void SaveImage(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TakePicturesActivity.this);

                builder.setMessage("Your picture has been saved.");


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }
}
