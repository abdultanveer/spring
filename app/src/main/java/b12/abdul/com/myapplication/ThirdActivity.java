package b12.abdul.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    public void handleClicks(View view) {
        EditText thirdEditText = findViewById(R.id.editTextThird);
        String three = thirdEditText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("thirdkey",three);
        setResult(RESULT_OK,intent);
        finish();
    }
}
