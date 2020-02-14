package com.writetoexcel.literal;

public interface Message {
    String SUCCESS_MSG = "File Saved Successfully";
    String FILE_IO_ERROR = "Can not write in Excel File\nMay be file is open";
    String S3BUCKET_SUCCESS_MSG = "File upload on S3 bucket successfully";
}
