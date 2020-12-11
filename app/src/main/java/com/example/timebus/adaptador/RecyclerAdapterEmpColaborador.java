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
import com.example.timebus.model.ItemList5;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapterEmpColaborador extends RecyclerView.Adapter<RecyclerAdapterEmpColaborador.RecyclerHolder>{

    private List<ItemList5> items;
    private List<ItemList5> originalItems;
    private RecyclerItemClick itemClick;

    private String doamin_image = "http://192.168.1.6/ejemploBDRemota/imgEmpresas/";

    public RecyclerAdapterEmpColaborador(List<ItemList5> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view_emp_colaborador, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final ItemList5 item = items.get(position);
        //holder.imgItem.setImageResource(item.getImgResource());
        Picasso.get()
                .load(doamin_image+item.getImgEmp())
                .into(holder.imgItem);
        holder.tvTitulo.setText(item.getNomEmpBus());
        //holder.tvDescripcion.setText(item.getDireccionTerm());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(item.getNomEmpBus().equals("Leon de Huanuco")){
                    System.out.println(" Has clickeado en Leon de Huanuco");
                    String valor = "Leon de Huanuco";
                    itemClick.itemClick(item, valor);
                }

                if(item.getNomEmpBus().equals("Turismo Carhuamayo")){

                    String valor = "Turismo Carhuamayo";
                    itemClick.itemClick(item, valor);
                }

                if(item.getNomEmpBus().equals("Expreso Lobato")){
                    System.out.println(" Has clickeado en Expreso Lobato");
                    String valor = "Expreso Lobato";
                    itemClick.itemClick(item, valor);
                }
                //Colaborador=75271531
                if(item.getNomEmpBus().equals("Apocalipsis")){

                    String valor = "Apocalipsis";
                    itemClick.itemClick(item, valor);
                }

                if(item.getNomEmpBus().equals("Turismo Raraz")){

                    String valor = "Turismo Raraz";
                    itemClick.itemClick(item, valor);
                }

                if(item.getNomEmpBus().equals("Expreso Molina")){
                    System.out.println(" Has clickeado en Expreso Lobato");
                    String valor = "Expreso Molina";
                    itemClick.itemClick(item, valor);
                }
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
                List<ItemList5> collect = originalItems.stream()
                        .filter(i -> i.getNomEmpBus().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (ItemList5 i : originalItems) {
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
        void itemClick(ItemList5 item, String valor);
    }
}
