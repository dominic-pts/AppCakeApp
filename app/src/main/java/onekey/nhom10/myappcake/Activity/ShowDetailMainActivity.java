package onekey.nhom10.myappcake.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import onekey.nhom10.myappcake.Domain.FoodDomain;
import onekey.nhom10.myappcake.Helper.ManagermenCart;
import onekey.nhom10.myappcake.R;

public class ShowDetailMainActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, starTxt, caloryTxt, timeTxt, totalPriceTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagermenCart nanagementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        nanagementCart = new ManagermenCart(this);

        iniView();
        getBundle();
    }

    private void iniView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCartBtn);
        minusBtn = findViewById(R.id.minusItemBtn);
        picFood = findViewById(R.id.foodPic);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
        starTxt = findViewById(R.id.starTxt);
        caloryTxt = findViewById(R.id.ViCaloriesTxt);
        timeTxt = findViewById(R.id.timeTxt);
    }

    public void getBundle() {
        object = (FoodDomain)getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText(object.getFee() + " đ");
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        caloryTxt.setText(object.getCalories() + " Calories");
        starTxt.setText(object.getStar() + "");
        timeTxt.setText(object.getTime() + " minutes");
        totalPriceTxt.setText( String.valueOf(numberOrder * object.getFee()) + " đ");

        plusBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText( String.valueOf(numberOrder * object.getFee()) + " đ");
            }
        });
        addToCartBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                nanagementCart.insertFood(object);
                // chuyển ra màn hình home
                startActivity( new Intent(ShowDetailMainActivity.this, MainActivity.class));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
                totalPriceTxt.setText( String.valueOf(numberOrder * object.getFee())+ " đ");
            }
        });
    }
}