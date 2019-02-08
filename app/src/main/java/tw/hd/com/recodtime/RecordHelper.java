package tw.hd.com.recodtime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordHelper extends SQLiteOpenHelper {
    public RecordHelper(Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public RecordHelper(Context context){
        super(context,"recoddate",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  recorder(_id INTEGER PRIMARY KEY NOT NULL ," +
                "rch INTEGER NOT NULL ," +
                "rstream INTEGER NOT NULL," +
                "rhdd VARCHAR NOT NULL," +
                "rday  VARCHAR NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
