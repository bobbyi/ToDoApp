package com.example.todoapp;

import android.app.Activity;
import android.text.ClipboardManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

class ListViewItem {
    private String text;
    
    public ListViewItem(String text) {
        this.text = text;
    }
    
    @Override 
    public String toString() {
        return text;
    }
}

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements OnKeyListener
{
    private EditText editText;
    private ListView listView;
    ArrayAdapter<ListViewItem> listViewAdapter;
    private ClipboardManager clipboardManager;
    private int selectedItemIdx;
    private TextView selectedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        setupEditText();
        setupListView();
    }

    private void setupListView() {
        listView = (ListView) findViewById(R.id.items);
        registerForContextMenu(listView);
        listViewAdapter = new ArrayAdapter<ListViewItem>(this,
                android.R.layout.simple_list_item_1);
        listView.setAdapter(listViewAdapter);
    }

    private void setupEditText() {
        editText = (EditText) findViewById(R.id.edit_text);
        editText.setOnKeyListener(this);
    }
 
    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (enterWasPressed(keyCode, event)) {
            addItem();
            return true;
        }
        return false;
    }

    private boolean enterWasPressed(int keyCode, KeyEvent event) {
        return (event.getAction() == KeyEvent.ACTION_DOWN)
                && (keyCode == KeyEvent.KEYCODE_ENTER);
    }

    public void addItem(View view) {
        addItem();
    }

    public void addItem() {
        String text = editText.getText().toString();
        text = text.replace('\n', ' ').trim();
        if (!text.equals("")) {
            listViewAdapter.add(new ListViewItem(text));
        }
        editText.setText("");
    }

    public void copyText(View view) {
        clipboardManager.setText("Text\nwith\nnewlines");
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        selectedView = (TextView) info.targetView;
        selectedItemIdx = info.position;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu_copy_text:
                String text = selectedView.getText().toString();
                clipboardManager.setText(text);
                return true;
            case R.id.context_menu_delete:
                listViewAdapter.remove(listViewAdapter.getItem(selectedItemIdx));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
