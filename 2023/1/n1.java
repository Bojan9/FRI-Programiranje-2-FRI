public class n1 {
  int kaksenDan(String datum, int danes, int letos) {
    String[] parts = datum.split("[.]");
    int mesec = Integer.parseInt(parts[0]);
    int dan   = Integer.parseInt(parts[1]);
    
    int[] meseci = {31,28,31,30,31,30,31,31,30,31,30,31};
    // koliko dni je minilo od 1.1. do (vkljucno) danes
    int dni = 0;
    for (int i = 0; i < mesec-1; i++) 
      dni+=meseci[i];
    dni+=dan;
    
    double povprecje = letos / dni;
    return danes > povprecje ? 1 : (danes < povprecje ? -1 : 0);
  }
  
  public static void main(String[] args) {
    //args=new String[] {"04.03", "511", "47523"};
    //args=new String[] {"01.01", "829", "830"};
    //args=new String[] {"12.31", "317", "116070"};
    //args=new String[] {"12.31", "318", "116070"};
    
    if (args.length < 3) {
      System.out.println("Uporaba programa: java Naloga11 datum danes letos");
      System.exit(0);
    }
    n1 n1 = new n1();
    System.out.println(n1.kaksenDan(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2])));
  }
}