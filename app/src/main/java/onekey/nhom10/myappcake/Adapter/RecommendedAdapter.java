package onekey.nhom10.myappcake.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import onekey.nhom10.myappcake.Activity.ShowDetailMainActivity;
import onekey.nhom10.myappcake.Domain.FoodDomain;
import onekey.nhom10.myappcake.R;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {
    ArrayList<FoodDomain> recommendedDomains;

    public RecommendedAdapter(ArrayList<FoodDomain> recommendedDomains) {
        this.recommendedDomains = recommendedDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_recommended,parent,false);
        return new ViewHolder(inflate);
    }



    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, int position) {
        holder.title.setText(recommendedDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(recommendedDomains.get(position).getFee()));

        int drawableReourceId = holder.itemView.getContext().getResources()
                .getIdentifier(recommendedDomains.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(v -> {
            Intent intent = new Intent (holder.itemView.getContext(), ShowDetailMainActivity.class);
            intent.putExtra("object", recommendedDomains.get(position));
            holder.itemView.getContext().startActivity (intent);
        });
    }

    @Override
    public int getItemCount() {
        return recommendedDomains.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        ImageView addBtn;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            fee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
