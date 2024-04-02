package Homework.Teden3;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class DN03 {

  static String[] preberiBesede(String imeDatoteke) throws Exception {
    // odprem datoteko
    Scanner sc = new Scanner(new File(imeDatoteke));
    // preberem stevilo besed ...
    int steviloBesed = sc.nextInt();
    // ... in ustvarim tabelo primerne dolzine ...
    String[] besede = new String[steviloBesed];
    // .... ter preberem vse besede iz datoteke v tabelo
    for (int i = 0; i < steviloBesed; i++) {
      besede[i] = sc.next();
    }
    sc.close();
    
    return besede;
  }

  static String ustvariGeslo(String[] besede, int dolzina, int seme) {
    String geslo = "";
    Random rnd = new Random(seme);
    // generirem n crk in sicer tako, da napjre ...
    for (int i = 0; i < dolzina; i++) {
      int beseda = rnd.nextInt(besede.length); // ... izberem besedo ...
      int crka = rnd.nextInt(besede[beseda].length()); // nato se crko      
      geslo = geslo + besede[beseda].charAt(crka);
    }
    return geslo;
  }
  
  public static void main(String[] args) throws Exception {
    // args = new String[]{"viri/gesla.txt", "8", "100"}; 

    if (args.length != 3) {
      System.out.println("Napacno stevilo argumentov.");
      System.exit(0);
    }

    // shranim (in pretvorim) argumente 
    String imeDatoteke = args[0];              // ime datoteke z gesli
    int dolzina = Integer.parseInt(args[1]);   // dolzina gesla, ki ga generiram
    int seme    = Integer.parseInt(args[2]);   // seme za naključno zaporedje

    String[] besede = preberiBesede(imeDatoteke); 
    String geslo    = ustvariGeslo(besede, dolzina, seme);
    System.out.println(geslo);
  }
}