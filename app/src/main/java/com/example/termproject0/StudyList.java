package com.example.termproject0;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StudyList {
    @PrimaryKey(autoGenerate = true)
    private int studyNum;
    private String studyList;

    public StudyList(String studyList) {
        this.studyList = studyList;
    }

    public int getStudyNum() {
        return studyNum;
    }

    public void setStudyNum(int studyNum) {
        this.studyNum = studyNum;
    }


    public String getStudyList() {
        return studyList;
    }

    public void setStudyList(String studyList) {
        this.studyList = studyList;
    }

    @Override
    public String toString() {
        return "StudyList{" +
                "studyNum=" + studyNum +
                ", studyList='" + studyList + '\'' +
                '}';
    }
}
