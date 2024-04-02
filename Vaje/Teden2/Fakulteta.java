package Vaje.Teden2;

public class Fakulteta {

    // vrne celoštevilsko vrednost fakultete (natančen izračun)
    public static long fakultetaL(int n) {
        long rezultat = 1;
        for (int i = 2; i <= n; i++) {
            rezultat = rezultat * i;
        }
        return rezultat;
    }

    // rekurzivna rešitev, vrne celoštevilsko vrednost fakultete (natančen izračun)
    public static long fakultetaR(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * fakultetaR(n - 1);
    }

    // vrne celoštevilski približek fakultete po Stirlingu
    public static long stirlingL(int n) {
        return Math.round(Math.sqrt(2 * Math.PI * n) * Math.pow(n / Math.E, n));
    }

    // vrne približek fakultete kot realno število (tip double ima večji obseg kot tip long)
    public static double fakultetaD(int n) {
        double rezultat = 1;
        for (int i = 2; i <= n; i++) {
            rezultat = rezultat * i;
        }
        return rezultat;
    }

    // vrne realni približek fakultete po Stirlingu
    public static double stirlingD(int n) {
        return Math.sqrt(2 * Math.PI * n) * Math.pow(n / Math.E, n);
    }

    // tabeliraj za n od 1 do 20, celoštevilski izračun Stirlingove formule
    public static void izpisTabelaL() {
        // izpis glave tabele
        System.out.printf("%3s %15s %22s %15s\n", "n", "n!", "Stirling(n)", "napaka (%)");
        for (int i = 0; i < 58; i++) {
            System.out.print("-");
        }
        System.out.println();
        // izpis vrednosti fakultete za vse n od 1 do 20
        for (int i = 1; i <= 20; i++) {
            long f = fakultetaL(i);
            long s = stirlingL(i);
            double napaka = 100.0 * (f - s) / f;
            System.out.printf("%3d %20d %20d %11.7f\n", i, f, s, napaka);
        }
    }

    // tabeliraj za n od 1 do 100, realni izračun Stirlingove formule
    public static void izpisTabelaD() {
        // izpis glave tabele
        System.out.printf("%3s %10s %22s %14s\n", "n", "n!", "Stirling(n)", "napaka (%)");
        for (int i = 0; i < 52; i++) {
            System.out.print("-");
        }
        System.out.println();
        // izpis vrednosti fakultete za vse n od 1 do 100, eksponentni zapis približka fakultete
        for (int i = 1; i <= 100; i++) {
            double f = fakultetaD(i);
            double s = stirlingD(i);
            double napaka = 100 * (f - s) / f;
            System.out.printf("%3d %17.9E %17.9E %11.7f\n", i, f, s, napaka);
        }
    }

    //
    // DODATNI IZZIVI
    //

    // računanje približka Pi z Nilakanthovo vrsto
    public static double izracunajPiNilakantha(int k) {
        double rez = 3.0;
        int predznak = 1;
        for (int i = 2; i <= k; i++) {
            rez = rez + predznak * 4.0 / ((2.0 * i - 2) * (2.0 * i - 1) * 2.0 * i);
            predznak *= -1;
        }
        return rez;
    }

    // računanje približka Pi z verižnimi ulomki rekurzivno
    private static double ulomek(int i, int max) {
        if (i == max) {
            return 0.0;
        }
        return (i * i) / ((2.0 * i + 1.0) + ulomek(i + 1, max));
    }

    public static double izracunajPiRekurzivno(int k) {
        return 4.0 / (1 + ulomek(1, k));
    }

    // računanje približka Pi z verižnimi ulomki iterativno
    public static double izracunajPi(int k) {
        double rez = 0.0;
        for (int i = k - 1; i > 0; i--) {
            rez = (i * i) / ((2.0 * i + 1.0) + rez);
        }
        return 4.0 / (1.0 + rez);
    }

    public static void izpisTabelaPiNilakantha() {
        System.out.print("  k     Math.PI             PI (Nilakantha)      razlika \n");
        System.out.print("-----------------------------------------------------------------\n");
        for (int n = 1; n <= 22; n++) {
            double izracunPi = izracunajPiNilakantha(n);
            System.out.printf("%3d   %.15f   %.15f   %+.15f\n", n, Math.PI, izracunPi, Math.PI - izracunPi);
        }
        System.out.println();
    }

    public static void izpisTabelaPiVerizni() {
        System.out.print("  k     Math.PI             PI (rekurzivno)     PI (iterativno)      razlika \n");
        System.out.print("-------------------------------------------------------------------------------------\n");
        for (int n = 1; n <= 22; n++) {
            double piRek = izracunajPiRekurzivno(n);
            System.out.printf("%3d   %.15f   %.15f   %.15f   %+.15f\n", n, Math.PI, piRek, izracunajPi(n), Math.PI - piRek);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        izpisTabelaL();
        System.out.println();
        izpisTabelaD();
        System.out.println();

        // dodatni izzivi
        izpisTabelaPiNilakantha();
        izpisTabelaPiVerizni();
    }
}