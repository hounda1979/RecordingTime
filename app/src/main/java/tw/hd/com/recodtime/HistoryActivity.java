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
//        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       Cursor cursor =  recordHelper.getReadableDatabase().query("recorder",null,null,null,
                null,null,null);
       HistoryAdapter historyAdapter = new HistoryAdapter(cursor);
        recyclerView.setAdapter(historyAdapter);
        Toast.makeText(this,"點擊ID號碼,可看詳細資料",Toast.LENGTH_LONG).show();




    }
    public void Button_OK(View view){
        finish();
    }
    public  class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
        Cursor cursor;
        public HistoryAdapter(Cursor cursor) {
            this.cursor = cursor;
        }

        @NonNull
        @Override
        public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.historyitem,viewGroup,false);
            return new HistoryHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int i) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            final String hdd = cursor.getString(cursor.getColumnIndex("rhdd"));
            final int steram = cursor.getInt(cursor.getColumnIndex("rstream"));
            final String day = cursor.getString(cursor.getColumnIndex("rday"));
            final int ch = cursor.getInt(cursor.getColumnIndex("rch"));
            historyHolder.t_id.setText(id+"");
            historyHolder.t_ch.setText(ch+"");
//            historyHolder.t_day.setText(day);
            historyHolder.t_stream.setText(steram+"");
//            historyHolder.t_hdd.setText(hdd);
            historyHolder.t_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("ch",ch);
                    bundle.putString("hdd",hdd);
                    bundle.putInt("stream",steram);
                    bundle.putString("day",day);
                    Intent intent = new Intent(HistoryActivity.this,ResultActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();

                }
            });


        }

        @Override
        public int getItemCount() {
            return cursor.getCount();
        }

        public class HistoryHolder extends RecyclerView.ViewHolder{
            TextView t_id;
            TextView t_hdd;
            TextView t_stream;
            TextView t_day;
            TextView t_ch;

            public HistoryHolder(@NonNull View itemView) {
                super(itemView);
                t_id = itemView.findViewById(R.id.ic_text_his);
                t_stream = itemView.findViewById(R.id.stream_text_his);
//                t_hdd = itemView.findViewById(R.id.hdd_text_his);
//                t_day = itemView.findViewById(R.id.day_text_his);
                t_ch = itemView.findViewById(R.id.ch_text_his);

            }
        }
    }


}
