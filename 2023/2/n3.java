import java.io.File;
import java.io.FileInputStream;

public class n3 {

  static void hexDump(String filename) {
    try {
      // odprem datoteko ...
      FileInputStream fis = new FileInputStream(new File(filename));
      
      int s=0;
      String znaki = "";
      // ... dokler ima datoteka še vsebino ...
      while (fis.available() > 0) {
        // ... berem znak po znaku ...
        int z = fis.read();s++;
        
        znaki += (z >=32 && z<=127) ? (char) z : ".";
        
        System.out.printf("%02X ", z);
        //if (s%8  == 0) System.out.print("  ");
        if (s%16 == 0) {
          System.out.println("   |" + znaki+"|");
          znaki="";
        }
      }
      
      // izpišem še preostanek zadnje vrstice
      if (s%16 != 0) {
        while (s%16 != 0) {
          System.out.print("   ");
          znaki += " ";
          s++;
        }
        System.out.println("   |" + znaki+"|");
      }
      
      // ... in na koncu zaprem datoteko
      fis.close();
    } catch (Exception e) {
      System.out.println("Napaka: " + e.toString());
    }  
  }
  
  public static void main(String[] args) {
    //args = new String[]{"viri/a.txt"};
    
    hexDump(args[0]);
  }
}