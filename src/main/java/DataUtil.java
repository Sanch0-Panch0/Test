import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class DataUtil {

    private static int i=0;
    private static int j=0;
    private static int k = 3;
    private static int l = 20;

    //Создаём двумерный массив в который переносим таблицу Data.xls при помощи Apache poi и возвращаем массив как результат, предварительно отсортировав
    public static String[][] excelDataGet() throws IOException {
        String[][] places = new String[l][k];
        Workbook wb = new HSSFWorkbook(new FileInputStream("Data.xls"));
        for (i = 0; i != l; i++){
           for (j = 0; j != k; j++){
               places[i][j] = getCellText(wb.getSheetAt(0).getRow(i).getCell(j));
           }
        }
        Arrays.sort(places, Comparator.comparingInt(arr -> (int) Float.parseFloat(arr[2])));
        return places;
    }

    //Метод для определения типа клетки и записи её значения с переводом в строковый тип данных
    private static String getCellText(Cell cell){
        String result = null;
        switch (cell.getCellType()) {
            case STRING:
                result = cell.getRichStringCellValue().getString();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    result = cell.getDateCellValue().toString();
                } else {
                    result = Double.toString(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                result = Boolean.toString(cell.getBooleanCellValue());
                break;
            case FORMULA:
                result = cell.getCellFormula().toString();
                break;
            case BLANK:
                result ="";
                break;
            default:
        }

        return result;
    }
}
