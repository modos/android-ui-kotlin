package controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.modos.ehsan.R;

import java.util.ArrayList;

import model.LatestModel;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.MyViewHolder> {

    private ArrayList<LatestModel> data;

    public LatestAdapter(ArrayList<LatestModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public LatestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_list_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LatestAdapter.MyViewHolder holder, int position) {
        TextView name = holder.name;
        TextView address = holder.address;
        TextView price = holder.price;
        ImageView photo = holder.photo;

        name.setText(data.get(position).getName());
        address.setText(data.get(position).getAddress());
        price.setText(data.get(position).getPrice());
        photo.setImageResource(data.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, address, price;
        ImageView photo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.latest_name);
            this.address = itemView.findViewById(R.id.latest_address);
            this.price = itemView.findViewById(R.id.latest_price);
            this.photo = itemView.findViewById(R.id.profile);
        }
    }
}
