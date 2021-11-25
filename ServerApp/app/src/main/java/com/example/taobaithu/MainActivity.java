package com.example.taobaithu;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
//    private static int APP_REQUEST_CODE = 7171;
//    private FirebaseAuth firebaseAuth;
//    private FirebaseAuth.AuthStateListener listener;
//    private AlertDialog dialog;
//    private DatabaseReference severRef;
//    private List<AuthUI.IdpConfig> providers;
    Button btn;
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        firebaseAuth.addAuthStateListener(listener);
//    }
//
//    @Override
//    protected void onStop() {
//        if (listener != null)
//            firebaseAuth.removeAuthStateListener(listener);
//        super.onStop();
//
//    }
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        innit();

    }

//    private void innit() {
//        providers = Arrays.asList(new AuthUI.IdpConfig.PhoneBuilder().build());
//        severRef = FirebaseDatabase.getInstance().getReference(Common.SEVER_REF);
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        dialog = new SpotsDialog.Builder().setContext(this).setCancelable(false).build();
//        listener = firebaseAuthLocal -> {
//
//            FirebaseUser user = firebaseAuthLocal.getCurrentUser();
//            if (user != null) {
//            // check user from
//                checkSeverUserFromFirebase(user);
//
//            } else {
//
//                phoneLogin();
//            }
//        };
//    }
//
//    private void checkSeverUserFromFirebase(FirebaseUser user) {
//        dialog.show();
//        severRef.child(user.getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
//                        if (datasnapshot.exists()) {
//                            //
//                            ServerUserModel userModel = datasnapshot.getValue(ServerUserModel.class);
//                            if (userModel.isActive()) {
//
//                                goToHomeActivity(userModel);
//                            }
//                            else {
//                                Toast.makeText(MainActivity.this, "You must be allowed from Admin to access this app", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } else {
//                            //User not exists in database
//                            dialog.dismiss();
//                            showRegisterDialog(user);
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError dataerror) {
//                        dialog.dismiss();
//                        Toast.makeText(MainActivity.this, "abc" + dataerror.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//    }
//
//    private void showRegisterDialog(FirebaseUser user) {
//        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
//        builder.setTitle("Register");
//        builder.setMessage("Please fill information \n admin willl accept your account late ");
//        View itemView = LayoutInflater.from(this).inflate(R.layout.layout_register, null);
//        EditText edt_name = (EditText) itemView.findViewById(R.id.edt_name);
//        EditText edt_phone = (EditText) itemView.findViewById(R.id.edt_phone);
//
//
//        //set data
//        edt_name.setText(user.getPhoneNumber());
//        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        })
//                .setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if (TextUtils.isEmpty(edt_name.getText().toString())) {
//                    Toast.makeText(MainActivity.this, "Nhập tên", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                ServerUserModel serverUserModel = new ServerUserModel();
//                serverUserModel.setUid(user.getUid());
//                serverUserModel.setName(edt_name.getText().toString());
//                serverUserModel.setPhone(edt_phone.getText().toString());
//                serverUserModel.setActive(false);//defaul failed we must active user by  manual in fire base
//
//                dialog.show();
//
//                severRef.child(serverUserModel.getUid())
//                        .setValue(serverUserModel)
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                dialog.dismiss();
//                                Toast.makeText(MainActivity.this, "abc"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        }).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        dialog.dismiss();
//                        Toast.makeText(MainActivity.this, "Congratulation ! Register ! Admin will check and acti", Toast.LENGTH_SHORT).show();
//                        goToHomeActivity(serverUserModel);
//
//                    }
//                });
//
//            }
//        });
//
//
//    }
//
//    private void goToHomeActivity(ServerUserModel serverUserModel) {
//
//        dialog.dismiss();
//
//        Common.currentServerUser = serverUserModel;
//        startActivity(new Intent(this, HomeActivity.class));
//        finish();
//
//    }
//
//    private void phoneLogin() {
//        startActivityForResult(AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers)
//                .build(), APP_REQUEST_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == APP_REQUEST_CODE) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//            if (resultCode == RESULT_OK) {
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//            } else {
//
//                Toast.makeText(this, "Failed to sign in ", Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    }
}