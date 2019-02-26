package tw.hd.com.recodtime;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    private OnItemClickListener monItemClickListener;
    private OnItemLongClickListener monItemLongClickListener;

    public void setMonItemClickListener(OnItemClickListener monItemClickListener) {
        this.monItemClickListener = monItemClickListener;
    }

    public void setMonItemLongClickListener(OnItemLongClickListener monItemLongClickListener) {
        this.monItemLongClickListener = monItemLongClickListener;
    }

    Cursor cursor;

    public HistoryAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.historyitem,viewGroup,false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryHolder historyHolder, int i) {
        cursor.moveToPosition(i);
        int id = cursor.getInt(cursor.getColumnIndex("_id"));
        int ch = cursor.getInt(cursor.getColumnIndex("rch"));
        int stream = cursor.getInt(cursor.getColumnIndex("rstream"));
        String hdd = cursor.getString(cursor.getColumnIndex("rhdd"));
        String day = cursor.getString(cursor.getColumnIndex("rday"));
        historyHolder.id.setText(id+"");
        historyHolder.stream.setText(stream+"");
        historyHolder.ch.setText(ch+"");
        if(monItemClickListener != null){
            historyHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = historyHolder.getLayoutPosition();
                    monItemClickListener.onItemClick(historyHolder.itemView,position);
                }
            });
        }
        if(monItemLongClickListener != null){
            historyHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = historyHolder.getLayoutPosition();
                    monItemLongClickListener.onItemLongClick(historyHolder.itemView,position);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class HistoryHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView stream;
        TextView ch;
        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.ic_text_his);
            stream = itemView.findViewById(R.id.stream_text_his);
            ch = itemView.findViewById(R.id.ch_text_his);
        }
    }
}
