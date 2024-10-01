package com.example.librarystar;

import com.example.librarystar.Models.*;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiInterface {
    // Genres CRUD operations
    @GET("genres")
    Call<ArrayList<Genre>> getGenresList();

    @GET("genres/{id}")
    Call<Genre> getGenre(@Path("id") int id);

    @POST("genres")
    Call<Genre> addGenre(@Body Genre genre);

    @DELETE("genres/{id}")
    Call<Void> deleteGenre(@Path("id") int id);

    @PUT("genres/{id}")
    Call<Genre> updateGenre(@Path("id") int id, @Body Genre genre);

    // Actions CRUD operations
    @GET("actions")
    Call<ArrayList<Action>> getActionsList();

    @GET("actions/{id}")
    Call<Action> getAction(@Path("id") int id);

    @POST("actions")
    Call<Action> addAction(@Body Action action);

    @DELETE("actions/{id}")
    Call<Void> deleteAction(@Path("id") int id);

    @PUT("actions/{id}")
    Call<Action> updateAction(@Path("id") int id, @Body Action action);

    // Notifications CRUD operations
    @GET("notifications")
    Call<ArrayList<Notification>> getNotificationsList();

    @GET("notifications/{id}")
    Call<Notification> getNotification(@Path("id") int id);

    @POST("notifications")
    Call<Notification> addNotification(@Body Notification notification);

    @DELETE("notifications/{id}")
    Call<Void> deleteNotification(@Path("id") int id);

    @PUT("notifications/{id}")
    Call<Notification> updateNotification(@Path("id") int id, @Body Notification notification);
    // Authors CRUD operations
    @GET("authors")
    Call<ArrayList<Author>> getAuthorsList();

    @GET("authors/{id}")
    Call<Author> getAuthor(@Path("id") int id);

    @POST("authors/")
    Call<Author> addAuthor(@Body Author author);

    @DELETE("authors/{id}")
    Call<Void> deleteAuthor(@Path("id") int id);

    @PUT("authors/{id}")
    Call<Author> updateAuthor(@Path("id") int id, @Body Author author);

    // AuthorBooks CRUD operations
    @GET("authorbooks")
    Call<ArrayList<AuthorBooks>> getAuthorBooksList();

    @GET("authorbooks/{id}")
    Call<AuthorBooks> getAuthorBook(@Path("id") int id);

    @POST("authorbooks/")
    Call<AuthorBooks> addAuthorBook(@Body AuthorBooks authorBooks);

    @DELETE("authorbooks/{id}")
    Call<Void> deleteAuthorBook(@Path("id") int id);

    @PUT("authorbooks/{id}")
    Call<AuthorBooks> updateAuthorBook(@Path("id") int id, @Body AuthorBooks authorBooks);

    // UserBooks CRUD operations
    @GET("userbooks")
    Call<ArrayList<UserBooks>> getUserBooksList();

    @GET("userbooks/{id}")
    Call<UserBooks> getUserBook(@Path("id") int id);

    @GET("users/get_by_role/{id}")
    Call<ArrayList<User>> getUsersByRole(@Path("id") int id);

    @POST("books/")
    Call<Book> addBook(@Body Book book);

    @POST("userbooks/")
    Call<UserBooks> addUserBook(@Body UserBooks userBooks);
    @GET("books/get_by_user/{id}")
    Call<ArrayList<Book>> getBooksByUserId(@Path("id") int id);
    @GET("books/get_by_author/{id}")
    Call<ArrayList<Book>> getBooksByAuthorId(@Path("id") int id);
    @GET("books/get_by_genre/{id}")
    Call<ArrayList<Book>> getBooksByGenreId(@Path("id") int id);
    @GET("books")
    Call<ArrayList<Book>> getBooks();
    @GET("books/{id}")
    Call<Book> getBook(@Path("id") int id);

    @GET("authors/get_by_book/{id}")
    Call<ArrayList<Author>> getAuthorsByBookId(@Path("id") int id);

    @DELETE("userbooks/{id}")
    Call<Void> deleteUserBook(@Path("id") int id);

    @PUT("userbooks/{id}")
    Call<UserBooks> updateUserBook(@Path("id") int id, @Body UserBooks userBooks);

    // Users CRUD operations
    @GET("users")
    Call<ArrayList<User>> getUsersList();

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int id);



    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
            //"charset: utf-8"
    })
    @POST("users/")
    Call<User> addUser(@Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    // Roles CRUD operations
    @GET("roles")
    Call<ArrayList<Role>> getRolesList();
    @GET("roles/{id}")
    Call<Role> getRole(@Path("id") int id);
    @GET("roles/1")
    Call<Role> getRoleUser();

    @POST("roles")
    Call<Role> addRole(@Body Role role);

    @DELETE("roles/{id}")
    Call<Void> deleteRole(@Path("id") int id);

    @PUT("roles/{id}")
    Call<Role> updateRole(@Path("id") int id, @Body Role role);
    @POST("auth/")
    Call<User> getUserByLogs(@Body User user);
}
