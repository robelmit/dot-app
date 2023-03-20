package com.rbstudio.ethiopia.pixel;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rbstudio.ethiopia.pixel.database.fixeddatabase;
import com.rbstudio.ethiopia.pixel.object.Fixed;
import com.rbstudio.ethiopia.pixel.util.Fixeddecoration;
import com.rbstudio.ethiopia.pixel.util.fixedtouchlistner;
import com.rbstudio.ethiopia.pixel.view.FixedAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class fixed extends AppCompatActivity {
    private FixedAdapter mAdapter;
    private List<Fixed> notesList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;
    private ImageView imager;
    public int fi,se,th;
    private fixeddatabase db;
    public int ficorrect,secorrect,thcorrect;
    TextView depyear,depmon,rest;
    EditText name,price,year,description,number;

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

        setContentView(R.layout.activity_fixed);
        ficorrect=1;
        depyear=findViewById(R.id.depyear);
        depmon=findViewById(R.id.depmonth);
        rest=findViewById(R.id.rest);
        number=findViewById(R.id.ednum);
        name=findViewById(R.id.namesheet);
        price=findViewById(R.id.nmntay);
        year=findViewById(R.id.yearsheet);
        description=findViewById(R.id.descr);
        ImageView imageViewpro=findViewById(R.id.imgr);
        imageViewpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(fixed.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("");
        }
//robi code
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noNotesView = findViewById(R.id.empty_notes_view);
        imager=findViewById(R.id.imager);

        //robi code

        LinearLayout linearLayout = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        db = new fixeddatabase(this);

        notesList.addAll(db.getAllNotes());
//getting the sheet
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                YoYo.with(Techniques.FadeIn)
                        .duration(700)
                        .playOn(findViewById(R.id.bottom_sheet));
                final Button cancel,add;

                cancel=findViewById(R.id.cancel);
                add=findViewById(R.id.add);
                //final String Name= name.getText().toString();
               // final Integer Price= Integer.parseInt(price.getText().toString());
               // final Integer Year= Integer.parseInt(year.getText().toString());
               // final String Desc=description.getText().toString();


                //implement hacker montherfucking tax
                number.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable s) {}

                    @Override
                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                        if(s.length() != 0)
                            fi=Integer.parseInt(number.getText().toString());
                        tex(fi);
                    }
                });

                year.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable s) {}

                    @Override
                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                        if(s.length() != 0)
                            se=Integer.parseInt(year.getText().toString());
                        tex1(se);
                    }
                });

                price.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void afterTextChanged(Editable s) {}

                    @Override
                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                        if(s.length() != 0)
                            th=Integer.parseInt(price.getText().toString());
                        tex2(th);
                    }
                });




                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                });
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String na=name.getText().toString();
                        String des=description.getText().toString();
                        try {
                            EditText ednum;
                            ednum=findViewById(R.id.ednum);

                            try{
                                int num = Integer.parseInt(ednum.getText().toString());
                                int pr = Integer.parseInt(price.getText().toString());
                                int yea = Integer.parseInt(year.getText().toString());
                                createNote(na,pr,yea,des,num);
                            }catch (NumberFormatException e){
                                e.printStackTrace();
                            }

                        }catch (NumberFormatException e){
                            e.printStackTrace();
                        }

                        number.setText(null);
                        price.setText(null);
                        year.setText(null);
                        name.setText(null);
                        description.setText(null);
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                    }
                });

                //showNoteDialog(false, null, -1);
            }
        });

        mAdapter = new FixedAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new Fixeddecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        //robi code only

        toggleEmptyNotes();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerView.addOnItemTouchListener(new fixedtouchlistner(this,
                recyclerView, new fixedtouchlistner.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createNote(String name,int price,int year,String des,int number) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertNote(name,price,year,des,number);
        // get the newly inserted note from db
        Fixed n = db.getNote(id);
        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n);
            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyNotes();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateNote(String note, int position) {
        Fixed n = notesList.get(position);
        // updating note text
        n.setName(note);
        // updating note in db
        db.updateNote(n);
        // refreshing the list
        notesList.set(position, n);
        mAdapter.notifyItemChanged(position);
        toggleEmptyNotes();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteNote(int position) {
        // deleting the note from db
        db.deleteNote(notesList.get(position));

        // removing the note from the list
        notesList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
    private void showActionsDialog(final int position) {
        final CharSequence colors[] = new CharSequence[]{"Show details", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    alert(notesList.get(position), position);
                } else {
                    deleteNote(position);

                }
            }
        });
        builder.show();
    }




    /**
     * Shows alert dialog with EditText options to enter / edit
     * a note.
     * when shouldUpdate=true, it automatically displays old note and changes the
     * button text to UPDATE
     */
    private void showNoteDialog(final boolean shouldUpdate, final Fixed note, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(fixed.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputNote = view.findViewById(R.id.note);
        //TextView dialogTitle = view.findViewById(R.id.dialog_title);
        //dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_note_title) : getString(R.string.lbl_edit_note_title));

        if (shouldUpdate && note != null) {
            inputNote.setText(note.getName());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputNote.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Enter note!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && note != null) {
                    // update note by it's id
                    updateNote(inputNote.getText().toString(),position);
                } else {
                    // create new note
                    //  createNote(inputNote.getText().toString());
                }
            }
        });
    }

    /**
     * Toggling list and empty notes view
     */
    private void toggleEmptyNotes() {
        // you can check notesList.size() > 0

        if (db.getNotesCount() > 0) {
            noNotesView.setVisibility(View.GONE);
            imager.setVisibility(View.GONE);

        } else {
            noNotesView.setVisibility(View.VISIBLE);
            imager.setVisibility(View.VISIBLE);

        }
    }

    public void tex(int c){
        ficorrect=c;
        dotot();

    }
    public void tex1(int c){
        secorrect=c;
        dotot();
    }
    public void tex2(int c){
        thcorrect=c;
        dotot();
    }
    public void dotot(){
        try {
            int a,b,c;
            a=((ficorrect *thcorrect)/secorrect);
            b=(a/12);
            c=(ficorrect*thcorrect) - a;
            depyear.setText("Total Dep ="+String.valueOf(a)+ " br");
            depmon.setText("Monthly dep ="+String.valueOf(b) +" br");
            rest.setText("Rest Value =" + String.valueOf(c)+" br");
        }catch (ArithmeticException e){
            e.printStackTrace();
        }


    }
    public void alert(final Fixed fixed, final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.materialdetail, null);
        builder.setView(customLayout);
        TextView name=customLayout.findViewById(R.id.name);
        TextView purchase=customLayout.findViewById(R.id.purchase);
        TextView year=customLayout.findViewById(R.id.year);
        //TextView dep=customLayout.findViewById(R.id.dep);
        //TextView month=customLayout.findViewById(R.id.month);
        //TextView rest=customLayout.findViewById(R.id.rest);
        TextView des=customLayout.findViewById(R.id.description);

        name.setText(fixed.getName());
        purchase.setText(String.valueOf(fixed.getPrice()));
        year.setText(String.valueOf(String.valueOf(fixed.getYear())));
        des.setText(fixed.getDescription());
        double a,b,c;
        try {
            a=((fixed.getNumber()*fixed.getPrice())/fixed.getYear());
            b=(a/12);
            c=(fixed.getNumber()*fixed.getPrice()) - a;
           // dep.setText(String.valueOf(fixed.getNumber()));
            //month.setText(String.valueOf(b));
            //rest.setText(String.valueOf(c));
        }catch (ArithmeticException e){
            e.printStackTrace();
        }



        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    @Override
    public void onBackPressed() {
        if (true){
            //
        } else {
            super.onBackPressed();
        }
    }
}