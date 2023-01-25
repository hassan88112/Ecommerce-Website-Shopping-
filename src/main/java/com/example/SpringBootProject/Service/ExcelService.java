package com.example.SpringBootProject.Service;


import com.example.SpringBootProject.Entity.ExcelTest;
import com.example.SpringBootProject.Entity.Product;
import com.example.SpringBootProject.Repository.ExcelRepo;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ExcelService {

    @Autowired
    private ExcelRepo excelRepo;

    public List<ExcelTest> findAllExcelTest(){

        return excelRepo.findAll();
    }
    public Optional<ExcelTest> findExcelTestById(Long id){

        return excelRepo.findById(id);
    }

    public ExcelTest AddExcelTest(ExcelTest e){

        return excelRepo.save(e);
    }
    public void deleteExcelTestById(Long id){

        excelRepo.deleteById(id);
    }


    public Boolean saveDateFromUploadFlie(MultipartFile file) {
        Boolean isFlag=false;
        String e= FilenameUtils.getExtension(file.getOriginalFilename());
        if (e.equalsIgnoreCase("xls") || e.equalsIgnoreCase("xlsx")){

            isFlag=readDataFromExcel(file);
        }

        return true;
    }

    private Boolean readDataFromExcel(MultipartFile file) {
        Workbook workbook=getWorkBook(file);
        Sheet sheet=workbook.getSheetAt(0);
        Iterator<Row> rows=sheet.iterator();
        rows.next();
        while (rows.hasNext()){

            Row row=rows.next();
            ExcelTest test=new ExcelTest();
            if (row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING) {
                test.setFirstName(row.getCell(0).getStringCellValue());
            }

            if (row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC){
                String salary= NumberToTextConverter.toText(row.getCell(1).getNumericCellValue());
                test.setSalary(salary);
            } else if (row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {
                test.setSalary(row.getCell(1).getStringCellValue());
            }
            test.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
            excelRepo.save(test);

        }
        return true;
    }

    private Workbook getWorkBook(MultipartFile file) {
        Workbook workbook=null;
        String extension= FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            if (extension.equalsIgnoreCase("xlsx")){
                workbook=new XSSFWorkbook(file.getInputStream());
            } else if (extension.equalsIgnoreCase("xls")) {
                workbook=new HSSFWorkbook(file.getInputStream());
            }

        }catch (Exception p){
            System.out.println(p);

        }
        return workbook;
    }
}
