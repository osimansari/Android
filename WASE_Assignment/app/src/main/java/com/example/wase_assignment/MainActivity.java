package com.example.wase_assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import android.text.format.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker,btn_calc;
    EditText txtDate,resAge, resDay;
    private int mYear, mMonth, mDay;
    public int sYear, sMonth, sDay;
    public String dayOfTheWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btn_calc=(Button)findViewById(R.id.cal_btn);

        txtDate=(EditText)findViewById(R.id.in_date);
        resAge=(EditText)findViewById(R.id.res_age);
        resDay=(EditText)findViewById(R.id.res_day);


        btnDatePicker.setOnClickListener(this);
        btn_calc.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            sDay=dayOfMonth;
                            sMonth=monthOfYear+1;
                            sYear=year;
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btn_calc)
        {
            resAge.setText(""+getAge(sYear,sMonth,sDay));


            resDay.setText(""+getDayOfTheWeek());
        }
    }

    public String getAge(int year, int month, int day) {
        //calculating age from dob
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return ""+age;
    }

    public String getDayOfTheWeek()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(txtDate.getText().toString());
            dayOfTheWeek = (String) DateFormat.format("EEEE", date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfTheWeek;
    }

}
