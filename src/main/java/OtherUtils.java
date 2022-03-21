public class OtherUtils {
    public static float stringToFloat(String text){
        //Удаление всего, кроме чисел и запятых из текста
        text = text.replaceAll("[^0-9,]", "");
        //Возврат результата с заменой запятых на точки
        return Float.parseFloat(text.replaceAll(",", "."));
    }
}
