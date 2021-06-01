package com.neeraj.texteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    protected EditText textbox;
    protected int CREATE_FILE = 1;
    protected int READ_FILE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textbox = (EditText)findViewById(R.id.editTextTextMultiLine);
    }

    private String readTextFromUri(Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream =
                     getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        return stringBuilder.toString();
    }

    private void alterDocument(Uri uri) {
        try {
            ParcelFileDescriptor pfd = this.getContentResolver().
                    openFileDescriptor(uri, "w");
            FileOutputStream fileOutputStream =
                    new FileOutputStream(pfd.getFileDescriptor());
            fileOutputStream.write(String.valueOf(textbox.getText()).getBytes());
            fileOutputStream.close();
            pfd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == READ_FILE
                && resultCode == Activity.RESULT_OK){
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                String file_content="null";
                try {
                    file_content = readTextFromUri(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textbox.setText(file_content);
            }
        }
        else if(requestCode == CREATE_FILE && resultCode == Activity.RESULT_OK){
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                alterDocument(uri);
            }
        }
    }

    protected void createFile(Uri pickerInitialUri){

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "untitled.txt");

        startActivityForResult(intent, CREATE_FILE);
    }

    protected void openFile(Uri pickerInitialUri){

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");

        startActivityForResult(intent, READ_FILE);
    }

    public void saveFile(View view){
        createFile(Uri.fromFile(Environment.getDataDirectory()));
    }

    public void loadFile(View view){
        openFile(Uri.fromFile(Environment.getDataDirectory()));
    }
}