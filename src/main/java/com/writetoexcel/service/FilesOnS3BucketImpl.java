package com.writetoexcel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FilesOnS3BucketImpl implements FileOnS3Bucket {
    private AmazonClient amazonClient;
    @Autowired
    FilesOnS3BucketImpl (AmazonClient amazonClient){
        this.amazonClient = amazonClient;
    }

    public String uploadFile(File excelFile){
        return this.amazonClient.uploadFile(excelFile);
    }

}
