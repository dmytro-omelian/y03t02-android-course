package com.example.myapplication.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {

    private String directory;

    public FileRepository(String directory) {
        this.directory = directory;
    }

    public List<String> read(String filename) throws IOException {
        List<String> records = new ArrayList<>();
        String file = String.format("%s/%s", directory, filename);

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while( (line = reader.readLine()) != null) {
            records.add(line);
        }
        reader.close();
        return records;
    }

    public void write(String filename, String record) throws IOException {
        String file = String.format("%s/%s", directory, filename);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(record);
        writer.close();
    }

}
