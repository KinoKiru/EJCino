import java.util.ArrayList;

public class DeckValue {
    ArrayList<Integer> cardsValue = cardsInsert();

    public ArrayList<Integer> cardsInsert(){
        ArrayList<Integer> result = new ArrayList<>();
        int value = 1;
        for (int i = 0; i < 52; i++) {
            if(value>13){
                value = 1;

            }
            result.add(value);
            value++;
        }
        return result;
    }

}
