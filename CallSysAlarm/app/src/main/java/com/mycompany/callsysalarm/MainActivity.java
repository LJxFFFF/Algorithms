package com.mycompany.callsysalarm;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Integer> arrayList = new ArrayList<>();
    private Button btn;
    private TimePicker timePicker;
    private EditText editText;
    private int hour = 0;
    private int minutes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList.add(Calendar.SUNDAY);
        arrayList.add(Calendar.SATURDAY);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.message);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minutes = minute;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn:
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, editText.getText().toString());
                intent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
                intent.putExtra(AlarmClock.EXTRA_DAYS, arrayList);
                startActivity(intent);
        }
    }

}
