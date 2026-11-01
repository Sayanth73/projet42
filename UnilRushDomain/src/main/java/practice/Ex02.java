package practice;

import java.util.HashMap;
import java.util.Map;

public class Ex02 {
    public  static void main(String[] args) {
        Map<String, String> annuaire = new HashMap<>();
        annuaire.put("Maman", "079 123 45 67");
        annuaire.put("Tom", "078 987 65 43");
        annuaire.put("Coop", "024 111 22 33");
        for (String numero : annuaire.values()) {
            System.out.println(numero);
            if (numero.equals(annuaire.get("Tom")))
                System.out.println("trouvé");

        }
    }
}
