package com.example.termproject0;

import android.app.Activity;
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
import java.util.Collections;
import java.util.List;

public class WriteActivity extends AppCompatActivity {

    SharedPreferences pref;          // 프리퍼런스
    SharedPreferences.Editor editor; // 에디터

    String firstArray;
    String secondArray;
    String listType="";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        setTitle("쓰기 학습 선택");

        TextView textwrite = (TextView) findViewById(R.id.textwrite);
        Button p3btnr = (Button) findViewById(R.id.p3btnr);
        Button p3btnw = (Button) findViewById(R.id.p3btnw);
        Button p3btnrand = (Button) findViewById(R.id.p3btnrand);

        AppDatabaseStudyList dbStudyList = Room.databaseBuilder(this, AppDatabaseStudyList.class, "studyList-db").allowMainThreadQueries().build();
        List<StudyList> studyListList = dbStudyList.studyListDao().getAll();
        firstArray = studyListList.get(0).getStudyList();
        secondArray = studyListList.get(1).getStudyList();

        p3btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VocaWriteActivity.class);
                intent.putExtra("sendData", firstArray);
                intent.putExtra("listType", listType);
                startActivity(intent);
            }
        });


        p3btnw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), VocaWriteActivity.class);
                intent.putExtra("sendData", secondArray);
                intent.putExtra("listType", listType);
                startActivity(intent);
            }
        });

        p3btnrand.setOnClickListener(new View.OnClickListener() {
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

                Intent intent = new Intent(getApplicationContext(), WriteAction.class);
                intent.putExtra("bookL", sendRandom);
                startActivity(intent);
//                Toast.makeText(getApplicationContext(), sendRandom, Toast.LENGTH_LONG).show();

            }
        });

    }
}