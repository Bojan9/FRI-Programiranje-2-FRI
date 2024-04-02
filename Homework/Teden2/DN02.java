package Homework.Teden2;

/**
 * Izpis besedila v kvadrat.
 *
 * Napiši program, ki besedilo (prvi argument ob klicu programa) izpiše
 * v obliki kvadrata. Natančneje: če je x enak navzgor zaokroženemu
 * kvadratnemu korenu dolžine besedila, naj program prvih x znakov
 * zapiše v prvo vrstico, drugih x znakov v drugo vrstico in tako naprej.
 * Če je dolžina besedila manjša od x*x, naj program na prazna mesta v
 * kvadratu izpiše znak '.'.
 *
 * Primer: izpis besedila "abc" naj bo tak:
 * a  b
 * c  .
 *
 * izpis besedila "abcdefghi" tak:
 *  a  b  c
 *  d  e  f
 *  g  h  i
 *
 *  izpis besedila "abcdefghij" pa tak:
 *  a  b  c  d
 *  e  f  g  h
 *  i  j  .  .
 *  .  .  .  .
 *
 *
 * Namig: navzdol zaokrožen koren števila n lahko izračunaš tako:
 *
 *   int koren = (int) Math.sqrt(n);
 */

 public class DN02 {
    public static void main(String[] args) {
        //args = new String[]{"abcdefghij"};

        if (args.length == 0) return;

        // dolocitev velikosti stranice kvadrata
        int n     = args[0].length();    // stevilo crk
        int koren = (int) Math.sqrt(n); // koren stevila crk (zaokrozeno navzdol)
        if (koren*koren < n) koren++;   // ce n ni kvadratno stevilo, zaokrozim navzgor

        int izpis = 0; char crka = ' ';
        for (int i = 0; i <koren ; i++) {
            for (int j=0; j<koren; j++) {
                if (izpis < n) {
                    crka = args[0].charAt(izpis++);
                } else {
                    crka = '.';
                }
                System.out.print(" " + crka + " ");
            }
            System.out.println();
        }
    }
}