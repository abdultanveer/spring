package b12.abdul.com.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import b12.abdul.com.myapplication.database.DbOperations;
import b12.abdul.com.myapplication.database.FeedReaderContract.FeedEntry;

public class HomeActivity extends AppCompatActivity {
DbOperations dbOperations;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       String name = getIntent().getStringExtra("mykey");
        TextView textView = findViewById(R.id.textViewdata);
        textView.setText(name);
        dbOperations = new DbOperations(this);
        dbOperations.openDb();
        Cursor cursor = dbOperations.readRows();
         adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor,
                new String[]{FeedEntry.COLUMN_NAME_TITLE,FeedEntry.COLUMN_NAME_SUBTITLE},
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
}
