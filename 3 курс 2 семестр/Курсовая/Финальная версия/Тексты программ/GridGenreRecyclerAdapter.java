package com.example.librarystar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarystar.Models.Author;
import com.example.librarystar.Models.Book;
import com.example.librarystar.Models.BookWithAuthors;
import com.example.librarystar.Models.Genre;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridGenreRecyclerAdapter extends RecyclerView.Adapter<GridGenreRecyclerAdapter.ViewHolder> {
    private List<Genre> data;
    private Context context;

    public GridGenreRecyclerAdapter(List<Genre> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_grid_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre genre = data.get(position);
        int startColor = Color.parseColor(genre.getFirst_color()); // Начальный цвет градиента
        int endColor = Color.parseColor(genre.getSecond_color()); // Конечный цвет градиента
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{startColor, endColor});
        gradientDrawable.setCornerRadius(16f);
        holder.card.setBackground(gradientDrawable);
        Picasso.with(holder.card.getContext()).load("https://lolpigg.pythonanywhere.com" + genre.getIcon_path()).into(holder.img);
        holder.tv.setText(genre.getName());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GenreDetailActivity.class);
                intent.putExtra("id", genre.getId());
                intent.putExtra("name", genre.getName());
                view.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;
        ConstraintLayout card;
        ViewHolder(View view){
            super(view);
            tv = view.findViewById(R.id.card_tv);
            img = view.findViewById(R.id.card_img);
            card = view.findViewById(R.id.card_block);
        }
    }
}
