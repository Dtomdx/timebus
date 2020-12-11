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

import com.example.timebus.model.ItemList2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapterEmpresas extends RecyclerView.Adapter<RecyclerAdapterEmpresas.RecyclerHolder> {

    private List<ItemList2> items;
    private List<ItemList2> originalItems;
    private RecyclerItemClick itemClick;

    private String doamin_image = "http://192.168.1.6/ejemploBDRemota/imgEmpresas/";

    public RecyclerAdapterEmpresas(List<ItemList2> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view2, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final ItemList2 item = items.get(position);
        //holder.imgItem.setImageResource(item.getImgResource());
        Picasso.get()
                .load(doamin_image+item.getImgEmp())
                .into(holder.imgItem);
        holder.tvTitulo.setText(item.getNomEmpBus());
        holder.tvDescripcion.setText(item.getPreCiu());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getNomEmpBus().equals("Apocalipsis")){
                    String nomEmpresaBus = "Apocalipsis";
                    itemClick.itemClick(item, nomEmpresaBus);
                }

                if(item.getNomEmpBus().equals("Turismo Raraz")){
                    String nomEmpresaBus = "Turismo Raraz";
                    itemClick.itemClick(item, nomEmpresaBus);
                }

                if(item.getNomEmpBus().equals("Expreso Molina")){
                    String nomEmpresaBus = "Expreso Molina";
                    itemClick.itemClick(item, nomEmpresaBus);
                }

                if(item.getNomEmpBus().equals("Expreso Lobato")){
                    String nomEmpresaBus = "Expreso Lobato";
                    itemClick.itemClick(item, nomEmpresaBus);
                }

                if(item.getNomEmpBus().equals("Turismo Carhuamayo")){
                    String nomEmpresaBus = "Turismo Carhuamayo";
                    itemClick.itemClick(item, nomEmpresaBus);
                }

                if(item.getNomEmpBus().equals("Leon de Huanuco")){
                    String nomEmpresaBus = "Leon de Huanuco";
                    itemClick.itemClick(item, nomEmpresaBus);
                }
                //itemClick.itemClick(item);
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
                List<ItemList2> collect = originalItems.stream()
                        .filter(i -> i.getNomEmpBus().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (ItemList2 i : originalItems) {
                    if (i.getNomEmpBus().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView imgItem;
        private TextView tvTitulo;
        private TextView tvDescripcion;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            imgItem = itemView.findViewById(R.id.imgItem);
            tvTitulo = itemView.findViewById(R.id.tvHorSalida);
            tvDescripcion = itemView.findViewById(R.id.tvAsiDisp);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(ItemList2 item, String v_nomEmpresaBus);
    }
}
