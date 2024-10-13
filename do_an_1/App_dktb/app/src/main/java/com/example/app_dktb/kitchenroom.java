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

public class kitchenroom extends AppCompatActivity {
    AlertDialog.Builder builderDiaglog;
    AlertDialog alertDialog;
    SessionManager sessionManager;

    TextView tv_seekbarfridge;
    SeekBar seekbarfridge;
    ImageView Fridge_icon,Light_icon,Exhaust_icon,Stove_icon;
    Switch sw_Fridge,sw_Light,sw_Exhaust,sw_Stove;
    Boolean stateFridge=false;
    Boolean stateLight=false;
    Boolean stateExhaust=false;
    Boolean stateStove=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchenroom);
        init();

        Fridge_icon = findViewById(R.id.imagefridge);
        Light_icon = findViewById(R.id.lighticon);
        Stove_icon = findViewById(R.id.imagestove);
        Exhaust_icon = findViewById(R.id.exhausticon);
        sw_Fridge = findViewById(R.id.switchfridge);
        sw_Light = findViewById(R.id.swLight);
        sw_Exhaust = findViewById(R.id.swExhaust);
        sw_Stove = findViewById(R.id.switchstove);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference();

//điều khiển on off của Fridge
        //firebase thay đổi làm Fridge on off
        databaseReference.child("Kitchen_Room").child("Fridge").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if (value.equals("ON")) {
                    stateFridge = true; //bật TV
                    Fridge_icon.setImageResource(R.drawable.fridge_on);
                    sw_Fridge.setChecked(true);
                } else if (value.equals("OFF")) {
                    stateFridge = false; //tắt TV
                    Fridge_icon.setImageResource(R.drawable.fridge);
                    sw_Fridge.setChecked(false);
                }
                //switch thay đổi Fridge on off
                sw_Fridge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            stateFridge = true;
                            Fridge_icon.setImageResource(R.drawable.fridge_on);
                            databaseReference.child("Kitchen_Room").child("Fridge").setValue("ON");
                        } else {
                            stateFridge = false;
                            Fridge_icon.setImageResource(R.drawable.fridge);
                            databaseReference.child("Kitchen_Room").child("Fridge").setValue("OFF");
                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//điều khiển on off của Light
        //firebase thay đổi làm Light on off
        databaseReference.child("Kitchen_Room").child("Light").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if (value.equals("ON")) {
                    stateLight = true; //bật TV
                    Light_icon.setImageResource(R.drawable.countertop_on);
                    sw_Light.setChecked(true);
                } else if (value.equals("OFF")) {
                    stateLight = false; //tắt TV
                    Light_icon.setImageResource(R.drawable.countertop);
                    sw_Light.setChecked(false);
                }
                //switch thay đổi Light on off
                sw_Light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            stateLight = true;
                            Light_icon.setImageResource(R.drawable.countertop_on);
                            databaseReference.child("Kitchen_Room").child("Light").setValue("ON");
                        } else {
                            stateLight = false;
                            Light_icon.setImageResource(R.drawable.countertop);
                            databaseReference.child("Kitchen_Room").child("Light").setValue("OFF");
                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//điều khiển on off của Exhaust
        //firebase thay đổi làm Exhaust on off
        databaseReference.child("Kitchen_Room").child("Exhaust").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if (value.equals("ON")) {
                    stateExhaust = true; //bật TV
                    Exhaust_icon.setImageResource(R.drawable.kitchenhood_on);
                    sw_Exhaust.setChecked(true);
                } else if (value.equals("OFF")) {
                    stateExhaust = false; //tắt TV
                    Exhaust_icon.setImageResource(R.drawable.kitchenhood);
                    sw_Exhaust.setChecked(false);
                }
                //switch thay đổi Exhaust on off
                sw_Exhaust.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            stateExhaust = true;
                            Exhaust_icon.setImageResource(R.drawable.kitchenhood_on);
                            databaseReference.child("Kitchen_Room").child("Exhaust").setValue("ON");
                        } else {
                            stateExhaust = false;
                            Exhaust_icon.setImageResource(R.drawable.kitchenhood);
                            databaseReference.child("Kitchen_Room").child("Exhaust").setValue("OFF");
                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//điều khiển on off của Stove
        //firebase thay đổi làm Stove on off
        databaseReference.child("Kitchen_Room").child("Stove").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value= dataSnapshot.getValue().toString();
                if (value.equals("ON")) {
                    stateStove = true; //bật TV
                    Stove_icon.setImageResource(R.drawable.stove_on);
                    sw_Stove.setChecked(true);
                } else if (value.equals("OFF")) {
                    stateStove = false; //tắt TV
                    Stove_icon.setImageResource(R.drawable.stove);
                    sw_Stove.setChecked(false);
                }
                //switch thay đổi Stove on off
                sw_Stove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            stateStove = true;
                            Stove_icon.setImageResource(R.drawable.stove_on);
                            databaseReference.child("Kitchen_Room").child("Stove").setValue("ON");
                        } else {
                            stateStove = false;
                            Stove_icon.setImageResource(R.drawable.stove);
                            databaseReference.child("Kitchen_Room").child("Stove").setValue("OFF");
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
                Intent intent=new Intent(kitchenroom.this,room.class);
                startActivity(intent);
            }

            public void profile_page(){
                Intent intent=new Intent(kitchenroom.this,profile.class);
                startActivity(intent);
            }

            private void showAlertDialog(int dialog) {
                builderDiaglog = new AlertDialog.Builder(kitchenroom.this);
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

    }
}
