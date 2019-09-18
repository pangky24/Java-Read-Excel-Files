package com.refined.model;

import lombok.Data;

@Data
public class MasterModel {
    private String name;
    private String fpCode;
    private String employeeIdentificationNumber;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFpCode() {
        return fpCode;
    }

    public void setFpCode(String fpCode) {
        this.fpCode = fpCode;
    }

    public String getEmployeeIdentificationNumber() {
        return employeeIdentificationNumber;
    }

    public void setEmployeeIdentificationNumber(String employeeIdentificationNumber) {
        this.employeeIdentificationNumber = employeeIdentificationNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
