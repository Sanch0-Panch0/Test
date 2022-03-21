import java.io.IOException;

public class Schedule {

    private static String[][] places;
    private static float tripDuration = 24;
    private static float sleepDuration = 8;
    private static float placesVisited;
    private static int i;
    private static int j;
    private static int maxi = 0;
    private static int days = 2;


    public static void main(String[] args) {
        //Обработка исключения на случай неверного ввода данных
        try {
            places = DataUtil.excelDataGet();
        } catch (IOException e) {
            System.out.println("Ошибка ввода данных");
            e.printStackTrace();
        }
        //Вывод списка достопримечательностей без превышения максимального количество времени пребывания
        System.out.println("Оптимальный порядок посещения достопримечательностей: ");
        System.out.println("------------------------------------------------------------------------------");
        System.out.printf("%-60s %10s %n", "Название", "Длительность");
        System.out.println("------------------------------------------------------------------------------");
        for (j = 1; j <= days; j++){
            System.out.println("День " + j);
            placesVisited = 0;
            for (i = maxi; i < places.length; i++){
                if(placesVisited + OtherUtils.stringToFloat(places[i][1]) <= tripDuration - sleepDuration) {
                    placesVisited += OtherUtils.stringToFloat(places[i][1]);
                    System.out.printf("%-60s %10s %n", places[i][0], places[i][1]);
                    maxi = i;
                }
            }
        }
    }
}
