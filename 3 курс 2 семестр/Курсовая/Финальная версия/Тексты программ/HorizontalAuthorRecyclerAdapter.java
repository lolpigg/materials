package com.example.librarystar;
import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class HorizontalAuthorRecyclerAdapter extends RecyclerView.Adapter<HorizontalAuthorRecyclerAdapter.ViewHolder> {
    private List<Author> data;
    private Context context;

    public HorizontalAuthorRecyclerAdapter(List<Author> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.author_horizontal_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Author author = data.get(position);
        Picasso.with(context).load("https://lolpigg.pythonanywhere.com" + author.getImage_path()).into(holder.img);
        holder.tv.setText(author.getFull_name());
        String date = "";
        date += author.getYear_of_birth();
        if (author.getYear_of_death() != null) date += " - " + author.getYear_of_death();
        holder.tv_date.setText(date);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AuthorDetailActivity.class);
                intent.putExtra("id", author.getId());
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
        TextView tv_date;
        ImageView img;
        ConstraintLayout card;
        ViewHolder(View view){
            super(view);
            tv = view.findViewById(R.id.card_tv);
            img = view.findViewById(R.id.card_img);
            card = view.findViewById(R.id.card_block);
            tv_date = view.findViewById(R.id.card_date);
        }
    }
}
