package com.example.retrofit.model;

/**
 * Created by techjini on 12/1/16.
 */
public class Book {
    private String bookId;
    private String name;
    private String price;
    private String inStock;

    /**
     * @return The bookId
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * @param bookId The bookId
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return The inStock
     */
    public String getInStock() {
        return inStock;
    }

    /**
     * @param inStock The inStock
     */
    public void setInStock(String inStock) {
        this.inStock = inStock;
    }


}
