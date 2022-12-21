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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managermenCart = new ManagermenCart(this);

        initView();
        initList();
        bottomNavigation();
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this,"Bạn đã mua hàng thành công",Toast.LENGTH_SHORT).show();
                //xoá sau khi mua hàng thành công
                managermenCart.deleteAllNumberFood();
                // chuyển ra màn hình chính menu để tiếp tục mua hàng
                startActivity( new Intent(CartActivity.this,MainActivity.class));
            }
        });
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
        //ẩn đi hay hiện chúng lên một cách dễ dàng
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
        double pencentTax = 0.02; // có thể thay đổi mặt hàng này cho giá thuế
        double delivery = 10; //  có thể thay đổi mục này giá giao hàng

        tax = Math.round((managermenCart.getTotaFee()* pencentTax)*100.0)/100.0;
        double total = Math.round((managermenCart.getTotaFee() + tax + delivery)*100.0)/100.0;
        double itemTotal = Math.round(managermenCart.getTotaFee() * 100.0)/100.0;

        totalFeeTxt.setText( itemTotal + " đ");
        taxTxt.setText(tax + " đ");
        deliveryTxt.setText(delivery + " đ" );
        totalTxt.setText( total + " đ" );

    }

    private void initView() {

        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view);
        scrollView=findViewById(R.id.scrollView);
        emptyTxt=findViewById(R.id.emptyTxt);

    }
    private void bottomNavigation () {
        LinearLayout homeBtn=findViewById(R.id.homeBtn);
        LinearLayout cartBtn=findViewById(R.id.cartBtn);
        LinearLayout profileBtn=findViewById(R.id.profileBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity (new Intent(CartActivity.this, MainActivity.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CartActivity.this, CartActivity.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CartActivity.this, ProfileActivity.class));
                Toast.makeText(CartActivity.this, "Đang Phát Chuyển", Toast.LENGTH_LONG).show();
            }
        });
    }
}