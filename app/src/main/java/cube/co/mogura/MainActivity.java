package cube.co.mogura;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import android.content.Context;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import android.graphics.Paint.Align;









public class MainActivity extends Activity {

    TextView scoreText;
    TextView timeText;

    int[] imageResources ={ //イメージビューのリソースIDの配列
            R.id.imageView,R.id.imageView2,R.id.imageView3,
            R.id.imageView4,R.id.imageView5,R.id.imageView6,
            R.id.imageView7,R.id.imageView8,R.id.imageView9,
            R.id.imageView10,R.id.imageView11,R.id.imageView12
    };

    Mole[] cat;//モグラの配列


    int time;//時間の変数
    int score;//スコアの変数

    Timer timer;//タイマー
    TimerTask timerTask;//タイマーで処理する内容
    Handler h;//ハンドラー（タイマーからUIスレッドへの通信用）
    Random random =new Random();//ランダムな数字を発生させる


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //レイアウトのテキストビュとの関連づけ
        scoreText =(TextView)findViewById(R.id.scoreText);
        timeText =(TextView)findViewById(R.id.timeText);

        //モグラの数だけ配列を作る
        cat = new Mole[12];
        for(int i = 0; i < 12; i ++){
            //レイアウトのイメージビューを一つずつ取り出し
            ImageView imageView =(ImageView)findViewById(imageResources[i]);
            //イメージビューを使ってi番目のモグラのインスタンスを作成
            cat[i]=new Mole(imageView);

        }

        //猫の数だけ配列を作る
        /*cat = new Cat[3];
        for(int i = 0; i < 3; i ++){
        //レイアウトのイメージビューを一つずつ取り出し
            ImageView imageView =(ImageView)findViewById(imageResources[i]);
            //イメージビューを使ってi番目のモグラのインスタンスを作成
            cat[i]=new  Mole(imageView);

        }*/


        //Handlerのインスタンスを作成
        h = new Handler();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   //スタートボタンを押したとき
   public void start(View v){
        time = 30;//タイマーの初期化
        score = 0;//スコアの初期化
        timeText.setText(String.valueOf(time));
        scoreText.setText(String.valueOf(score));

        //BGMの再生開始
        Sound.playBGM();

        timer = new Timer();
        timerTask = new TimerTask(){
            @Override
            public void run(){
                //ゲームが始まったら
                //TODO一定時間ごとに行う処理を書く
                h.post(new Runnable(){
                    @Override
                    public void run(){
                        //0<=r<12のランダムな数字を
                        int r = random.nextInt(12);
                        //r番目の猫が飛び出す
                        cat[r].start();


                        time = time-1;//時間を１減らす
                        timeText.setText(String.valueOf(time));
                        //時間が０以下なら
                        if(time <= 0){
                            timer.cancel();//タイマーを終了する
                            //BGMの停止
                            Sound.stopBGM();
                            Sound.playSE();
                        }

                    }
                });
            }
        };
        //timer.schedule(timerTask,0, 1000);

    }

    public void tapMole(View v){
        //叩いたImageViewのタグを取得
        String tag_str =(String)v.getTag();
        //tag_strをint型に変換
        int tag_int = Integer.valueOf(tag_str);

        //対応したモグラを叩く
        score += cat[tag_int].tapMole();

        scoreText.setText(String.valueOf(score));


    }



    /*public void onClick(View view){
        switch (view.getId()){
            case R.id.start:
                Intent intent = new Intent(this, FirstActivity.class);
                startActivity(intent);
                break;
        }
    }*/
}
