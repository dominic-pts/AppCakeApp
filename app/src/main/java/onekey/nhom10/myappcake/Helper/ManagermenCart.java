package onekey.nhom10.myappcake.Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import onekey.nhom10.myappcake.Domain.FoodDomain;
import onekey.nhom10.myappcake.Interface.ChangeNumberItemsListener;

public class ManagermenCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagermenCart(Context context, TinyDB tinyDB) {
        this.context = context;
        this.tinyDB = tinyDB;
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0 ; i < listFood.size(); i++){
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }

        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context,"Add to your Cart",Toast.LENGTH_SHORT).show();
    }
    private ArrayList<FoodDomain> getListCart(){
        return  tinyDB.getListObject("CartList");
    }

    // xoá
    public void minusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if (listfood.get(position).getNumberInCart() == 1){
            listfood.remove(position);
        }
        else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CasdList", listfood);
        changeNumberItemsListener.change();
    }
    // Thêm
    public void plusNumberFood(ArrayList<FoodDomain> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener){

        listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CasdList", listfood);
        changeNumberItemsListener.change();
    }

    public Double  getTotaFee(){
        ArrayList<FoodDomain> listfood2 = getListCart();
        double fee = 0;
        for(int i = 0 ; i < listfood2.size();i++){
                fee = fee + (listfood2.get(i).getFee() * listfood2.get(i).getNumberInCart());
            }
            return fee;
        }
    }



