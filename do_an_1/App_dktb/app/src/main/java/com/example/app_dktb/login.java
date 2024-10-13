package com.example.app_dktb;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class login extends AppCompatActivity {
    //Trang đăng nhập
    private EditText edit_Txt_Email_login, edit_Txt_Password;
    private TextView btn_register_toggle;
    private Button btn_login;
    private FirebaseAuth mAuth;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(getApplication());
        mAuth = FirebaseAuth.getInstance();
        AnhXa();


//        sự kiện nút nhấn đăng nhập
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
//         nút nhấn register tới giao diện register.xml
        btn_register_toggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                register();
            }
        });

        CheckLogin();
    }

    public void login(){
        String email, password;
        email = edit_Txt_Email_login.getText().toString();
        password = edit_Txt_Password.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sessionManager.SetLogin(true);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, room.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void register(){
        Intent intent=new Intent(login.this,register.class);
        startActivity(intent);
    }

    private void CheckLogin(){
        if(!sessionManager.Check()){
            Toast.makeText(this, "Mời bạn đăng nhập", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(getApplication(),room.class);
            startActivity(intent);
            finish();
        }
    }

    public void AnhXa(){
        //Trang đăng nhập
        edit_Txt_Email_login=findViewById(R.id.edit_Txt_Email_login);
        edit_Txt_Password=findViewById(R.id.edit_Txt_Password);
        btn_login=findViewById(R.id.btn_login);
        btn_register_toggle=findViewById(R.id.btn_register_toogle);
    }
}
