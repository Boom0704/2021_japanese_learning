package com.example.termproject0;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int wordNum;
    private String kanji;
    private String gana;
    private String kor;
    private int check;
    private int score;


    public Word(String kanji, String gana, String kor, int check, int score) {
        this.kanji = kanji;
        this.gana = gana;
        this.kor = kor;
        this.check = check;
        this.score = score;
    }

    public int getWordNum() {
        return wordNum;
    }

    public void setWordNum(int wordNum) {
        this.wordNum = wordNum;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getGana() {
        return gana;
    }

    public void setGana(String gana) {
        this.gana = gana;
    }

    public String getKor() {
        return kor;
    }

    public void setKor(String kor) {
        this.kor = kor;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordNum=" + wordNum +
                ", kanji='" + kanji + '\'' +
                ", gana='" + gana + '\'' +
                ", kor='" + kor + '\'' +
                ", check=" + check +
                ", score=" + score +
                '}';
    }
}
