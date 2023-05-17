package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.fragments.ContentFragment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements ContentFragment.OpenFileInputCallback {
    private final static String FILE_NAME = "content.txt";
//    private FileRepository fileRepository;
//    private ReadFileService readFileService;
//    private WriteFileService writeFileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fileRepository = new FileRepository("com/example/myapplication/storage");
//        readFileService = new ReadFileService(fileRepository);
//        writeFileService = new WriteFileService(fileRepository);

        if (savedInstanceState == null) {
            ContentFragment contentFragment = new ContentFragment();
            contentFragment.setOpenFileInputCallback(this);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, contentFragment)
                    .commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Remove the default behavior of recreating the fragments
    }

    public void saveText(View view) {
        FileOutputStream fos = null;
        try {
            EditText textBox = (EditText) findViewById(R.id.textInputEditText);
            String text = textBox.getText().toString();
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл збережено", Toast.LENGTH_SHORT).show();
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public FileInputStream openFileInputStream(String fileName) throws FileNotFoundException {
        // Invoke the openFileInput method here
        // You can put your implementation logic here
        return openFileInput(fileName);
    }

    // відкриття файлу
    public void openText(View view) {
        FileInputStream fin = null;
        TextView textView = (TextView) findViewById(R.id.fileOutput); // FIXME here should be button from new fragment
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
//            if (text.isEmpty()) {
//                return "Input is empty";
//            }
//            return text;
            textView.setText(text);
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
//        return "Error occurred.";
    }
}