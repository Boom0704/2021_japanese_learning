package com.example.termproject0;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {StudyList.class}, version = 1)
public abstract class AppDatabaseStudyList extends RoomDatabase {
    public abstract StudyListDao studyListDao();
}