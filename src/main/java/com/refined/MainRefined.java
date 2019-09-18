package com.refined;

import com.refined.model.ApplyModel;
import com.refined.model.MasterModel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainRefined {
    public final String READ_MASTER_FILE = "D:\\phg_project\\Java-Read-Excel-Files\\src\\main\\resources\\master.xlsx";
    public final String READ_APPLY_FILE = "D:\\phg_project\\Java-Read-Excel-Files\\src\\main\\resources\\apply.xlsx";

    DataFormatter dataFormatter = new DataFormatter();

    public static void main(String args[]) {
        MainRefined mr = new MainRefined();

        mr.process();
    }

    private void process() {
        try {
            List<ApplyModel> applyModelList = readApplyData();
            List<MasterModel> masterModelList = readMasterData();

//            System.out.println(applyModelList.toString());
//            System.out.println(masterModelList.toString());

            List<MasterModel> filteredList = fillteredData(applyModelList, masterModelList);
            System.out.println(filteredList.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    private List<MasterModel> fillteredData(List<ApplyModel> applyModelList, List<MasterModel> masterModelList) {
        List<MasterModel> filteredModelList = new ArrayList<MasterModel>();

        for(ApplyModel applyModel : applyModelList) {
            for(MasterModel masterModel : masterModelList) {
                if(applyModel.getName() == null || masterModel.getName() == null) {
                    continue;
                }
                if(applyModel.getName().trim().equals(masterModel.getName().trim())) {
                    filteredModelList.add(masterModel);
                }
            }
        }

        return filteredModelList;
    }

    private List<ApplyModel> readApplyData() throws IOException, InvalidFormatException {
        List<ApplyModel> applyModelList = new ArrayList<ApplyModel>();
        int skipRowIdx = 2;
        int pickColumnIdx = 16;
        Workbook workbook = WorkbookFactory.create(new File(READ_APPLY_FILE));
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());

            Iterator<Row> rowIterator = sheet.rowIterator();

            int rowIdx = 0;


            while (rowIterator.hasNext()) {
                int colIdx = 0;
                Row row = rowIterator.next();

                if (rowIdx++ < skipRowIdx) {
                    continue;
                }

                // Now let's iterate over the columns of the current row
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if(colIdx++ == pickColumnIdx) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        System.out.print(cellValue + "\t");

                        String splitMemo[] = cellValue.split("<BR>");

                        ApplyModel applyModel = new ApplyModel();
                        for(String memo : splitMemo) {
                            System.out.println(memo);
                            String splitMemoEntity[] = memo.split(":");

                            if(splitMemoEntity[0].trim().equals("성명")) {
                                applyModel.setName(splitMemoEntity[1].trim());
                            } else if(splitMemoEntity[0].trim().equals("전화번호")) {
                                applyModel.setPhone(splitMemoEntity[1].trim());
                            } else if(splitMemoEntity[0].trim().equals("직함(FP,FP소장,FP프라임리더,GFP,기타(직접작성) 중 한 가지 선택")) {
                                applyModel.setTitle(splitMemoEntity[1].trim());
                            } else if(splitMemoEntity[0].trim().equals("자격인증(없음,우수인증설계사,골든펠로우,MDRT) 중 선택/예시 외 불가")) {
                                applyModel.setCertificate(splitMemoEntity[1].trim());
                            }
                        }
                        applyModelList.add(applyModel);
                    }
                }
                System.out.println();
            }
        }

        workbook.close();
        return applyModelList;
    }

    private List<MasterModel> readMasterData() throws IOException, InvalidFormatException {
        List<MasterModel> masterModelList = new ArrayList<MasterModel>();
        int skipRowIdx = 1;

        Workbook workbook = WorkbookFactory.create(new File(READ_MASTER_FILE));
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        System.out.println("Retrieving Sheets using Iterator");
        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();
            System.out.println("=> " + sheet.getSheetName());

            Iterator<Row> rowIterator = sheet.rowIterator();
            int rowIdx = 0;
            while (rowIterator.hasNext()) {
                int colIdx = 0;
                Row row = rowIterator.next();

                if (rowIdx++ < skipRowIdx) {
                    continue;
                }

                MasterModel masterModel = new MasterModel();

                // Now let's iterate over the columns of the current row
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    String cellValue = dataFormatter.formatCellValue(cell);
                    System.out.print(cellValue + "\t");

                    if(colIdx == 0) {
                        masterModel.setName(cellValue);
                    } else if(colIdx == 1) {
                        masterModel.setEmployeeIdentificationNumber(cellValue);
                    }

                    ++colIdx;

                }
                masterModelList.add(masterModel);
                System.out.println();
            }
        }

        return masterModelList;
    }
}
