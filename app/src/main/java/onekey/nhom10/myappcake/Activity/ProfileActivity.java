package onekey.nhom10.myappcake.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import onekey.nhom10.myappcake.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigation();

    }


    private void bottomNavigation () {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);


        homeBtn.setOnClickListener(v -> startActivity (new Intent(ProfileActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, CartActivity.class));
        });
        profileBtn.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
            Toast.makeText(ProfileActivity.this, "Đang Phát Chuyển", Toast.LENGTH_LONG).show();
        });

    }
}