package Homework.Teden4;

/**
 *   Prvi del programa vsebuje metodi za kodiranje dvojiskiZapisZnaka() in
 *   dvojiskiZapisSporocila() - to ni bil del domače naloge, sem pa metodi 
 *   potreboval za generiranje dvojiskih sporočil (vhod za DN04).
 *   Drugi del programa vsebuje metodi odkodirajZnakI() in asciiSporocilo(),
 *   ki ju potrebujem za rešitev domače naloge.
 */
public class DN04 {
    //***************** KODIRANJE *******************************************
    
    // Kodiranje enega znaka. Primer: 'A' -> "01000001"
    static String dvojiskiZapisZnaka(char znak) {
      int koda = (int) znak;
      // za pretvorbo v binarno obliko uporabim metodo Integer.toString()
      String binarno = Integer.toString(koda, 2);
      
      // če je niz krajši od 8 znakov, dodam ničle na začetek
      while (binarno.length() < 8)
        binarno = "0"+binarno;
      return binarno;
    }
    
    // celotno sporocilo pretvorim v dvojiski zapis
    // za pretvorbo posameznega znaka uporabim metodo dvojiskiZapisZnaka()
    static String dvojiskiZapisSporocila(String niz) {
      String rezultat = "";
      for (int i = 0; i < niz.length(); i++) {      
        rezultat += dvojiskiZapisZnaka(niz.charAt(i)); 
      }
      return rezultat;
    }
    
    //***************** DEKODIRANJE *****************************************
    
    
    // pretvorba enega znaka iz dvojiskega v ASCII zapis. Primer: "01000001" -> 'A'
    static char odkodirajZnak(String crka) {
      int vC = 0, vrednost = 1;
      for (int j = 7; j>=0; j--, vrednost<<=1)
        vC += (crka.charAt(j)-'0') *  vrednost;
      return (char) vC;
    }
    
    static String asciiSporocilo(String dvojiskoSporocilo) {
      // prvotno (odkodirano) sporocilo
      String ascii = "";
      
      // dokler dvojiskoSporocilo se vsebuje znake (potrebujem vsaj 
      // 8 nicel in enic, da lahko "dekodiram")
      while (dvojiskoSporocilo.length() > 7) {      
        // v "crka" shranim prvih osem znakov dvojiškega sporočila ...
        String crka = dvojiskoSporocilo.substring(0,8);
        // ... dvojiskemu sporocilu "odrežem" prvih 8 znakov ...
        dvojiskoSporocilo = dvojiskoSporocilo.substring(8);
        
        // ... iz znak "crka" pretvorim v ASCII obliko
        ascii += odkodirajZnak(crka);
      }
      return ascii;
    }
    
    public static void main(String[] args) {   
      //args = new String[]{"010110010110111101110101001001110110110001101100001000000110111001100101011001010110010000100000011000010010000001110000011001010110111000100000011000010110111001100100001000000111000001100001011100000110010101110010001011100010111000101110"};
      
      if (args.length < 1) {      
        System.out.println("Napaka - program portebuje en argument!");
      } else {        
        System.out.println(asciiSporocilo(args[0]));        
      } 
    } 
  }