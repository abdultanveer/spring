package b12.abdul.com.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnFocusChangeListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText nameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"iam in oncreate");
         nameEditText = findViewById(R.id.editTextName);
        nameEditText.setOnFocusChangeListener(this);
        Spinner  spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,countries);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"starting",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"pausing",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"resuming",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"stopping",Toast.LENGTH_SHORT).show();

    }

    public static String[] countries = {"india", "usa","uk"};

    public void clickHandler(View view) {
        switch (view.getId()){
                case R.id.buttonLogin:

                    String name = nameEditText.getText().toString();

                    Intent hIntent = new Intent(MainActivity.this,HomeActivity.class);
                    hIntent.putExtra("mykey",name);
                    startActivity(hIntent);

                    // Toast.makeText(this,"spring people", Toast.LENGTH_SHORT).show();
                    /*TextView mTextView = findViewById(R.id.textViewResult);
                    mTextView.setText(name);*/
                    break;
                case R.id.button2:
                    Intent dIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1234568765"));
                    startActivity(dIntent);
                    break;
                case R.id.buttonsafr:
                    Intent thirdIntent = new Intent(MainActivity.this,ThirdActivity.class);
                    startActivityForResult(thirdIntent,003);
                    break;
        }

    }

    @Override
    public void onFocusChange(View view, boolean isFocussed) {
        if(isFocussed){
            Toast.makeText(this,"Focussed",Toast.LENGTH_SHORT).show();
        }
        else if(!isFocussed){
            Toast.makeText(this,"lost focus",Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String item = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(this,item,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String three = data.getStringExtra("thirdkey");
        TextView mTextView = findViewById(R.id.textViewResult);
        mTextView.setText(three);

    }
}
