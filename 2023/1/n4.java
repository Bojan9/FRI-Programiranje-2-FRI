public class n4 {
  
    // Narisi en kvadratek na kroznici (0,0,r); velikost kvadratka: 2k*2k; kot je podan v stopinjah
    static void kvadratek(double r,  double k, int kot) {
      double x = r * Math.cos(kot*Math.PI/180);
      double y = r * Math.sin(kot*Math.PI/180);
      StdDraw.filledRectangle(x, y, k, k);
    }
    
    // izris animacije: krog s polmerom r in m kvadratov, ki krožijo po krožnici
    static void kaca(int r, int m) {
      StdDraw.setScale(-100,100);
      StdDraw.enableDoubleBuffering();
      
      // velikost kvadratka (1/10 premera)
      double k = r/10;
      int   kot = 0;     // začetni kot
      int deltaKot = 15; // za toliko povečam kot pri vsakem premiku
      
      
      while (true) {
        // pripravim povrsino ...
        StdDraw.clear(StdDraw.WHITE);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);
        // ... narisem krog ...
        StdDraw.circle(0, 0, r);  
        
        // ... in vse kvadratke
        StdDraw.setPenColor(StdDraw.CYAN);
        for (int i = 0; i < m; i++) {
          kvadratek(r, k, kot+i*deltaKot);
        }
        
        kot +=deltaKot;
        
        StdDraw.pause(100);
        StdDraw.show();
      }  
    }
     
    public static void main(String[] args) {
      kaca(40, 10);
    }
  }