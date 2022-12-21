package onekey.nhom10.myappcake.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import onekey.nhom10.myappcake.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ConstraintLayout startBtn = findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // khi khởi động lên thì đến cái màn hình này đầu tiên rồi nhấn vô thì chuyển qua màn hình chính
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
        });
    }
}