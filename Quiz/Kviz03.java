import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Kviz03 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sestejPolinoma(new int[] { 1, 2, 3 }, new int[]{ 4, 5, 6 })));
        System.out.println(Arrays.toString(zmnoziPolinoma(new int[] { 1, 2, 3 }, new int[]{ 4, 5, 6 })));
        System.out.println(jeAnagram("abac", "baca", true));
        String niz = "abc perica  reže raci rep def";
        System.out.printf("Najdaljši palindrom v nizu '%s' je '%s'\n", niz, najdaljsiPalindrom(niz, false));
        niz = "abc perica  reže raci rep def";
        System.out.printf("Najdaljši palindrom v nizu '%s' je '%s'\n", niz, najdaljsiPalindrom(niz, true));
        niz = "abcdeJavaRuleseluRavaJfghijk";
        System.out.printf("Najdaljši palindrom v nizu '%s' je '%s'\n", niz, najdaljsiPalindrom(niz, true));
        Matrika m1 = Matrika.preberiMartiko("assets/m1.txt");
        m1.izpisi();
        System.out.println(bsdChecksum("assets/bsdcheck.txt"));
        preveriInNarisi(new int[] { 1,3,0,2 });
        System.out.println();
        preveriInNarisi(new int[] { 1,0,3,2 });
        System.out.println(steviloPostavitev(6));

    }

    private static int[] sestejPolinoma(int[] a, int[] b) {
        int[] r = new int[Math.max(a.length, b.length)];
        for (int i = 0; i < a.length; i++) {
            r[i] += a[i];
        }
        for (int i = 0; i < b.length; i++) {
            r[i] += b[i];
        }
        return r;
    }

    private static int[] zmnoziPolinoma(int[] a, int[] b) {
        int[] r = new int[a.length + b.length - 1];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                r[i + j] += a[i] * b[j];
            }
        }
        return r;
    }

    private static boolean jeAnagram(String prvaBeseda, String drugaBeseda, boolean zanemariVelikost) {
        if (prvaBeseda.length() != drugaBeseda.length()) {
            return false;
        }
        if (prvaBeseda.length() == 0) {
            return true;
        }
        if (zanemariVelikost) {
            prvaBeseda = prvaBeseda.toLowerCase();
            drugaBeseda = drugaBeseda.toLowerCase();
        }
        String crka = Character.toString(prvaBeseda.charAt(0));
        if (drugaBeseda.contains(crka)) {
            return jeAnagram(prvaBeseda.substring(1), drugaBeseda.replaceFirst(crka, ""), zanemariVelikost);
        }
        return false;
    }

    private static String najdaljsiPalindrom(String niz, boolean presledki) {
        String palindrom = "";
        int levo, desno;
        for (int i = 1; i < niz.length(); i++) {
            // Razširjujemo na levo in desno stran
            // Lahko bi malo skrajšal kodo tako, da bi premestil ponavljanje kode v novo funckijo

            // Za sode palindrome
            levo = i - 1;
            desno = i;
            while (levo >= 0 && desno < niz.length()) {
                if (niz.charAt(levo) != niz.charAt(desno)) {
                    while (!presledki && (niz.charAt(levo) == ' ' && levo >= 0)) {
                        levo--;
                    }
                    while (!presledki && (niz.charAt(desno) == ' ' && desno < niz.length())) {
                        desno++;
                    }
                }
                if (niz.charAt(levo) != niz.charAt(desno)) {
                    break;
                }
                String beseda = niz.substring(levo, desno + 1);
                if (beseda.length() >= palindrom.length()) {
                    palindrom = beseda;
                }
                levo--;
                desno++;
            }

            // Za lihe palindrome
            levo = i - 1;
            desno = i + 1;
            while (levo >= 0 && desno < niz.length()) {
                if (niz.charAt(levo) != niz.charAt(desno)) {
                    while (!presledki && (niz.charAt(levo) == ' ' && levo >= 0)) {
                        levo--;
                    }
                    while (!presledki && (niz.charAt(desno) == ' ' && desno < niz.length())) {
                        desno++;
                    }
                }
                if (niz.charAt(levo) != niz.charAt(desno)) {
                    break;
                }
                String beseda = niz.substring(levo, desno + 1);
                if (beseda.length() >= palindrom.length()) {
                    palindrom = beseda;
                }
                levo--;
                desno++;
            }
        }
        return palindrom;
    }

    static int izracunaj(String racun) {
        String[] veriga = racun.split(" ");
        // Sklad nam dovoli vzemanje in dodajanje iz/na vrh kupa
        // Po prvem algoritmu iz https://en.wikipedia.org/wiki/Reverse_Polish_notation#Postfix_evaluation_algorithm
        java.util.Stack<Integer> sklad = new java.util.Stack<Integer>();
        for (String op : veriga) {
            int op1, op2;
            switch (op) {
                case "+":
                    op1 = sklad.pop();
                    op2 = sklad.pop();
                    sklad.push(op2 + op1);
                    break;
                case "-":
                    op1 = sklad.pop();
                    op2 = sklad.pop();
                    sklad.push(op2 - op1);
                    break;
                case "*":
                    op1 = sklad.pop();
                    op2 = sklad.pop();
                    sklad.push(op2 * op1);
                    break;
                case "/":
                    op1 = sklad.pop();
                    op2 = sklad.pop();
                    sklad.push(op2 / op1);
                    break;
                default:
                    sklad.push(Integer.parseInt(op));
                    break;
            }
        }
        return sklad.pop();
    }

    static int bsdChecksum(String imeDatoteke) {
        try {
            java.io.FileInputStream fs = new java.io.FileInputStream(new java.io.File(imeDatoteke));
            int checksum = 0;
            while (fs.available() > 0) {
                int znak = fs.read();
                checksum = (checksum >> 1) + ((checksum & 0x1) << 15);
                checksum = (checksum + znak) & 0xFFFF;
            }
            fs.close();
            return checksum;
        } catch (java.io.IOException e) {
            return 0;
        }
    }

    static void preveriInNarisi(int[] kraljice) {
        boolean seNapadajo = false;
        for (int i = 0; i < kraljice.length; i++) {
            for (int j = 0; j < kraljice.length; j++) {
                if (!seNapadajo && i != j) {
                    // Preverjanje diagonal
                    if (Math.abs(kraljice[i] - kraljice[j]) == Math.abs(i - j)) {
                        seNapadajo = true;
                    }
                }
                if (kraljice[j] == i) {
                    System.out.print("K ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        if (seNapadajo) {
            System.out.println("Kraljice se napadajo");
        } else {
            System.out.println("Kraljice se ne napadajo");
        }
    }

    static int steviloPostavitev(int n) {
        int p = 0;
        java.util.List<Integer> permutacije = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            permutacije.add(i);
        }
        do {
            if (!seNapadajo(permutacije)) {
                p++;
            }
        } while (novaPermutacija(permutacije));
        return p;
    }

    static boolean novaPermutacija(java.util.List<Integer> a) {
        int i = a.size() - 2;
        while (i >= 0 && a.get(i) >= a.get(i + 1)) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = a.size() - 1;
        while (a.get(i) >= a.get(j)) {
            j--;
        }

        java.util.Collections.swap(a, i, j);
        java.util.Collections.reverse(a.subList(i + 1, a.size()));
        return true;
    }

    static boolean seNapadajo(java.util.List<Integer> kraljice) {
        for (int i = 0; i < kraljice.size(); i++) {
            for (int j = 0; j < kraljice.size(); j++) {
                if (i != j) {
                    // Preverjanje diagonal
                    if (Math.abs(kraljice.get(i) - kraljice.get(j)) == Math.abs(i - j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*static int steviloPostavitev(int n) {
        boolean[][] postavitve = new boolean[n][n];
        if (najdiResitev(postavitve, 0, n)) {
            for (int i = 0; i < postavitve.length; i++) {
                for (int j = 0; j < postavitve[i].length; j++) {
                    if (postavitve[i][j]) {
                        System.out.print("K ");
                    } else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }
        }
        return 0;
    }

    static boolean najdiResitev(boolean[][] postavitve, int x, int n) {
        for (int y = 0; y < n; y++) {
            if (dovoli(postavitve, x, y, n)) {
                postavitve[x][y] = true;
                if (x == n - 1 || najdiResitev(postavitve, x + 1, n)) {
                    return true;
                }
                postavitve[x][y] = false;
            }
        }
        return false;
    }

    static boolean dovoli(boolean[][] postavitve, int x, int y, int n) {
        for (int i = 0; i <= x; i++) {
            if (postavitve[i][y] || (i <= y && postavitve[x - i][y - i]) || (y + i < n && postavitve[x - i][y + i])) {
                return false;
            }
        }
        return true;
    }

    static int steviloPostavitev2(int n) {
        boolean[][] postavitve = new boolean[n][n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //System.out.println(jeResljivo(postavitve, i, j));
                if (!jeResljivo(postavitve, i, j)) {
                    postavitve[i][j] = true;
                    k++;
                }
            }
        }
        return k;
    }

    static boolean jeResljivo(boolean[][] postavitve, int x, int y) {
        for (int i = 0; i < postavitve.length; i++) {
            for (int j = 0; j < postavitve[i].length; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (postavitve[i][j] && (i == x || j == y)) {
                    return true;
                }
                if (postavitve[i][j] && (Math.abs(i - x) == Math.abs(j - y))) {
                    return true;
                }
            }
        }
        return false;
    }*/

    static void preberiInIzpisi(String oznaka) {
        try {
            java.util.Scanner scanner = new java.util.Scanner(new java.io.File(oznaka + "-prijave.txt"));
            java.util.TreeMap<String, String[]> podatki = new java.util.TreeMap<>();
            while (scanner.hasNextLine()) {
                String[] vrstica = scanner.nextLine().split(":");
                if (vrstica.length == 2) {
                    podatki.put(vrstica[0], new String[] { vrstica[1], "VP" });
                }
            }
            scanner.close();
            for (int i = 1; i <= 4; i++) {
                scanner = new java.util.Scanner(new java.io.File(oznaka + "-n" + i + ".txt"));
                while (scanner.hasNextLine()) {
                    String[] vrstica = scanner.nextLine().split(":");
                    if (podatki.containsKey(vrstica[0])) {
                        String[] podatek = podatki.get(vrstica[0]);
                        if (podatek[1].equals("VP")) {
                            podatek[1] = "0";
                        }
                        podatek[1] = String.valueOf(Integer.parseInt(podatek[1]) + Integer.parseInt(vrstica[1]));
                        podatki.replace(vrstica[0], podatek);
                    }
                }
            }
            scanner.close();
            java.util.SortedSet<java.util.Map.Entry<String, String[]>> sortiraniPodatki = new java.util.TreeSet<>(
                    (e1, e2) -> {
                        int rezultat = e1.getValue()[0].compareTo(e2.getValue()[0]);
                        return rezultat != 0 ? rezultat : 1;
                    }
            );
            sortiraniPodatki.addAll(podatki.entrySet());
            for (java.util.Map.Entry<String, String[]> entry : sortiraniPodatki) {
                String id = entry.getKey();
                String[] podatek = entry.getValue();
                System.out.println(id + ":" + podatek[0] + ":" + podatek[1]);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

class Tocka {
    int x, y;

    Tocka(int x, int y) {
        this.x = x;
        this.y = y;
    }

    double razdalja(Tocka tocka) {
        return Math.sqrt(Math.pow(tocka.x - this.x, 2) + Math.pow(tocka.y - this.y, 2));
    }

    public String toString() {
        return String.format("Tocka (%d, %d)", this.x, this.y);
    }

    static Tocka[] preberiTocke(String imeDatoteke) {
        ArrayList<Tocka> tocke = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(imeDatoteke));
            while (scanner.hasNextLine()) {
                String[] tocka = scanner.nextLine().split(" ");
                if (tocka.length == 2) {
                    tocke.add(new Tocka(Integer.parseInt(tocka[0]), Integer.parseInt(tocka[1])));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tocke.toArray(new Tocka[0]);
    }

    static String tabelaToString(Tocka[] tocke) {
        if (tocke.length == 0) {
            return "[]";
        }
        String tabela = "[";
        for (Tocka tocka : tocke) {
            tabela += String.format("(%d,%d), ", tocka.x, tocka.y);
        }
        tabela = tabela.substring(0, tabela.length() - 2);
        tabela += "]";
        return tabela;
    }

    static void najblizji(Tocka[] t1, Tocka[] t2) {
        if (t1.length == 0) {
            System.out.println("Prva tabela ne vsebuje točk");
            return;
        } else if (t2.length == 0) {
            System.out.println("Druga tabela ne vsebuje točk");
            return;
        }

        Tocka[] najblizjiTocki = new Tocka[2];
        double razdalja = 0;
        for (Tocka tocka1 : t1) {
            for (Tocka tocka2 : t2) {
                double d = tocka1.razdalja(tocka2);
                if (d < razdalja || najblizjiTocki[0] == null) {
                    najblizjiTocki[0] = tocka1;
                    najblizjiTocki[1] = tocka2;
                    razdalja = d;
                }
            }
        }
        System.out.printf("Najbližji točki sta %s in %s, razdalja med njima je %.2f", najblizjiTocki[0], najblizjiTocki[1], razdalja);
    }
}

class Matrika {
    int[][] matrika;

    Matrika(int[][] matrika) {
        this.matrika = matrika;
    }

    Matrika zmnozi(Matrika b) {
        // Ne vem, če je vse prav (stopnje); tako in tako so matrike "kvadratne"
        int[][] produkt = new int[this.matrika.length][b.matrika.length];
        for (int i = 0; i < this.matrika.length; i++) {
            for (int j = 0; j < b.matrika.length; j++) {
                for (int k = 0; k < this.matrika[0].length; k++) {
                    produkt[i][j] += this.matrika[i][k] * b.matrika[k][j];
                }
            }
        }
        return new Matrika(produkt);
    }

    public void izpisi() {
        for (int i = 0; i < this.matrika.length; i++) {
            for (int j = 0; j < this.matrika[i].length; j++) {
                System.out.printf(" %2d", this.matrika[i][j]);
            }
            System.out.println();
        }
    }

    static Matrika preberiMartiko(String imeDatoteke) {
        try {
            int i = 0;
            Scanner scanner = new Scanner(new File(imeDatoteke));
            int velikost = Integer.parseInt(scanner.nextLine());
            int[][] surovaMatrika = new int[velikost][velikost];
            while (scanner.hasNextLine()) {
                String[] vrstica = scanner.nextLine().split(" ");
                for (int j = 0; j < vrstica.length; j++) {
                    surovaMatrika[i][j] = Integer.parseInt(vrstica[j]);
                }
                i++;
            }
            scanner.close();
            return new Matrika(surovaMatrika);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class Kompleksno {
    private String ime;
    private double re;
    private double im;

    Kompleksno(String ime, double re, double im) {
        this.ime = ime;
        this.re = re;
        this.im = im;
    }

    Kompleksno(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public String toString() {
        return String.format("%s = (%.3f + %.3f*i)", this.ime, this.re, this.im);
    }

    double velikost() {
        return Math.sqrt(this.re * this.re + this.im * this.im);
    }

    void pristej(Kompleksno b) {
        this.re += b.re;
        this.im += b.im;
    }

    void pomnozi(Kompleksno b) {
        double oRe = this.re;
        this.re = this.re * b.re - this.im * b.im;
        this.im = oRe * b.im + this.im * b.re;
    }
}
