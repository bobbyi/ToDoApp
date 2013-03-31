package com.example.todoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	editText = (EditText)findViewById(R.id.edit_message);

    	editText.setOnKeyListener(new OnKeyListener() {
    		public boolean onKey(View v, int keyCode, KeyEvent event) {
    			// If the event is a key-down event on the "enter" button
    			if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
    				addItem();
    				return true;
    			}
    			return false;
    		}
    	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void toast(String message) {
    	Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void addItem() {
    	String text = editText.getText().toString();
    	toast(text);
    	editText.setText("");
    }
 
    public void onButtonClick(View view) {
    	addItem();
    }
}
