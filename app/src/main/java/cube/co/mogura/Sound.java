package cube.co.mogura;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore;



/**
 * Created by KANNA on 15/10/17.
 */
public final class Sound {
    //コンテキストを格納する変数
    private static Context context;
    //mediaPlayerクラスを格納する変数
    private static MediaPlayer mediaPlayer;
    //SoundPoolクラスを格納する変数
    private static SoundPool soundPool;
    //効果音のサクンドファイルのリソースIDを格納する変数
    private static int sidSE;
    //初期化メソッド
    public static void init(final Context context){
        //コンテキストを取得
        Sound.context = context;
        //SoundPoolクラス（管理クラス）の作成
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        //サウンドファイルを取り込む
        sidSE = soundPool.load(context, R.raw.cat1a, 1);

    }

    //効果音を再生するメソッド
    public static void playSE() {
        soundPool.play(sidSE, 1.0F, 1.0F, 0, 0, 1.0F);

    }

    //BGMを再生するメソッド
    public static void playBGM(){
        initBGM(R.raw.mainbgm);

    }

    //BGMを一時停止するメソッド
    public static void pauseBGM(){
        if (mediaPlayer != null) mediaPlayer.pause();

    }

    //BGMを停止するメソッド
    public static void stopBGM(){
        if (mediaPlayer != null) mediaPlayer.stop();

    }

    //BGMの初期化と再生を開始するメソッド
    private static synchronized void initBGM(final int resourceId){
        //medoaPlayerクラスがnullでないなら
        if (mediaPlayer != null) {
            //リソースを破棄
            mediaPlayer.release();
            //mediaPlayerフィールドにnullを代入
            mediaPlayer = null;

        }

        //mediaPlayerクラスのオブジェクトを作成し、サウンドファイルを読み込む
        mediaPlayer =MediaPlayer.create(context, resourceId);
        //ループ再生を設定
        mediaPlayer.setLooping(true);
        //音量を設定
        mediaPlayer.setVolume(0.1F, 0.1F);
        //再生を開始
        mediaPlayer.start();

    }
}
