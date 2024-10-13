package com.example.app_dktb;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class livingroom extends AppCompatActivity {
    AlertDialog.Builder builderDiaglog;
    AlertDialog alertDialog;

    SessionManager sessionManager;

    ImageView TV_icon,Lamp_icon,Camera_icon,Fan_icon;
    Switch sw_TV, sw_Lamp, sw_Camera,sw_Fan;
    boolean stateTV=false;
    boolean stateLamp=false;
    boolean stateCamera=false;
    boolean stateFan=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livingroom);
        TV_icon=findViewById(R.id.tiviicon);
        Lamp_icon=findViewById(R.id.lampicon);
        Camera_icon=findViewById(R.id.cameraicon);
        Fan_icon=findViewById(R.id.fanicon);
        sw_TV=findViewById(R.id.swTivi);
        sw_Lamp=findViewById(R.id.swLamp);
        sw_Camera=findViewById(R.id.swCamera);
        sw_Fan=findViewById(R.id.swFan);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();

//điều khiển on off của TV
        //firebase thay đổi làm tv on off
        databaseReference.child("Living_Room").child("TV").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if(value.equals("ON")){
                    stateTV=true; //bật TV
                    TV_icon.setImageResource(R.drawable.tv_on);
                    sw_TV.setChecked(true);
                }
                else if(value.equals("OFF")){
                    stateTV=false; //tắt TV
                    TV_icon.setImageResource(R.drawable.tv);
                    sw_TV.setChecked(false);
                }
                //switch thay đổi tv on off
                sw_TV.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked) {
                            stateTV=true;
                            TV_icon.setImageResource(R.drawable.tv_on);
                            databaseReference.child("Living_Room").child("TV").setValue("ON");
                        }
                        else {
                            stateTV=false;
                            TV_icon.setImageResource(R.drawable.tv);
                            databaseReference.child("Living_Room").child("TV").setValue("OFF");
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
        databaseReference.child("Living_Room").child("Lamp").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if(value.equals("ON")){
                    stateLamp=true; //bật TV
                    Lamp_icon.setImageResource(R.drawable.ceilinglamp_on);
                    sw_Lamp.setChecked(true);
                }
                else if(value.equals("OFF")){
                    stateLamp=false; //tắt TV
                    Lamp_icon.setImageResource(R.drawable.ceilinglamp);
                    sw_Lamp.setChecked(false);
                }
                //switch thay đổi Lamp on off
                sw_Lamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked) {
                            stateLamp=true;
                            Lamp_icon.setImageResource(R.drawable.ceilinglamp_on);
                            databaseReference.child("Living_Room").child("Lamp").setValue("ON");
                        }
                        else {
                            stateLamp=false;
                            Lamp_icon.setImageResource(R.drawable.ceilinglamp);
                            databaseReference.child("Living_Room").child("Lamp").setValue("OFF");
                        }
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//điều khiển on off của camera
        //firebase thay đổi làm camera on off
        databaseReference.child("Living_Room").child("Camera").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if(value.equals("ON")){
                    stateCamera=true; //bật TV
                    Camera_icon.setImageResource(R.drawable.camera_on);
                    sw_Camera.setChecked(true);
                }
                else if(value.equals("OFF")){
                    stateCamera=false; //tắt TV
                    Camera_icon.setImageResource(R.drawable.camera);
                    sw_Camera.setChecked(false);
                }
                //switch thay đổi camera on off
                sw_Camera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked) {
                            stateCamera=true;
                            Camera_icon.setImageResource(R.drawable.camera_on);
                            databaseReference.child("Living_Room").child("Camera").setValue("ON");
                        }
                        else {
                            stateCamera=false;
                            Camera_icon.setImageResource(R.drawable.camera);
                            databaseReference.child("Living_Room").child("Camera").setValue("OFF");
                        }
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//điều khiển on off của fan
        //firebase thay đổi làm fan on off
        databaseReference.child("Living_Room").child("Fan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if(value.equals("ON")){
                    stateFan=true; //bật TV
                    Fan_icon.setImageResource(R.drawable.fan_on);
                    sw_Fan.setChecked(true);
                }
                else if(value.equals("OFF")){
                    stateFan=false; //tắt TV
                    Fan_icon.setImageResource(R.drawable.fan);
                    sw_Fan.setChecked(false);
                }
                //switch thay đổi fan on off
                sw_Fan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if(isChecked) {
                            stateFan=true;
                            Fan_icon.setImageResource(R.drawable.fan_on);
                            databaseReference.child("Living_Room").child("Fan").setValue("ON");
                        }
                        else {
                            stateFan=false;
                            Fan_icon.setImageResource(R.drawable.fan);
                            databaseReference.child("Living_Room").child("Fan").setValue("OFF");
                        }
                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //bottom navigation
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
                Intent intent=new Intent(livingroom.this,room.class);
                startActivity(intent);
            }

            public void profile_page(){
                Intent intent=new Intent(livingroom.this,profile.class);
                startActivity(intent);
            }

            private void showAlertDialog(int dialog) {
                builderDiaglog = new AlertDialog.Builder(livingroom.this);
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
}
