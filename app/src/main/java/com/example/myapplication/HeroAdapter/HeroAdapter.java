package com.example.myapplication.HeroAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Hero;
import com.example.myapplication.ui.ListFragmentDirections;

public class HeroAdapter extends ListAdapter<Hero, HeroAdapter.ItemHolder> {
    private ListFragmentDirections.ActionListFragmentToDetailFlragment action;
    private NavController navController;
    private Context context;

    public HeroAdapter(Context context, NavController navController, ListFragmentDirections.ActionListFragmentToDetailFlragment action){
        super(DIFF_CALLBACK);

        this.context = context;
        this.navController = navController;
        this.navController = navController;
        this.action = action;
    }

    private static final DiffUtil.ItemCallback<Hero> DIFF_CALLBACK = new DiffUtil.ItemCallback<Hero>() {
        @Override
        public boolean areItemsTheSame(@NonNull Hero oldItem, @NonNull Hero newItem) {
            return oldItem.getUserId() == newItem.getUserId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Hero oldItem, @NonNull Hero newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };

    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView title, body;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);

            itemView.setOnClickListener(View ->{
                int position = getAdapterPosition();

                if(position != RecyclerView.NO_POSITION){
                    action = ListFragmentDirections.actionListFragmentToDetailFlragment(getItem(position));
                    navController.navigate(action);
                }
            });
        }
    }

    @NonNull
    @Override
    public HeroAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_iterm, parent, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroAdapter.ItemHolder holder, int position) {
        final Hero hero = getItem(position);

        /****
         * Bind widgets with data
         */
        holder.body.setText(hero.getBody());
        holder.title.setText(hero.getTitle());

        Log.d("TTS", hero.getTitle());
    }
}