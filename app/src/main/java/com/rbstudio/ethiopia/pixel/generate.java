package com.rbstudio.ethiopia.pixel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dewinjm.monthyearpicker.MonthYearPickerDialog;
import com.github.dewinjm.monthyearpicker.MonthYearPickerDialogFragment;
import com.rbstudio.ethiopia.pixel.database.SalaryDatabase;
import com.rbstudio.ethiopia.pixel.database.fivedatabase;
import com.rbstudio.ethiopia.pixel.database.fixeddatabase;
import com.rbstudio.ethiopia.pixel.database.fourdatabase;
import com.rbstudio.ethiopia.pixel.database.loandatabase;
import com.rbstudio.ethiopia.pixel.database.onedatabase;
import com.rbstudio.ethiopia.pixel.database.threedatabase;
import com.rbstudio.ethiopia.pixel.database.twodatabase;
import com.rbstudio.ethiopia.pixel.object.Five;
import com.rbstudio.ethiopia.pixel.object.Fixed;
import com.rbstudio.ethiopia.pixel.object.Fixed;
import com.rbstudio.ethiopia.pixel.object.Four;
import com.rbstudio.ethiopia.pixel.object.Loan;
import com.rbstudio.ethiopia.pixel.object.One;
import com.rbstudio.ethiopia.pixel.object.Salary;
import com.rbstudio.ethiopia.pixel.object.Three;
import com.rbstudio.ethiopia.pixel.object.Two;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import jxl.Cell;
import jxl.CellFeatures;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class generate extends AppCompatActivity  {
    String kaleb;
    String dehack,derob;
    int yearSelected;
    int monthSelected;
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

        setContentView(R.layout.activity_generate);
        ImageView imageViewpro=findViewById(R.id.imgr);
        imageViewpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(generate.this,MainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        final View fail,success;
        Button button,month;
        button=findViewById(R.id.button);
        month=findViewById(R.id.month);
        final TextView textView=findViewById(R.id.text);
        fail=findViewById(R.id.fail);
        success=findViewById(R.id.sucess);
        fail.setVisibility(View.VISIBLE);
        success.setVisibility(View.GONE);
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//Set default values
                Calendar calendar = Calendar.getInstance();
                yearSelected = calendar.get(Calendar.YEAR);
                monthSelected = calendar.get(Calendar.MONTH);

                MonthYearPickerDialogFragment dialogFragment = MonthYearPickerDialogFragment
                        .getInstance(monthSelected, yearSelected);

                dialogFragment.show(getSupportFragmentManager(), null);
                dialogFragment.setOnDateSetListener(new MonthYearPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int year, int monthOfYear) {

                             switch (monthOfYear){
                                 case 0:{
                                      dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                      derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"31";
                                      break;
                                 }
                                 case 1:{
                                     dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"29";
                                     break;

                                 }
                                 case 2:{
                                     dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"31";
                                     break;

                                 }
                                 case 3:{
                                     dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"30";
                                     break;

                                 }
                                 case 4:{
                                     dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"31";
                                     break;

                                 }
                                 case 5:{
                                     dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"30";
                                     break;

                                 }
                                 case 6:{
                                     dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"31";
                                     break;

                                 }
                                 case 7:{
                                     dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"31";
                                     break;

                                 }
                                 case 8:{
                                     dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"30";
                                     break;

                                 }
                                 case 9:{
                                     dehack=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+"0"+String.valueOf(monthOfYear+1)+"-"+"31";
                                     break;

                                 }
                                 case 10:{
                                     dehack=String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+"30";
                                     break;

                                 }
                                 case 11:{
                                     dehack=String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+"00";
                                     derob=String.valueOf(year)+"-"+String.valueOf(monthOfYear+1)+"-"+"31";
                                     break;

                                 }
                                 default:{
                                     dehack="";
                                     derob="";
                                     break;

                                 }
                             }



                            textView.setText(String.valueOf(monthOfYear)+" "+String.valueOf(year));
                            kaleb=String.valueOf(monthOfYear)+" "+String.valueOf(year);
                           // Toast.makeText(generate.this,String.valueOf(year)+String.valueOf(monthOfYear),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kaleb==null||kaleb.length()<=1){
                    Toast.makeText(generate.this,"Please enter the date correctly",Toast.LENGTH_SHORT).show();
                }
                else {

                    if (!derob.isEmpty()){

                        run(dehack,derob,yearSelected,monthSelected);
                    }
                }

            }
        });



    }
    public void run(String dehack,String derob,int yearselected,int monthselected){
        //Toast.makeText(getApplicationContext(),dehack +" "+derob,Toast.LENGTH_LONG).show();
        SalaryDatabase db = new SalaryDatabase(this);
        fixeddatabase db1 = new fixeddatabase(this);
        loandatabase db2=new loandatabase(this);
        onedatabase db3=new onedatabase(this);
        twodatabase db4=new twodatabase(this);
        threedatabase db5=new threedatabase(this);
        fourdatabase db6=new fourdatabase(this);
        fivedatabase db7=new fivedatabase(this);
       DatabaseHelper db8=new DatabaseHelper(this);
        final Cursor cursor = db.getsalarycustom(dehack,derob);
        final Cursor cursor1 = db1.getmaterialcustom(dehack,derob);
        final Cursor cursor2 = db2.getloancustom(dehack,derob);
        final Cursor cursor3 = db8.getproanydate(dehack,derob);
        final Cursor cursor4 = db8.getseranydate(dehack,derob);
        final Cursor cursor5 = db3.getanydate(dehack,derob);
        final Cursor cursor6 = db4.getanydate(dehack,derob);
        final Cursor cursor7 = db5.getanydate(dehack,derob);
        final Cursor cursor8 = db6.getanydate(dehack,derob);
        final Cursor cursor9 = db7.getanydate(dehack,derob);
       /* final Cursor cursor8 = db8.getanydate(dehack,derob);
        final Cursor cursor9 = db3.getanydate(dehack,derob);*/



        //now let's go baby
        if (cursor4.getCount()<=0 && cursor3.getCount()<=0){
            Toast.makeText(generate.this,"Data is empty ",Toast.LENGTH_SHORT).show();
        }
        else {
            View fail,success;
            fail=findViewById(R.id.fail);
            success=findViewById(R.id.sucess);
            fail.setVisibility(View.GONE);
            success.setVisibility(View.VISIBLE);
            File sd = Environment.getExternalStorageDirectory();
            String csvFile = "pixel.xls";

            File directory = new File(sd.getAbsolutePath());
            //create directory if not exist
            if (!directory.isDirectory()) {
                directory.mkdirs();
            }
            try {

                //file path
                File file = new File(directory, csvFile);
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setLocale(new Locale("en", "EN"));
                WritableWorkbook workbook;
                workbook = Workbook.createWorkbook(file, wbSettings);
                //Excel sheet name. 0 represents first sheet
                WritableSheet sheet = workbook.createSheet("salary", 5);
                WritableSheet sheet1 = workbook.createSheet("fixed material", 4);
                WritableSheet sheet2 = workbook.createSheet("loan", 3);
                WritableSheet sheet3 = workbook.createSheet("Product sold", 1);
                WritableSheet sheet4 = workbook.createSheet("Service provided", 0);
                WritableSheet sheet5 = workbook.createSheet("Profit sheet", 2);
                // column and row
                WritableCellFormat cFormat = new WritableCellFormat();
                WritableCellFormat cFormat1 = new WritableCellFormat();
                WritableCellFormat cFormat2 = new WritableCellFormat();
                WritableCellFormat cFormat3 = new WritableCellFormat();
                cFormat.setBackground(Colour.CORAL);
                cFormat2.setBackground(Colour.BROWN);
                cFormat3.setBackground(Colour.CORAL);
                cFormat1.setBackground(Colour.BLUE2);
                WritableFont font = new WritableFont(WritableFont.TIMES, 12, WritableFont.BOLD);
                WritableFont font1 = new WritableFont(WritableFont.TAHOMA, 18, WritableFont.BOLD);
                font.setColour(Colour.WHITE);
                font1.setColour(Colour.WHITE);
                cFormat.setFont(font);
                cFormat1.setFont(font);
                cFormat2.setFont(font);
                cFormat3.setFont(font);
               // cFormat.setAlignment(Alignment.CENTRE);
                //cFormat1.setAlignment(Alignment.CENTRE);
               // cFormat.setShrinkToFit(true);
               // cFormat1.setShrinkToFit(true);
                //cFormat2.setShrinkToFit(true);
                for (int i=0; i<10; i++){
                    sheet.setColumnView(i,20);
                    sheet2.setColumnView(i,20);
                    sheet3.setColumnView(i,20);
                    sheet4.setColumnView(i,20);
                    sheet5.setColumnView(i,30);

                }
                for (int i=0; i<10; i++){
                    sheet1.setColumnView(i,18);
                }

                //salary sheet
                sheet.addCell(new Label(0, 0, "No",cFormat));
                sheet.addCell(new Label(1, 0, "Name",cFormat));
                sheet.addCell(new Label(2, 0, "Type of job",cFormat));
                sheet.addCell(new Label(3, 0, "Labor Cost",cFormat));
                sheet.addCell(new Label(4, 0, "Taxes in %",cFormat));
                sheet.addCell(new Label(5, 0, "Payment",cFormat));
                sheet.addCell(new Label(6, 0, "description",cFormat));
                int salaryexpense=0;
                if (cursor.moveToFirst()) {
                    do {
                        int id=cursor.getInt(cursor.getColumnIndex(Salary.COLUMN_ID));
                        String nam= cursor.getString(cursor.getColumnIndex(Salary.COLUMN_NAME));
                        String job=cursor.getString(cursor.getColumnIndex(Salary.COLUMN_JOBYPE));
                        int salary= cursor.getInt(cursor.getColumnIndex(Salary.COLUMN_SALARY));
                        int tax =cursor.getInt(cursor.getColumnIndex(Salary.COLUMN_Tax));
                        String des=cursor.getString(cursor.getColumnIndex(Salary.COLUMN_DESCRIPTION));

                        int i = cursor.getPosition() + 1;
                        sheet.addCell(new Label(0, i, Integer.toString(id)));
                        sheet.addCell(new Label(1, i, nam));
                        sheet.addCell(new Label(2, i, job));
                        sheet.addCell(new Label(3, i, Integer.toString(salary)));
                        sheet.addCell(new Label(4, i, Integer.toString(tax)));
                        double paid=(tax/100)*salary;
                        int hhh=90;
                        salaryexpense=salaryexpense+salary;
                        sheet.addCell(new Label(5, i, Double.toString(paid)));
                        sheet.addCell(new Label(6, i, des));

                    } while (cursor.moveToNext());
                }
//fixed material

                sheet1.addCell(new Label(0, 0, "Number",cFormat1));
                sheet1.addCell(new Label(1, 0, "Purchase Year",cFormat1));
                sheet1.addCell(new Label(2, 0, "Material Type",cFormat1));
                sheet1.addCell(new Label(3, 0, "Purchase Cost",cFormat1));
                sheet1.addCell(new Label(4, 0, "Depreciation year",cFormat1));
                sheet1.addCell(new Label(5, 0, "Total depreciation per year",cFormat1));
                sheet1.addCell(new Label(6, 0, "Rest Value",cFormat1));
                sheet1.addCell(new Label(7, 0, "Monthly Depreciation Cost",cFormat1));
                sheet1.addCell(new Label(8, 0, "Description",cFormat1));
                int mondepexpe=0;
                if (cursor1.moveToFirst()) {
                    do {
                        String date=cursor1.getString(cursor1.getColumnIndex(Fixed.COLUMN_TIMESTAMP));
                        int id=cursor1.getInt(cursor1.getColumnIndex(Fixed.COLUMN_ID));
                        String num= cursor1.getString(cursor1.getColumnIndex(Fixed.COLUMN_Number));
                        String name=cursor1.getString(cursor1.getColumnIndex(Fixed.COLUMN_NAME));
                        int price= cursor1.getInt(cursor1.getColumnIndex(Fixed.COLUMN_PRICE));
                        int year =cursor1.getInt(cursor1.getColumnIndex(Fixed.COLUMN_YEAR));
                        String des=cursor1.getString(cursor1.getColumnIndex(Fixed.COLUMN_DESCRIPTION));

                        int i = cursor1.getPosition() + 1;
                        sheet1.addCell(new Label(0, i, String.valueOf(id)));
                        sheet1.addCell(new Label(1, i, formatDate(date)));
                        sheet1.addCell(new Label(2, i, name));
                        sheet1.addCell(new Label(3, i, String.valueOf(price)));
                        sheet1.addCell(new Label(4, i, String.valueOf(year) ));
                        int yearcst=price/year;
                        int mondc=yearcst/12;
                        int restvalue=price-yearcst;

                        sheet1.addCell(new Label(5, i,String.valueOf(yearcst)));
                        sheet1.addCell(new Label(6, i,String.valueOf(restvalue)));
                        sheet1.addCell(new Label(7, i,String.valueOf(mondc)));
                        sheet1.addCell(new Label(8, i,des));
                        mondepexpe=mondepexpe+mondc;
                    } while (cursor1.moveToNext());
                }

//loan sheet


                //




                //



                sheet2.addCell(new Label(0, 0, "Name",cFormat2));
                sheet2.addCell(new Label(1, 0, "Date of payment",cFormat2));
                sheet2.addCell(new Label(2, 0, "Name of company",cFormat2));
                sheet2.addCell(new Label(3, 0, "Amount of loan",cFormat2));
                sheet2.addCell(new Label(4, 0, "Interest",cFormat2));
                sheet2.addCell(new Label(5, 0, "monthly payment",cFormat2));
               // sheet2.addCell(new Label(6, 0, "Remaining",cFormat2));
                sheet2.addCell(new Label(6, 0, "Description",cFormat2));
                int loanexpense=0;
                if (cursor2.moveToFirst()) {
                    do {
                        String date=cursor2.getString(cursor2.getColumnIndex(Loan.COLUMN_TIMESTAMP));
                        int id=cursor2.getInt(cursor2.getColumnIndex(Loan.COLUMN_ID));
                        String name=cursor2.getString(cursor2.getColumnIndex(Loan.COLUMN_NAME));
                        int amount= cursor2.getInt(cursor2.getColumnIndex(Loan.COLUMN_AMOUNT));
                        int intereset =cursor2.getInt(cursor2.getColumnIndex(Loan.COLUMN_INTEREST));
                        String des=cursor2.getString(cursor2.getColumnIndex(Loan.COLUMN_DESCRIPTION));
                        int payment=cursor2.getInt(cursor2.getColumnIndex(Loan.COLUMN_PAYMENT));
                        int princ=cursor2.getInt(cursor2.getColumnIndex(Loan.COLUMN_PRINCIPAL));


                        int i = cursor2.getPosition() + 1;
                        sheet2.addCell(new Label(0, i, String.valueOf(id)));
                        sheet2.addCell(new Label(1, i, formatDate(date)));
                        sheet2.addCell(new Label(2, i, name));
                        sheet2.addCell(new Label(3, i, String.valueOf(amount)));
                        sheet2.addCell(new Label(4, i, String.valueOf(intereset) ));
                        int sum=payment+princ;
                        double a=(intereset /100);
                        double total=amount *a;
                        int remain=amount-sum;

                        int lol=princ+payment;
                        sheet2.addCell(new Label(5, i,String.valueOf(sum)));

                        //sheet2.addCell(new Label(6, i,String.valueOf(remain)));
                        sheet2.addCell(new Label(6, i,des));
                        loanexpense=loanexpense+lol;
                    } while (cursor2.moveToNext());
                }

//daily sale

                sheet3.addCell(new Label(0, 0, "Number",cFormat2));
                sheet3.addCell(new Label(1, 0, "Date ",cFormat2));
                sheet3.addCell(new Label(2, 0, "Number of item ",cFormat2));
                sheet3.addCell(new Label(3, 0, "product cost",cFormat2));
                sheet3.addCell(new Label(4, 0, "selling price",cFormat2));
                sheet3.addCell(new Label(5, 0, "Total profit",cFormat2));
                int revepro=0;
                int losspro=0;
                if (cursor3.moveToFirst()) {
                    do {
                        String date=cursor3.getString(cursor3.getColumnIndex(Note.COLUMN_TIMESTAMP));
                        int id=cursor3.getInt(cursor3.getColumnIndex(Note.COLUMN_ID));
                        int numb =cursor3.getInt(cursor3.getColumnIndex(Note.COLUMN_PRONUM));
                        int pricesold =cursor3.getInt(cursor3.getColumnIndex(Note.COLUMN_PRICESOLD));
                        int pricebuy =cursor3.getInt(cursor3.getColumnIndex(Note.COLUMN_PRICEBUY));

                        int profit=numb*(pricesold-pricebuy);
                        int ff=numb*pricesold;
                        int gg=numb*pricebuy;
                        revepro=revepro+ff;
                        losspro=losspro+gg;

                        int i = cursor3.getPosition() + 1;
                        sheet3.addCell(new Label(0, i, String.valueOf(id)));
                        sheet3.addCell(new Label(1, i, formatDate(date)));
                        sheet3.addCell(new Label(2, i, String.valueOf(numb)));
                        sheet3.addCell(new Label(3, i, String.valueOf(pricebuy)));
                        sheet3.addCell(new Label(4, i, String.valueOf(pricesold)));
                        sheet3.addCell(new Label(5, i, String.valueOf(profit)));
                    } while (cursor3.moveToNext());
                }

                //service boy
                sheet4.addCell(new Label(0, 0, "Service name",cFormat2));
                sheet4.addCell(new Label(1, 0, "Date ",cFormat2));
                sheet4.addCell(new Label(2, 0, "labor income ",cFormat2));
                sheet4.addCell(new Label(3, 0, "service expense",cFormat2));
                sheet4.addCell(new Label(4, 0, "Total profit",cFormat2));
                sheet4.addCell(new Label(5, 0, "description",cFormat2));
                int  revenue=0;
                int  lossser=0;
                if (cursor4.moveToFirst()) {
                    do {
                        String date=cursor4.getString(cursor4.getColumnIndex(Service.COLUMN_TIMESTAMP));
                        String name=cursor4.getString(cursor4.getColumnIndex(Service.COLUMN_SERVICE));
                        int id=cursor4.getInt(cursor4.getColumnIndex(Service.COLUMN_ID));
                        int labor =cursor4.getInt(cursor4.getColumnIndex(Service.COLUMN_PAYMENT));
                        int expense =cursor4.getInt(cursor4.getColumnIndex(Service.COLUMN_EXPENSE));
                        String  desc =cursor4.getString(cursor4.getColumnIndex(Service.COLUMN_DESCRIPTION));

                        int profit=labor-expense;
                        revenue=revenue+labor;
                        lossser=lossser+expense;
                        int i = cursor4.getPosition() + 1;
                        sheet4.addCell(new Label(0, i,name));
                        sheet4.addCell(new Label(1, i, formatDate(date)));
                        sheet4.addCell(new Label(2, i, String.valueOf(labor)));
                        sheet4.addCell(new Label(3, i, String.valueOf(expense)));
                        sheet4.addCell(new Label(4, i, String.valueOf(profit)));
                        sheet4.addCell(new Label(5, i, desc));
                    } while (cursor4.moveToNext());
                }





                //profit sheet
                SharedPreferences preferences = getSharedPreferences("namer",MODE_PRIVATE);
                String namer =preferences.getString("name", "");
                sheet5.addCell(new Label(0, 0, "Name of plsp",cFormat3));
                sheet5.addCell(new Label(0, 1, "Report time schedule ",cFormat3));
                sheet5.addCell(new Label(0, 2, "Revenue from service",cFormat1));
                sheet5.addCell(new Label(0, 3, "Revenue from sale of material",cFormat1));
                sheet5.addCell(new Label(0, 4, "Total revenue",cFormat2));
                sheet5.addCell(new Label(0, 5, ""));
                sheet5.addCell(new Label(0, 6, ""));
                sheet5.addCell(new Label(0, 7, "Expenses",cFormat3));
                sheet5.addCell(new Label(0, 8, "Service Expense",cFormat2));
                sheet5.addCell(new Label(0, 9, "product cost ",cFormat2));
                sheet5.addCell(new Label(0, 10, "loan expense ",cFormat2));
                sheet5.addCell(new Label(0, 11, "salary expense  ",cFormat2));
                sheet5.addCell(new Label(0, 12, "fixed material ",cFormat2));
                sheet5.addCell(new Label(0, 13, ""));
                sheet5.addCell(new Label(0, 14, "other Expenses",cFormat3));
                sheet5.addCell(new Label(0, 15, "Rent Expense",cFormat1));
                sheet5.addCell(new Label(0, 16, "Supply Expense ",cFormat1));
                sheet5.addCell(new Label(0, 17, "Advertizement Expense ",cFormat1));
                sheet5.addCell(new Label(0, 18, "Payable Expense",cFormat1));
                sheet5.addCell(new Label(0, 19, "other  Expense",cFormat1));
                sheet5.addCell(new Label(0, 20, "Total  Expense sum",cFormat1));


               /* sheet5.addCell(new Label(0, 4, "Expenses",cFormat2));
                sheet5.addCell(new Label(0, 5, "Rent Expense",cFormat1));
                sheet5.addCell(new Label(0, 6, "Supply Expense ",cFormat1));
                sheet5.addCell(new Label(0, 7, "Advertizement Expense ",cFormat1));
                sheet5.addCell(new Label(0, 8, "Payable Expense",cFormat1));
                sheet5.addCell(new Label(0, 9, "other  Expense",cFormat1));*/
                sheet5.addCell(new Label(1, 0, namer,cFormat3));
                sheet5.addCell(new Label(1, 1, "Report of " +(monthselected+1)+" " +yearselected,cFormat3));
                sheet5.addCell(new Label(1, 2, String.valueOf(revenue)));
                sheet5.addCell(new Label(1, 3, String.valueOf(revepro)));
                int rentexpense=0;
                if (cursor5.moveToFirst()) {
                    do {
                        int id=cursor5.getInt(cursor5.getColumnIndex(One.COLUMN_ID));
                       // String nam= cursor3.getString(cursor3.getColumnIndex(One.COLUMN_RENT));
                        //String job=cursor3.getString(cursor3.getColumnIndex(Salary.COLUMN_JOBYPE));
                        int rent= cursor5.getInt(cursor5.getColumnIndex(One.COLUMN_RENT));
                        //int tax =cursor3.getInt(cursor3.getColumnIndex(Salary.COLUMN_Tax));
                        String des=cursor5.getString(cursor5.getColumnIndex(One.COLUMN_DESCRIPTION));

                        int i = cursor5.getPosition() + 1;
                        rentexpense=rentexpense+rent;

                    } while (cursor5.moveToNext());
                }
                int supplyexpense=0;
                if (cursor6.moveToFirst()) {
                    do {
                        int id=cursor6.getInt(cursor6.getColumnIndex(Two.COLUMN_ID));
                        // String nam= cursor3.getString(cursor3.getColumnIndex(One.COLUMN_RENT));
                        //String job=cursor3.getString(cursor3.getColumnIndex(Salary.COLUMN_JOBYPE));
                        int supply= cursor6.getInt(cursor6.getColumnIndex(Two.COLUMN_SUPPLY));
                        //int tax =cursor3.getInt(cursor3.getColumnIndex(Salary.COLUMN_Tax));
                        String des=cursor6.getString(cursor6.getColumnIndex(Two.COLUMN_DESCRIPTION));

                        int i = cursor6.getPosition() + 1;
                        supplyexpense=supplyexpense+supply;
                    } while (cursor6.moveToNext());
                }
                int advertexpense=0;
                if (cursor7.moveToFirst()) {
                    do {
                        int id=cursor7.getInt(cursor7.getColumnIndex(Three.COLUMN_ID));
                        // String nam= cursor3.getString(cursor3.getColumnIndex(One.COLUMN_RENT));
                        //String job=cursor3.getString(cursor3.getColumnIndex(Salary.COLUMN_JOBYPE));
                        int advert= cursor7.getInt(cursor7.getColumnIndex(Three.COLUMN_ADVERTIZE));
                        //int tax =cursor3.getInt(cursor3.getColumnIndex(Salary.COLUMN_Tax));
                        String des=cursor7.getString(cursor7.getColumnIndex(Three.COLUMN_DESCRIPTION));

                        int i = cursor7.getPosition() + 1;
                        advertexpense=advertexpense+advert;
                    } while (cursor7.moveToNext());
                }
                int payableexpense=0;
                if (cursor8.moveToFirst()) {
                    do {
                        int id=cursor8.getInt(cursor8.getColumnIndex(Four.COLUMN_ID));
                        // String nam= cursor3.getString(cursor3.getColumnIndex(One.COLUMN_RENT));
                        //String job=cursor3.getString(cursor3.getColumnIndex(Salary.COLUMN_JOBYPE));
                        int payable= cursor8.getInt(cursor8.getColumnIndex(Four.COLUMN_PAYABLE));
                        //int tax =cursor3.getInt(cursor3.getColumnIndex(Salary.COLUMN_Tax));
                        String des=cursor8.getString(cursor8.getColumnIndex(Four.COLUMN_DESCRIPTION));

                        int i = cursor8.getPosition() + 1;
                        payableexpense=payableexpense+payable;
                    } while (cursor8.moveToNext());
                }
                int otherexpense=0;
                if (cursor9.moveToFirst()) {
                    do {
                        int id=cursor9.getInt(cursor9.getColumnIndex(Five.COLUMN_ID));
                        // String nam= cursor3.getString(cursor3.getColumnIndex(One.COLUMN_RENT));
                        //String job=cursor3.getString(cursor3.getColumnIndex(Salary.COLUMN_JOBYPE));
                        int other= cursor9.getInt(cursor9.getColumnIndex(Five.COLUMN_Other));
                        //int tax =cursor3.getInt(cursor3.getColumnIndex(Salary.COLUMN_Tax));
                        String des=cursor9.getString(cursor9.getColumnIndex(Five.COLUMN_DESCRIPTION));

                        int i = cursor9.getPosition() + 1;
                        otherexpense=otherexpense+other;
                    } while (cursor9.moveToNext());
                }


                sheet5.addCell(new Label(1, 4, String.valueOf(revenue+revepro)));
                sheet5.addCell(new Label(1, 5, ""));
                sheet5.addCell(new Label(1, 6, ""));
                sheet5.addCell(new Label(1, 8, String.valueOf(lossser)));
                sheet5.addCell(new Label(1, 9, String.valueOf(losspro)));
                sheet5.addCell(new Label(1, 10, String.valueOf(loanexpense)));
                sheet5.addCell(new Label(1, 11, String.valueOf(salaryexpense)));
                sheet5.addCell(new Label(1, 12, String.valueOf(mondepexpe)));
                sheet5.addCell(new Label(1, 15, String.valueOf(rentexpense)));
                sheet5.addCell(new Label(1, 16, String.valueOf(supplyexpense)));
                sheet5.addCell(new Label(1, 17, String.valueOf(advertexpense)));
                sheet5.addCell(new Label(1, 18, String.valueOf(payableexpense)));
                sheet5.addCell(new Label(1, 19, String.valueOf(otherexpense)));
                sheet5.addCell(new Label(1, 20, String.valueOf(lossser+losspro+loanexpense+salaryexpense+mondepexpe+rentexpense+supplyexpense+advertexpense+payableexpense+otherexpense)));



              /*  sheet5.addCell(new Label(1, 5, String.valueOf(rentexpense)));
                sheet5.addCell(new Label(1, 6, String.valueOf(supplyexpense)));
                sheet5.addCell(new Label(1, 7, String.valueOf(advertexpense)));
                sheet5.addCell(new Label(1, 8, String.valueOf(payableexpense)));
                sheet5.addCell(new Label(1, 9, String.valueOf(otherexpense)));*/







/*
                if (cursor1.moveToFirst()) {
                    do {
                        String date=cursor1.getString(cursor1.getColumnIndex(Fixed.COLUMN_TIMESTAMP));
                        int id=cursor1.getInt(cursor1.getColumnIndex(Fixed.COLUMN_ID));
                        String num= cursor1.getString(cursor1.getColumnIndex(Fixed.COLUMN_Number));
                        String name=cursor1.getString(cursor1.getColumnIndex(Fixed.COLUMN_NAME));
                        int price= cursor1.getInt(cursor1.getColumnIndex(Fixed.COLUMN_PRICE));
                        int year =cursor1.getInt(cursor1.getColumnIndex(Fixed.COLUMN_YEAR));
                        String des=cursor1.getString(cursor1.getColumnIndex(Fixed.COLUMN_DESCRIPTION));

                        int i = cursor1.getPosition() + 1;
                        sheet5.addCell(new Label(1, i, formatDate(date)));
                        sheet5.addCell(new Label(2, i, name));
                        sheet5.addCell(new Label(3, i, String.valueOf(price)));
                        sheet5.addCell(new Label(4, i, String.valueOf(year) ));
                        int yearcst=price/year;
                        int mondc=yearcst/12;
                        int restvalue=price-yearcst;
                        sheet5.addCell(new Label(5, i,String.valueOf(yearcst)));
                        sheet5.addCell(new Label(6, i,String.valueOf(restvalue)));
                        sheet5.addCell(new Label(7, i,String.valueOf(mondc)));
                        sheet5.addCell(new Label(8, i,des));

                    } while (cursor1.moveToNext());
                }*/



                //closing curso
                //r

                cursor1.close();
                cursor.close();
                cursor2.close();
                cursor3.close();
                cursor4.close();
                cursor5.close();
                cursor6.close();
                cursor7.close();
                cursor8.close();
                cursor9.close();
                workbook.write();
                workbook.close();
                Toast.makeText(getApplication(),
                        "Data Exported in a Excel Sheet", Toast.LENGTH_SHORT).show();
            } catch (RowsExceededException e1) {
                e1.printStackTrace();
            } catch (WriteException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }



//profit sheet





    }
    @Override
    public void onBackPressed() {
        if (true){
            //
        } else {
            super.onBackPressed();
        }
    }
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM yyyy");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
    public void gener(){

    }
    public int sum(int a,int b,int c){
        return (a-(b*c));
    }


}
