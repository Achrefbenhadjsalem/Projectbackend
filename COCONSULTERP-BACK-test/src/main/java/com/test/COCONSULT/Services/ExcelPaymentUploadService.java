package com.test.COCONSULT.Services;

import com.test.COCONSULT.Entity.Payment;
import com.test.COCONSULT.DTO.TypePayment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelPaymentUploadService {
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Payment> getPaymentsDataFromExcel(InputStream inputStream) {
        List<Payment> payments = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Feuil1"); // Assuming sheet name is "payment"

            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue; // Skip header row
                }
                Iterator<Cell> cellIterator = row.iterator();
                Payment payment = new Payment(); // Move instantiation inside the loop
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int cellIndex = cell.getColumnIndex();
                    switch (cellIndex) {
                        case 0: // paymentDate
                            if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                                payment.setPaymentDate(cell.getLocalDateTimeCellValue().toLocalDate());
                            }
                            break;
                        case 1: // amount
                            if (cell.getCellType() == CellType.NUMERIC) {
                                double amount = cell.getNumericCellValue();
                                payment.setAmount(amount);
                            }


                            break;
                        case 2: // paymentMethod
                            if (cell.getCellType() == CellType.STRING) {
                                String paymentMethodStr = cell.getStringCellValue();
                                TypePayment paymentMethod = TypePayment.valueOf(paymentMethodStr);
                                payment.setPaymentMethod(paymentMethod);
                            }
                            break;
                        case 3: // referenceNumber
                            if (cell.getCellType() == CellType.STRING) {
                                payment.setReferenceNumber(cell.getStringCellValue());
                            }
                            break;
                        default:
                            break;
                    }
                }
                payments.add(payment);
                rowIndex++; // Increment rowIndex after processing each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payments;
    }
}
