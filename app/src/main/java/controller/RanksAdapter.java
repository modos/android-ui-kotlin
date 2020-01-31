package controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.modos.ehsan.R;

import java.util.ArrayList;
import model.RanksModel;

public class RanksAdapter extends RecyclerView.Adapter<RanksAdapter.MyViewHolder> {

    private ArrayList<RanksModel> data;

    public RanksAdapter(ArrayList<RanksModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranks_list_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            TextView name = holder.name;
            TextView address = holder.address;
            TextView price = holder.price;

            name.setText(data.get(position).getName());
            address.setText(data.get(position).getAddress());
            price.setText(data.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, address, price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.address = itemView.findViewById(R.id.address);
            this.price = itemView.findViewById(R.id.price);
        }
    }
}
