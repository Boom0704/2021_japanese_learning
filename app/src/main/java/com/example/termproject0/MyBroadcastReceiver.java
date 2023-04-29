package com.example.termproject0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        AppDatabaseWord dbWord = Room.databaseBuilder(context, AppDatabaseWord.class, "word-db").allowMainThreadQueries().build();
        AppDatabaseStudyList dbStudyList = Room.databaseBuilder(context, AppDatabaseStudyList.class, "studyList-db").allowMainThreadQueries().build();
        AppDatabaseBook dbbook = Room.databaseBuilder(context, AppDatabaseBook.class, "book-db").allowMainThreadQueries().build();
        List<Book> bookList = dbbook.bookDao().getAll();
        List<Word> renewalWordList = dbWord.wordDao().getAll();

        for (int i=0;i<renewalWordList.size();i++)
        {
            //데이터베이스에 단어별 점수 갱신
            int[] a=new int[5];
            int b=10000;
            int check=renewalWordList.get(i).getCheck();

            for(int j=0;j<5;j++){
                if(check>b) {
                    a[j]=1;
                    check=check-b;
                }
                b=b%10;
            }
            int sumA = a[0]+a[1]+a[2]+a[3]+a[4];
            int newScore=renewalWordList.get(i).getScore();
            int newCheck=a[1]*10000+a[2]*1000+a[3]*100+a[4]*10;
            if(sumA==0&renewalWordList.get(i).getScore()!=0){newScore-=-2;}
            if(sumA==2){newScore-=+1;}
            if(sumA==3){newScore-=+3;}

            Word newWord =
                    new Word(renewalWordList.get(i).getKanji(),
                            renewalWordList.get(i).getGana(),
                            renewalWordList.get(i).getKor(),
                            newCheck,
                            newScore);
            newWord.setWordNum(i+1);
            dbWord.wordDao().update(newWord);
            //데이터베이스에 단어별 점수 갱신

        }
        List<Word>updateWordDao = dbWord.wordDao().getAll();
        List<String>reviewList= new ArrayList<>();
        for (int i=0;i<updateWordDao.size();i++)
        {
            if(updateWordDao.get(i).getScore()<-5){
                reviewList.add(String.valueOf((updateWordDao.get(i).getWordNum())));
            }
        }
        while (reviewList.size()>20){
            Collections.shuffle(reviewList);
            String makeRiview="";
            for (int i = 0 ; i<20; i++){
                if(i==19){
                    makeRiview=makeRiview+Integer.parseInt(reviewList.get(i));
                }else{
                    makeRiview=makeRiview+Integer.parseInt(reviewList.get(i))+"-";
                }
            }
            dbbook.bookDao().insert(new Book(makeRiview,1));

        }

        List<Word>checkWordDao = dbWord.wordDao().getAll();
        String makeRiviewList="";
        for (int i  =checkWordDao.size(); i>checkWordDao.size()-20; i--){
            if(i==checkWordDao.size()-19){
                makeRiviewList = makeRiviewList+i;
            }else{
                makeRiviewList = makeRiviewList+i+"-";
            }
        }

        StudyList newSL = new StudyList (makeRiviewList);
        newSL.setStudyNum(2);
        dbStudyList.studyListDao().update(newSL);

        Toast.makeText(context, "데이터베이스 업데이트가 완료되었습니다.", Toast.LENGTH_LONG).show();
    }
}