package practice;

import java.util.HashMap;
import java.util.Map;

public class Ex01 {
    public static void main (String[] args)
    {
        Map<String,Integer> annuaire = new HashMap<>();
        annuaire.put("Say", 23);
        annuaire.put("Marie", 21);
        annuaire.put("Say", 24);
        System.out.println(annuaire.get("Say"));
        System.out.println(annuaire.size());
        System.out.println(annuaire.get("Paul"));
        System.out.println(annuaire.getOrDefault("Paul", 0));

    }
}
