package com.example.myapplication.services;

import com.example.myapplication.repositories.FileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileService {

    private final FileRepository fileRepository;

    public ReadFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<String> readFromFile(String filename) {
        List<String> records = new ArrayList<>();
        try {
            records = this.fileRepository.read(filename);
            return records;
        } catch (IOException e) {
            System.out.printf("Couldn't read from file: %s, error message: %s", filename, e);
        }
        return records;
    }

}
