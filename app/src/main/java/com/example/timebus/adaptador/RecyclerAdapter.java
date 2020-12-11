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
import com.example.timebus.model.ItemList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private List<ItemList> items;
    private List<ItemList> originalItems;
    private RecyclerItemClick itemClick;

    private String doamin_image = "http://192.168.1.6/ejemploBDRemota/imgTerminales/";

    public RecyclerAdapter(List<ItemList> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final ItemList item = items.get(position);
        //holder.imgItem.setImageResource(item.getImgResource());
        Picasso.get()
                .load(doamin_image+item.getImgTerm())
                .into(holder.imgItem);
        holder.tvTitulo.setText(item.getNomTerm());
        holder.tvDescripcion.setText(item.getDireccionTerm());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(item.getNomTerm().equals("Terminal los Andes")){
                    System.out.println(" Has clickeado en Terminal los Andes");
                    String valor = "Terminal los Andes";
                    itemClick.itemClick(item, valor);
                }
                else{
                    if(item.getNomTerm().equals("Terminal de Huancayo")){
                        System.out.println("Has clickeado en Terminal de Huancayo");
                        String valor = "Terminal de Huancayo";
                        itemClick.itemClick(item, valor);
                    }
                }
            }
        });

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
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
                List<ItemList> collect = originalItems.stream()
                        .filter(i -> i.getNomTerm().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (ItemList i : originalItems) {
                    if (i.getNomTerm().toLowerCase().contains(strSearch)) {
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
        void itemClick(ItemList item, String valor);
    }
}
