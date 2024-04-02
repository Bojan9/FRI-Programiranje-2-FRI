class Mnozica {
    int elementi;
    
    Mnozica() {
      elementi = 0;
    }
    Mnozica(int elementi) {
      this.elementi = elementi;
    }
    
    private static int getIndex(char c) {
      return c - 'a';
    }
    
    void dodajElement(char znak) {           // dodaj element v množico
      elementi = elementi | (1<<getIndex(znak));
    }
    
    void brisiElement(char znak) {           // odstrani element iz množice
      elementi = elementi & ~(1<<getIndex(znak));
    }
    
    boolean jeElement(char znak) {
      return (elementi & ((1<<getIndex(znak)))) != 0;
    }
      
    public String toString() {		          // vrne seznam elementov množice
      String result = "";
      for (char z = 'a'; z <= 'z'; z++) {
        if (jeElement(z))
          result += (result.isEmpty() ? "" : ",") + z;
      }
      return result;
    }
  
    static Mnozica unija(Mnozica m1, Mnozica m2) {   // vrne unijo dveh mnozic
      return new Mnozica(m1.elementi | m2.elementi);
    }
    
    static Mnozica presek(Mnozica m1, Mnozica m2) {  // vrne presek dveh mnozic
      return new Mnozica(m1.elementi & m2.elementi);
    }
  }

public class n2 {
    public static void main(String[] args) {
      Mnozica m1 = new Mnozica();
      m1.dodajElement('a');  m1.dodajElement('b');
      m1.dodajElement('d');  m1.dodajElement('x');
      m1.brisiElement('x');
      System.out.println(m1.toString());
      
      Mnozica m2 = new Mnozica();
      m2.dodajElement('b');  m2.dodajElement('c');
      m2.dodajElement('d');  m2.dodajElement('y');
      m2.brisiElement('c');
      System.out.println(m2.toString());
      
      Mnozica m3 = Mnozica.unija(m1, m2);
      System.out.println(m3);
      
      Mnozica m4 = Mnozica.presek(m1, m2);
      System.out.println(m4);
    } 
  }