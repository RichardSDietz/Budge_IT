
package com.example.budgeit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner monthSpinner;
    private Spinner typeSpinner;
    private Spinner yearSpinner;
    private Spinner monthSpinnerCalc;
    private Spinner yearSpinnerCalc;
    EditText amount, description;
    Button confirm,checkData;
    DBHelper DB;
    TextView income, mandatory, luxury, leisure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database
        DB = new DBHelper(this);
        //EditTexts
        amount = findViewById(R.id.IncomeText);
        description = findViewById(R.id.descriptionText);
        //Buttons
        confirm = findViewById(R.id.confirmButton);
        checkData = findViewById(R.id.checkDataButton);
        //TextViews
        income = findViewById(R.id.IncomeCalc);
        mandatory = findViewById(R.id.MandatoryCalc);
        luxury = findViewById(R.id.LuxuryCalc);
        leisure = findViewById(R.id.LeisureCalc);
        //////MONTH SPINNER CODE
        monthSpinner = findViewById(R.id.monthSpinner);

        List<Month> monthList = new ArrayList<>();
        Month month1 = new Month("January");
        monthList.add(month1);
        Month month2 = new Month("February");
        monthList.add(month2);
        Month month3 = new Month("March");
        monthList.add(month3);
        Month month4 = new Month("April");
        monthList.add(month4);
        Month month5 = new Month("May");
        monthList.add(month5);
        Month month6 = new Month("June");
        monthList.add(month6);
        Month month7 = new Month("July");
        monthList.add(month7);
        Month month8 = new Month("August");
        monthList.add(month8);
        Month month9 = new Month("September");
        monthList.add(month9);
        Month month10 = new Month("October");
        monthList.add(month10);
        Month month11 = new Month("November");
        monthList.add(month11);
        Month month12 = new Month("December");
        monthList.add(month12);

        ArrayAdapter<Month> adapter = new ArrayAdapter<Month>(this, android.R.layout.simple_spinner_dropdown_item, monthList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter);

        //////YEAR SPINNER CODE
        yearSpinner = findViewById(R.id.yearSpinner);

        int holdYear = Calendar.getInstance().get(Calendar.YEAR);

        //Creates year list
        List<Year> yearList = new ArrayList<>();
        //Fills the year list
        for(int i = 0; i < 9; i++){
            Year year = new Year(String.valueOf(holdYear+i));
            yearList.add(year);
        }

        ArrayAdapter<Year> adapter2 = new ArrayAdapter<Year>(this, android.R.layout.simple_spinner_dropdown_item, yearList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter2);

        //////TYPE SPINNER CODE
        typeSpinner = findViewById(R.id.typeSpinner);

        List<TranType> typeList = new ArrayList<>();
        TranType type1 = new TranType("Income");
        typeList.add(type1);
        TranType type2 = new TranType("Mandatory Bills");
        typeList.add(type2);
        TranType type3 = new TranType("Luxury Bills");
        typeList.add(type3);
        TranType type4 = new TranType("Leisure");
        typeList.add(type4);

        ArrayAdapter<TranType> adapter1 = new ArrayAdapter<TranType>(this, android.R.layout.simple_spinner_dropdown_item, typeList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter1);

        //Confirm button functionality
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monthOut = monthSpinner.getSelectedItem().toString();
                Integer yearOut = Integer.parseInt(yearSpinner.getSelectedItem().toString());
                String transTypeOut = typeSpinner.getSelectedItem().toString();
                Float amountOut = Float.parseFloat(amount.getText().toString());
                String descriptionOut = description.getText().toString();
                Boolean checkinsertdata = DB.insertexpensedata(monthOut, yearOut, transTypeOut, amountOut, descriptionOut);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        //////MONTH SPINNER CODE
        monthSpinnerCalc = findViewById(R.id.monthSpinnerCalc);

        ArrayAdapter<Month> adapterCalc = new ArrayAdapter<Month>(this, android.R.layout.simple_spinner_dropdown_item, monthList);
        adapterCalc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinnerCalc.setAdapter(adapterCalc);

        //////YEAR SPINNER CODE
        yearSpinnerCalc = findViewById(R.id.yearSpinnerCalc);

        int holdYearCalc = Calendar.getInstance().get(Calendar.YEAR);

        //Creates year list
        List<Year> yearListCalc = new ArrayList<>();
        //Fills the year list
        for(int i = 0; i < 9; i++){
            Year year = new Year(String.valueOf(holdYear+i));
            yearList.add(year);
        }

        ArrayAdapter<Year> adapter2Calc = new ArrayAdapter<Year>(this, android.R.layout.simple_spinner_dropdown_item, yearList);
        adapter2Calc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinnerCalc.setAdapter(adapter2Calc);

        //Check Data Button Functionality
        checkData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                income.setText("0");
                mandatory.setText("0");
                luxury.setText("0");
                leisure.setText("0");
                while(res.moveToNext()){
                    if (res.getString(0).equals(monthSpinnerCalc.getSelectedItem().toString()) && res.getString(1).equals(yearSpinnerCalc.getSelectedItem().toString()))
                    {
                        //Income Calc
                        if(res.getString(2).equals("Income")) {
                            Float finishedIncomeCalc;
                            finishedIncomeCalc = Float.parseFloat(res.getString(3));
                            finishedIncomeCalc = Float.parseFloat(income.getText().toString()) + finishedIncomeCalc;
                            income.setText(finishedIncomeCalc.toString());
                            Toast.makeText(MainActivity.this, "Income", Toast.LENGTH_SHORT).show();
                        }

                        //Mandatory Bills Calc
                        if(res.getString(2).equals("Mandatory Bills")) {
                            Float finishedMandCalc;
                            finishedMandCalc = Float.parseFloat(res.getString(3));
                            finishedMandCalc = Float.parseFloat(mandatory.getText().toString()) + finishedMandCalc;
                            mandatory.setText(finishedMandCalc.toString());
                            Toast.makeText(MainActivity.this, "Mandatory", Toast.LENGTH_SHORT).show();
                        }

                        //Luxury Bills Calc
                        if(res.getString(2).equals("Luxury Bills")) {
                            Float finishedLuxuryCalc;
                            finishedLuxuryCalc = Float.parseFloat(res.getString(3));
                            finishedLuxuryCalc = Float.parseFloat(luxury.getText().toString()) + finishedLuxuryCalc;
                            luxury.setText(finishedLuxuryCalc.toString());
                        }

                        //Leisure Calc
                        if(res.getString(2).equals("Leisure")) {
                            Float finishedLeisureCalc;
                            finishedLeisureCalc = Float.parseFloat(res.getString(3));
                            finishedLeisureCalc = Float.parseFloat(leisure.getText().toString()) + finishedLeisureCalc;
                            leisure.setText(finishedLeisureCalc.toString());
                        }
                    }
                }
            }
        });

        /*
        checkData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Month: "+res.getString(0)+"\n");
                    buffer.append("Year: "+res.getString(1)+"\n");
                    buffer.append("Transaction Type: "+res.getString(2)+"\n");
                    buffer.append("Amount: "+res.getString(3)+"\n");
                    buffer.append("Description: "+res.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Expense Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        */

        //monthSpinner.getSelectedItem().toString()
        //String month, Integer year, String transType, Float amount, String description
        //typeSpinner.getSelectedItem().toString()
    }
}