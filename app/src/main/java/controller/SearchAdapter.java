package controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.modos.ehsan.R;

import java.util.ArrayList;

import model.LatestModel;
import view.SearchActivity;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> implements Filterable{

    private ArrayList<LatestModel> data;
    private ArrayList<LatestModel> filteredData;

    public SearchAdapter(ArrayList<LatestModel> data) {
        this.data = data;
        this.filteredData = data;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()){
                    filteredData = data;
                }else{
                    ArrayList<LatestModel> filtered = new ArrayList<>();

                    for (LatestModel latestModel : data){

                        if (SearchActivity.CHECK_BOX_NAME_IS_CHECKED){
                            if (latestModel.getName().contains(charString)){
                                filtered.add(latestModel);
                            }
                        }

                        if (SearchActivity.CHECK_BOX_ADDRESS_IS_CHECKED){
                            if (latestModel.getAddress().contains(charString)){
                                filtered.add(latestModel);
                            }
                        }

                        if (SearchActivity.CHECK_BOX_PRICE_IS_CHECKED){
                            if (latestModel.getPrice().contains(charString)){
                                filtered.add(latestModel);
                            }
                        }

                    }

                    filteredData = filtered;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredData;

                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredData = (ArrayList<LatestModel>) filterResults.values;

                SearchActivity.empty.setVisibility(filteredData.isEmpty() ? View.VISIBLE : View.GONE);

                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
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

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        final LatestModel latestModel = filteredData.get(position);
        TextView name = holder.name;
        TextView address = holder.address;
        TextView price = holder.price;
        ImageView photo = holder.photo;

        name.setText(latestModel.getName());
        address.setText(latestModel.getAddress());
        price.setText(latestModel.getPrice());
        photo.setImageResource(latestModel.getPhoto());
    }
}
