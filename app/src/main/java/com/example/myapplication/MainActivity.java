package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.myapplication.fragments.ContentFragment;
import com.example.myapplication.repositories.FileRepository;
import com.example.myapplication.services.ReadFileService;
import com.example.myapplication.services.WriteFileService;

public class MainActivity extends AppCompatActivity {

    private FileRepository fileRepository;
    private ReadFileService readFileService;
    private WriteFileService writeFileService;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        fileRepository = new FileRepository("com/example/myapplication/storage");
        readFileService = new ReadFileService(fileRepository);
        writeFileService = new WriteFileService(fileRepository);

        if (savedInstanceState == null) {
            ContentFragment contentFragment = new ContentFragment(readFileService, writeFileService);
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
}