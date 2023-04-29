package com.example.termproject0;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ReadAction extends AppCompatActivity{


    //전역 변수 설정 존
    String[] bookListDivide = new String[20];
    int[] bookListInt = new int[20];
    //현재 단어를 나타내주는 지표
    int mouse = 0;
    //힌트 보기 위한 장소
    int coin = 0;
    //단어를 통과했는지 아닌지 확인
    boolean[] wordListDivideReversal = new boolean[20];
    //노래 저장
    int[] soundID= new int[20];
    private ReadAction context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_action);
        setTitle("읽기로 일본어 학습");

        Button readingbtnmain = (Button) findViewById(R.id.readingbtnmain);
        Button readingbtnwrong = (Button) findViewById(R.id.readingbtnwrong);
        Button readingbtnsound = (Button) findViewById(R.id.readingbtnsound);
        Button readingbtnpass = (Button) findViewById(R.id.readingbtnpass);
        AppDatabaseWord dbWord = Room.databaseBuilder(this, AppDatabaseWord.class, "word-db").allowMainThreadQueries().build();
        List<Word> wordList = dbWord.wordDao().getAll();
        Intent intent = getIntent();

        Arrays.fill(wordListDivideReversal, true);
        //섞기
        List<String> list = new ArrayList<>();
        for (int i = 0; i < intent.getStringExtra("bookL").split("-").length; i++) {

            String[] divide1 = intent.getStringExtra("bookL").split("-");
            list.add(divide1[i]);

        }
        Collections.shuffle(list);
        list.toArray(bookListDivide);
        for(int i = 0; i < intent.getStringExtra("bookL").split("-").length; i++){
            bookListInt[i]=Integer.parseInt(bookListDivide[i]);
        }
        //임시로 텍스트 순서 출력
        getCurrentScore();
        readSection();



        // 음악 파일 재생
        final SoundPool sp = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
        // 최대 음악파일의 개수 // 스트림 타입 // 음질 - 기본값:0
        ArrayList<Integer> idList = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            StringBuilder fileName = new StringBuilder("r");
            fileName.append( bookListInt[i] );
            idList.add( getResources().getIdentifier( fileName.toString(), "raw", getPackageName()) );
        }
        for (int i = 0; i < 20; i++){
            soundID[i]=sp.load(this, idList.get(i),1);
        }
        // 현재 화면의 제어권자 // 음악 파일 // 우선순위




        //온클릭 메소드
        readingbtnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitDisplay();
                String kanji = wordList.get(bookListInt[mouse]-1).getKanji();
                String kor = wordList.get(bookListInt[mouse]-1).getKor();


                if (coin == 0) {
                    readingbtnmain.setText(kor);
                    coin = 1;
                } else {
                    readingbtnmain.setText(kanji);
                    coin = 0;
                }
            }
        });
        readingbtnsound.setOnClickListener(view -> {


            sp.play(soundID[mouse],1,1,0,0,1f);
            // 준비한 soundID // 왼쪽 볼륨 float 0.0(작은소리)~1.0(큰소리) // 오른쪽볼륨 float
            // 우선순위 int // 반복회수 int -1:무한반복, 0:반복안함 // 재생속도 float 0.5(절반속도)~2.0(2배속)

        });
        readingbtnwrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                //데이터베이스에 단어별 점수 갱신
                List<Word> renewalWordList = dbWord.wordDao().getAll();
                Word newWord =
                        new Word(renewalWordList.get(bookListInt[mouse]-1).getKanji(),
                                renewalWordList.get(bookListInt[mouse]-1).getGana(),
                                renewalWordList.get(bookListInt[mouse]-1).getKor(),
                                renewalWordList.get(bookListInt[mouse]-1).getCheck(),
                                renewalWordList.get(bookListInt[mouse]-1).getScore());
                newWord.setWordNum(bookListInt[mouse]);
                newWord.setScore(renewalWordList.get(bookListInt[mouse]-1).getScore()-2);
                dbWord.wordDao().update(newWord);
                //데이터베이스에 단어별 점수 갱신

                mouseUp();
                readSection();
                coin = 0;
            }
        });
        readingbtnpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordListDivideReversal[mouse] = false;

                //데이터베이스에 단어별 점수 갱신
                List<Word> renewalWordList = dbWord.wordDao().getAll();
                int newCheck=renewalWordList.get(bookListInt[mouse]-1).getCheck() ;
                if(renewalWordList.get(bookListInt[mouse]-1).getCheck()==0)
                    newCheck = 1;
                Word newWord =
                        new Word(renewalWordList.get(bookListInt[mouse]-1).getKanji(),
                                renewalWordList.get(bookListInt[mouse]-1).getGana(),
                                renewalWordList.get(bookListInt[mouse]-1).getKor(),
                                newCheck,
                                renewalWordList.get(bookListInt[mouse]-1).getScore());
                newWord.setWordNum(bookListInt[mouse]);
                newWord.setScore(renewalWordList.get(bookListInt[mouse]-1).getScore()+3);

                dbWord.wordDao().update(newWord);
                //데이터베이스에 단어별 점수 갱신

                mouseUp();
                readSection();
                coin = 0;
            }
        });
    }

    void readSection() {

        AppDatabaseWord dbWord = Room.databaseBuilder(this, AppDatabaseWord.class, "word-db").allowMainThreadQueries().build();
        List<Word> wordList = dbWord.wordDao().getAll();
        //객체가져오기
        TextView ganaText = (TextView) findViewById(R.id.ganaText);
        Button readingbtnmain = (Button) findViewById(R.id.readingbtnmain);
        Button readingbtnwrong = (Button) findViewById(R.id.readingbtnwrong);
        Button readingbtnsound = (Button) findViewById(R.id.readingbtnsound);
        Button readingbtnpass = (Button) findViewById(R.id.readingbtnpass);

        String kanji = wordList.get(bookListInt[mouse]-1).getKanji();
        String gana = wordList.get(bookListInt[mouse]-1).getGana();


        if (wordListDivideReversal[mouse] == true) {
            readingbtnmain.setText(kanji);
            ganaText.setText(gana);
            getCurrentScore();
            return;
        }

        for (int i = 0; i < 20; i++) {
            if(wordListDivideReversal[i]==true){
                getCurrentScore();
                return;
            }
        }
        readingbtnwrong.setEnabled(false);
        readingbtnsound.setEnabled(false);
        readingbtnpass.setEnabled(false);
        readingbtnmain.setText("완료");
        return;
    }
    void getCurrentScore(){
        String showScore="";
        for (int i = 0; i < 20; i++) {
            if(wordListDivideReversal[i]==true){
                showScore=showScore+" - ";
            }else{
                showScore=showScore+"O";
            }
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(showScore);
        }

    }
    void mouseUp(){
        if(mouse==19) {
            mouse = 0;
        }else{
            mouse++;
        }
        for (int i = 0; i < 20; i++) {
            if(wordListDivideReversal[mouse]==true){
                return;
            }else{
                if(mouse==19) {
                    mouse = 0;
                }else{
                    mouse++;
                }
            }
        }
        return;
    }
    void exitDisplay(){
        for (int i = 0; i < 20; i++) {
            if(wordListDivideReversal[i]){
                return;
            }
        }
        finish();
        return;
    }
//    int setAudio(){
//        // 음악 파일 재생
//        final SoundPool sp = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
//        // 최대 음악파일의 개수 // 스트림 타입 // 음질 - 기본값:0
//        ArrayList<Integer> idList = new ArrayList<>();
//        for(int i = 0; i < 200; i++){
//            StringBuilder fileName = new StringBuilder("r");
//            fileName.append( i + 1 );
//            idList.add( getResources().getIdentifier( fileName.toString(), "raw", getPackageName()) );
//        }
//        final int soundID = sp.load(this, idList.get(bookListInt[mouse]-1),1);
//        // 현재 화면의 제어권자 // 음악 파일 // 우선순위
//        return soundID;
//    }
}