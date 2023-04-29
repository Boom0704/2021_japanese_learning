package com.example.termproject0;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WriteAction extends AppCompatActivity {

    //전역 변수 설정 존
    String[] bookListDivide = new String[20];
    int[] bookListInt = new int[20];
    //현재 단어를 나타내주는 지표
    int mouse = 0;
    //힌트 보기 위한 장소
    int coin = 0;
    //단어를 통과했는지 아닌지 확인
    int release = 0;
    boolean[] wordListDivideReversal = new boolean[20];
    //노래 저장
    int[] soundID= new int[20];
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_action);
        setTitle("쓰기로 일본어 학습");


        AppDatabaseWord dbWord = Room.databaseBuilder(this, AppDatabaseWord.class, "word-db").allowMainThreadQueries().build();
        Button writingbtncheck = (Button) findViewById(R.id.writingbtncheck);
        Button writingbtnsound = (Button) findViewById(R.id.writingbtnsound);
        Button writingbtnclear = (Button) findViewById(R.id.writingbtnclear);
        LinearLayout writingtvget = (LinearLayout) findViewById(R.id.writingtvget);
        TextView writingtvhangle = (TextView) findViewById(R.id.writingtvhangle);
        final MyView m = new MyView(this);





        Arrays.fill(wordListDivideReversal, true);

        Intent intentGet = getIntent();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < intentGet.getStringExtra("bookL").split("-").length; i++) {

            String[] divide1 = intentGet.getStringExtra("bookL").split("-");
            list.add(divide1[i]);

        }
        Collections.shuffle(list);
        list.toArray(bookListDivide);
        for(int i = 0; i < intentGet.getStringExtra("bookL").split("-").length; i++){
            bookListInt[i]=Integer.parseInt(bookListDivide[i]);
        }

        // 음악 파일 재생
        final SoundPool sp = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
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




        //온클릭 //온클릭 //온클릭 //온클릭 //온클릭 //온클릭 //온클릭 //온클릭 //온클릭 //온클릭
        writingbtnsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.play(soundID[mouse],1,1,0,0,1f);
            }
        });
        writingbtncheck.setOnClickListener(new View.OnClickListener() { //지우기 버튼 눌렸을때
            @Override
            public void onClick(View v){
                List<Word> wordList = dbWord.wordDao().getAll();
                String kor = wordList.get(bookListInt[mouse]-1).getKor();
                String kanji = wordList.get(bookListInt[mouse]-1).getKanji();
                String gana = wordList.get(bookListInt[mouse]-1).getGana();
                if(release==0){
                    writingbtnclear.setText("WRONG");
                    writingbtncheck.setText("PASS");
                    writingtvhangle.setText(kor+"/ "+kanji+"/ "+gana);
                    release=1;
                }
                else{
                    wordListDivideReversal[mouse] = false;

                    //데이터베이스에 단어별 점수 갱신
                    List<Word> renewalWordList = dbWord.wordDao().getAll();
                    Word newWord =
                            new Word(renewalWordList.get(bookListInt[mouse]-1).getKanji(),
                                    renewalWordList.get(bookListInt[mouse]-1).getGana(),
                                    renewalWordList.get(bookListInt[mouse]-1).getKor(),
                                    renewalWordList.get(bookListInt[mouse]-1).getCheck(),
                                    renewalWordList.get(bookListInt[mouse]-1).getScore());
                    newWord.setWordNum(bookListInt[mouse]);
                    newWord.setScore(renewalWordList.get(bookListInt[mouse]-1).getScore()-1);
                    dbWord.wordDao().update(newWord);
                    //데이터베이스에 단어별 점수 갱신

                    mouseUp();
                    readSection();
                    release=0;
                    writingbtnclear.setText("CLEAR");
                    writingbtncheck.setText("CHECK");
                    points.clear();
                    m.invalidate();
                }
            }
        });
        writingbtnclear.setOnClickListener(new View.OnClickListener() { //지우기 버튼 눌렸을때
            @Override
            public void onClick(View v){
                exitDisplay();
                if(release==0){
                    points.clear();
                    m.invalidate();
                }
                else{
                    //데이터베이스에 단어별 점수 갱신
                    List<Word> renewalWordList = dbWord.wordDao().getAll();
                    Word newWord =
                            new Word(renewalWordList.get(bookListInt[mouse]-1).getKanji(),
                                    renewalWordList.get(bookListInt[mouse]-1).getGana(),
                                    renewalWordList.get(bookListInt[mouse]-1).getKor(),
                                    renewalWordList.get(bookListInt[mouse]-1).getCheck(),
                                    renewalWordList.get(bookListInt[mouse]-1).getScore());
                    newWord.setWordNum(bookListInt[mouse]);
                    newWord.setScore(renewalWordList.get(bookListInt[mouse]-1).getScore()+4);
                    dbWord.wordDao().update(newWord);
                    //데이터베이스에 단어별 점수 갱신

                    mouseUp();
                    readSection();
                    release=0;
                    writingbtnclear.setText("CLEAR");
                    writingbtncheck.setText("CHECK");
                    points.clear();
                    m.invalidate();
                }
            }
        });

        writingtvget.addView(m);

        getCurrentScore();
        readSection();
    }


    void readSection() {

        AppDatabaseWord dbWord = Room.databaseBuilder(this, AppDatabaseWord.class, "word-db").allowMainThreadQueries().build();
        List<Word> wordList = dbWord.wordDao().getAll();
        //객체가져오기

        String kor = wordList.get(bookListInt[mouse]-1).getKor();

        TextView writingtvhangle = (TextView) findViewById(R.id.writingtvhangle);
        Button writingbtncheck = (Button) findViewById(R.id.writingbtncheck);
        Button writingbtnsound = (Button) findViewById(R.id.writingbtnsound);
        Button writingbtnclear = (Button) findViewById(R.id.writingbtnclear);



        if (wordListDivideReversal[mouse] == true) {
            writingtvhangle.setText(kor);
            getCurrentScore();
            return;
        }

        for (int i = 0; i < 20; i++) {
            if(wordListDivideReversal[i]==true){
                getCurrentScore();
                return;
            }
        }
        writingbtncheck.setEnabled(false);
        writingbtnsound.setEnabled(false);
        writingbtnclear.setEnabled(false);
        writingtvhangle.setText("완료");
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



    //다른 클래스////다른 클래스////다른 클래스////다른 클래스////다른 클래스////다른 클래스////다른 클래스////다른 클래스////다른 클래스////다른 클래스//
    class Point{
        float x;
        float y;
        boolean check;
        int color;

        public Point(float x, float y, boolean check,int color)
        {
            this.x = x;
            this.y = y;
            this.check = check;
            this.color = color;
        }
    }

    ArrayList<Point> points = new ArrayList<Point>();
    int color = Color.BLACK;

    class MyView extends View
    {
        public MyView(Context context) { super(context); }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint p = new Paint();
            p.setStrokeWidth(15);
            for(int i=1 ; i<points.size() ; i++)
            {
                p.setColor(points.get(i).color);
                if(!points.get(i).check)
                    continue;
                canvas.drawLine(points.get(i-1).x,points.get(i-1).y,points.get(i).x,points.get(i).y,p);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            exitDisplay();
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                    points.add(new Point(x,y,false , color));
                case MotionEvent.ACTION_MOVE :
                    points.add(new Point(x,y,true , color));
                    break;
                case MotionEvent.ACTION_UP :
                    break;
            }
            invalidate();
            return true;
        }
    }

}
