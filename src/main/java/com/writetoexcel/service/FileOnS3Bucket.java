package com.writetoexcel.service;

import java.io.File;

public interface FileOnS3Bucket {
    public String uploadFile(File excelFile);
}
