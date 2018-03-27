package b12.abdul.com.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import b12.abdul.com.myapplication.database.FeedReaderContract.FeedEntry;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ansari on 3/27/2018.
 */

public class DbOperations {

    SQLiteDatabase database;
    FeedReaderDbHelper dbHelper;
    public DbOperations(Context context){
        dbHelper = new FeedReaderDbHelper(context);
    }

    public void openDb(){
        database = dbHelper.getWritableDatabase();
    }
    public void closeDb(){}

    public void createRow(String mTitle, String mSubtitle){
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE,mTitle);
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE,mSubtitle);

        database.insert(FeedEntry.TABLE_NAME,null,values);

    }
    public String readRow(){
      Cursor cursor = database.query(FeedEntry.TABLE_NAME,
                null,null,null,null,null,null);
      cursor.moveToLast();
      int titleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE);
      String title = cursor.getString(titleIndex);
        int subtitleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE);
        String subtitle = cursor.getString(subtitleIndex);
        return  title+"\t"+subtitle;

    }

    public Cursor readRows(){
        return database.rawQuery("select * from entry",null);
    }
    public void update(){}
    public void delete(){}
}
