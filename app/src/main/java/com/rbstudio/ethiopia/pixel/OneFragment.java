package com.rbstudio.ethiopia.pixel;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.rbstudio.ethiopia.pixel.database.loandatabase;
import com.rbstudio.ethiopia.pixel.object.Loan;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import info.hoang8f.android.segmented.SegmentedGroup;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import static android.support.constraint.Constraints.TAG;
import static android.view.View.combineMeasuredStates;

public class OneFragment extends Fragment {
PieChart pieChart;
    DatePickerDialog datePickerDialog;
    public List<Integer> numb;
    public List<Integer> priceb;
    public List<Integer> priceso;
    public List<Integer> income;
    public List<Integer> expense;
   public  String datea,dateb;
    public TextView sepro,seexp,seloss,seprice,sersale;
    public TextView propro,proexp,proloss,proprice;
    public int fqri;
    public  Cursor cursor;
    public Cursor cursor1;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());


        final View view=inflater.inflate(R.layout.fragment_one, container, false);
        proprice =view.findViewById(R.id.proprice);
       propro=view.findViewById(R.id.propro);
       proexp=view.findViewById(R.id.proexp);
       proloss=view.findViewById(R.id.proloss);
       sepro=view.findViewById(R.id.serpro);
       seexp=view.findViewById(R.id.serexp);
       seloss=view.findViewById(R.id.serloss);
       sersale=view.findViewById(R.id.sersale);

    fqri=10;

       //check segment





//new code

        //


        DatabaseHelper db = new DatabaseHelper(getActivity());
            cursor = db.getprotoday();
            cursor1 = db.getsertoday();


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

        totp=view.findViewById(R.id.totp);
        tote=view.findViewById(R.id.tote);
        totl=view.findViewById(R.id.totl);
        totsale=view.findViewById(R.id.totalsale);
        int hn=x-y;
        totsale.setText(String.valueOf(p+x));
        totp.setText(String.valueOf(hn+d));
        tote.setText(String.valueOf(f+y));
        totl.setText(String.valueOf(k));




    //robi graph

        pieChart=view.findViewById(R.id.chart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.1f);
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

        return view;

    }
}