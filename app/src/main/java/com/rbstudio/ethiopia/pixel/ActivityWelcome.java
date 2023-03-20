package com.rbstudio.ethiopia.pixel;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ActivityWelcome extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 0;
    private ImageView mImage;
    private Uri mImageUri;
    private PrefManager prefManager;
    SharedPreferences prefs;
    int pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences=getSharedPreferences("fqr",MODE_PRIVATE);
        int gg=sharedPreferences.getInt("theme",0);
        if (gg==0) {
            this.setTheme(R.style.rbstyle);

        }
        else {
            switch (gg) {
                case R.style.AppTheme_brown: {
                    this.setTheme(R.style.AppTheme_brown);
                    break;
                }
                case R.style.AppTheme_darpink: {
                    this.setTheme(R.style.AppTheme_darpink);
                    break;
                }
                case R.style.AppTheme_green: {
                    this.setTheme(R.style.AppTheme_green);
                    break;
                }
                case R.style.AppTheme_red: {
                    this.setTheme(R.style.AppTheme_red);
                    break;
                }
                case R.style.AppTheme_pink: {
                    this.setTheme(R.style.AppTheme_pink);
                    break;
                }
                case R.style.AppTheme_violet: {
                    this.setTheme(R.style.AppTheme_violet);
                    break;
                }
                case R.style.AppTheme_skyblue: {
                    this.setTheme(R.style.AppTheme_skyblue);
                    break;
                }
                case R.style.AppTheme_grey: {
                    this.setTheme(R.style.AppTheme_grey);
                    break;
                }

            }
        }
        SharedPreferences sharedPreference=getSharedPreferences("robi",MODE_PRIVATE);
        String b;
        String a= sharedPreference.getString("language","");
        if( a == "" )
        {
            a="en";
        }
        else{
            a=a;
        }

        //String languageToLoad  = "am"; // change your language here
        Locale locale = new Locale(a);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());



        //Checking for first time launch - before calling setContentView()


        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_welcome);
        prefs= getSharedPreferences("first", 0);
        if (prefs.getString("email","")==""){
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putString("email", "");
            prefEditor.apply();
        }
        else {
            launchHomeScreen();
            finish();
        }

        ImageView imageView;
final EditText editText;
final Button save,upload,proceed;
final TextView textView=findViewById(R.id.textView);
editText=findViewById(R.id.editText);
save=findViewById(R.id.button);
upload=findViewById(R.id.upload);
proceed=findViewById(R.id.proceed);
mImage=findViewById(R.id.image);

        SharedPreferences preferences = getSharedPreferences("namer",MODE_PRIVATE);
        String mImageUri = preferences.getString("image", null);
        String name = preferences.getString("name", null);


        if (mImageUri != null) {
            mImage.setImageURI(Uri.parse(mImageUri));
        } else {
            mImage.setImageResource(R.drawable.usphoto);
        }

 pro=10;
save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SharedPreferences preferences = getSharedPreferences("namer",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", String.valueOf(editText.getText().toString()));
        editor.commit();
        pro=15;
        textView.setText(preferences.getString("name", null));
    }
});

  upload.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          imageSelect();
      }
  });

proceed.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        if(editText.getText().toString().length()<=0 || pro==10){
            Toast.makeText(ActivityWelcome.this, "Please enter the name correctly", Toast.LENGTH_SHORT).show();
        }
        else {
            finish();
            Intent intent=new Intent(ActivityWelcome.this,MainActivity.class);
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putString("email", "yes");
            prefEditor.apply();
            startActivity(intent);
        }

    }
});

    }
    public void imageSelect() {
        permissionsCheck();
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void permissionsCheck() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_IMAGE_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a image.
                // The Intent's data Uri identifies which item was selected.
                if (data != null) {

                    // This is the key line item, URI specifies the name of the data
                    mImageUri  = data.getData();

                    // Saves image URI as string to Default Shared Preferences
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("image", String.valueOf(mImageUri));
                    editor.commit();

                    // Sets the ImageView with the Image URI
                    mImage.setImageURI(mImageUri);
                    mImage.invalidate();
                }
            }
        }
    }


    private void launchHomeScreen() {
        startActivity(new Intent(ActivityWelcome.this, MainActivity.class));
        finish();

    }
    }
