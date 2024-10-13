package com.example.app_dktb;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
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

import java.util.HashMap;
import java.util.Map;

public class room extends AppCompatActivity {
    AlertDialog.Builder builderDiaglog;
    AlertDialog alertDialog;

    SessionManager sessionManager;


    ImageView imageliving,imagebed,imagekitchen;
    TextView textliving,textbed,textkitchen,textnhietdo,textdoam;
    TextClock time;
    private static final String TIME_FORMAT_24 = "HH:mm:ss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room);
        sessionManager = new SessionManager(getApplication());

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
                Intent intent=new Intent(room.this,room.class);
                startActivity(intent);
            }

            public void profile_page(){
                Intent intent=new Intent(room.this,profile.class);
                startActivity(intent);

            }

            private void showAlertDialog(int dialog) {
                builderDiaglog = new AlertDialog.Builder(room.this);
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

        imageliving = findViewById(R.id.imagelivingroom);
        imagebed = findViewById(R.id.imagebedroom);
        imagekitchen = findViewById(R.id.imagekitchenroom);
        textliving = findViewById(R.id.textlivingroom);
        textbed = findViewById(R.id.textbedroom);
        textkitchen = findViewById(R.id.textkitchenroom);
        time=findViewById(R.id.texttime);
        textnhietdo = findViewById(R.id.nhiet_do);
        textdoam = findViewById(R.id.do_am);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        // hiển thị thòi gian
        String formatdate = "E,   d-M-yyyy    hh:mm:ss";
        time.setFormat24Hour(formatdate);

        //đọc nhiệt độ, độ ẩm
        final DatabaseReference databaseReference = database.getReference();

        databaseReference.child("DHT11").child("Nhiet do").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value= snapshot.getValue().toString();
                value = value + "°C";
                textnhietdo.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("DHT11").child("Do am").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value= snapshot.getValue().toString();
                value = value + "%";
                textdoam.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // nút nhấn để chuyển tới giao diện living room
        imageliving.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(room.this,livingroom.class);
                startActivity(intent);
            }
        });
        textliving.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(room.this,livingroom.class);
                startActivity(intent);
            }
        });

        //nut nhấn để chuyển tới bedroom
        imagebed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(room.this,bedroom.class);
                startActivity(intent);
            }
        });
        textbed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(room.this,bedroom.class);
                startActivity(intent);
            }
        });

        //nút nhấn để chuyển tới kitchen room
        imagekitchen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(room.this,kitchenroom.class);
                startActivity(intent);
            }
        });
        textkitchen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(room.this,kitchenroom.class);
                startActivity(intent);
            }
        });
    }
}
