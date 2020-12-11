package com.example.timebus.adaptador;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timebus.R;
import com.example.timebus.model.ItemList3;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapterDetViaje extends  RecyclerView.Adapter<RecyclerAdapterDetViaje.RecyclerHolder>{

    private List<ItemList3> items;
    private List<ItemList3> originalItems;
    private RecyclerItemClick itemClick;

    private String doamin_image = "http://192.168.1.6/ejemploBDRemota/imgBus/";

    public RecyclerAdapterDetViaje(List<ItemList3> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view3, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final ItemList3 item = items.get(position);
        //holder.imgItem.setImageResource(item.getImgResource());
        Picasso.get()
                .load(doamin_image+item.getImgBus())
                .into(holder.imgItem);
        holder.tvHorSalida.setText(item.getHoraSalBus());
        holder.tvAsiDisp.setText(item.getAsientoDispBus());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            items.clear();
            items.addAll(originalItems);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<ItemList3> collect = originalItems.stream()
                        .filter(i -> i.getHoraSalBus().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (ItemList3 i : originalItems) {
                    if (i.getHoraSalBus().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView tvHorSalida;
        private TextView tvAsiDisp;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            imgItem = itemView.findViewById(R.id.imgItem);
            tvHorSalida = itemView.findViewById(R.id.tvHorSalida);
            tvAsiDisp = itemView.findViewById(R.id.tvAsiDisp);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(ItemList3 item);
    }
}
