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

public class VerticalBookRecyclerAdapter extends RecyclerView.Adapter<VerticalBookRecyclerAdapter.ViewHolder> {
    private List<BookWithAuthors> data;
    private Context context;

    public VerticalBookRecyclerAdapter(List<BookWithAuthors> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_vertical_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = data.get(position).getBook();
        String name = book.getName() + "\n";
        for (Author auth: data.get(position).getAuthors()) {
            name += auth.getFull_name() + "\n";
        }
        name += data.get(position).getBook().getYear_of_creating();
        Picasso.with(context).load(book.getImage_path()).into(holder.img);
        holder.tv.setText(name);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookDetailActivity.class);
                intent.putExtra("id", book.getId());
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
