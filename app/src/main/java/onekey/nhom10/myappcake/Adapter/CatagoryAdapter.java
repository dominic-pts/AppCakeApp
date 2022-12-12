package onekey.nhom10.myappcake.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import onekey.nhom10.myappcake.Domain.CatagoryDomain;
import onekey.nhom10.myappcake.R;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.ViewHolder> {
    ArrayList<CatagoryDomain> catagoryDomains;

    public CatagoryAdapter(ArrayList<CatagoryDomain> catagoryDomains) {
        this.catagoryDomains = catagoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_catagory,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CatagoryAdapter.ViewHolder holder, int position) {
    holder.catagoryName.setText(catagoryDomains.get(position).getTitle());
    String picUrl = "";
    switch (position){
        case 0:{
            picUrl = "cat_1";
            break;
        }
        case 1:{
            picUrl = "cat_2";
            break;
        }
        case 2:{
            picUrl = "cat_3";
            break;
        }
        case 3:{
            picUrl = "cat_4";
            break;
        }
        case 4:{
            picUrl = "cat_5";
            break;
        }
        case 5:{
            picUrl = "logo_birthday_3";
            break;
        }
    }
    int drawableReourceId = holder.itemView.getContext().getResources()
            .getIdentifier(picUrl,"drawable",
                    holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.catagoryPic);
    }

    @Override
    public int getItemCount() {
        return catagoryDomains.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView catagoryName;
        ImageView catagoryPic;
        ConstraintLayout  mainLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            catagoryName = itemView.findViewById(R.id.catagoryName);
            catagoryPic = itemView.findViewById(R.id.catagoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
