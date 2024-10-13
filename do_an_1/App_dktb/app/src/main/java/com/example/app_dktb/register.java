package com.example.app_dktb;

import static android.content.ContentValues.TAG;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    //Trang đăng kí
    EditText edit_Txt_Username_register,   edit_Txt_email_register,   edit_Txt_pass_register,    edit_Txt_confirm_pass_register;
    Button btn_register;
    TextView btn_login_toggle;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth = FirebaseAuth.getInstance();
        //Trang đăng kí
        edit_Txt_Username_register=findViewById(R.id.edit_Txt_Username_register);
        edit_Txt_email_register=findViewById(R.id.edit_Txt_email_register);
        edit_Txt_pass_register=findViewById(R.id.edit_Txt_pass_register);
        edit_Txt_confirm_pass_register=findViewById(R.id.edit_Txt_confirm_pass_register);
        btn_register=findViewById(R.id.btn_register);
        btn_login_toggle=findViewById(R.id.btn_login_toggle);

        //sự kiện nút nhấn regis them nguoiw dung
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        // nút nhấn regis_login tới giao diện main.xml
        btn_login_toggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login_toggle();
            }
        });
    }
    private void register(){
        String email, password, confirm_password, username;
        email = edit_Txt_email_register.getText().toString();
        password = edit_Txt_pass_register.getText().toString();

        username = edit_Txt_Username_register.getText().toString();
        confirm_password = edit_Txt_confirm_pass_register.getText().toString();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Vui lòng nhập tên người dùng", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }else{
            if (password.length() < 6) {
                Toast.makeText(this, "Vui lòng nhập 6 ký tự cho mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(TextUtils.isEmpty(confirm_password)){
            Toast.makeText(this, "Vui lòng nhập mật khẩu xác nhận", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.equals(confirm_password)){
            Task<AuthResult> authResultTask = mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                        login_toggle();
                    } else {
                        Toast.makeText(getApplicationContext(), "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else{
            Toast.makeText(this, "Mật khẩu bạn nhập không trùng khớp", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void login_toggle(){
        Intent intent=new Intent(register.this,login.class);
        startActivity(intent);
    }


}
