import java.io.*;
import java.util.*;

public class Kviz04 {

    public static void main(String[] args) throws Exception {
        poisciInIzpisiBarve("assets/style.css");
        statistikaStavkov("assets/besedilo1.txt");
        preberiRacunInIzpisi("assets/racun.txt");
        izpisi("assets/skrito.dat");
        preveri("05 / 350 951", "assets/skrito.dat");
        System.out.println(Arrays.toString(getVrstica(20)));
        izpisiBesedilo("assets/besedilo.txt", 20, 30);
        izracunaj("assets/test1.clc");
        histogram("assets/ikona.p2b");
        ArrayListPlus alp1 = new ArrayListPlus();
        alp1.set(3, "3");
        alp1.set(4, "4");
        alp1.set(1, "1");
        System.out.println(alp1);
    }

    static void dvojnaNagrada(String datotekaIgralke, String datotekaIralci) {
        java.util.Scanner scanner;
        try {
            java.util.ArrayList<String[]> igralke = new java.util.ArrayList<>();
            java.util.ArrayList<String[]> igralci = new java.util.ArrayList<>();

            scanner = new java.util.Scanner(new java.io.File(datotekaIgralke));
            while (scanner.hasNextLine()) {
                String[] vrstica = scanner.nextLine().split(",");
                for (int i = 0; i < vrstica.length; i++) {
                    vrstica[i] = vrstica[i].trim();
                }
                igralke.add(vrstica);
            }

            scanner = new java.util.Scanner(new java.io.File(datotekaIralci));
            while (scanner.hasNextLine()) {
                String[] vrstica = scanner.nextLine().split(",");
                for (int i = 0; i < vrstica.length; i++) {
                    vrstica[i] = vrstica[i].trim();
                }
                igralci.add(vrstica);
            }
            scanner.close();

            java.util.ArrayList<String> sortiraneNagrade = new java.util.ArrayList<>();

            for (String[] nagrade1 : igralke) {
                for (String[] nagrade2 : igralci) {
                    if (nagrade1[1].equals(nagrade2[1]) && nagrade1[4].equals(nagrade2[4])) {
                        sortiraneNagrade.add(String.format("Film: %s, Leto: %s, Igralka: %s, Igralec: %s",
                                nagrade1[4], nagrade1[1], nagrade1[3], nagrade2[3]));
                    }
                }
            }

            java.util.Collections.sort(sortiraneNagrade);

            for (String nagrada : sortiraneNagrade) {
                System.out.println(nagrada);
            }
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void poisciInIzpisiBarve(String imeDatoteke) {
        try {
            java.util.Scanner scanner = new java.util.Scanner(new java.io.File(imeDatoteke));
            while (scanner.hasNext()) {
                String beseda = scanner.next();
                if (beseda.equalsIgnoreCase("color:")) {
                    String barva = scanner.next().replace(";", "");
                    if (!barva.matches("^#([A-Fa-f0-9]{6})$")) {
                        continue;
                    }
                    // RGB
                    int r = Integer.parseInt(barva.substring(1, 3), 16);
                    int g = Integer.parseInt(barva.substring(3, 5), 16);
                    int b = Integer.parseInt(barva.substring(5, 7), 16);

                    // HSL
                    float R = r / 255f;
                    float G = g / 255f;
                    float B = b / 255f;
                    float M = Math.max(R, Math.max(G, B));
                    float m = Math.min(R, Math.min(G, B));
                    float C = M - m;
                    // Hue
                    float h = 0;
                    if (C == 0) {
                        h = 0;
                    } else if (M == R) {
                        h = (G - B) / C;
                    } else if (M == G) {
                        h = (B - R) / C + 2f;
                    } else if (M == B) {
                        h = (R - G) / C + 4f;
                    }
                    h = 60f * h;
                    if (h < 0) {
                        h += 360f;
                    }
                    // Luminance
                    float l = (M + m) * 0.5f;
                    // Saturation
                    float s = 0;
                    if (l == 1) {
                        s = 0;
                    } else {
                        s = C / (1f - Math.abs(2f * l - 1f));
                    }
                    System.out.printf("%s -> rgb(%d, %d, %d) -> hsl(%d, %d, %d)",
                            barva, r, g, b, Math.round(h), Math.round(s * 100f), Math.round(l * 100f));
                    System.out.println();
                }
            }
            scanner.close();
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void statistikaStavkov(String imeDatoteke) throws IzjemaManjkajocegaLocila {
        try {
            java.util.Scanner scanner = new java.util.Scanner(new java.io.File(imeDatoteke));
            java.util.TreeMap<Integer, Integer> frekvence = new java.util.TreeMap<>();
            int trenutnaFrekvenca = 0;
            while (scanner.hasNext()) {
                String beseda = scanner.next();
                trenutnaFrekvenca++;
                if (beseda.contains(".") || beseda.contains("!") || beseda.contains("?")) {
                    if (frekvence.containsKey(trenutnaFrekvenca)) {
                        frekvence.replace(trenutnaFrekvenca, frekvence.get(trenutnaFrekvenca) + 1);
                    } else {
                        frekvence.put(trenutnaFrekvenca, 1);
                    }
                    trenutnaFrekvenca = 0;
                } else if (!scanner.hasNext()) {
                    throw new IzjemaManjkajocegaLocila();
                }
            }
            for (java.util.Map.Entry<Integer, Integer> podatki : frekvence.entrySet()) {
                System.out.printf("Stavki dolzine %d se pojavijo: %dx.", podatki.getKey(), podatki.getValue());
                System.out.println();
            }
            scanner.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Napaka pri branju datoteke.");
        }
    }

    static void preberiRacunInIzpisi(String imeDatoteke) {
        try {
            java.util.Scanner scanner = new java.util.Scanner(new java.io.File(imeDatoteke));
            double skupajBrezDDV = 0;
            double skupajDDV = 0;
            while (scanner.hasNextLine()) {
                String[] vrstica = scanner.nextLine().split("\t");
                if (vrstica.length == 3) {
                    double DDV = Double.parseDouble(vrstica[1].replace(",", "."));
                    double znesek = Double.parseDouble(vrstica[2].replace(",", "."));
                    skupajDDV += DDV;
                    skupajBrezDDV += znesek - DDV;
                }
            }
            scanner.close();
            System.out.printf("Skupaj brez DDV: %6.2f", skupajBrezDDV);
            System.out.println();
            System.out.printf("DDV:             %6.2f", skupajDDV);
            System.out.println();
            System.out.printf("ZNESEK SKUPAJ:   %6.2f", skupajBrezDDV + skupajDDV);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Napaka pri branju datoteke!");
        }
    }

    static void izpisi(String imeDatoteke) {
        System.out.println("V datoteki " + imeDatoteke + " so naslednje številke:");
        try {
            java.io.FileInputStream fs = new java.io.FileInputStream(new java.io.File(imeDatoteke));
            int i = 1;
            StringBuilder trenutnaStevilka = new StringBuilder();
            while (fs.available() > 0) {
                int znak = fs.read();
                trenutnaStevilka.append(String.format("%8s", Integer.toBinaryString(znak)).replace(" ", "0"));
                if (i % 3 == 0) {
                    String stevilka = String.valueOf(Integer.parseInt(trenutnaStevilka.toString(), 2));
                    System.out.printf("0%s / %s %s",
                            stevilka.charAt(0), stevilka.substring(1, 4), stevilka.substring(4, 7));
                    System.out.println();
                    trenutnaStevilka = new StringBuilder();
                }
                i++;
            }
            fs.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static void preveri(String stevilka, String imeDatoteke) {
        try {
            java.io.FileInputStream fs = new java.io.FileInputStream(new java.io.File(imeDatoteke));
            int i = 1;
            StringBuilder trenutnaStevilka = new StringBuilder();
            while (fs.available() > 0) {
                int znak = fs.read();
                trenutnaStevilka.append(String.format("%8s", Integer.toBinaryString(znak)).replace(" ", "0"));
                if (i % 3 == 0) {
                    String zeljenaStevilka = String.valueOf(Integer.parseInt(trenutnaStevilka.toString(), 2));
                    String formatiranaStevilka = String.format("0%s / %s %s",
                            zeljenaStevilka.charAt(0), zeljenaStevilka.substring(1, 4), zeljenaStevilka.substring(4, 7));
                    if (stevilka.equalsIgnoreCase(formatiranaStevilka)) {
                        System.out.println("Številka " + stevilka + " je v datoteki");
                        fs.close();
                        return;
                    }
                    trenutnaStevilka = new StringBuilder();
                }
                i++;
            }
            System.out.println("Številke " + stevilka + " ni v datoteki");
            fs.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static int[] getVrstica(int n) {
        java.util.ArrayList<int[]> vrstice = new java.util.ArrayList<>();
        vrstice.add(new int[] { 1 });
        for (int i = 2; i <= n; i++) {
            int zacetnaStevilka = i % 10;
            int[] vrstica = new int[i];
            vrstica[0] = zacetnaStevilka;
            for (int j = 1; j < i; j++) {
                vrstica[j] = (vrstica[j - 1] + vrstice.get(i - 2)[j - 1]) % 10;
            }
            vrstice.add(vrstica);
        }
        return vrstice.get(n - 1);
    }

    static void izpisiBesedilo(String imeDatoteke, int n, int s) {
        try {
            java.util.Scanner scanner = new java.util.Scanner(new java.io.File(imeDatoteke));
            StringBuilder stavek = new StringBuilder();
            while (scanner.hasNext()) {
                String beseda = scanner.next();
                if (stavek.length() + beseda.length() <= 20) {
                    stavek.append(beseda).append(" ");
                } else {
                    int d = s - stavek.length();
                    for (int i = 0; i <= (d % 2 == 1 ? d / 2 : d / 2 - 1); i++) {
                        System.out.print(".");
                    }
                    System.out.print(stavek.toString().trim());
                    for (int i = 0; i <= d / 2; i++) {
                        System.out.print(".");
                    }
                    System.out.println();
                    stavek = new StringBuilder(beseda).append(" ");
                }
            }
            int d = s - stavek.length();
            for (int i = 0; i <= (d % 2 == 1 ? d / 2 : d / 2 - 1); i++) {
                System.out.print(".");
            }
            System.out.print(stavek.toString().trim());
            for (int i = 0; i <= d / 2; i++) {
                System.out.print(".");
            }
            System.out.println();
            scanner.close();
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void izracunaj(String imeDatoteke) {
        try {
            java.io.DataInputStream ds = new java.io.DataInputStream(new java.io.FileInputStream(new java.io.File(imeDatoteke)));
            if (ds.readByte() == 0x43 && ds.readByte() == 0x41 && ds.readByte() == 0x4C && ds.readByte() == 0x43) {
                int steviloRacunov = ds.readInt();
                for (int i = 0; i < steviloRacunov; i++) {
                    int prviOperand = ds.readInt();
                    byte operacija = ds.readByte();
                    int drugiOperand = ds.readInt();
                    switch (operacija) {
                        case 0x2A:
                            System.out.println(prviOperand * drugiOperand);
                            break;
                        case 0x2B:
                            System.out.println(prviOperand + drugiOperand);
                            break;
                        case 0x2D:
                            System.out.println(prviOperand - drugiOperand);
                            break;
                        case 0x2F:
                            System.out.println(prviOperand / drugiOperand);
                            break;
                        default:
                            System.out.println("Napačen operator.");
                            ds.close();
                            return;
                    }
                }
            } else {
                System.out.println("Datoteka test2.clc ni v formatu CLC!");
            }
            ds.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static void histogram(String imeDatoteke) {
        try {
            java.io.DataInputStream ds = new java.io.DataInputStream(new java.io.FileInputStream(new java.io.File(imeDatoteke)));
            if (imeDatoteke.substring(imeDatoteke.lastIndexOf(".")).equals(".p2b")) {
                if (ds.readByte() == 0x50 && ds.readByte() == 0x32 && ds.readByte() == 0x42) {
                    int sirina = ds.readInt();
                    int visina = ds.readInt();
                    java.util.TreeMap<String, Integer> barve = new java.util.TreeMap<>(
                            // Presenečen kaj lahko IntelliJ naredi :)
                            // Iz new Comparator itd. pretvori v lambdo in nato v tole.
                            java.util.Comparator.comparingInt(o -> Integer.parseInt(o, 16))
                    );
                    for (int i = 0; i < sirina * visina; i++) {
                        byte r = ds.readByte();
                        byte g = ds.readByte();
                        byte b = ds.readByte();
                        String barva = Integer.toString(Integer.parseInt(String.format("%02x%02x%02x", r, g, b), 16), 16);
                        if (barve.containsKey(barva)) {
                            barve.put(barva, barve.get(barva) + 1);
                        } else {
                            barve.put(barva, 1);
                        }
                    }
                    for (java.util.Map.Entry<String, Integer> entry : barve.entrySet()) {
                        System.out.printf("%6s %d", entry.getKey(), entry.getValue());
                        System.out.println();
                    }
                } else {
                    System.out.println("Datoteka ni v formatu P2B: napaka pri podpisu slike.");
                }
            } else {
                System.out.println("Datoteka ni v formatu P2B: napaka v imenu datoteke.");
            }
            ds.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println(imeDatoteke + " (No such file or directory)");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

interface SkladInterface {
    public boolean isEmpty();   // je sklad prazen?
    public void push(Object o); // doda element na vrh sklada
    public Object pop();        // vrne element z vrha sklada
    public void reverse();      // obrne vrstni red elementov na skladu
}

class Sklad implements SkladInterface {
    java.util.Stack<Object> sklad = new java.util.Stack<>();

    @Override
    public boolean isEmpty() {
        return sklad.isEmpty();
    }

    @Override
    public void push(Object o) {
        sklad.push(o);
    }

    @Override
    public Object pop() {
        return sklad.pop();
    }

    @Override
    public void reverse() {
        sklad.sort(java.util.Collections.reverseOrder());
    }
}

class ArrayListPlus extends ArrayList<String> {

    ArrayListPlus() {
        super();
    }

    ArrayListPlus(String elements) {
        super();
        String[] elementi = elements.split(";");
        for (String podatek : elementi) {
            this.add(podatek);
        }
    }

    public String set(int index, String element) {
        if (index >= this.size()) {
            for (int i = 0; i < index; i++) {
                if (i >= this.size()) {
                    this.add("");
                } else if (this.get(i) == null) {
                    this.set(index, "");
                }
            }
            this.add(element);
            return element;
        }
        return super.set(index, element);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (String value : this) {
            s.append(String.format("%s;", value));
        }
        return s.toString().substring(0, s.length() - 1);
    }
}

class IzjemaManjkajocegaLocila extends RuntimeException {
    IzjemaManjkajocegaLocila() {
        super();
    }

    @Override
    public String getMessage() {
        return "Izjema manjkajocega locila.";
    }
}
