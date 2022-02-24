
package com.example.budgeit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner monthSpinner;
    private Spinner typeSpinner;
    private Spinner yearSpinner;
    private Info[]  storedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        TranType type2 = new TranType("Savings");
        typeList.add(type2);
        TranType type3 = new TranType("Bill");
        typeList.add(type3);
        TranType type4 = new TranType("Need");
        typeList.add(type4);
        TranType type5 = new TranType("Want");
        typeList.add(type5);

        ArrayAdapter<TranType> adapter1 = new ArrayAdapter<TranType>(this, android.R.layout.simple_spinner_dropdown_item, typeList);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter1);

        //monthSpinner.getSelectedItem().toString()
        //typeSpinner.getSelectedItem().toString()
    }
}