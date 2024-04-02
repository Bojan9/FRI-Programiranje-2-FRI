
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Stanje {
   private String datum;
   private int danes;
   private int letos;

  public Stanje(String datum, int danes, int letos) {
    this.datum = datum;
    this.danes = danes;
    this.letos = letos;
  }

  public String getDatum() {
    return datum;
  }

  public void setDatum(String datum) {
    this.datum = datum;
  }

  public int getDanes() {
    return danes;
  }

  public void setDanes(int danes) {
    this.danes = danes;
  }

  public int getLetos() {
    return letos;
  }

  public void setLetos(int letos) {
    this.letos = letos;
  }

  @Override
  public String toString() {
    return String.format("Dan: %s, Danes: %d, Letos: %d", datum, danes, letos);
  }      
}

public class n3 {
  static ArrayList<Stanje> preberiStanja(String imeDatoteke) {
    ArrayList<Stanje> stanja = new ArrayList();
    try (Scanner sc = new Scanner(new File(imeDatoteke))) {
      while (sc.hasNextLine()) {
        String[] deli = sc.nextLine().split(":");
        Stanje s = new Stanje(deli[0], Integer.parseInt(deli[1]), Integer.parseInt(deli[2]));
        stanja.add(s);
      }
    } catch (Exception e) {
      System.out.println("Napaka: " + e);
    }
    return stanja;
  }
  
  static void izpisiTop10(ArrayList<Stanje> stanja) {
    stanja.sort(new Comparator<Stanje>() {
      public int compare(Stanje o1, Stanje o2) {
        if (o2.getDanes() != o1.getDanes())
          return o2.getDanes() - o1.getDanes();
        else 
          return o2.getDatum().compareTo(o1.getDatum());        
      }      
    });
    for (int i = 9; i >=0; i--) {
      System.out.println(stanja.get(i));
    }
  }
  
  public static void main(String[] args) {
    //args = new String[]{"viri/u12b.txt"};
    
    ArrayList<Stanje> stanja = preberiStanja(args[0]);
    izpisiTop10(stanja);
  }
}