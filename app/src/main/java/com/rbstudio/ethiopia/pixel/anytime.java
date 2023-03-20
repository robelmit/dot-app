package com.rbstudio.ethiopia.pixel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class anytime extends AppCompatActivity {
    PieChart pieChart;
    DatePickerDialog datePickerDialog;
    public List<Integer> numb;
    public List<Integer> priceb;
    public List<Integer> priceso;
    public List<Integer> income;
    public List<Integer> expense;
    public  String datea,dateb;
    public TextView sepro,seexp,seloss,sersale;
    public TextView propro,proexp,proloss,proprice;
    public int fqri;
    public  Cursor cursor;
    public Cursor cursor1;
    DatePickerDialog picker;
    String aa="",bb="";
    public Button button,button1,button2;
    public TextView date1,date2;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anytime);
        ImageView imageViewpro=(ImageView)findViewById(R.id.back);
        prefs= getSharedPreferences("email", 0);
        imageViewpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor prefEditor = prefs.edit();
                prefEditor.putString("date1", "");
                prefEditor.putString("date2", "");
                prefEditor.apply();
                Intent intent=new Intent(anytime.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });


        //check mkr
        DatabaseHelper db = new DatabaseHelper(this);

        if (robi()==1){
            cursor=db.getproanydate(returna(),returnb());
            cursor1=db.getseranydate(returna(),returnb());
        }
        else if (robi()==2){
            //Toast.makeText(anytime.this,"any time",Toast.LENGTH_SHORT).show();
            cursor=db.getproanydate(returna(),returnb());
            cursor1=db.getseranydate(returna(),returnb());
            if(cursor!=null && cursor.getCount()>0){

            }
            else {
                Toast.makeText(anytime.this,"null data",Toast.LENGTH_SHORT).show();
            }

        }
        else{
            cursor=db.getproanydate(returna(),returnb());
            cursor1=db.getseranydate(returna(),returnb());
        }
        proprice =findViewById(R.id.proprice);
        propro=findViewById(R.id.propro);
        proexp=findViewById(R.id.proexp);
        proloss=findViewById(R.id.proloss);
        sepro=findViewById(R.id.serpro);
        seexp=findViewById(R.id.serexp);
        seloss=findViewById(R.id.serloss);
        sersale=findViewById(R.id.sersale);

//learn pro
        button=findViewById(R.id.from);
        button1=findViewById(R.id.to);
        button2=findViewById(R.id.generate);
        date1=findViewById(R.id.text1);
        date2=findViewById(R.id.text2);


        fqri=10;

        //check segment


        //let's go with the button


//new code


        //
             Toast.makeText(anytime.this,returna(),Toast.LENGTH_SHORT).show();
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
//        String dateString1 = format.format(returna());
  //      String dateString2 = format.format(returnb());
            date1.setText(returna());
            date2.setText(returnb());







        int a,b,c;
        numb = new ArrayList<>();
        priceb = new ArrayList<>();
        priceso = new ArrayList<>();
        income=new ArrayList<>();
        expense=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                int num= cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PRONUM));
                int pricebuy= cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PRICEBUY));
                int pricesold =cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PRICESOLD));
                int i = cursor.getPosition() + 1;
                numb.add(num);
                priceb.add(pricebuy);
                priceso.add(pricesold);
            } while (cursor.moveToNext());
        }

        //closing cursor
        cursor.close();

        List<Integer> aa=new ArrayList<>();
        List<Integer> bb=new ArrayList<>();
        List<Integer> cc=new ArrayList<>();
        List<Integer> dd=new ArrayList<>();
        for(int i=0;i<numb.size();i++){

            try {
                aa.add((numb.get(i)*priceso.get(i))-(numb.get(i)*priceb.get(i)));

            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        //
        for(int i=0;i<priceb.size();i++){

            try {
                bb.add(((numb.get(i)*priceb.get(i))));

            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        //
        for(int i=0;i<priceso.size();i++){

            try {
                cc.add((numb.get(i)*priceb.get(i))-(numb.get(i)*priceso.get(i)));

            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        for(int i=0;i<priceso.size();i++){

            try {
                dd.add((numb.get(i)*priceso.get(i)));

            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
        int aaa=0;
        int d=0;
        int ddd=0;
        int  f=0;
        int lll=0;
        int l=0;
        int p=0;
        int ppp=0;
        for(int i=0;i<dd.size();i++){
            p=dd.get(i)+ppp;
            ppp=p;
        }
        for(int i=0;i<aa.size();i++){
            d=aa.get(i)+aaa;
            aaa=d;
        }
        //
        for(int i=0;i<bb.size();i++){
            f=bb.get(i)+ddd;
            ddd=f;
        }
        //
        for(int i=0;i<cc.size();i++){
            l=cc.get(i)+lll;
            lll=l;
        }
        int k;
        if (l<0){
            k=0;
        }
        else {
            k=l;
        }
        try {
            proprice.setText(String.valueOf((String.valueOf(p))));
            propro.setText(String.valueOf((String.valueOf(d))));
            proexp.setText(String.valueOf((String.valueOf(f))));
            proloss.setText(String.valueOf((String.valueOf(k))));
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
//service
        if (cursor1.moveToFirst()) {
            do {

                int in= cursor1.getInt(cursor1.getColumnIndex(Service.COLUMN_PAYMENT));
                int ex =cursor1.getInt(cursor1.getColumnIndex(Service.COLUMN_EXPENSE));
                int i = cursor1.getPosition() + 1;
                income.add(in);
                expense.add(ex);
            } while (cursor1.moveToNext());
        }
        cursor1.close();

        int xx=0;
        int x=0;
        int yy=0;
        int  y=0;

        try {
            for(int i=0;i<income.size();i++){
                x=income.get(i)+xx;
                xx=x;
            }
            for(int i=0;i<expense.size();i++){
                y=expense.get(i)+yy;
                yy=y;
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        try {
            //  seprice.setText(((String.valueOf(x))));
            sersale.setText(((String.valueOf(x))));
            seexp.setText(((String.valueOf(y))));
            sepro.setText(((String.valueOf(x-y))));
            seloss.setText((("0")));
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        TextView totp,tote,totl,totsale;

        totp=findViewById(R.id.totp);
        tote=findViewById(R.id.tote);
        totl=findViewById(R.id.totl);
        totsale=findViewById(R.id.totalsale);
        int hn=x-y;
        totsale.setText(String.valueOf(p+x));
        totp.setText(String.valueOf(hn+d));
        tote.setText(String.valueOf(f+y));
        totl.setText(String.valueOf(k));




        //robi graph

        pieChart=findViewById(R.id.chart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(1f);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);
        ArrayList<PieEntry> yvalues=new ArrayList<>();
        yvalues.add(new PieEntry(d+hn,"Profit"));
        yvalues.add(new PieEntry(p+x,"Total Sale"));
        yvalues.add(new PieEntry(f+y,"expense"));
        if (l<0){

        }
        else {
            yvalues.add(new PieEntry(k,"loss"));

        }

        final PieDataSet dataSet=new PieDataSet(yvalues,"");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.createColors(ColorTemplate.MATERIAL_COLORS));
        PieData pieData=new PieData(dataSet);
        pieData.setValueTextColor(Color.YELLOW);
        pieData.setValueTextSize(20f);
        pieChart.setUsePercentValues(true);
        pieChart.setData(pieData);
        pieChart.animateY(1000, Easing.EaseInOutCubic);


        //robi




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//robixeo
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(anytime.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String dateno=String.valueOf(monthOfYear + " "+ dayOfMonth+ " "+year);
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                String dateString = format.format(calendar.getTime());
                                date1.setText(dateString);
                                storea(dateString);


                                //showtimebaby(datea);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
             // date picker dialog
                datePickerDialog = new DatePickerDialog(anytime.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                String dateString = format.format(calendar.getTime());
                                date2.setText(dateString);
                                storeb(dateString);
                                //showtimebaby(datea);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date1.getText().toString().equals("") || date2.getText().toString().equals("")){

                }
                else {
                    restart();

                }

            }
        });

    }
    public void  storea(String a){
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString("date1", a);
        prefEditor.apply();


    }
    public void storeb(String b){
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString("date2", b);
        prefEditor.apply();

    }
    public String returna(){
        String myStrValue = prefs.getString("date1", "");
        return  myStrValue;
    }
    public String returnb(){
        String myStrValue = prefs.getString("date2", "");
        return  myStrValue;
    }

    public void restart(){
       Intent intent=new Intent(anytime.this,anytime.class);
       finish();
       startActivity(intent);
    }
    public int robi(){

        String myStrValue = prefs.getString("date1", "");
        String myStrValue1 = prefs.getString("date2", "");
       if(myStrValue.length()==0 || myStrValue1.length()==0){
           return 1;
       }
       else {

           return 2;
       }
    }
    @Override
    public void onBackPressed() {
        if (true){

        } else {
            super.onBackPressed();
        }
    }

}
