package com.example.termproject0;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    SharedPreferences pref;          // 프리퍼런스
    SharedPreferences.Editor editor; // 에디터

    String firstArray;
    String secondArray;
    String listType="";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        setTitle("읽기 학습 선택");

        AppDatabaseStudyList dbStudyList = Room.databaseBuilder(this, AppDatabaseStudyList.class, "studyList-db").allowMainThreadQueries().build();
        AppDatabaseWord dbWord = Room.databaseBuilder(this, AppDatabaseWord.class, "word-db").allowMainThreadQueries().build();
        AppDatabaseBook dbbook = Room.databaseBuilder(this, AppDatabaseBook.class, "book-db").allowMainThreadQueries().build();
        List<StudyList> studyListList = dbStudyList.studyListDao().getAll();
        firstArray = studyListList.get(0).getStudyList();
        secondArray = studyListList.get(1).getStudyList();

        TextView textread = (TextView) findViewById(R.id.textread);
        Button p2btnr = (Button) findViewById(R.id.p2btnr);
        Button p2btnw = (Button) findViewById(R.id.p2btnw);
        Button p2btnrand = (Button) findViewById(R.id.p2btnrand);

        Button makeReview = (Button) findViewById(R.id.makeReview);


        makeReview.setOnClickListener(new View.OnClickListener() {
            private Context context;

            @Override
            public void onClick(View view) {

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
            }
        });

        p2btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listType="read-first";
                Intent intent = new Intent(getApplicationContext(), VocaReadActivity.class);
                intent.putExtra("sendData", firstArray);
                intent.putExtra("listType", listType);
                startActivity(intent);
            }
        });

        p2btnw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listType="read-second";
                Intent intent = new Intent(getApplicationContext(), VocaReadActivity.class);
                intent.putExtra("sendData", secondArray);
                intent.putExtra("listType", listType);
                startActivity(intent);
            }
        });

        p2btnrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendRandom="";
                List<Integer> list = new ArrayList<>();
                for (int i = 0 ; i<200; i++){
                    list.add(i);
                }
                Collections.shuffle(list);
                for (int i = 0 ; i<20; i++){
                    if(i==19){
                        sendRandom=sendRandom+list.get(i);
                    }else{
                        sendRandom=sendRandom+list.get(i)+"-";
                    }
                }

                Intent intent = new Intent(getApplicationContext(), ReadAction.class);
                intent.putExtra("bookL", sendRandom);
                startActivity(intent);
            }
        });


    }


}
