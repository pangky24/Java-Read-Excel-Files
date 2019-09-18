package com.refined.model;

public class Constants {
    public enum TitleEnum {
        FP("FP", "FP"),//
        FPManager("FPManager", "FP소장"),//
        FPPrimeLeader("FPPrimeLeader", "FP프라임리더"),//
        GFP("GFP", "GFP"),//
        ETC("ETC", "ETC"),//
        ;

        String code;
        String name;

        TitleEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    public enum CertificateEnum {
        ExcellentDesigner("ExcellentDesigner", "우수인증설계사"),//
        GoldenFellow("GoldenFellow", "골든펠로우"),//
        MDRT("MDRT", "MDRT"),//
        ;

        String code;
        String name;

        CertificateEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }

    public enum MasterEnum {
        ExcellentDesigner("ExcellentDesigner", "우수인증설계사"),//
        GoldenFellow("GoldenFellow", "GoldenFellow"),//
        MDRT("MDRT", "MDRT"),//
        FPPrimeLeader("FPPrimeLeader", "FP프라임리더"),//
        FPManager("FPManager", "FP소장"),//
        SM("SM", "SM"),//
        ;

        String code;
        String name;

        MasterEnum(String code, String name) {
            this.code = code;
            this.name = name;
        }
    }
}
