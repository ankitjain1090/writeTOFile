package com.writetoexcel.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.writetoexcel.literal.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class AmazonClient {
    private AmazonS3Client s3Client;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazon(){
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey , this.secretKey);
        this.s3Client = new AmazonS3Client(credentials);
    }
    public String uploadFile(File excelFile){
        try{
            String fileName = excelFile.getName();
            uploadFileTOS3Bucket(fileName , excelFile);
            return Message.S3BUCKET_SUCCESS_MSG;
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    private void uploadFileTOS3Bucket(String fileName , File file){
        s3Client.putObject(bucketName , fileName , file);
    }
}
