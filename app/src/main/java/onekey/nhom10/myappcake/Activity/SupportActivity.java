package onekey.nhom10.myappcake.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import onekey.nhom10.myappcake.R;

public class SupportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);



        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        LinearLayout supportBtn=findViewById(R.id.supportbtn);
        homeBtn.setOnClickListener(v -> startActivity (new Intent(SupportActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(SupportActivity.this, CartActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(SupportActivity.this, ProfileActivity.class)));
//        supportBtn.setOnClickListener(v -> startActivity(new Intent(SupportActivity.this, SupportActivity.class)));
    }
}