package com.example.librarystar;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarystar.Models.Author;
import com.example.librarystar.Models.Book;
import com.example.librarystar.Models.BookWithAuthors;
import com.example.librarystar.Models.UserBooks;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoryzontalBookWithoutAuthorsRecyclerAdapter extends RecyclerView.Adapter<HoryzontalBookWithoutAuthorsRecyclerAdapter.ViewHolder> {
    private List<Book> data;
    private Context context;

    public HoryzontalBookWithoutAuthorsRecyclerAdapter(List<Book> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_horizontal_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = data.get(position);
        holder.tv.setText(book.getName());
        Picasso.with(context).load(book.getImage_path()).into(holder.img);
        holder.tv_above.setText(book.getYear_of_creating() + " год");
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BookDetailActivity.class);
                intent.putExtra("id", book.getId());
                view.getContext().startActivity(intent);
            }
        });
        holder.card_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface apiInterface = ServiceBuilder.buildRequest().create(ApiInterface.class);
                Call<UserBooks> addBookUser = apiInterface.addUserBook(new UserBooks(data.get(holder.getAdapterPosition()).getId(), StaticData.user_id));
                addBookUser.enqueue(new Callback<UserBooks>() {
                    @Override
                    public void onResponse(Call<UserBooks> call, Response<UserBooks> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(view.getContext(), "Книга добавлена в библиотеку успешно!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (response.code() == 400){
                                Toast.makeText(view.getContext(), "Книга уже добавлена в ваши любимые!", Toast.LENGTH_SHORT).show();
                            }
                            else Toast.makeText(view.getContext(), "Ошибка сервера", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserBooks> call, Throwable t) {
                        Toast.makeText(view.getContext(), "Ошибка запроса", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    private void addBook(int book_id){

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv, tv_above;
        ImageView img;
        ConstraintLayout card;
        Button card_button;
        ViewHolder(View view){
            super(view);
            tv = view.findViewById(R.id.card_tv);
            tv_above = view.findViewById(R.id.card_date);
            img = view.findViewById(R.id.card_img);
            card = view.findViewById(R.id.card_block);
            card_button = view.findViewById(R.id.card_button);
        }
    }
}
