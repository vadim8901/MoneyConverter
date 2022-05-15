import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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

    public List<Money> getCurrency() {
        Document document = Jsoup.connect("http://www.cbr.ru/scripts/XML_daily.asp").get();

        Elements FirstElements = document.getElementsByAttributeValue("ID", "R01135");

        Elements SecondElements = document.getElementsByAttributeValue("ID", "R01535");

        findElement(FirstElements);
        findElement(SecondElements);

        return monies;
    }
}

