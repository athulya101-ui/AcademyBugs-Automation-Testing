package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static Object[][] getExcelDataAsMap(
            String filePath,
            String sheetName)
            throws IOException {

        FileInputStream fileInputStream =
                new FileInputStream(filePath);

        XSSFWorkbook workbook =
                new XSSFWorkbook(fileInputStream);

        Sheet sheet =
                workbook.getSheet(sheetName);

        if (sheet == null) {

            workbook.close();
            fileInputStream.close();

            throw new RuntimeException(
                "Excel sheet not found: "
                + sheetName
            );
        }

        Row headerRow =
                sheet.getRow(0);

        int totalColumns =
                headerRow.getLastCellNum();

        int totalRows =
                sheet.getLastRowNum();

        DataFormatter formatter =
                new DataFormatter();

        Object[][] data =
                new Object[totalRows][1];

        for (int rowIndex = 1;
             rowIndex <= totalRows;
             rowIndex++) {

            Row currentRow =
                    sheet.getRow(rowIndex);

            Map<String, String> rowData =
                    new LinkedHashMap<>();

            for (int columnIndex = 0;
                 columnIndex < totalColumns;
                 columnIndex++) {

                String columnName =
                        formatter.formatCellValue(
                            headerRow.getCell(columnIndex)
                        ).trim();

                String value = "";

                if (currentRow != null
                        && currentRow.getCell(
                            columnIndex
                        ) != null) {

                    value =
                        formatter.formatCellValue(
                            currentRow.getCell(
                                columnIndex
                            )
                        ).trim();
                }

                rowData.put(
                    columnName,
                    value
                );
            }

            data[rowIndex - 1][0] =
                    rowData;
        }

        workbook.close();
        fileInputStream.close();

        System.out.println(
            "Excel rows loaded: "
            + data.length
        );

        return data;
    }
}