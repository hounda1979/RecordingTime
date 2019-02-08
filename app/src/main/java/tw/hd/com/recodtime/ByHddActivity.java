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
import android.widget.Toast;

public class ByHddActivity extends AppCompatActivity {

    private static final String TAG = ByHddActivity.class.getSimpleName();
    private EditText edit_ch;
    private int[] hddarray ={0,0,0};
    String temp,temp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_hdd);
        setView();


    }

    private void setView() {
        edit_ch = findViewById(R.id.edit_hdd_ch);
        Spinner spinner_hdd = findViewById(R.id.spinner_hdd);
        final ArrayAdapter<CharSequence> adapterHdd = ArrayAdapter.createFromResource(this,
                R.array.Hdds, android.R.layout.simple_spinner_item);
        adapterHdd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hdd.setAdapter(adapterHdd);
        spinner_hdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                temp = adapterHdd.getItem(position).toString();
//                Toast.makeText(ByHddActivity.this,temp,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Spinner spinner_stream = findViewById(R.id.spinner_hdd_stream);
        final ArrayAdapter<CharSequence> adapterSteam = ArrayAdapter.createFromResource(this,
                R.array.MainStream,android.R.layout.simple_spinner_item);
        adapterSteam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_stream.setAdapter(adapterSteam);
        spinner_stream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp1 = adapterSteam.getItem(position).toString();

//                Toast.makeText(ByHddActivity.this,adapterSteam.getItem(position),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void hdd_ok(View view){
        String temp123 = edit_ch.getText().toString();

        hddarray[2] = Integer.parseInt(temp123);
        switch (temp){
            case "1TB":
                hddarray[0] = 1;
                break;
            case "2TB":
                hddarray[0] = 2;
                break;
            case "3TB":
                hddarray[0] = 3;
                break;
            case "4TB":
                hddarray[0] = 4;
                break;
            case "6TB":
                hddarray[0] = 6;
                break;
            case "8TB":
                hddarray[0] = 8;
                break;
            case "10TB":
                hddarray[0] = 10;
                break;

        }
        hddarray[1] = Integer.parseInt(temp1);
        RecordHdd recordHdd = new RecordHdd(hddarray);
        String res = recordHdd.calculation();
        Intent intent = new Intent(ByHddActivity.this,ResultActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("hdd",temp);
        bundle.putInt("stream",hddarray[1]);
        bundle.putInt("ch",hddarray[2]);
        bundle.putString("day",res);
        intent.putExtras(bundle);

        startActivity(intent);
        RecordHelper recordHelper = new RecordHelper(this);
        ContentValues contentValues = new ContentValues();
        contentValues.put("rch",hddarray[2]);
        contentValues.put("rstream",hddarray[1]);
        contentValues.put("rhdd",temp);
        contentValues.put("rday",res);
        long id = recordHelper.getWritableDatabase()
                .insert("recorder",null,contentValues);
        if(id > -1){
            Log.d(TAG, "recordhelper add  sussice: " + id );
        }else{
            Log.d(TAG, "recordhelper add faild : ");
        }
        finish();
//        Toast.makeText(this,"錄影CH數 :"+hddarray[2]+"\t"+"選擇HDD大小:"+temp+"\t"+"可錄影天數:"+res,Toast.LENGTH_LONG).show();
    }
}
