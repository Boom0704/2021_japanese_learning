package com.example.termproject0;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabaseBook extends RoomDatabase {
    public abstract BookDao bookDao();
}
