package com.example.todoapp;

import android.app.Activity;
import android.text.ClipboardManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
    private EditText editText;
    private EditText displayText;
    private ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.edit_message);
        displayText = (EditText)findViewById(R.id.display_message);
        clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE); 

        createEnterKeyListener();
    }

    private void createEnterKeyListener() {
        editText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (enterWasPressed(keyCode, event)) {
                    addItem();
                    return true;
                }
                return false;
            }

            private boolean enterWasPressed(int keyCode, KeyEvent event) {
                return (event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
 
    public void addItem() {
        String text = editText.getText().toString();
        text = text.replace('\n', ' ').trim();
        if (!text.equals("")) {
            showText(text);
        }
        editText.setText("");
    }
 
    private void showText(String text) {
        displayText.setText(text);
    }

    public void onButtonClick(View view) {
        addItem();
    }

    public void copyText(View view) {
        clipboardManager.setText("Text\nwith\nnewlines");
    }

    public void pasteText(View view) {
        CharSequence text = clipboardManager.getText();
        editText.setText(text);
    }
}
