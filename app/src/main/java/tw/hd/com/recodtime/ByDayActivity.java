package tw.hd.com.recodtime;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ByDayActivity extends AppCompatActivity {

    private static final String TAG = ByDayActivity.class.getSimpleName();
    private int stream;
    private SeekBar editday;
    private SeekBar editch;
    private int day;
    private TextView textView;
    private int ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_day);
        editday = findViewById(R.id.seekBar_day_byday);
        editch = findViewById(R.id.seekBar_ch_day);
        Spinner spinner = findViewById(R.id.spinner_stream_s);
        textView = findViewById(R.id.textViewseek);
        final TextView chseektext = findViewById(R.id.seek_text_ch_day);
        editch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ch = progress;
                setboboseekbar(seekBar,progress,chseektext);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        editday.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar1, int progress, boolean fromUser) {
                day = progress; //取得要計算的日期
                setboboseekbar(seekBar1, progress,textView);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this,R.array.MainStream,android.R.layout.simple_spinner_item);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stream = Integer.parseInt(spAdapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setboboseekbar(SeekBar seekBar, int progress,TextView textView) {
        //設定氣泡文字
        textView.setText(String.valueOf(progress));
        //取得氣泡文字寛
        float textWidth = textView.getWidth();
        //取得seekbar最左邊X位置
        float seekleft = seekBar.getLeft();
        //進度條刻度
        float max = Math.abs(seekBar.getMax());
        //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
        float thumb = dip2px(ByDayActivity.this,15);
        //移動一個單位 ,TEXT應變化的距離 = (SEEKBAR 的寛度 - 兩頭空的空間)/總progress的長度
        float average = ((float)seekBar.getWidth()-2*thumb)/max;
        //textview 應該所在的位置 = seekbar 最左端 + seekbar 左端空的空間 + 當前progress應加的長度 - textview 寛度的一半(保持居中的作用)
        float pox = seekleft - textWidth/2 + thumb + average * (float)progress;
        textView.setX(pox);
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale + 0.5f);
    }

    public void ok_button(View view){
//        day = Integer.parseInt(editday.getText().toString());
//        ch = Integer.parseInt(editch.getText().toString());
        if(day == 0){
            day = day+1;
        }
        if(ch == 0){
            ch = ch+1;
        }
        RecordDay recordDay = new RecordDay(ch,stream, day);
        String res = recordDay.calculation();
        Intent intent = new Intent(ByDayActivity.this,ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ch", ch);
        bundle.putInt("stream",stream);
        bundle.putString("hdd",res);
        bundle.putInt("day", day);
        intent.putExtras(bundle);
        startActivity(intent);
        ContentValues contentValues = new ContentValues();
        contentValues.put("rch", ch);
        contentValues.put("rstream",stream);
        contentValues.put("rhdd",res);
        contentValues.put("rday",String.valueOf(day));
        RecordHelper recordHelper = new RecordHelper(this);
        long id = recordHelper.getWritableDatabase()
                .insert("recorder",null,contentValues);
        if(id > -1){
            Log.d(TAG, "recordhelper add  sussice: " + id );
        }else{
            Log.d(TAG, "recordhelper add faild : ");
        }
        finish();

    }
}
