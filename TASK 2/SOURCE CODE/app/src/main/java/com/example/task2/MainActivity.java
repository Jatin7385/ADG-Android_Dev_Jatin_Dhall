package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private CoordinatorLayout coordinatorLayout;
    private Button btn_snackBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_date = (Button) findViewById(R.id.button_date);
        Button btn_customAlert = (Button) findViewById(R.id.button_customAlert);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        btn_snackBar = findViewById(R.id.button_snackBar);
        Button btn_toast = (Button) findViewById(R.id.button_Toast);
        Button btn_ProgressBar = (Button) findViewById(R.id.button_ProgressBar);

        btn_customAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(),"date Picker");

            }
        });
        btn_snackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar();
            }
        });
        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });
        btn_ProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(5000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        findViewById(R.id.ballpulseindicator).setVisibility(View.VISIBLE);
                    }

                    public void onFinish() {
                        findViewById(R.id.ballpulseindicator).setVisibility(View.GONE);
                    }
                }.start();
        }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c= Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        Button btn_date = (Button) findViewById(R.id.button_date);
        btn_date.setText(currentDateString);
    }

    void showDialog()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.custom_alert,null);
        Button cancel = (Button) view.findViewById(R.id.button_cancel);
        AlertDialog alertDialog = new AlertDialog.Builder(this).setView(view).create();
        alertDialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
    public void showSnackbar(){
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "This is a Snackbar", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    public void showToast()
    {
        Toast.makeText(MainActivity.this,"This is a Toast",Toast.LENGTH_SHORT).show();
    }
}