package tw.hd.com.recodtime;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class ByDayActivity extends AppCompatActivity {

    private static final String TAG = ByDayActivity.class.getSimpleName();
    private int stream;
    private EditText editday;
    private EditText editch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_day);
        editday = findViewById(R.id.edit_steam_day);
        editch = findViewById(R.id.edit_stream_ch);
        Spinner spinner = findViewById(R.id.spinner_stream_s);
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

    public void ok_button(View view){
        int day = Integer.parseInt(editday.getText().toString());
        int ch = Integer.parseInt(editch.getText().toString());
        RecordDay recordDay = new RecordDay(ch,stream,day);
        String res = recordDay.calculation();
        Intent intent = new Intent(ByDayActivity.this,ResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ch",ch);
        bundle.putInt("stream",stream);
        bundle.putString("hdd",res);
        bundle.putInt("day",day);
        intent.putExtras(bundle);
        startActivity(intent);
        ContentValues contentValues = new ContentValues();
        contentValues.put("rch",ch);
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
