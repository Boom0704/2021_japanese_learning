package com.example.termproject0;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface StudyListDao {

    @Query("SELECT * FROM StudyList")
    List<StudyList> getAll();

    @Insert
    void insert(StudyList studyList);

    @Delete
    void delete(StudyList studyList);

    @Update
    void update(StudyList studyList);

}
