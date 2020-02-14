package com.writetoexcel.controller;

import com.writetoexcel.literal.Message;
import com.writetoexcel.model.CustomerData;
import com.writetoexcel.service.CustomerDataService;
import com.writetoexcel.service.FileOnS3Bucket;
import com.writetoexcel.service.FilesOnS3BucketImpl;
import com.writetoexcel.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/writeToExcel")
public class writeToExcel {
    @Autowired
    CustomerDataService customerDataService;

    @Autowired
    FilesOnS3BucketImpl filesOnS3Bucket;

    @Autowired
    Environment env;

    @PostMapping("/data")
    public ResponseEntity<Object> writeToExcel(@ModelAttribute CustomerData customerData){

        String todayDate = DateUtil.getLocalDate().toString();
        File excelFile = new File(env.getProperty("excelHomePath")+"\\"+todayDate+"_worksheet.xlsx");
        String returnMSG = customerDataService.writeDataToExcel(customerData , excelFile);
        if(!returnMSG.equals(Message.SUCCESS_MSG)){
            return new ResponseEntity<Object>(returnMSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String returnMSGS3Bucket = filesOnS3Bucket.uploadFile(excelFile);
        if(!returnMSGS3Bucket.equals(Message.S3BUCKET_SUCCESS_MSG)){
            return new ResponseEntity<Object>(returnMSGS3Bucket, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Object>(Message.SUCCESS_MSG, HttpStatus.OK);
    }
}
