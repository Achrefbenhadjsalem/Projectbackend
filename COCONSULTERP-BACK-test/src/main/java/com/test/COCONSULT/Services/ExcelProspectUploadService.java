package com.test.COCONSULT.Services;

import com.test.COCONSULT.Entity.Prospect;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelProspectUploadService {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Prospect> getProspectsDataFromExcel(InputStream inputStream) {
        List<Prospect> prospects = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("prospects"); // Assuming sheet name is "prospects"
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Prospect prospect = new Prospect(); // Move instantiation inside the loop
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            prospect.setIdProspect((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            prospect.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            prospect.setTitle(cell.getStringCellValue());
                            break;
                        case 3:
                            prospect.setEmail(cell.getStringCellValue());
                            break;
                        case 4:
                            if (cell.getCellType() == CellType.STRING) {
                                prospect.setNumTel(cell.getStringCellValue());
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                prospect.setNumTel(String.valueOf((long) cell.getNumericCellValue()));
                            }
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                prospects.add(prospect);
                rowIndex++; // Increment rowIndex after processing each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prospects;
    }

}
