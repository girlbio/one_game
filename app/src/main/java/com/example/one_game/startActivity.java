package com.example.one_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class startActivity extends AppCompatActivity {
    private EditText et_name,et_pwd;
    private Button btn_submit,btn_register;
    private String name,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
    }
    private void init(){
        et_name=findViewById(R.id.et_name);
        et_pwd=findViewById(R.id.et_pw);
        btn_submit= findViewById(R.id.login);
        btn_register=findViewById(R.id.reg);
        btn_register.setOnClickListener(new View.OnClickListener() {                  //注册事件
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(startActivity.this,Register.class);          //跳转到注册页面
                startActivity(intent);
                Toast.makeText(startActivity.this,"前往注册！",Toast.LENGTH_SHORT).show();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login:
                        //从编辑框获取姓名，邮箱，密码
                        name = et_name.getText().toString().trim();
                        pwd = et_pwd.getText().toString().trim();
                        if (TextUtils.isEmpty(name)) {
                            Toast.makeText(startActivity.this, "姓名为空，请输入姓名", Toast.LENGTH_LONG).show();
                        }
                        if (TextUtils.isEmpty(pwd)) {
                            Toast.makeText(startActivity.this, "密码为空，请输入密码", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(startActivity.this, "登录成功", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(startActivity.this,game.class);
                            startActivity(intent);
                        }
                }
            }
        });
    }
}
