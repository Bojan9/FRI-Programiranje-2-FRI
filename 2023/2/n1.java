public class n1 {

    // pretvorba stevila iz desetiskega v dvojiski sistem
     static String vDvojisko(int x) {
       // za nepozitivna stevila vrnemo 0
       if (x <= 0) return "0";
       
       String rezultat="";
   
       while(x>0) {
         // zadnjo cifro dodamo v rezultat ...
         if ((x & 1) == 1)
           rezultat = "1" + rezultat;
         else
           rezultat = "0" + rezultat;
         
         // ... nato jo "odrezemo" od x
         x = x >> 1;
       }
       
       return rezultat;
     }
     
     static int vDesetisko(String d) {
       int utez = 1;
       int rezultat = 0;
       
       // sprehodimo se od najmanj do najbolj pomembnega bita
       for (int i = d.length()-1; i >= 0; i--) {
         // ce je bit prizgan, ga pristejemo razultatu
         if (d.charAt(i) == '1') 
           rezultat += utez;
         utez = utez*2;
       }
       
       return rezultat;
     }  
     
     public static void main(String[] args) {
       //args = new String[]{"42"};
       
       int x = Integer.parseInt(args[0]);
       System.out.println("x: " + x);
       
       String dvojisko = vDvojisko(x);
       System.out.println("b: " + dvojisko);
       
       int y = vDesetisko(dvojisko);
       System.out.println("d: " + y);
       
     }
   }