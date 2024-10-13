package com.example.app_dktb;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class bedroom extends AppCompatActivity {
    TextView tv_seekbar;
    SeekBar seekbar;
    ImageView TiVi_icon,Lampp_icon,AirConditon_icon;

    AlertDialog.Builder builderDiaglog;
    AlertDialog alertDialog;
    SessionManager sessionManager;

    Switch sw_TiVi, sw_Lampp, sw_AirConditon;
    boolean stateTiVi=false;
    boolean stateLampp=false;
    boolean stateAirConditon=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bedroom);
        sessionManager = new SessionManager(getApplication());
        init();

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv_seekbar.setText(""+i +"°C");


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        TiVi_icon=findViewById(R.id.tvicon);
        Lampp_icon=findViewById(R.id.lampicon);
        AirConditon_icon=findViewById(R.id.imageaircondition);
        sw_TiVi=findViewById(R.id.swTvv);
        sw_Lampp=findViewById(R.id.swlampp);
        sw_AirConditon=findViewById(R.id.switchairconditon);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();

//điều khiển on off của TV
        //firebase thay đổi làm tv on off
        databaseReference.child("Bed_Room").child("TV").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if(value.equals("ON")){
                    stateTiVi=true; //bật TV
                    TiVi_icon.setImageResource(R.drawable.tv_on);
                    sw_TiVi.setChecked(true);
                }
                else if(value.equals("OFF")){
                    stateTiVi=false; //tắt TV
                    TiVi_icon.setImageResource(R.drawable.tv);
                    sw_TiVi.setChecked(false);
                }
                //switch thay đổi tv on off
                sw_TiVi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked) {
                            stateTiVi=true;
                            TiVi_icon.setImageResource(R.drawable.tv_on);
                            databaseReference.child("Bed_Room").child("TV").setValue("ON");
                        }
                        else {
                            stateTiVi=false;
                            TiVi_icon.setImageResource(R.drawable.tv);
                            databaseReference.child("Bed_Room").child("TV").setValue("OFF");
                        }
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//điều khiển on off của lamp
        //firebase thay đổi làm lamp on off
        databaseReference.child("Bed_Room").child("Lamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if(value.equals("ON")){
                    stateLampp=true; //bật TV
                    Lampp_icon.setImageResource(R.drawable.lamp_on);
                    sw_Lampp.setChecked(true);
                }
                else if(value.equals("OFF")){
                    stateLampp=false; //tắt TV
                    Lampp_icon.setImageResource(R.drawable.lamp);
                    sw_Lampp.setChecked(false);
                }
                //switch thay đổi Lamp on off
                sw_Lampp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked) {
                            stateLampp=true;
                            Lampp_icon.setImageResource(R.drawable.lamp_on);
                            databaseReference.child("Bed_Room").child("Lamp").setValue("ON");
                        }
                        else {
                            stateLampp=false;
                            Lampp_icon.setImageResource(R.drawable.lamp);
                            databaseReference.child("Bed_Room").child("Lamp").setValue("OFF");
                        }
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//điều khiển on off của air condition
        //firebase thay đổi làm tv on off
        databaseReference.child("Bed_Room").child("AirCondition").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if(value.equals("ON")){
                    stateAirConditon=true; //bật TV
                    AirConditon_icon.setImageResource(R.drawable.air_condition_on);
                    sw_AirConditon.setChecked(true);
                }
                else if(value.equals("OFF")){
                    stateAirConditon=false; //tắt TV
                    AirConditon_icon.setImageResource(R.drawable.air_condition);
                    sw_AirConditon.setChecked(false);
                }
                //switch thay đổi tv on off
                sw_AirConditon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked) {
                            stateAirConditon=true;
                            AirConditon_icon.setImageResource(R.drawable.air_condition_on);
                            databaseReference.child("Bed_Room").child("AirCondition").setValue("ON");
                        }
                        else {
                            stateAirConditon=false;
                            AirConditon_icon.setImageResource(R.drawable.air_condition);
                            databaseReference.child("Bed_Room").child("AirCondition").setValue("OFF");
                        }
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.homeaction:
                        //chuyển trang dashboard
                        dashboard_page();
                        break;
                    case R.id.personaction:
                        // chuyển trang profile
                        profile_page();
                        break;
                    case R.id.logoutaction:
                        showAlertDialog(R.layout.dialog);
                        break;

                }
                return false;
            }

            public void dashboard_page(){
                Intent intent=new Intent(bedroom.this,room.class);
                startActivity(intent);
            }

            public void profile_page(){
                Intent intent=new Intent(bedroom.this,profile.class);
                startActivity(intent);
            }

            private void showAlertDialog(int dialog) {
                builderDiaglog = new AlertDialog.Builder(bedroom.this);
                View layoutView = getLayoutInflater().inflate(dialog,null);
                Button btnlogout = layoutView.findViewById(R.id.btnlogout);
                Button btnCancel = layoutView.findViewById(R.id.btncancel);

                builderDiaglog.setView(layoutView);
                alertDialog = builderDiaglog.create();
                alertDialog.show();

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                btnlogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sessionManager.SetLogin(false);
                        Intent intent1 = new Intent(getApplication(),login.class);
                        startActivity(intent1);
                        finish();
//                        alertDialog.dismiss();
                    }
                });
            }
        });

    }
    private void init(){
        tv_seekbar = findViewById(R.id.tv_seekbar);
        seekbar = findViewById(R.id.seekBar);
    }

}