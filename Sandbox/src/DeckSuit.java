import java.util.ArrayList;

public class DeckSuit {

    ArrayList<String > cardSuit = cardsInsert();

    public ArrayList<String> cardsInsert(){
        ArrayList<String> result = new ArrayList<>();
        String value = "";
        int times = 0;
        for (int i = 0; i != 4; i++) {
            while(times !=13) {

                if (i == 0) {
                    value = "C";
                }
                if (i == 1) {
                    value = "D";
                }
                if (i == 2) {
                    value = "H";
                }
                if (i == 3) {
                    value = "S";
                }
                times++;
                result.add(value);
            }
            times = 0;
        }
        return result;
    }
}
