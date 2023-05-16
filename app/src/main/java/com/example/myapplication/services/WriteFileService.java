package com.example.myapplication.services;

import com.example.myapplication.repositories.FileRepository;

import java.io.IOException;

public class WriteFileService {

    private final FileRepository fileRepository;

    public WriteFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void writeToFile(String filename, String record) {
        try {
            this.fileRepository.write(filename, record);
        } catch (IOException e) {
            System.err.printf("Couldn't write %s to file: %s, error message: %s\n", record, filename, e);
        }
    }

}
