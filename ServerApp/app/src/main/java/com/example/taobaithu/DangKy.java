package com.example.taobaithu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DangKy extends AppCompatActivity {
    private static final String NOTIFICATION_CHANNEL ="Bạn đã đăng ký tài khoản thành công" ;
    Button btnDangKy, btnBack;
    EditText edtEmail, editPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        AnhXa();

        mAuth = FirebaseAuth.getInstance();



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
                Noti();
            }
        });

    }

    private void DangKy() {
        String email = edtEmail.getText().toString();
        String password = editPass.getText().toString();

        if (email.equals("")) {
            Toast.makeText(DangKy.this, "Vui Lòng Điền Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(DangKy.this, "Vui Lòng Nhập Mật Khẩu", Toast.LENGTH_SHORT).show();
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DangKy.this, "đăng kí thành công", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();
                            //laays thoong tin
                            String email = user.getEmail();
                            String uid = user.getUid();

                            HashMap<String, String> hashMap = new HashMap<>();
                            //put tghong tin
                            hashMap.put("email",email);
                            hashMap.put("uid",uid);
                            hashMap.put("name","");
                            hashMap.put("phone","");
                            hashMap.put("image","");
                            hashMap.put("cover","");
                            //fire base data
                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            DatabaseReference reference = database.getReference("User");
                            Intent intent = new Intent(DangKy.this,DangNhap.class);
                            startActivity(intent);
                            //put data
                            reference.child(uid).setValue(hashMap);



                        } else {
                            Toast.makeText(DangKy.this, "lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
    private void Noti(){

        String email = edtEmail.getText().toString();
//       dang ky de  hien thi
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(DangKy.this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "kenh thong bao";
            String description = "mo ta thong bao";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManagerCHannel = getSystemService(NotificationManager.class);
            notificationManagerCHannel.createNotificationChannel(channel);
        }
// thong bao khi them
        NotificationCompat.Builder builder = new NotificationCompat.Builder(DangKy.this
                , NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Xin chào " +email )
                .setContentText("Bạn đã Đăng ký tài khoản thành công")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(33, builder.build());


    }

    private void AnhXa() {
        btnBack = findViewById(R.id.btnChuyenDangNhap);
        btnDangKy = findViewById(R.id.btnDangKy);
        editPass = findViewById(R.id.edtPasswordDangKy);
        edtEmail = findViewById(R.id.edtDangKy);


    }
}