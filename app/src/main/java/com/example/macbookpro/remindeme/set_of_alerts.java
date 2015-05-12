package com.example.macbookpro.remindeme;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.ArrayList;
import java.lang.String;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;



public class set_of_alerts extends FragmentActivity implements OnItemClickListener {





    protected ListView l1;
    protected List<Listviewitem> items;
    private CustomeListviewAdapter adapter;





    public static long timePicked = -1;
    static Calendar c;
    static int h;
    static int m;



    protected List<String> platform=new ArrayList<>();
    protected List<String> platform2=new ArrayList<>();


    public static String pill = "";
    public static String patient= "";


    ImageButton Edit;
    public static String addingpill = "";
    public static String addingpatient= "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_of_alerts);
        Edit=(ImageButton) findViewById(R.id.edit);
        l1 = (ListView) findViewById(R.id.listView);
        items = new ArrayList<>();
        adapter = new CustomeListviewAdapter(this, items);
        l1.setAdapter(adapter);
        l1.setOnItemClickListener(this);








        //platform=new ArrayList<>();
        //platform2=new ArrayList<>();

        platform.add(" "); platform.add("Patric james");
        platform2.add(" "); platform2.add("Iron pills");
















    }





    public void adding(View view) {
        final AlertDialog.Builder alertSimple = new AlertDialog.Builder(set_of_alerts.this);
        alertSimple.setTitle("Set your alarm");
        alertSimple.setIcon(R.drawable.clock2);


        LayoutInflater INFLATER = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View chooseView = INFLATER.inflate(R.layout.spinnerlayout, null);

        Spinner pills_spinner;
        Spinner patients_spinner;
        ArrayAdapter<String> patient_adapter;
        ArrayAdapter<String> pills_adapter;


         pills_spinner = (Spinner) chooseView.findViewById(R.id.spiner);
         patients_spinner=(Spinner) chooseView.findViewById(R.id.spinner);
        Button but1 = (Button) chooseView.findViewById(R.id.dody);


        patient_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, platform);
        patient_adapter.setDropDownViewResource(R.layout.itemofspinner);
        patients_spinner.setAdapter(patient_adapter);
        patients_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                patient = tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                patient = "";
            }
        });





        pills_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, platform2);
        pills_adapter.setDropDownViewResource(R.layout.itemofspinner);
        pills_spinner.setAdapter(pills_adapter);


        pills_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                pill =  tv.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragments();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }});


        alertSimple.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                adapter.notifyDataSetChanged();

                if (timePicked != -1) {


                    AlarmReciver alarm = new AlarmReciver();

                    if (alarm != null) {

                        alarm.setOnetimeTimer(set_of_alerts.this, timePicked);

                        items.add(new Listviewitem() {{
                            bd = R.drawable.clock;
                            Title = "Alarm is set on " + h + ":" + m;
                            subti = "You should give " + pill + " ''Please be Specific about timing.'' " + "for Patient " + patient+".";
                        }});
                        Toast.makeText(getApplicationContext(), "time is set", Toast.LENGTH_LONG).show();
                    } else {
                        //warn the user for ALARM not set?
                        Toast.makeText(getApplicationContext(), "time is not set", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }).setNegativeButton("cancel", null);











        alertSimple.setView(chooseView);
        alertSimple.create();
        alertSimple.show();

    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Listviewitem it = items.get(position);
        final AlertDialog.Builder dle = new AlertDialog.Builder(set_of_alerts.this);
        dle.setTitle("DELETE ALERT");
        dle.setIcon(R.drawable.clock2);
        dle.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                items.remove(it);
                adapter = new CustomeListviewAdapter(set_of_alerts.this, items);
                l1.setAdapter(adapter);


            }});

        //alarm.delala(MainActivity2Activity.this);
        dle.create();
        dle.show();

    }


















   /*public void modify(View view) {

        final AlertDialog.Builder tomodify = new AlertDialog.Builder(set_of_alerts.this);
        tomodify.setTitle("Edit profile");
        tomodify.setIcon(R.drawable.edit);

        LayoutInflater INFLATER2 = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View editView = INFLATER2.inflate(R.layout.toinsert, null);

        Spinner addpill = (Spinner) editView.findViewById(R.id.pills);
        Spinner addpatient = (Spinner) editView.findViewById(R.id.patients);



        ArrayAdapter<CharSequence> adapt1 = ArrayAdapter.createFromResource(this, R.array.patients, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapt2 = ArrayAdapter.createFromResource(this, R.array.pills, android.R.layout.simple_spinner_dropdown_item);

        adapt1.setDropDownViewResource(R.layout.itemofspinner);
        adapt2.setDropDownViewResource(R.layout.itemofspinner);

        addpill.setAdapter(adapt1);
        addpatient.setAdapter(adapt2);







        patient_adapter.notifyDataSetChanged();
        pills_adapter.notifyDataSetChanged();

        addpill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                addingpill = tv.getText().toString();

                platform.add(addingpill);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addingpill = " ";

            }
        });




        addpatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view;
                addingpatient = tv.getText().toString();


                platform2.add(addingpatient);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                addingpatient = "  ";

            }


        });



        tomodify.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                pills_adapter = new ArrayAdapter<>(set_of_alerts.this, android.R.layout.simple_spinner_item, platform2);
                pills_spinner.setAdapter(pills_adapter);

                patient_adapter = new ArrayAdapter<>(set_of_alerts.this, android.R.layout.simple_spinner_item, platform);
                patients_spinner.setAdapter(patient_adapter);




            }
        });

        tomodify.setNegativeButton("cancel", null);


        tomodify.setView(editView);
        tomodify.create();
        tomodify.show();
    }*/



















    public static class TimePickerFragments extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current time as the default values for the picker
            c = new GregorianCalendar();
            c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

// Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            //Calendar n = new GregorianCalendar();
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            final long time=c.getTimeInMillis();
            timePicked = time;
            h=hourOfDay;
            m=minute;}}









    class Listviewitem {
        public int bd;
        public String Title;
        public String subti;
    }




}

