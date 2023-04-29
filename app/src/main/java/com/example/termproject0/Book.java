package com.example.termproject0;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int bookNum;
    private String bookList;
    //학습은 기본 0, 복습은 1 공부해서 만들어진 오답은 2
    private int bookType;


    public Book(String bookList, int bookType) {
        this.bookList = bookList;
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookNum=" + bookNum +
                ", bookList='" + bookList + '\'' +
                ", bookType=" + bookType +
                '}';
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public String getBookList() {
        return bookList;
    }

    public void setBookList(String bookList) {
        this.bookList = bookList;
    }

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }
}
