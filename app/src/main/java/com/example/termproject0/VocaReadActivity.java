package com.example.termproject0;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import java.util.List;

public class VocaReadActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosing_voca_read);
        setTitle("읽기 학습 단어 선택");

        Intent intent = getIntent();
        String getData = intent.getStringExtra("sendData");
        String listType = intent.getStringExtra("listType");

        int[] getDataDivide = new int[20];
        String[] listTypeDivide = new String[2];
        String[] divide1 = getData.split("-");
        String[] divide2 = listType.split("-");

        for (int i = 0; i < divide1.length; i++) {
            getDataDivide[i] = Integer.parseInt(divide1[i]);
        }
        for (int i = 0; i < divide2.length; i++) {
            listTypeDivide[i] = divide2[i];
        }






        LinearLayout BtnLinearLayout = (LinearLayout) findViewById(R.id.BtnLinearLayout);
        Context context = this;

        AppDatabaseBook dbbook = Room.databaseBuilder(this, AppDatabaseBook.class, "book-db").allowMainThreadQueries().build();
        List<Book> bookList = dbbook.bookDao().getAll();


        for (int i = 0; i < getDataDivide.length; i++) {
            if (getDataDivide[i] != 0) {
                Button btn = new Button(context);


                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.solid_button));
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(100,10,100,10);  // 왼쪽, 위, 오른쪽, 아래 순서입니다.
                btn.setLayoutParams(params);

                if(i<9){
                    btn.setText("단어  0" + getDataDivide[i]);
                }else if(i<99){
                    btn.setText("단어  " + getDataDivide[i]);
                }else {
                    btn.setText("단어 0" + getDataDivide[i]);
                }
                BtnLinearLayout.addView(btn);

                final int j = i;

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), ReadAction.class);
                        intent.putExtra("bookL", bookList.get(j).getBookList());
                        startActivity(intent);


                    }
                });

            }

        }


    }
}
