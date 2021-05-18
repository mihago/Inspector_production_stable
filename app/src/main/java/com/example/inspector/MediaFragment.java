package com.example.inspector;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MediaFragment extends Fragment {
    ExecutorService mImageCaptureExecutorService;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private final int MAX_AMOUNT_OF_PHOTOS=3;
    ImageCapture imageCapture;
    RecyclerView MediaF_photos;
    final int CAMERA_PERMISSION=150;
    ArrayList<Bitmap> photoarray=new ArrayList<Bitmap>();
    Preview preview;
    Button MediaF_next;
    Camera camera;
    CameraSelector cameraSelector;
    PreviewView previewView;
    ProcessCameraProvider cameraProvider;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_TAKE_PHOTO = 3;
    private static final int RESULT_OK = -1;
    private ImageView imageView;
    private Button addPhoto;
    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    public MediaFragment() {
        // Required empty public constructor
    }



    public static MediaFragment newInstance(String param1, String param2) {
        MediaFragment fragment = new MediaFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);




    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);






        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Toast.makeText(getContext(),"Слишком много фотографий",Toast.LENGTH_LONG);
            Log.d("gfg","1");
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            photoarray.add(imageBitmap);
          MediaF_next.setText("ggtg");



        }
        else{

        }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_media, container, false);


        MediaF_next = (Button) v.findViewById(R.id.MediaF_next);
         MediaF_photos = (RecyclerView) v.findViewById(R.id.MediaF_photos) ;
         GridLayoutManager glm;
         glm=new GridLayoutManager(getActivity(),2,GridLayoutManager.HORIZONTAL,false);





        MediaF_photos.setLayoutManager(glm);

        MediaF_photos.setAdapter(new CustomAdapter(photoarray));










        addPhoto = v.findViewById(R.id.addPhoto);
        addPhoto.setOnClickListener(v4->{
          int request_code =ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA);
          if(photoarray.size()<MAX_AMOUNT_OF_PHOTOS){
              if(request_code == PackageManager.PERMISSION_GRANTED){

                  dispatchTakePictureIntent(1);
              }
              else{
                  ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION);
              }

          }else{

              Toast.makeText(getContext(),"Слишком много фотографий",Toast.LENGTH_LONG);
          }

        });
        //previewView =v.findViewById(R.id.previewView);



        MediaF_next.setOnClickListener(v3 ->{
            My f = (My) getActivity();
            ArrayList<String> uris = new ArrayList<>();
            Log.d("gfg","100500");
            for(int i=0;i<photoarray.size();i++){
                Log.d("gfg","hfgggg");
                Toast.makeText(getContext(),"cgfhfhghjhjg",Toast.LENGTH_LONG);
                File image=null;
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String imageFileName = "JPEG_" + timeStamp + "_";
                    File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                    try {
                        image = File.createTempFile(
                                imageFileName,  /* prefix */
                                ".jpg",         /* suffix */
                                storageDir      /* directory */
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }




//Convert bitmap to byte array
                Bitmap bitmap =photoarray.get(i);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(image);
                    Log.d("gfg","123");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    fos.write(bitmapdata);
                    Log.d("gfg","123");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Uri uri =Uri.fromFile(image);

                uris.add(uri.toString());
                Toast.makeText(getContext(),uri.toString(),Toast.LENGTH_LONG);
            }
           Bundle b =f.getBundle();
            b.putStringArrayList("g",uris);
            f.setBundle(b);

            f.addValue(MainActivity.photoarray,"" +photoarray.size());
            f.addValue(MainActivity.photoarray,photoarray);




            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container,new MapFragment()).addToBackStack("MapFragment").commit();



        });






        return v;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {

        switch (requestCode){
            case CAMERA_PERMISSION:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    dispatchTakePictureIntent(9 );
                }
                break;
        }
    }

    private void dispatchTakePictureIntent(int i) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        } catch (ActivityNotFoundException e) {


        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "com.example.inspector.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        Objects.requireNonNull(getContext()).sendBroadcast(mediaScanIntent);

    }
    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.max(1, Math.min(photoW/targetW, photoH/targetH));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }


}