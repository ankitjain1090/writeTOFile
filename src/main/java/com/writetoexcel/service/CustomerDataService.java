package com.writetoexcel.service;

import com.writetoexcel.model.CustomerData;

import java.io.File;

public interface CustomerDataService {
    public String writeDataToExcel(CustomerData customerData , File excelFile);
}
