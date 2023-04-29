package com.example.termproject0;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Word.class}, version = 1)
public abstract class AppDatabaseWord extends RoomDatabase {
    public abstract WordDao wordDao();
}
