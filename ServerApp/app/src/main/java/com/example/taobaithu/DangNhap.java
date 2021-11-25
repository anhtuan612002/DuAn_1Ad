package com.example.taobaithu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DangNhap extends AppCompatActivity {
    private static final String NOTIFICATION_CHANNEL = "Đăng Nhập Thành công";
    Button btnDangNhap, btnDangKi;
    EditText edtDangNhap, edtPasswordLogin;
    LinearLayout frmLoginUse;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        Anhxa();
        mAuth = FirebaseAuth.getInstance();


        Animation animScale1 = AnimationUtils.loadAnimation(this, R.anim.anim_dichuyen);
        frmLoginUse.startAnimation(animScale1);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();

            }

        });

        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);

            }
        });

//        Intent intent = new Intent(this, TaskService.class);
//        startService(intent);


    }


    private void DangNhap() {
        String taikhoan = edtDangNhap.getText().toString();
        String matkhau = edtPasswordLogin.getText().toString();
        if (taikhoan.equals("")) {
            Toast.makeText(DangNhap.this, "Vui Lòng Điền Email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (matkhau.equals("")) {
            Toast.makeText(DangNhap.this, "Vui Lòng Nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
        }

        mAuth.signInWithEmailAndPassword(taikhoan, matkhau)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(DangNhap.this, "đăng nhap thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DangNhap.this, HomeActivity.class);
                            startActivity(intent);


                            FirebaseUser user = mAuth.getCurrentUser();
                            if (task.getResult().getAdditionalUserInfo().isNewUser()) {
                                //laays thoong tin
                                String email = user.getEmail();
                                String uid = user.getUid();

                                HashMap<String, String> hashMap = new HashMap<>();
                                //put tghong tin
                                hashMap.put("email", email);
                                hashMap.put("uid", uid);
                                hashMap.put("name", "");
                                hashMap.put("phone", "");
                                hashMap.put("image", "");
                                hashMap.put("cover", "");
                                //fire base data
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference reference = database.getReference("User");
                                //put data
                                reference.child(uid).setValue(hashMap);

                            }

                        } else {
                            Toast.makeText(DangNhap.this, "lỗi", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    private void Anhxa() {
        btnDangKi = findViewById(R.id.btnOnDangKy);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        edtPasswordLogin = findViewById(R.id.edtMkDn);
        edtDangNhap = findViewById(R.id.edtTkDn);
        frmLoginUse = (LinearLayout) findViewById(R.id.frm_login_use);
    }

}