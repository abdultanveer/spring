package b12.abdul.com.myapplication;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import b12.abdul.com.myapplication.database.DbOperations;
import b12.abdul.com.myapplication.database.FeedReaderContract;
import b12.abdul.com.myapplication.database.FeedReaderContract.FeedEntry;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
DbOperations dbOperations;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getLoaderManager().initLoader(007,null,this);

      /* String name = getIntent().getStringExtra("mykey");
        TextView textView = findViewById(R.id.textViewdata);
        textView.setText(name);
       dbOperations = new DbOperations(this);
        dbOperations.openDb();*/
       // getDbData();
        /*Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, null,null,null,null);*/
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                null,
                new String[]{"address","body"},
                new int[]{android.R.id.text1,android.R.id.text2});
        ListView listView = findViewById(R.id.listviewdb);
        listView.setAdapter(adapter);
    }

    private void getDbData() {
        Cursor cursor = dbOperations.readRows();
        adapter = new SimpleCursorAdapter(this,
               android.R.layout.simple_list_item_2,
               cursor,
               new String[]{FeedEntry.COLUMN_NAME_TITLE, FeedEntry.COLUMN_NAME_SUBTITLE},
               new int[]{android.R.id.text1,android.R.id.text2});
        ListView listView = findViewById(R.id.listviewdb);
        listView.setAdapter(adapter);
    }

    public void clickListener(View view) {
        switch (view.getId()){
            case R.id.buttonSet:
                dbOperations.createRow("abdul","ansari");
                adapter.notifyDataSetChanged();
                break;
            case R.id.buttonget:
                TextView textView = findViewById(R.id.textViewdata);
                textView.setText(dbOperations.readRow());
                break;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri uriSms = Uri.parse("content://sms/inbox");

        return new CursorLoader(this,uriSms,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
