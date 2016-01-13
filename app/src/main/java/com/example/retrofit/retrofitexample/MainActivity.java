package com.example.retrofit.retrofitexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.retrofit.API.BooksAPI;
import com.example.retrofit.model.Book;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    public static final String ROOT_URL = "https://dl.dropboxusercontent.com/";
    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_BOOK_ID = "key_book_id";
    public static final String KEY_BOOK_NAME = "key_book_name";
    public static final String KEY_BOOK_PRICE = "key_book_price";
    public static final String KEY_BOOK_STOCK = "key_book_stock";
    //List view to show data
    private ListView listView;
    //List of type books this list will store type Book which is our data model
    private List<Book> books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewBooks);

        //Calling the method that will fetch data
        getBooks();

        //Setting onItemClickListener to listview
        listView.setOnItemClickListener(this);
    }

    private void getBooks() {
//While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        Retrofit client = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(JacksonConverterFactory.create()).build();
        BooksAPI service = client.create(BooksAPI.class);
        Call<List<Book>> call = service.getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(retrofit.Response<List<Book>> response, Retrofit retrofit) {
                loading.dismiss();
                books=response.body();
                showList();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }


    //Our method to show list
    private void showList() {
        //String array to store all the book names
        String[] items = new String[books.size()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < books.size(); i++) {
            //Storing names to string array
            items[i] = books.get(i).getName();
        }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);

        //Setting adapter to listview
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
        Intent intent = new Intent(this, ShowBooksActivity.class);

        //Getting the requested book from the list
        Book book = books.get(position);

        //Adding book details to intent
        intent.putExtra(KEY_BOOK_ID, book.getBookId());
        intent.putExtra(KEY_BOOK_NAME, book.getName());
        intent.putExtra(KEY_BOOK_PRICE, book.getPrice());
        intent.putExtra(KEY_BOOK_STOCK, book.getInStock());

        //Starting another activity to show book details
        startActivity(intent);
    }
}
