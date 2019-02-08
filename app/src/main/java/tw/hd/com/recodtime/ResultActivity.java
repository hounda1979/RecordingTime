package tw.hd.com.recodtime;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textdream;
    String day ;
    String hdd;
    int ch;
    int stream;
    private ImageView toLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textdream = findViewById(R.id.text_stream_res);
        TextView texthdd = findViewById(R.id.text_hdd_res);
        TextView textday = findViewById(R.id.text_day_res);
        TextView textch = findViewById(R.id.text_ch_res);
        toLine = findViewById(R.id.toLine);
        Button button_ok = findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();

        day = intent.getExtras().getString("day","0");
        if(day.equals("0")){
            day = intent.getExtras().getInt("day",0)+"";
        }
        hdd = intent.getExtras().getString("hdd","0");
        if(hdd.equals("0")){
            hdd = String.valueOf(intent.getExtras().getInt("hdd",0));
        }
        stream = intent.getExtras().getInt("stream",0);
        ch = intent.getExtras().getInt("ch",0);

        textday.setText(day);
        textdream.setText(stream+"");
        texthdd.setText(hdd);
        textch.setText(ch+"");

        toLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName("jp.naver.line.android"
                        , "jp.naver.line.android.activity.selectchat.SelectChatActivity");
                Intent shareToLine = new Intent();
                shareToLine.setAction(Intent.ACTION_SEND);
                shareToLine.setType("text/plain");
                shareToLine.putExtra(Intent.EXTRA_SUBJECT,"DVR/NVR 錄影計算結果");
                shareToLine.putExtra(Intent.EXTRA_TEXT,"CH數 : "+ch+" 碼流 : "+stream+" Hdd大小 : "+ hdd+" 數影天數 : "+day);
                shareToLine.setComponent(componentName);
                startActivity(Intent.createChooser(shareToLine,"Line"));

            }
        });

    }
}
