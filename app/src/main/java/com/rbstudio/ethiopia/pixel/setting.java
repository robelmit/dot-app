package com.rbstudio.ethiopia.pixel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rbstudio.ethiopia.pixel.object.Loan;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class setting extends AppCompatActivity {
    String[] language = { "English", "አማርኛ", "ትግርኛ"};
    final Context context = this;

    ImageButton button3;
    ImageButton button4;
    ImageButton button5;
    ImageButton button6;
    ImageButton button7;
    ImageButton button8;
    ImageButton button9;
    ImageButton button10;
    Button hacku;
    Toolbar toolbar;
    SharedPreferences prefs;
    Context conte=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String languageToLoad  = "am"; // change your language here


        if (gettheme() == 0) {
            this.setTheme(R.style.rbstyle);

        } else {
            switch (gettheme()) {
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


        String a;
        if (getlan().equals("")) {
            a = "en";
        } else {
            a = getlan();
        }

       /* Locale locale = new Locale(a);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());*/
        Resources resources=getResources();
        Configuration conf=resources.getConfiguration();
        Locale locale=new Locale(a);
        conf.locale =locale;
        Locale.setDefault(locale);
        DisplayMetrics dm=resources.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLayoutDirection(conf.locale);
        }
        conte.getResources().updateConfiguration(conf,conte.getResources().getDisplayMetrics());
        resources.updateConfiguration(conf,dm);
        //Toast.makeText(setting.this,a,Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_setting);
        prefs= getSharedPreferences("email", 0);
        ImageView imag=findViewById(R.id.delete);
        TextView email=findViewById(R.id.labeled);


        RelativeLayout relativeLayout=findViewById(R.id.card);
        Button alertDialogButton = (Button)findViewById(R.id.hacku);
        if (getemail().length()>=1){
            email.setText(getemail());
            alertDialogButton.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }
        else{
            email.setText("robi@gmail.com");
            alertDialogButton.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        }
      imag.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              //robiti zawedey
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setMessage("are you sure to delete ?");
              // add a button
              builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.cancel();
                      sharedpref("");
                      Intent intent=new Intent(setting.this,setting.class);
                      finish();
                      startActivity(intent);

                  }
              });
              builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      dialog.cancel();
                  }
              });
              // create and show the alert dialog
              AlertDialog dialog = builder.create();
              dialog.show();

          }
      });

        alertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomViewAlertDialog();
                Toast.makeText(getApplicationContext(), "robiti", Toast.LENGTH_LONG).show();          }
        });
        Context context;
        LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.editdialog, null);
        button3 = (ImageButton)findViewById(R.id.button114);
        button4 = (ImageButton)findViewById(R.id.button115);
        button5 = (ImageButton)findViewById(R.id.button116);
        button6 = (ImageButton)findViewById(R.id.button117);
        button7 = (ImageButton)findViewById(R.id.button118);
        button8 = (ImageButton)findViewById(R.id.button119);
        button9 = (ImageButton)findViewById(R.id.button120);
        button10 = (ImageButton)findViewById(R.id.button121);
        ImageView imageViewpro=(ImageView)findViewById(R.id.back);
        imageViewpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(setting.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        toolbar = (Toolbar) findViewById(R.id.to);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        }
        ImageView imageView;
        ImageView sheet=findViewById(R.id.mabutton);


        final Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, language);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        LinearLayout linearLayout = findViewById(R.id.bottoms);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
sheet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }
});

//sheet color
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storetheme(R.style.AppTheme_brown);
                finish();
                Intent intent=new Intent(setting.this,SplashScreen.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storetheme(R.style.AppTheme_darpink);
                finish();
                Intent intent=new Intent(setting.this,SplashScreen.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storetheme(R.style.AppTheme_green);
                finish();
                Intent intent=new Intent(setting.this,SplashScreen.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storetheme(R.style.AppTheme_red);
                Intent intent=new Intent(setting.this,SplashScreen.class);
                startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storetheme(R.style.AppTheme_pink);
                finish();
                Intent intent=new Intent(setting.this,SplashScreen.class);
                startActivity(intent);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                storetheme(R.style.AppTheme_violet);
                Intent intent=new Intent(setting.this,SplashScreen.class);
                startActivity(intent);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storetheme(R.style.AppTheme_skyblue);
                finish();
                Intent intent=new Intent(setting.this,SplashScreen.class);
                startActivity(intent);
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storetheme(R.style.AppTheme_grey);
                finish();
                Intent intent=new Intent(setting.this,SplashScreen.class);
                startActivity(intent);
            }
        });


        switch (getlan()){
            case "am":{
                spin.setSelection(1,true);
                break;
            }
            case "en":{
                spin.setSelection(0,true);
                break;
            }
            case "ti":{
                spin.setSelection(2,true);
                break;
            }
            default:{
                spin.setSelection(0,true);
                break;
            }
        }

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                //  Toast.makeText(getApplicationContext(), "Selected Language: "+i , Toast.LENGTH_SHORT).show();

                switch (i){
                    case 0:
                    {
                        store("en");
                        finish();
                        Intent intent=new Intent(setting.this,SplashScreen.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        store("am");
                        finish();
                        Intent intent=new Intent(setting.this,SplashScreen.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        store("ti");
                        finish();
                        Intent intent=new Intent(setting.this,SplashScreen.class);
                        startActivity(intent);
                        break;
                    }
                    default:{
                        store("en");
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                store("en");
            }
        });






        //SharedPreferences sharedPreferences = getSharedPreferences("fqr", MODE_PRIVATE);
        //gint gg = sharedPreferences.getInt("theme", 0);*/

    }



    public void store(String language){
        SharedPreferences sharedPreference=getSharedPreferences("robi",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreference.edit();
        editor.putString("language",language);
        editor.apply();
    }
    public String getlan(){
        SharedPreferences sharedPreference=getSharedPreferences("robi",MODE_PRIVATE);
        return sharedPreference.getString("language","");
    }
    public void storetheme(int theme){
        SharedPreferences sharedPreferences=getSharedPreferences("fqr",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("theme",theme);
        editor.apply();
    }
    public int gettheme(){
        SharedPreferences sharedPreferences=getSharedPreferences("fqr",MODE_PRIVATE);
        int gg=sharedPreferences.getInt("theme",0);
        return gg;
    }
    private void showCustomViewAlertDialog()
    {
        final View customLayout = getLayoutInflater().inflate(R.layout.editdialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customLayout);
        // add a button
        final EditText editText=customLayout.findViewById(R.id.editText);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
                else {
                    sharedpref(editText.getText().toString());
                    dialog.cancel();
                    finish();
                    Intent intent=new Intent(setting.this,setting.class);
                    startActivity(intent);
                }

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.cancel();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (true){
           //
        } else {
            super.onBackPressed();
        }
    }

    public void sharedpref(String value){
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString("email", value);
        prefEditor.apply();
    }
    public String getemail(){
        String myStrValue = prefs.getString("email", "");
        return myStrValue;
    }
}
