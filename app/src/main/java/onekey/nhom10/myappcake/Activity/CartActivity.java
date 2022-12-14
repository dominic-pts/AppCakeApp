package onekey.nhom10.myappcake.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import onekey.nhom10.myappcake.Adapter.CartListAdapter;
import onekey.nhom10.myappcake.Adapter.CatagoryAdapter;
import onekey.nhom10.myappcake.Helper.ManagermenCart;
import onekey.nhom10.myappcake.Helper.TinyDB;
import onekey.nhom10.myappcake.Interface.ChangeNumberItemsListener;
import onekey.nhom10.myappcake.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagermenCart managermenCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private TextView btnThanhToan;
    private ScrollView scrollView;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managermenCart = new ManagermenCart(this);

        initView();
        initList();
        calculateCard();
        btnThanhToanSum();
        bottomNavigation();
    }
    private void initView() {

        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view);
        scrollView=findViewById(R.id.scrollView);
        emptyTxt=findViewById(R.id.emptyTxt);
        btnThanhToan = findViewById(R.id.btnThanhToan);

    }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managermenCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCard();
            }
        });
        //???n ??i hay hi???n ch??ng l??n m???t c??ch d??? d??ng
        recyclerViewList.setAdapter(adapter);
        if (managermenCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double pencentTax = 0.02; // c?? th??? thay ?????i m???t h??ng n??y cho gi?? thu???
        double delivery = 10; //  c?? th??? thay ?????i m???c n??y gi?? giao h??ng

        tax = Math.round((managermenCart.getTotaFee()* pencentTax)*100.0)/100.0;
        double total = Math.round((managermenCart.getTotaFee() + tax + delivery)*100.0)/100.0;
        double itemTotal = Math.round(managermenCart.getTotaFee() * 100.0)/100.0;

        totalFeeTxt.setText( itemTotal + " ??");
        taxTxt.setText(tax + " ??");
        deliveryTxt.setText(delivery + " ??" );
        totalTxt.setText( total + " ??" );

    }

    private void btnThanhToanSum() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this,"B???n ???? mua h??ng th??nh c??ng",Toast.LENGTH_SHORT).show();
                //xo?? sau khi mua h??ng th??nh c??ng
                managermenCart.deleteAllNumberFood();
                // chuy???n ra m??n h??nh ch??nh menu ????? ti???p t???c mua h??ng
                startActivity( new Intent(CartActivity.this,MainActivity.class));
            }
        });
    }


    private void bottomNavigation () {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        homeBtn.setOnClickListener(v -> startActivity (new Intent(CartActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(CartActivity.this, CartActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(CartActivity.this, ProfileActivity.class)));
    }
}