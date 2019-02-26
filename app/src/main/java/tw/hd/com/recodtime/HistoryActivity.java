package tw.hd.com.recodtime;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityhistory);
        RecordHelper recordHelper = new RecordHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclehis);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       final Cursor cursor =  recordHelper.getReadableDatabase().query("recorder",null,null,null,
                null,null,null);
       HistoryAdapter historyAdapter = new HistoryAdapter(cursor);
       historyAdapter.setMonItemClickListener(new HistoryAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(View view, int position) {
               Toast.makeText(HistoryActivity.this,"長按可以看詳細資料",Toast.LENGTH_LONG).show();
           }
       });
       historyAdapter.setMonItemLongClickListener(new HistoryAdapter.OnItemLongClickListener() {
           @Override
           public void onItemLongClick(View view, int position) {
               cursor.moveToPosition(position);
               Bundle bundle = new Bundle();
               Intent intent = new Intent(HistoryActivity.this,ResultActivity.class);
               bundle.putString("day",cursor.getString(cursor.getColumnIndex("rday")));
               bundle.putString("hdd",cursor.getString(cursor.getColumnIndex("rhdd")));
               bundle.putInt("stream",cursor.getInt(cursor.getColumnIndex("rstream")));
               bundle.putInt("ch",cursor.getInt(cursor.getColumnIndex("rch")));
               intent.putExtras(bundle);
               startActivity(intent);
               finish();
           }
       });
        recyclerView.setAdapter(historyAdapter);





    }
    public void Button_OK(View view){
        finish();
    }



}
