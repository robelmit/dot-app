package com.rbstudio.ethiopia.pixel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
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
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rbstudio.ethiopia.pixel.util.MyDividerItemDecoration;
import com.rbstudio.ethiopia.pixel.util.Recyclelistner;
import com.rbstudio.ethiopia.pixel.util.RecyclerTouchListener;
import com.rbstudio.ethiopia.pixel.view.NotesAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import info.hoang8f.android.segmented.SegmentedGroup;


public class Taskadder extends AppCompatActivity {
    private NotesAdapter mAdapter;
    private ProductAdapter Adapter;
    private List<Service> notesList = new ArrayList<Service>();
    private List<Note> notes = new ArrayList<Note>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView,recyclerView1;
    private TextView noNotesView;
    private ImageView imager;
    SegmentedGroup segmentedGroup;
    private DatabaseHelper db;
   TextView ptotal,pgross,pcost;
   public int fi,se,th;
    public int fir,sec,thi;
    public int fircorrect,seccorrect,thicorrect;
    public int ficorrect,secorrect,thcorrect;
    EditText product;
     EditText number;
     EditText pricebuy;
     EditText pricebuy1;
     EditText pricesold;
     EditText prodes;
     public Context context;

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
        setContentView(R.layout.activity_main);
        //alert


        ImageView imageViewpro=findViewById(R.id.imgr);
        imageViewpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Taskadder.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

//robi code
final EditText service,description,expense,payment,hour;
final Button cancel,add;
       segmentedGroup=findViewById(R.id.ab);
       coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView1 = findViewById(R.id.product_view);

        noNotesView = findViewById(R.id.empty_notes_view);
        imager=findViewById(R.id.imager);

        //robi code

        LinearLayout linearLayout = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);
        final Spinner spinner =findViewById(R.id.spinner);
        final Spinner spinner1 =findViewById(R.id.spinner1);
        description=findViewById(R.id.description);
        expense=findViewById(R.id.expense);
        payment=findViewById(R.id.payment);
        cancel=findViewById(R.id.cancel);
        add=findViewById(R.id.add);
        hour=findViewById(R.id.dsd);


        recyclerView1.setVisibility(View.GONE);
        segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.a) {

                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView1.setVisibility(View.GONE);
                    YoYo.with(Techniques.FadeIn)
                            .duration(700)
                            .playOn(recyclerView);
                    //do work when radioButton1 is active
                } else  if (checkedId == R.id.b) {
                    recyclerView.setVisibility(View.GONE);
                    recyclerView1.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.FadeIn)
                            .duration(700)
                            .playOn(recyclerView1);
                    //do work when radioButton2 is active
                }
            }
        });

        //new code bro
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.myRadioGroup);
        final RadioButton radio,radio1;
        radio=findViewById(R.id.male);
        radio1=findViewById(R.id.female);
        final LinearLayout alinear,blinear;
        alinear=findViewById(R.id.no);
        blinear=findViewById(R.id.yes);
        blinear.setVisibility(View.GONE);
radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View radioButton = group.findViewById(checkedId);
        int index = group.indexOfChild(radioButton);
        switch (index) {
            case 0:{
                alinear.setVisibility(View.VISIBLE);
                blinear.setVisibility(View.GONE);
                // perform your action here
                break;}
            case 1: {
                alinear.setVisibility(View.GONE);
                blinear.setVisibility(View.VISIBLE);
                // perform your action here
                break;
            }
        }
    }
});

        db = new DatabaseHelper(this);

        notesList.addAll(db.getAllNotes());
        notes.addAll(db.getAllNote());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                YoYo.with(Techniques.FadeIn)
                        .duration(700)
                        .playOn(findViewById(R.id.bottom_sheet));
                ptotal=findViewById(R.id.prototal);
                pcost=findViewById(R.id.procost);
                pgross=findViewById(R.id.progro);




                product=findViewById(R.id.product);
                number=findViewById(R.id.number);
                pricebuy1 =findViewById(R.id.pricebuy);
                pricesold=findViewById(R.id.pricesold);
                prodes=findViewById(R.id.prodes);
                final EditText other=findViewById(R.id.other);
                //String expe= expense.getText().toString();
                  //final String paye= payment.getText().toString();
                 // final Integer exp= Integer.parseInt(expe+"");
                  //final Integer pay=Integer.parseInt(paye+ "");
                expense.addTextChangedListener(new TextWatcher() {

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
                           fir=Integer.parseInt(expense.getText().toString());
                        text(fir);
                    }
                });
                payment.addTextChangedListener(new TextWatcher() {

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
                            sec=Integer.parseInt(payment.getText().toString());
                            text1(sec);
                    }
                });
                other.addTextChangedListener(new TextWatcher() {

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
                            try{
                                thi=Integer.parseInt(other.getText().toString());
                                text2(thi);
                            }catch (NumberFormatException e){
                                e.printStackTrace();
                            }
                    }
                });




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
                pricebuy1.addTextChangedListener(new TextWatcher() {

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
                            se=Integer.parseInt(pricebuy.getText().toString());
                        tex1(se);
                    }
                });
                pricesold.addTextChangedListener(new TextWatcher() {

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
                            th=Integer.parseInt(pricesold.getText().toString());
                        tex2(th);
                    }
                });



               //total.setText("Total price is " +fircorrect);
               //gross.setText("Gross profit is " +seccorrect);


                  //showNoteDialog(false, null, -1);
            }
        });

        mAdapter = new NotesAdapter(this, notesList);
        Adapter = new ProductAdapter(this, notes);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView1.setLayoutManager(mLayoutManager1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView1.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);
        recyclerView1.setAdapter(Adapter);

        //robi code only
        SegmentedGroup segmented2 = (SegmentedGroup) findViewById(R.id.segmented2);
        segmented2.setTintColor(Color.parseColor("#FF76FF03"));
        final LinearLayout linearLayout1,linearLayout2;
        linearLayout1=findViewById(R.id.line);
        linearLayout2=findViewById(R.id.linear);
        final RadioButton radioButton,radioButton1;
        radioButton=findViewById(R.id.rad);
        radioButton.setChecked(true);
        radioButton1=findViewById(R.id.rad1);
        linearLayout1.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.VISIBLE);
        //robi code on product implementation
        final EditText produc=findViewById(R.id.product);
        final EditText numbe=findViewById(R.id.number);
        pricebuy1 =findViewById(R.id.pricebuy);
        pricebuy = pricebuy1;
        final EditText pricesol=findViewById(R.id.pricesold);
        final EditText prode=findViewById(R.id.prodes);
        radioButton1.toggle();
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
            }
        });
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);
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

                if(!radioButton.isChecked()){
                    EditText other=findViewById(R.id.other);
                    String ser=spinner.getSelectedItem().toString();
                    String cust=spinner1.getSelectedItem().toString();
                    String des=description.getText().toString();


                    //a
                    EditText receivable=findViewById(R.id.receivable);

                    int exp=0,pay=0;
                    try {

                        if (radio.isChecked()){
                            exp = Integer.parseInt(expense.getText().toString()) +Integer.parseInt(other.getText().toString()) ;
                        }
                        else {
                            exp=0;
                        }


                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                    try {
                        if (radio.isChecked()){
                            pay = Integer.parseInt(payment.getText().toString());

                        }
                        else {
                            pay = Integer.parseInt(receivable.getText().toString());

                        }
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                    if (TextUtils.isEmpty(ser) || TextUtils.isEmpty(des)){

                        Toast.makeText(Taskadder.this,"Please enter the details correctly",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        createNote(ser,des,exp,pay,cust);
                    }
                }
                else {

                    String pro=produc.getText().toString();
                    int num=0,pribu=0,priso=0;
                    try{
                        num = Integer.parseInt(numbe.getText().toString());

                    }catch (NumberFormatException e){
                      e.printStackTrace();
                    }
                    try {
                        pribu = Integer.parseInt(pricebuy.getText().toString());
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                    try{
                        priso = Integer.parseInt(pricesol.getText().toString());

                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                    //String description=
                    String prode=prodes.getText().toString();
                    if (TextUtils.isEmpty(pro) || TextUtils.isEmpty(prode)){
                    Toast.makeText(Taskadder.this,"Please enter the details correctly",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        createpro(pro,num,pribu,priso,prode);

                    }



                }
                spinner.setSelection(0,true);
                expense.setText(null);
                payment.setText(null);
                description.setText(null);
                hour.setText(null);
                product.setText(null);
                number.setText(null);
                pricebuy1.setText(null);
                pricesol.setText(null);
                prode.setText(null);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        toggleEmptyNotes();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));
        recyclerView1.addOnItemTouchListener(new Recyclelistner(this,
                recyclerView1, new Recyclelistner.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialogoPro(position);
            }
        }));


    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createNote(String service,String des,int expense,int payment,String cust) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertNote(service,des,expense,payment,cust);
        // get the newly inserted note from db


        Service n = db.getNote(id);

        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n);
            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyNotes();
        }
    }
    private void createpro(String name,int  number,int pricebuy,int pricesold,String prodes) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertpro(name,number,pricebuy,pricesold,prodes);
        // get the newly inserted note from db
        Note n = db.getProduct(id);
        if (n != null) {
            // adding new note to array list at 0 position
            notes.add(0, n);
            // refreshing the list
            Adapter.notifyDataSetChanged();

            toggleEmptyNotes();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateNote(String note, int position) {
        Service n = notesList.get(position);
        // updating note text
        //n.setNote(note);
        // updating note in db
       // db.updateNote(n);
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
    private void pro(int position) {
        // deleting the note from db
        db.deleteProduct(notes.get(position));

        // removing the note from the list
        notes.remove(position);
        Adapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */

    private void showActionsDialog(final int position) {
        final CharSequence colors[] = new CharSequence[]{"Show details", "Delete"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {

                    alert(notesList.get(position), position);
                     Toast.makeText(getApplicationContext(),"showing detais",Toast.LENGTH_SHORT).show();

                    //showNoteDialog(true, notesList.get(position), position);
                } else {
                    deleteNote(position);

                }
            }
        });
        builder.show();
    }
    private void showActionsDialogoPro(final int position) {
        final CharSequence colors[] = new CharSequence[]{"Show details", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    alertpro(notes.get(position), position);
                } else {
                    pro(position);

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
    private void showNoteDialog(final boolean shouldUpdate, final Service note, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Taskadder.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputNote = view.findViewById(R.id.note);
        //TextView dialogTitle = view.findViewById(R.id.dialog_title);
        //dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_note_title) : getString(R.string.lbl_edit_note_title));

        if (shouldUpdate && note != null) {
        //    inputNote.setText(note.getNote());
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
        if (db.getNotesCount() > 0 || db.getNotesCoun() >0) {
            noNotesView.setVisibility(View.GONE);
            imager.setVisibility(View.GONE);
        } else {
            noNotesView.setVisibility(View.VISIBLE);
            imager.setVisibility(View.VISIBLE);
        }
    }
    public void text(int c){
        fircorrect=c;
        dototal();

    }
    public void text1(int c){
        seccorrect=c;
        dototal();
    }
    public void text2(int c){
        thicorrect=c;
        dototal();
    }
    public void dototal(){
        //total.setText("Total income is " +(seccorrect)+"br");
        //gross.setText("Gross profit is " +(seccorrect-(fircorrect+thicorrect))+"br");

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
        ptotal.setText("Total price is " +(ficorrect * thcorrect)+"br");
        pcost.setText("Total cost is " +(ficorrect * secorrect)+"br");
        pgross.setText("Gross profit is " +(ficorrect * (thcorrect-secorrect))+"br");

    }
    @Override
    public void onBackPressed() {
        if (true){
            //
        } else {
            super.onBackPressed();
        }
    }
    public void alert(final Service service, final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.detailshow, null);
        builder.setView(customLayout);
        TextView name=customLayout.findViewById(R.id.name);
        TextView labor=customLayout.findViewById(R.id.labor);
        TextView expense=customLayout.findViewById(R.id.expense);
        //TextView hour=customLayout.findViewById(R.id.hour);
        TextView des=customLayout.findViewById(R.id.description);
        TextView totalprice=customLayout.findViewById(R.id.price);
        TextView grossprofit=customLayout.findViewById(R.id.gross);
        name.setText(service.getService());
        labor.setText(String.valueOf(service.getPayment()));
        expense.setText(String.valueOf(service.getExpense()));
      //  hour.setText(service.);
        des.setText(service.getDescription());
        totalprice.setText(String.valueOf(service.getPayment()+service.getExpense()));
        grossprofit.setText(String.valueOf(service.getPayment()-service.getExpense()));
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
           }
       });

        builder.show();

    }
    public void alertpro( final Note note, final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.productdetail, null);
        builder.setView(customLayout);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        TextView itemname=customLayout.findViewById(R.id.name);
        TextView number=customLayout.findViewById(R.id.number);
        TextView selling=customLayout.findViewById(R.id.selling);
        TextView productcost=customLayout.findViewById(R.id.productcost);
        TextView description=customLayout.findViewById(R.id.description);
        TextView totalprice=customLayout.findViewById(R.id.price);
        TextView grossprofit=customLayout.findViewById(R.id.gross);
        TextView totalcost=customLayout.findViewById(R.id.cost);
        itemname.setText(note.getProduct());
        number.setText(String.valueOf(note.getPronum()));
        selling.setText(String.valueOf(note.getPricesold()));
        productcost.setText(String.valueOf(note.getPricebuy()));
        int a=note.getPronum()*note.getPricebuy();
        int b=note.getPronum()*note.getPricesold();
        totalprice.setText(String.valueOf(a));
        totalcost.setText(String.valueOf(b));
        grossprofit.setText(String.valueOf(b-a));
        description.setText(note.getDescription());

        builder.show();
    }
}
