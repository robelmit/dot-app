package com.rbstudio.ethiopia.pixel;

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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rbstudio.ethiopia.pixel.database.SalaryDatabase;
import com.rbstudio.ethiopia.pixel.object.Salary;
import com.rbstudio.ethiopia.pixel.util.MyDividerItemDecoration;
import com.rbstudio.ethiopia.pixel.util.RecyclerTouchListener;
import com.rbstudio.ethiopia.pixel.util.SalaryDecoration;
import com.rbstudio.ethiopia.pixel.view.SalaryAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class salary extends AppCompatActivity {
    private SalaryAdapter mAdapter;
    private List<Salary> notesList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;
public String sex;
    private SalaryDatabase db;
    private ImageView imager;
    public int fir,sec;
    public int fircorrect,seccorrect;
    public  TextView taxpaid,totalpayment;
    EditText name,salary,description,job,tax;
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

        setContentView(R.layout.activity_salary);
        //hey text
        taxpaid=findViewById(R.id.taxpaid);
        totalpayment=findViewById(R.id.totalpayment);
        tax=findViewById(R.id.tax);

        //bro edit text here ]
        job=findViewById(R.id.job);
        name=findViewById(R.id.wesidato);
        salary=findViewById(R.id.salary);
        description=findViewById(R.id.description);

        ImageView imageViewpro=findViewById(R.id.imgr);
        imageViewpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(salary.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
//robi code
        final Button cancel,add;
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noNotesView = findViewById(R.id.empty_notes_view);
        imager=findViewById(R.id.imager);

        //robi code
//seccorrect=0;


        LinearLayout linearLayout = findViewById(R.id.bottom_sheet);
        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        cancel=findViewById(R.id.cancel);
        add=findViewById(R.id.add);

        db = new SalaryDatabase(this);

        notesList.addAll(db.getAllNotes());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                YoYo.with(Techniques.FadeIn)
                        .duration(700)
                        .playOn(findViewById(R.id.bottom_sheet));

                //here we fucking get the edit text data


                salary.addTextChangedListener(new TextWatcher() {

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
                            fir=Integer.parseInt(salary.getText().toString());
                        text(fir);
                    }
                });
                tax.addTextChangedListener(new TextWatcher() {

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
                            sec=Integer.parseInt(tax.getText().toString());
                        text1(sec);
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
                         RadioGroup radioGroup;
                         RadioButton radioButton1,radioButton2;

                        radioGroup=findViewById(R.id.myRadioGroup);
                        sex="male";
                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                                  @Override
                                                                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                      switch (checkedId) {
                                                                          case R.id.male:
                                                                              sex = "male";
                                                                              break;
                                                                          case R.id.female:
                                                                              sex = "female";
                                                                              break;
                                                                      }
                                                                  }
                                                               });



                        String Name=name.getText().toString();
                        try {
                            int Salary= Integer.valueOf(salary.getText().toString());
                            String des=description.getText().toString();
                            String jobype=job.getText().toString();
                            int Tax=Integer.parseInt(tax.getText().toString());

                            createNote(Name,sex,Salary,des,jobype,Tax);
                        }catch (NumberFormatException e){

                        }
                        name.setText(null);
                        salary.setText(null);
                        description.setText(null);
                        job.setText(null);
                        tax.setText(null);
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


                    }



                });

                //showNoteDialog(false, null, -1);
            }
        });

        mAdapter = new SalaryAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SalaryDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);
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
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createNote(String name,String sex,int salary,String des,String job,int Tax) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertNote(name,sex,salary,des,job,Tax);
        // get the newly inserted note from db
        Salary n = db.getNote(id);
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
        Salary n = notesList.get(position);
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
                    Toast.makeText(getApplicationContext(),"showing detais",Toast.LENGTH_SHORT).show();

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
    private void showNoteDialog(final boolean shouldUpdate, final Salary note, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.note_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(salary.this);
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
    public void text(int c){
        fircorrect=c;
        dototal();

    }
    public void text1(int c){
        seccorrect=c;
        dototal();
    }
    public void dototal(){
        double a,b;
        a=(seccorrect*0.01)*fircorrect;
        b=fircorrect-a;
        taxpaid.setText("Tax paid is " + String.valueOf(a));
        totalpayment.setText("Total Payment is " +String.valueOf(b));

    }
    public void alert(final Salary salary, final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.salarydetail, null);
        builder.setView(customLayout);
        TextView name=customLayout.findViewById(R.id.name);
        TextView namesalary=customLayout.findViewById(R.id.salary);
        TextView payment=customLayout.findViewById(R.id.payment);
        TextView tax=customLayout.findViewById(R.id.tax);
        TextView des=customLayout.findViewById(R.id.description);

        name.setText(salary.getName());
        namesalary.setText(String.valueOf(salary.getSalary()));
        tax.setText(String.valueOf(String.valueOf(salary.getTax())));
        //  hour.setText(service.);
        des.setText(salary.getDescription());
        int a=salary.getTax();
        int b=salary.getSalary();
        double c=(0.01*a)*b;
        payment.setText(String.valueOf(c));
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