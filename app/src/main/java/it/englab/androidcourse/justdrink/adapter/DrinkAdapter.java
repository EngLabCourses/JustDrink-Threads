package it.englab.androidcourse.justdrink.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import it.englab.androidcourse.justdrink.R;
import it.englab.androidcourse.justdrink.listeners.ClickListener;
import it.englab.androidcourse.justdrink.model.Drink;

/**
 * Created by Peppe on 07/02/2017.
 */

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHolder> {

    private List<Drink> items = new ArrayList<>();
    private ClickListener listener;

    public void setList(List<Drink> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public DrinkAdapter(ClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drink, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Drink mDrink = items.get(position);
        holder.name.setText(mDrink.getStrDrink());

        ImageView thumb = holder.thumb;

        Picasso.with(thumb.getContext()).load(mDrink.getStrDrinkThumb()).into(thumb);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(mDrink.getIdDrink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView thumb;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.drink_name);
            thumb = (ImageView) itemView.findViewById(R.id.drink_thumb);
        }
    }
}
