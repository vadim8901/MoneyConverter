import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) throws IOException {
        List<Money> monies = new ArrayList<>();

        Parser parser = new Parser();
        
        monies = parser.getCurrency();

        double HUF = Double.parseDouble(monies.get(0).getMoney());

        double NOK = Double.parseDouble(monies.get(1).getMoney());

        double HUFNominal = Double.parseDouble(monies.get(0).getNominal());

        double NOKNominal = Double.parseDouble(monies.get(1).getNominal());

        System.out.println((NOK/HUF) * (HUFNominal / NOKNominal));
    }
}
