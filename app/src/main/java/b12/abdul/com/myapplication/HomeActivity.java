package b12.abdul.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import b12.abdul.com.myapplication.database.DbOperations;

public class HomeActivity extends AppCompatActivity {
DbOperations dbOperations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       String name = getIntent().getStringExtra("mykey");
        TextView textView = findViewById(R.id.textViewdata);
        textView.setText(name);
        dbOperations = new DbOperations(this);
        dbOperations.openDb();

    }

    public void clickListener(View view) {
        switch (view.getId()){
            case R.id.buttonSet:
                dbOperations.createRow("jabbar","anjaneya");
                break;
            case R.id.buttonget:
                TextView textView = findViewById(R.id.textViewdata);
                textView.setText(dbOperations.readRow());
                break;
        }
    }
}
