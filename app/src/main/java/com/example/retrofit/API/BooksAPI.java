package com.example.retrofit.API;

import com.example.retrofit.model.Book;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.Callback;

/**
 * Created by techjini on 12/1/16.
 */
public interface BooksAPI {

    /*Retrofit get annotation*/
    @GET("s/dmhctoi7tdctwxm/Samplebooks.json?dl=0")
   // public void getBooks(Callback<List<Book>> response);
    Call<List<Book>> getBooks();

}
