package com.example.termproject0;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("환견 설정");

        Button settingbtns = (Button) findViewById(R.id.settingbtns);
        Button reset = (Button) findViewById(R.id.reset);
        AppDatabaseWord dbWord = Room.databaseBuilder(this, AppDatabaseWord.class, "word-db").allowMainThreadQueries().build();
        AppDatabaseBook dbBook = Room.databaseBuilder(this, AppDatabaseBook.class, "book-db").allowMainThreadQueries().build();
        AppDatabaseStudyList dbStudyList = Room.databaseBuilder(this, AppDatabaseStudyList.class, "studyList-db").allowMainThreadQueries().build();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Word>deword = dbWord.wordDao().getAll();
                List<Book>debook = dbBook.bookDao().getAll();
                List<StudyList> destudy = dbStudyList.studyListDao().getAll();
                for (int i=0;i<destudy.size();i++){
                    StudyList newStudy = new StudyList("");
                    newStudy.setStudyNum(i+1);
                    dbStudyList.studyListDao().delete(newStudy);
                }
                for (int i=0;i<deword.size();i++){
                    Word newWord = new Word("","","",0,0);
                    newWord.setWordNum(i+1);
                    dbWord.wordDao().delete(newWord);
                }
                for (int i=0;i<debook.size();i++){
                    Book newBook = new Book("",0);
                    newBook.setBookNum(i+1);
                    dbBook.bookDao().delete(newBook);
                }


            }
        });
        settingbtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbWord.wordDao().insert(new Word("仕事 ", "しごと", "일_직업", 0, 0));
                dbWord.wordDao().insert(new Word("財布", "さいふ", "지갑", 0, 0));
                dbWord.wordDao().insert(new Word("ミルク", "-", "우유", 0, 0));
                dbWord.wordDao().insert(new Word("右", "みぎ", "오른쪽", 0, 0));
                dbWord.wordDao().insert(new Word("病院", "びょういん", "병원", 0, 0));
                dbWord.wordDao().insert(new Word("午後", "ごご", "오후", 0, 0));
                dbWord.wordDao().insert(new Word("話す", "はなす", "이야기하다", 0, 0));
                dbWord.wordDao().insert(new Word("電話", "でんわ", "전화", 0, 0));
                dbWord.wordDao().insert(new Word("手紙", "てがみ", "편지", 0, 0));
                dbWord.wordDao().insert(new Word("学校", "がっこう", "학교", 0, 0));
                dbWord.wordDao().insert(new Word("銀行", "ぎんこう", "은행", 0, 0));
                dbWord.wordDao().insert(new Word("悪い", "わるい", "나쁘다", 0, 0));
                dbWord.wordDao().insert(new Word("広い", "ひろい", "넓다", 0, 0));
                dbWord.wordDao().insert(new Word("住む", "すむ", "살다", 0, 0));
                dbWord.wordDao().insert(new Word("押す", "おす", "밀다", 0, 0));
                dbWord.wordDao().insert(new Word("物", "もの", "물건", 0, 0));
                dbWord.wordDao().insert(new Word("辞書", "じしょ", "사전", 0, 0));
                dbWord.wordDao().insert(new Word("宿題", "しゅくだい", "숙제", 0, 0));
                dbWord.wordDao().insert(new Word("答える", "こたえる", "대답하다", 0, 0));
                dbWord.wordDao().insert(new Word("電気", "でんき", "전기", 0, 0));
                dbWord.wordDao().insert(new Word("大人", "おとな", "어른", 0, 0));
                dbWord.wordDao().insert(new Word("低い", "ひくい", "(높이가) 낮다", 0, 0));
                dbWord.wordDao().insert(new Word("夏", "なつ", "여름", 0, 0));
                dbWord.wordDao().insert(new Word("交番", "こうばん", "파출소", 0, 0));
                dbWord.wordDao().insert(new Word("出口", "でぐち", "출구", 0, 0));
                dbWord.wordDao().insert(new Word("明るい", "明るい", "밝다", 0, 0));
                dbWord.wordDao().insert(new Word("ポケット", "-", "주머니", 0, 0));
                dbWord.wordDao().insert(new Word("年", "とし", "나이", 0, 0));
                dbWord.wordDao().insert(new Word("去年", "きょねん", "작년", 0, 0));
                dbWord.wordDao().insert(new Word("寝る", "ねる", "자다", 0, 0));
                dbWord.wordDao().insert(new Word("手", "て", "손", 0, 0));
                dbWord.wordDao().insert(new Word("塩", "しお", "소금", 0, 0));
                dbWord.wordDao().insert(new Word("千", "せん", "천", 0, 0));
                dbWord.wordDao().insert(new Word("外国", "がいこく", "외국", 0, 0));
                dbWord.wordDao().insert(new Word("食べ物", "たべもの", "음식", 0, 0));
                dbWord.wordDao().insert(new Word("兄", "あに", "형", 0, 0));
                dbWord.wordDao().insert(new Word("降る", "ふる", "(비가) 내리다", 0, 0));
                dbWord.wordDao().insert(new Word("時計", "とけい", "시계", 0, 0));
                dbWord.wordDao().insert(new Word("パソコン", "-", "컴퓨터", 0, 0));
                dbWord.wordDao().insert(new Word("川、河", "かわ", "강", 0, 0));
                dbWord.wordDao().insert(new Word("一番", "いちばん", "가장_최상", 0, 0));
                dbWord.wordDao().insert(new Word("一人", "ひとり", "한 명", 0, 0));
                dbWord.wordDao().insert(new Word("番号", "ばんごう", "번호", 0, 0));
                dbWord.wordDao().insert(new Word("今週", "こんしゅう", "이번주", 0, 0));
                dbWord.wordDao().insert(new Word("降りる", "おりる", "내리다", 0, 0));
                dbWord.wordDao().insert(new Word("洗う", "あらう", "씻다_닦다", 0, 0));
                dbWord.wordDao().insert(new Word("音楽", "おんがく", "음악", 0, 0));
                dbWord.wordDao().insert(new Word("一つ", "ひとつ", "한 개", 0, 0));
                dbWord.wordDao().insert(new Word("エレベーター", "-", "엘리베이터", 0, 0));
                dbWord.wordDao().insert(new Word("午前", "ごぜん", "오전", 0, 0));
                dbWord.wordDao().insert(new Word("パン", "-", "빵", 0, 0));
                dbWord.wordDao().insert(new Word("下がる", "さがる", "내려가다", 0, 0));
                dbWord.wordDao().insert(new Word("来年", "らいねん", "내년", 0, 0));
                dbWord.wordDao().insert(new Word("犬", "いぬ", "개", 0, 0));
                dbWord.wordDao().insert(new Word("小さい", "ちいさい", "작다", 0, 0));
                dbWord.wordDao().insert(new Word("ジャンパー", "-", "점퍼", 0, 0));
                dbWord.wordDao().insert(new Word("スカート", "-", "치마", 0, 0));
                dbWord.wordDao().insert(new Word("弟", "おとうと", "남동생", 0, 0));
                dbWord.wordDao().insert(new Word("勉強", "べんきょう", "공부", 0, 0));
                dbWord.wordDao().insert(new Word(" 学生", "がくせい", "학생", 0, 0));
                dbWord.wordDao().insert(new Word("魚 ", "さかな", "물고기", 0, 0));
                dbWord.wordDao().insert(new Word("お腹 ", "おなか", "배", 0, 0));
                dbWord.wordDao().insert(new Word("分かる ", "わかる", "이해하다 (알다)", 0, 0));
                dbWord.wordDao().insert(new Word("弱い ", "よわい", "약하다", 0, 0));
                dbWord.wordDao().insert(new Word("一時 ", "いちじ", "1시", 0, 0));
                dbWord.wordDao().insert(new Word("歩く ", "あるく", "걷다", 0, 0));
                dbWord.wordDao().insert(new Word("返す ", "かえす", "돌려주다", 0, 0));
                dbWord.wordDao().insert(new Word("豚 ", "ぶた", "돼지", 0, 0));
                dbWord.wordDao().insert(new Word("楽しい ", "たのしい", "즐겁다", 0, 0));
                dbWord.wordDao().insert(new Word("掛かる ", "かかる", "걸리다", 0, 0));
                dbWord.wordDao().insert(new Word("鉛筆 ", "えんぴつ", "연필", 0, 0));
                dbWord.wordDao().insert(new Word("丈夫な", "じょうぶな", "튼튼한", 0, 0));
                dbWord.wordDao().insert(new Word("馬 ", "うま", "발", 0, 0));
                dbWord.wordDao().insert(new Word("泳ぐ ", "およぐ", "수영하다", 0, 0));
                dbWord.wordDao().insert(new Word("秋 ", "あき", "가을", 0, 0));
                dbWord.wordDao().insert(new Word("左 ", "ひだり", "왼(쪽)", 0, 0));
                dbWord.wordDao().insert(new Word("吸う ", "すう", "들이마시다_피우다", 0, 0));
                dbWord.wordDao().insert(new Word("留学生 ", "りゅうがくせい", "유학생", 0, 0));
                dbWord.wordDao().insert(new Word("書く ", "かく", "쓰다", 0, 0));
                dbWord.wordDao().insert(new Word("高校 ", "こうこう", "고교 (고등학생)", 0, 0));
                dbWord.wordDao().insert(new Word("狭い ", "せまい", "좁다", 0, 0));
                dbWord.wordDao().insert(new Word("コップ ", "-", "컵", 0, 0));
                dbWord.wordDao().insert(new Word("読む ", "よむ", "읽다", 0, 0));
                dbWord.wordDao().insert(new Word("休む ", "やすむ", "쉬다", 0, 0));
                dbWord.wordDao().insert(new Word("待つ ", "まつ", "기다리다", 0, 0));
                dbWord.wordDao().insert(new Word("お母さん ", "おかあさん", "어머니", 0, 0));
                dbWord.wordDao().insert(new Word("十 ", "じゅう", "십", 0, 0));
                dbWord.wordDao().insert(new Word("台所 ", "だいどころ", "부엌", 0, 0));
                dbWord.wordDao().insert(new Word("会う ", "あう", "만나다", 0, 0));
                dbWord.wordDao().insert(new Word("南 ", "みなみ", "남", 0, 0));
                dbWord.wordDao().insert(new Word("飛行機 ", "ひこうき", "비행기", 0, 0));
                dbWord.wordDao().insert(new Word("写真 ", "しゃしん", "사진", 0, 0));
                dbWord.wordDao().insert(new Word("前 ", "まえ", "앞", 0, 0));
                dbWord.wordDao().insert(new Word("作る ", "つくる", "만들다", 0, 0));
                dbWord.wordDao().insert(new Word("ご飯 ", "ごはん", "밥", 0, 0));
                dbWord.wordDao().insert(new Word("聞く ", "きく", "듣다", 0, 0));
                dbWord.wordDao().insert(new Word("白い ", "しろい", "하얗다", 0, 0));
                dbWord.wordDao().insert(new Word("絵 ", "え", "그림", 0, 0));
                dbWord.wordDao().insert(new Word("ドア ", "-", "문", 0, 0));
                dbWord.wordDao().insert(new Word("同じ ", "おなじ", "같다", 0, 0));
                dbWord.wordDao().insert(new Word("動物 ", "どうぶつ", "동물", 0, 0));
                dbWord.wordDao().insert(new Word("靴 ", "くつ", "신발", 0, 0));
                dbWord.wordDao().insert(new Word("高い ", "たかい", "높다", 0, 0));
                dbWord.wordDao().insert(new Word("教室 ", "きょうしつ", "교실", 0, 0));
                dbWord.wordDao().insert(new Word("本 ", "ほん", "책", 0, 0));
                dbWord.wordDao().insert(new Word("デパート ", "-", "백화점", 0, 0));
                dbWord.wordDao().insert(new Word("生まれる ", "うまれる", "태어나다", 0, 0));
                dbWord.wordDao().insert(new Word("傘 ", "かさ", "우산", 0, 0));
                dbWord.wordDao().insert(new Word("危ない ", "あぶない", "위험하다", 0, 0));
                dbWord.wordDao().insert(new Word("新聞 ", "しんぶん", "신문", 0, 0));
                dbWord.wordDao().insert(new Word("西 ", "にし", "서", 0, 0));
                dbWord.wordDao().insert(new Word("暑い ", "あつい", "덥다", 0, 0));
                dbWord.wordDao().insert(new Word("お茶 ", "おちゃ", "차", 0, 0));
                dbWord.wordDao().insert(new Word("話 ", "はなし", "말(이야기)", 0, 0));
                dbWord.wordDao().insert(new Word("休み ", "やすみ", "쉬는날_방학", 0, 0));
                dbWord.wordDao().insert(new Word("大きい ", "おおきい", "크다", 0, 0));
                dbWord.wordDao().insert(new Word("遊ぶ", "あそぶ", "놀다", 0, 0));
                dbWord.wordDao().insert(new Word("今月 ", "こんげつ", "이번달", 0, 0));
                dbWord.wordDao().insert(new Word("机 ", "つくえ", "책상", 0, 0));
                dbWord.wordDao().insert(new Word("入り口 ", "いりぐち", "입구", 0, 0));
                dbWord.wordDao().insert(new Word("東西南北 ", "とうざいなんぼく", "동서남북", 0, 0));
                dbWord.wordDao().insert(new Word("食べる ", "たべる", "먹다", 0, 0));
                dbWord.wordDao().insert(new Word("来る ", "くる", "오다", 0, 0));
                dbWord.wordDao().insert(new Word("ボタン ", "-", "버튼", 0, 0));
                dbWord.wordDao().insert(new Word("静かな ", "しずかな", "조용한", 0, 0));
                dbWord.wordDao().insert(new Word("時間 ", "じかん", "시간", 0, 0));
                dbWord.wordDao().insert(new Word("紙 ", "かみ", "종이", 0, 0));
                dbWord.wordDao().insert(new Word("黒い ", "くろい", "까맣다", 0, 0));
                dbWord.wordDao().insert(new Word("友達 ", "ともだち", "친구", 0, 0));
                dbWord.wordDao().insert(new Word("母 ", "はは", "엄마", 0, 0));
                dbWord.wordDao().insert(new Word("赤い", "あかい", "빨갛다", 0, 0));
                dbWord.wordDao().insert(new Word("万 ", "まん", "1만", 0, 0));
                dbWord.wordDao().insert(new Word("コーヒー ", "-", "커피", 0, 0));
                dbWord.wordDao().insert(new Word("ノート ", "-", "노트", 0, 0));
                dbWord.wordDao().insert(new Word("新しい ", "あたらしい", "새롭다", 0, 0));
                dbWord.wordDao().insert(new Word("かばん ", "-", "가방", 0, 0));
                dbWord.wordDao().insert(new Word("海", "うみ", "바다", 0, 0));
                dbWord.wordDao().insert(new Word("先生 ", "せんせ", "선생님", 0, 0));
                dbWord.wordDao().insert(new Word("中学校 ", "ちゅうがっこう", "중학교", 0, 0));
                dbWord.wordDao().insert(new Word("遅い ", "おそい", "늦다", 0, 0));
                dbWord.wordDao().insert(new Word("言う ", "いう", "말하다", 0, 0));
                dbWord.wordDao().insert(new Word("北", "きた", "북", 0, 0));
                dbWord.wordDao().insert(new Word("春 ", "はる", "봄", 0, 0));
                dbWord.wordDao().insert(new Word("寒い ", "さむい", "춥다", 0, 0));
                dbWord.wordDao().insert(new Word("速い ", "はやい", "빠르다", 0, 0));
                dbWord.wordDao().insert(new Word("山 ", "やま", "산", 0, 0));
                dbWord.wordDao().insert(new Word("多い ", "おおい", "많다", 0, 0));
                dbWord.wordDao().insert(new Word("入る ", "はいる", "들어가다", 0, 0));
                dbWord.wordDao().insert(new Word("水 ", "みず", "물", 0, 0));
                dbWord.wordDao().insert(new Word("今年 ", "ことし", "올해", 0, 0));
                dbWord.wordDao().insert(new Word("上 ", "うえ", "위 (방향)", 0, 0));
                dbWord.wordDao().insert(new Word("靴下 ", "くつした", "양말", 0, 0));
                dbWord.wordDao().insert(new Word("レストラン ", "-", "레스토랑_식당", 0, 0));
                dbWord.wordDao().insert(new Word("雪 ", "ゆき", "눈", 0, 0));
                dbWord.wordDao().insert(new Word("公園 ", "こうえん", "공원", 0, 0));
                dbWord.wordDao().insert(new Word("大変 ", "たいへん", "굉장히_몹시", 0, 0));
                dbWord.wordDao().insert(new Word("セーター ", "-", "스웨터", 0, 0));
                dbWord.wordDao().insert(new Word("父 ", "ちち", "아빠", 0, 0));
                dbWord.wordDao().insert(new Word("買い物 ", "かいもの", "장보기", 0, 0));
                dbWord.wordDao().insert(new Word("お金 ", "おかね", "돈", 0, 0));
                dbWord.wordDao().insert(new Word("ナイフ ", "-", "칼", 0, 0));
                dbWord.wordDao().insert(new Word("短い ", "みじかい", "짧다", 0, 0));
                dbWord.wordDao().insert(new Word("外 ", "そと", "밖_바깥", 0, 0));
                dbWord.wordDao().insert(new Word("眼鏡", "めがね", "안경", 0, 0));
                dbWord.wordDao().insert(new Word("国 ", "くに", "나라", 0, 0));
                dbWord.wordDao().insert(new Word("知る ", "しる", "알다", 0, 0));
                dbWord.wordDao().insert(new Word("安い ", "やすい", "싸다", 0, 0));
                dbWord.wordDao().insert(new Word("青い ", "あおい", "파랗다", 0, 0));
                dbWord.wordDao().insert(new Word("教える", "おしえる", "가르치다", 0, 0));
                dbWord.wordDao().insert(new Word("木", "き", "나무", 0, 0));
                dbWord.wordDao().insert(new Word("痛い", "いたい", "아프다", 0, 0));
                dbWord.wordDao().insert(new Word("電車 ", "でんしゃ", "전차(전철)", 0, 0));
                dbWord.wordDao().insert(new Word("重い ", "おもい", "무겁다", 0, 0));
                dbWord.wordDao().insert(new Word("行く ", "いく", "가다", 0, 0));
                dbWord.wordDao().insert(new Word("子供", "こども", "아이", 0, 0));
                dbWord.wordDao().insert(new Word("切符 ", "きっぷ", "표", 0, 0));
                dbWord.wordDao().insert(new Word("私 ", "わたし", "나_저", 0, 0));
                dbWord.wordDao().insert(new Word("散歩", "さんぽ", "산책", 0, 0));
                dbWord.wordDao().insert(new Word("雨 ", "あめ", "비", 0, 0));
                dbWord.wordDao().insert(new Word("椅子", "いす", "의자", 0, 0));
                dbWord.wordDao().insert(new Word("食堂", "しょくどう", "식당", 0, 0));
                dbWord.wordDao().insert(new Word("軽い ", "かるい", "가볍다", 0, 0));
                dbWord.wordDao().insert(new Word("果物", "くだもの", "과일", 0, 0));
                dbWord.wordDao().insert(new Word("忙しい ", "いそがしい", "바쁘다", 0, 0));
                dbWord.wordDao().insert(new Word("出る ", "でる", "나가다", 0, 0));
                dbWord.wordDao().insert(new Word("車 ", "くるま", "차", 0, 0));
                dbWord.wordDao().insert(new Word("小学生 ", "しょうがくせい", "초등학생", 0, 0));
                dbWord.wordDao().insert(new Word("近い ", "ちかい", "가깝다", 0, 0));
                dbWord.wordDao().insert(new Word("足 ", "あし", "발", 0, 0));
                dbWord.wordDao().insert(new Word("朝", "あさ", "아침", 0, 0));
                dbWord.wordDao().insert(new Word("ネクタイ ", "-", "넥타이", 0, 0));
                dbWord.wordDao().insert(new Word("花 ", "はな", "꽃", 0, 0));
                dbWord.wordDao().insert(new Word("結婚 ", "けっこん", "결혼", 0, 0));
                dbWord.wordDao().insert(new Word("顔 ", "かお", "얼굴", 0, 0));
                dbWord.wordDao().insert(new Word("東 ", "ひがし", "동", 0, 0));
                dbWord.wordDao().insert(new Word("天気 ", "てんき", "날씨", 0, 0));
                dbWord.wordDao().insert(new Word(" 2位", "にい", "2위", 0, 0));
                dbWord.wordDao().insert(new Word("上手 ", "じょうず", "능숙함", 0, 0));
                dbWord.wordDao().insert(new Word("冬 ", "ふゆ", "겨울", 0, 0));
                dbWord.wordDao().insert(new Word("遠い ", "とおい", "멀다", 0, 0));

                dbBook.bookDao().insert(new Book("1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20",0));
                dbBook.bookDao().insert(new Book("21-22-23-24-25-26-27-28-29-30-31-32-33-34-35-36-37-38-39-40",0));
                dbBook.bookDao().insert(new Book("41-42-43-44-45-46-47-48-49-50-51-52-53-54-55-56-57-58-59-60",0));
                dbBook.bookDao().insert(new Book("61-62-63-64-65-66-67-68-69-70-71-72-73-74-75-76-77-78-79-80",0));
                dbBook.bookDao().insert(new Book("81-82-83-84-85-86-87-88-89-81-81-92-93-94-95-96-97-98-99-100",0));
                dbBook.bookDao().insert(new Book("101-102-103-104-105-106-107-108-109-110-111-112-113-114-115-116-117-118-119-120",0));
                dbBook.bookDao().insert(new Book("121-122-123-124-125-126-127-128-129-130-131-132-133-134-135-136-137-138-139-140",0));
                dbBook.bookDao().insert(new Book("141-142-143-144-145-146-147-148-149-150-151-152-153-154-155-156-157-158-159-160",0));
                dbBook.bookDao().insert(new Book("161-162-163-164-165-166-167-168-169-170-171-172-173-174-175-176-177-178-179-180",0));
                dbBook.bookDao().insert(new Book("181-182-183-184-185-186-187-188-189-190-191-192-193-194-195-196-197-198-199-200",0));
                dbStudyList.studyListDao().insert(new StudyList("1-2-3-4-5-6-7-8-9-10"));
                dbStudyList.studyListDao().insert(new StudyList(""));

            }
        });

    }
}
