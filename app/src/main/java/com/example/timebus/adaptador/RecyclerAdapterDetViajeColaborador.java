package com.example.timebus.adaptador;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timebus.R;
import com.example.timebus.model.ItemList6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapterDetViajeColaborador extends RecyclerView.Adapter<RecyclerAdapterDetViajeColaborador.RecyclerHolder> {

    private List<ItemList6> items;
    private List<ItemList6> originalItems;
    private RecyclerItemClick itemClick;

    private String doamin_image = "http://192.168.1.6/ejemploBDRemota/imgBus/";

    public RecyclerAdapterDetViajeColaborador(List<ItemList6> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_detviaje_colaborador, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final ItemList6 item = items.get(position);

        holder.tvCiudad.setText(item.getCiuDest());
        holder.tvHoraSalida.setText(item.getHoraSalBus());

        holder.tvPrecio.setText(item.getPreCiu());
        holder.tvAsiento.setText(item.getAsientoDispBus());


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
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<ItemList6> collect = originalItems.stream()
                        .filter(i -> i.getHoraSalBus().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            } else {
                items.clear();
                for (ItemList6 i : originalItems) {
                    if (i.getHoraSalBus().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {

        /*
        tvTitulo = horaSalida
        textViewLugLlegada = precio
        imgItem = imagen


         */

        private TextView tvCiudad;
        private TextView tvHoraSalida;
        private TextView tvPrecio;
        private TextView tvAsiento;


        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            tvCiudad = itemView.findViewById(R.id.tvCiudad);
            tvHoraSalida = itemView.findViewById(R.id.tvHoraSalida);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvAsiento = itemView.findViewById(R.id.tvAsiento);

        }
    }

    public interface RecyclerItemClick {
        void itemClick(ItemList6 item);
    }
}







