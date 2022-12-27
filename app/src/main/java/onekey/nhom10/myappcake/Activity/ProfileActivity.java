package onekey.nhom10.myappcake.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.load.model.ModelLoader;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import onekey.nhom10.myappcake.Helper.TinyDB;
import onekey.nhom10.myappcake.R;

public class ProfileActivity extends AppCompatActivity {
    EditText edittext_name, edittext_sdt, edittext_diachi, edittext_email;
    ConstraintLayout btn_luu;
    TinyDB tinyDB;
    String getTextName, getGetTextSdt, getGetTextDiaChi,getGetTextEmail ;
    TextView text_name , text_email;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Init();
        setOnclickListenes();
        bottomNavigation();

    }

    private void Init() {
        edittext_name= findViewById(R.id.edittext_name_profile);
        edittext_sdt= findViewById(R.id.edittext_sdt_profile);
        edittext_diachi= findViewById(R.id.edittext_diachi_profile);
        edittext_email= findViewById(R.id.edittext_email_profile);
        btn_luu= findViewById(R.id.btn_luu_profile);
        text_name = findViewById(R.id.text_name_profile);
        text_email = findViewById(R.id.text_email_profile);

        tinyDB = new TinyDB(getApplicationContext());

        getTextName = tinyDB.getString("StringText");
        text_name.setText(getTextName);
        edittext_name.setText(getTextName);


        getGetTextSdt = tinyDB.getString("StringSDT");
        edittext_sdt.setText(getGetTextSdt);

        getGetTextDiaChi = tinyDB.getString("StringDiaChi");
        edittext_diachi.setText(getGetTextDiaChi);

        getGetTextEmail = tinyDB.getString("StringEmail");
        edittext_email.setText(getGetTextEmail);
        text_email.setText(getGetTextEmail);


    }

    private  void setOnclickListenes(){
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextName = edittext_name.getText().toString().toUpperCase().trim();
                getGetTextSdt = edittext_sdt.getText().toString().trim();
                getGetTextDiaChi = edittext_diachi.getText().toString().trim();
                getGetTextEmail = edittext_email.getText().toString().trim();
                if ( !getTextName.isEmpty() & !getGetTextSdt.isEmpty() & !getGetTextDiaChi.isEmpty() & !getGetTextEmail.isEmpty()  ){
                    tinyDB.putString("StringText", getTextName);
                    text_name.setText(getTextName);

                    tinyDB.putString("StringSDT", getGetTextSdt);

                    tinyDB.putString("StringDiaChi", getGetTextDiaChi);

                    tinyDB.putString("StringEmail", getGetTextEmail);
                    text_email.setText(getGetTextEmail);
                    Toast.makeText(getApplicationContext(), "lưu thông tin thành công !!!", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void bottomNavigation () {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        LinearLayout supportBtn=findViewById(R.id.supportbtn);
        homeBtn.setOnClickListener(v -> startActivity (new Intent(ProfileActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, CartActivity.class)));
//        profileBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, ProfileActivity.class)));
        supportBtn.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, SupportActivity.class)));
    }
}