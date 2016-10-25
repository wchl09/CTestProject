package com.example.wangchunlei.ctestproject;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("test-lib");
    }

    TextView mGetStringTV, mAddArrayTV;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        mGetStringTV = (TextView) findViewById(R.id.sample_text1);
        mAddArrayTV = (TextView) findViewById(R.id.sample_text2);
        mGetStringTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetStringTV.setText(getStringFromC());
            }
        });
        mAddArrayTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddArrayTV.setText(addIntArray(new int[]{1, 2, 2, 3}));
            }
        });
        editText = (EditText) findViewById(R.id.editText);
        findViewById(R.id.sumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable editableText = editText.getEditableText();
                try {
                    float radius = Float.valueOf(editableText.toString());
                    if (radius < 0) {
                        Toast.makeText(MainActivity.this, "请输入大于0的半径", Toast.LENGTH_SHORT).show();
                    } else {
                        double circularvVolume = getCircularvVolume(radius);
                        Toast.makeText(MainActivity.this, "得出的半径是" + circularvVolume, Toast.LENGTH_SHORT).show();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "请输入数字", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native String getStringFromC();

    public native String addIntArray(int[] ints);

    public native double getCircularvVolume(float radius);
}
