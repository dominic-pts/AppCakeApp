package onekey.nhom10.myappcake.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import onekey.nhom10.myappcake.Adapter.CatagoryAdapter;
import onekey.nhom10.myappcake.Adapter.RecommendedAdapter;
import onekey.nhom10.myappcake.Domain.CatagoryDomain;
import onekey.nhom10.myappcake.Domain.FoodDomain;
import onekey.nhom10.myappcake.R;

public class MainActivity extends AppCompatActivity {

    private CatagoryAdapter adapter;
    private RecommendedAdapter adapter2;
    private RecyclerView recyclerViewCatagotyList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerViewCatagoty();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation () {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent(MainActivity.this, MainActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                Toast.makeText(MainActivity.this, "Đang Phát Chuyển", Toast.LENGTH_LONG).show();
            }
        });
}
//2h32
    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Donut chery","logo_1","flour, sugar, colorants, nuggets",13.0,10,20,1000));
        foodlist.add(new FoodDomain("Chesse Burger","burger","beef, Gouda Cheese, special sauce, Lettuce,tomato",15.20,4,18,1500));
        foodlist.add(new FoodDomain("Vagetable pizza","pizza3","olive ail, Vegetable oil,pitted Kalamata, cherry tomato, fresh oregano, basil",11.0,3,16,800));
        foodlist.add(new FoodDomain("Drink","logo_drink_1","olive ail, Vegetable oil,pitted Kalamata, cherry tomato, fresh oregano, basil",14.0,3,16,800));
        foodlist.add(new FoodDomain("pepperoni pizza","pizza1","slidces pepperoni, mozzarella cheese, fresh oregano,ground black pepper, pizzasauce",13.0,10,20,1000));
        foodlist.add(new FoodDomain("Birthday","logo_birthday_1","mozzarella cheese, fresh oregano,ground black pepper, pizzasauce",15.0,10,20,1000));
        foodlist.add(new FoodDomain("Donut kramen","logo_donut_1","fresh oregano,ground black pepper, pizzasauce",19.0,10,20,1000));
        foodlist.add(new FoodDomain("Hotdog","logo_hotdog_1","ground black pepper, pizzasauce",12.0,10,20,1000));

        adapter2 = new RecommendedAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCatagoty() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCatagotyList = findViewById(R.id.view1);
        recyclerViewCatagotyList.setLayoutManager(linearLayoutManager);

        ArrayList<CatagoryDomain> catagoryList = new ArrayList<>();
        catagoryList.add(new CatagoryDomain("Pizza","cat_1"));
        catagoryList.add(new CatagoryDomain("Burger","cat_2"));
        catagoryList.add(new CatagoryDomain("Hotdog","cat_3"));
        catagoryList.add(new CatagoryDomain("Drink","cat_4"));
        catagoryList.add(new CatagoryDomain("Donut","cat_5"));
        catagoryList.add(new CatagoryDomain("Birthday","logo_birthday_3"));

        adapter = new CatagoryAdapter(catagoryList);
        recyclerViewCatagotyList.setAdapter(adapter);
    }
}