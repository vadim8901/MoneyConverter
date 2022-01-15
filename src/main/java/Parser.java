import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static List<Money> monies = new ArrayList<>();

    private static void findElement(Elements elements){
        elements.forEach(element -> {
            Element valueNominal = element.child(2);
            Element valueMoney = element.child(4);
            String money = valueMoney.text();
            String nominal = valueNominal.text();
            monies.add(new Money(money.replace(',','.'),nominal));
        });
    }
    
    public static void main(String[] args) throws IOException {

       Document document = Jsoup.connect("http://www.cbr.ru/scripts/XML_daily.asp").get();

       Elements FirstElements = document.getElementsByAttributeValue("ID", "R01135");

       Elements SecondElements = document.getElementsByAttributeValue("ID", "R01535");

       findElement(FirstElements);
       findElement(SecondElements);

        monies.forEach(money -> {
            System.out.println(money.getMoney());
        });

        double HUF = Double.parseDouble(monies.get(0).getMoney());

        double NOK = Double.parseDouble(monies.get(1).getMoney());

        double HUFNominal = Double.parseDouble(monies.get(0).getNominal());

        double NOKNominal = Double.parseDouble(monies.get(1).getNominal());

        System.out.println((NOK/HUF) * (HUFNominal / NOKNominal));
    }
}

class Money{
    private String money;
    private String nominal;

    public Money(String money, String nominal) {
        this.money = money;
        this.nominal = nominal;
    }

    public String getMoney() {
        return money;
    }

    public String getNominal() { return nominal; }
}
