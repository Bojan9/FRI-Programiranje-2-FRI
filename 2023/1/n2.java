
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class n2 {
  static int[] meseci = {31,28,31,30,31,30,31,31,30,31,30,31};

  
  static void urediDatoteko(String vhodnaDatoteka) {
    try (Scanner sc = new Scanner(new File(vhodnaDatoteka));) {
      
      // podatke iz vhodne datoteke preberem v seznam
      ArrayList<String> podatki = new ArrayList();
      while (sc.hasNextLine()) 
        podatki.add(sc.nextLine());
      
      // podatke lahko uredimo "kot nize" (po abecedi); ker se vrstica zacne z 
      // datumom oblike DD.MM, bodo s tem podatki urejeni po datumih
      Collections.sort(podatki);
      
      int letos = 0; // stevilo kolesarjev do sedaj
      for (int i = 0; i < podatki.size(); i++) {
        String vrstica = podatki.get(i);
        String[] deli = vrstica.split(":");
        int danes = Integer.parseInt(deli[1]);
        letos += danes;
        
        System.out.println(vrstica + ":" + letos);
      }
      
    } catch (Exception e) {
      System.out.println("Napaka: " + e);
    }
  }
  
  public static void main(String[] args) {    
    // args = new String[]{"viri/n12c.txt"};
    urediDatoteko(args[0]);
  }
}