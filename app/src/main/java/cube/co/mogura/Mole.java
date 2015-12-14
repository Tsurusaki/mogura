package cube.co.mogura;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.widget.ImageView;
import android.graphics.Paint.Align;


import java.util.TimerTask;

/**
 * Created by KANNA on 15/11/02.
 */
public class Mole {

    int state; //モグラの状態０：潜っている　１：出てきてる　２：叩かれてる
    ImageView catImage;

    android.os.Handler h;

    Runnable hide;

    public Mole(ImageView imageView) {
        state = 0;
        catImage = imageView;
       // zyoushiImage.setImageResource(R.drawable.zyoushi1);
        catImage.setImageResource(R.drawable.cat1);

        h = new android.os.Handler();
        hide = new Runnable() {
            @Override
            public void run() {
                state = 0;
                catImage.setImageResource(R.drawable.cat1);
            }

        };

        //sondsクラスを初期化
        //Sound.init(context);

    }



    //スタートしたら（play)
    public void start() {
        if(state == 0) { // モグラが引っ込んでいる状態の時
            state = 1;
            //zyoushiImage.setImageResource(R.drawable.zyoushi2);
            catImage.setImageResource(R.drawable.cat2);

            h.postDelayed(hide, 1000);

        }
    }

    public int tapMole() {
        if (state == 1) { //モグラが出ている状況の時
            state = 2;
            //効果音を再生
            Sound.playSE();
            //zyoushiImage.setImageResource(R.drawable.zyoushi3);
            catImage.setImageResource(R.drawable.cat3);

            h.removeCallbacks(hide);//startのpostを消去
            h.postDelayed(hide, 1000);

            /*if (ImageView = catImage){
                return -10; //猫を叩いたら−１０
            }else {*/
            return 1;//スコア１点を返す

        }
        return 0;//スコア０点を返す

    }
}





   /* //スタート画面を作成するメソッド
    public void startScene(Canvas canvas) {
        //スコアを初期化
        score = 0;
        //背景画像を描画
        startImage = Bitmap.createScaledBitmap(
        startImage, canvas.getWidth(), canvas.getHeight(), true);
        canvas.drawBitmap(startImage, 0, 0, null);

        //アンチエイリアスを有効にする
        titlePaint.setAntiAlias(true);
        //タイトルの描画色
        titlePaint.setColor(Color.WHITE);
        //タイトルのテキストサイズ
        titlePaint.setTextSize(172);
        //タイトルのテキスト配置
        titlePaint.setTextAlign(Paint.Align.CENTER);
        //タイトルのテキストを描画
        canvas.drawText("ねこ集め", canvasCX, canvasCY - 400, titlePaint);

        //ハイスコアを描画
        //titlePaint.setColor(Color.YELLOW);
        //titlePaint.setTextSize(128);
        //canvas.drawText("HighScore:" +PlayLog.bestScore(),
        //canvasCX, canvasCY +400, titlePaint);

        //スタートボタンを描画
        canvas.drawBitmap(startButton,
                canvasCX - startButton.getWidth() / 2,
                canvasCY - startButton.getHeight(), null);

    }




    public void playScene(Canvas canvas){

        //残り時間を取得
        remaindTime =
                TIME - (System.currentTimeMillis() - gameStarted) / 1000;
        //残り時間が０より少なくなったら
        if (remaindTime < 0) {
            //BGMの停止
            //sSounds.stopBGM();

            //スコアのテェックと保存
            //PlayLog.logScore(score);
            //ゲームの状態をGame_Overに設定
            gameState = GAME_OVER;
            //リターン
            return;

        }

        //ゲームの終了画面を作成するメソッド
        public void overScene(Canvas canvas){
            //リトライボタンを描画
            canvas.drawBitmap(reteryButton,
                    canvasCX - reteryButton.getWidth() / 2,
                    canvasCY - reteryButton.getHeight(), null);
            //アンチエイリアスを有効にする
            titlePaint.setAntiAlias(true);
            //描画色
            titlePaint.setColor(Color.RED);
            //テキストサイズ
            titlePaint.setTextSize(172);
            //テキスト配置
            titlePaint.setTextAlign(Paint.Align.CENTER);
            //テキストを描画
            canvas.drawText("Time up!", canvasCX, canvasCY - 400, titlePaint);

            titlePaint.setColor(Color.BLUE);
            titlePaint.setTextSize(128);
            titlePaint.setTextAlign(Paint.Align.CENTER);
            //スコアを描画
            canvas.drawText("Your score:" + score, canvasCX,
                    canvasCY + 400, titlePaint);


        }




//　random を使うのかもしれない
// おみくじのアイディアが活用できそう

/*public class Cat {

    int state; //猫の状態０：潜っている　１：出てきてる　２：叩かれてる
    ImageView catImage;

    android.os.Handler h;

    Runnable hide;

    public Cat(ImageView imageView) {
        state = 0;
        catImage = imageView;
        catImage.setImageResource(R.drawable.cat1);

        h = new android.os.Handler();
        hide = new Runnable() {
            @Override
            public void run() {
                state = 0;
                catImage.setImageResource(R.drawable.cat1);
            }

        };
    }

    public void start() {
        if(state == 0) { // モグラが引っ込んでいる状態の時
            state = 1;
            catImage.setImageResource(R.drawable.cat2);

            h.postDelayed(hide, 1000        }
    }

    public int tapMole() {
        if (state == 1) { //モグラが出ている状況の時
            state = 2;
            catImage.setImageResource(R.drawable.cat3);

            h.removeCallbacks(hide);//startのpostを消去
            h.postDelayed(hide, 1000);
            return -10;//スコア10点マイナス
        }
        return 0;//スコア０点を返す


    }*/
