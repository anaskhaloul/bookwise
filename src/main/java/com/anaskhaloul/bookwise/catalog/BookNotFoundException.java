package com.anaskhaloul.bookwise.catalog;
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(long id) { super("Book " + id + " was not found"); }
}
