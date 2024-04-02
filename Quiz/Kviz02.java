import java.util.Arrays;

public class Kviz02 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(presek(new int[] {9,4,6,3}, new int[] {1,6,3,9,0})));
        System.out.println(Arrays.toString(unija(new int[] {3, 6, 9, 1, 3, 5}, new int[] {3, 4, 6, 1, 3, 8})));
        System.out.println(Arrays.toString(presek(new int[] {3, 6, 9, 1, 3, 5}, new int[] {3, 4, 6, 1, 3, 8})));
        System.out.println(Arrays.toString(range(0, 10, 2)));
        int[] t = new int[] {1, 2, 3, 4, 5, 6};
        rotiraj(t, 3);
        System.out.println(Arrays.toString(t));
        System.out.println(Arrays.toString(duplikati(new int[] {1, 5, 8, 56, 3, 9, 1, 43, 1, 2, 56, 12, 1, 3})));
        System.out.println(koren(10, 2));
        System.out.println(koren(15, 5));
        System.out.println(koren(101, 1));
        System.out.println(binarnoSestej("10", "11"));
        System.out.println(vsotaSkupnihCifer(155, 657));
        System.out.println(prevod("Dapanepas jepa lepap dapan"));
        System.out.println(prevod("Napaka"));
        System.out.println(prepleti("pomlad", "JESEN"));
        System.out.println(prepleti("december", "maj"));
        System.out.println(prepleti("POMLAD", "december"));
        vMorse("SOS");
        System.out.println(fibo(3));
        System.out.println(Arrays.toString(pascal(10)));
        izpisKoledarja(2019, 3);
    }

    private static int[] unija(int[] tabela1, int[] tabela2) {
        int[] unija = new int[tabela1.length + tabela2.length];
        int i = 0;

        for (int e : tabela1) {
            unija[i] = e;
            i++;
        }

        for (int e : tabela2) {
            unija[i] = e;
            i++;
        }

        return unija;
    }

    private static int[] presek(int[] tabela1, int[] tabela2) {
        java.util.ArrayList<Integer> presek = new java.util.ArrayList<>();
        for (int e1 : tabela1) {
            for (int e2 : tabela2) {
                if (e1 == e2 && !presek.contains(e1)) {
                    presek.add(e1);
                }
            }
        }

        int[] pPresek = new int[presek.size()];
        int i = 0;
        for (int e : presek) {
            pPresek[i] = e;
            i++;
        }

        return pPresek;
    }

    private static int[] range(int a, int b, int c) {
        java.util.ArrayList<Integer> range = new java.util.ArrayList<>();
        int x = 0;
        while (a + x * c < b) {
            range.add(a + x * c);
            x++;
        }

        int[] pRange = new int[range.size()];
        int i = 0;
        for (int e : range) {
            pRange[i] = e;
            i++;
        }

        return pRange;
    }

    private static void rotiraj(int[] tabela, int k) {
        for (int i = 0; i < k; i++) {
            int t = tabela[0];
            for (int j = 0; j < tabela.length - 1; j++) {
                tabela[j] = tabela[j + 1];
            }
            tabela[tabela.length - 1] = t;
        }
    }

    /*
    private static int[] duplikati(int [] tabela) {
        int novaDolzina = tabela.length;

        for (int i = 1; i < tabela.length; i++) {
            for (int j = 0; j < i; j++) {
                if (tabela[i] == tabela[j]) {
                    novaDolzina--;
                    break;
                }
            }
        }

        int[] brezDuplikatov = new int[novaDolzina];
        int k = 1;
        brezDuplikatov[0] = tabela[0];

        for (int i = 1; i < tabela.length; i++) {
            boolean duplikat = false;
            for (int j = 0; j < i; j++) {
                if (tabela[i] == tabela[j]) {
                    duplikat = true;
                    break;
                }
            }

            if (!duplikat) {
                brezDuplikatov[k] = tabela[i];
                k++;
            }
        }

        return brezDuplikatov;
    }
    */

    private static int[] duplikati(int[] tabela) {
        java.util.ArrayList<Integer> duplikati = new java.util.ArrayList<>();

        for (int e : tabela) {
            if (!duplikati.contains(e)) {
                duplikati.add(e);
            }
        }

        int[] pDuplikati = new int[duplikati.size()];
        int i = 0;
        for (int e : duplikati) {
            pDuplikati[i] = e;
            i++;
        }

        return pDuplikati;
    }

    private static double koren(int x, int d) {
        double[] decimalke = new double[d + 1];
        decimalke[0] = 1;

        for (int i = 1; i <= d; i++) {
            int j = 0;
            while ((decimalke[i] + 1 / Math.pow(10, i)) * (decimalke[i] + 1 / Math.pow(10, i)) <= x) {
                decimalke[i] = decimalke[i - 1] + j / Math.pow(10, i);
                j++;
            }
        }

        return decimalke[decimalke.length - 1];
    }

    private static String binarnoSestej(String s, String b) {
        String rezultat = "";
        int sLen = s.length();
        int bLen = b.length();
        int carry = 0;

        for (int i = 0; i < Math.max(sLen, bLen); i++) {
            int sAdd = 0;
            if (i < sLen) {
                sAdd = s.charAt(sLen - i - 1) - '0';
            }
            int bAdd = 0;
            if (i < bLen) {
                bAdd = b.charAt(bLen - i - 1) - '0';
            }
            int v = sAdd + bAdd + carry;
            carry = v / 2;
            rezultat = v % 2 + rezultat;
        }

        if (carry == 0) {
            return rezultat;
        }
        return "1" + rezultat;
    }

    private static int vsotaSkupnihCifer(int a, int b) {
        int vsota = 0;
        String aString = Integer.toString(a);
        String bString = Integer.toString(b);
        java.util.HashSet<Integer> upostevani = new java.util.HashSet<>();

        for (int i = 0; i < aString.length(); i++) {
            for (int j = 0; j < bString.length(); j++) {
                // Pridobi številke ('4' -> 4)
                int aTrenuten = Character.getNumericValue(aString.charAt(i));
                int bTrenuten = Character.getNumericValue(bString.charAt(j));
                // Če sta enaka in ni bil še vštet v vsoto
                if (aTrenuten == bTrenuten && !upostevani.contains(aTrenuten)) {
                    vsota += aTrenuten;
                    upostevani.add(aTrenuten);
                }
            }
        }

        return vsota;
    }

    private static String prevod(String niz) {
        boolean jePapajscina = true;
        for (int i = 0; i < niz.length(); i++) {
            char crka = niz.charAt(i);
            if ("aeiou".contains(Character.toString(crka).toLowerCase())) {
                if (i == niz.length() - 1) {
                    jePapajscina = false;
                    break;
                }
                String naslednjiDve = niz.substring(i + 1, i + 3);
                if (!naslednjiDve.toLowerCase().equals("pa")) {
                    jePapajscina = false;
                    break;
                }
                i += 2;
            }
        }

        if (jePapajscina) {
            for (int i = 0; i < niz.length(); i++) {
                char crka = niz.charAt(i);
                if ("aeiou".contains(Character.toString(crka).toLowerCase())) {
                    String naslednjiDve = niz.substring(i + 1, i + 3);
                    if (naslednjiDve.toLowerCase().equals("pa")) {
                        niz = niz.substring(0, i + 1) + niz.substring(i + 3);
                    }
                }
            }
        } else {
            for (int i = 0; i < niz.length(); i++) {
                char crka = niz.charAt(i);
                if ("aeiou".contains(Character.toString(crka).toLowerCase())) {
                    niz = niz.substring(0, i + 1) + "pa" + niz.substring(i + 1);
                    i += 2;
                }
            }
        }

        return niz;
    }

    private static String prepleti(String niz1, String niz2) {
        StringBuilder prepleten = new StringBuilder();

        int i = 0;
        int j = 0;
        boolean zebra = true;
        while (i < Math.max(niz1.length(), niz2.length()) || j < Math.max(niz1.length(), niz2.length())) {
            if (zebra) {
                if (i < niz1.length()) {
                    prepleten.append(niz1.charAt(i));
                } else {
                    prepleten.append(" ");
                }
                i++;
                zebra = !zebra;
            } else {
                if (j < niz2.length()) {
                    prepleten.append(niz2.charAt(j));
                } else {
                    prepleten.append(" ");
                }
                j++;
                zebra = !zebra;
            }
        }

        return prepleten.toString();
    }

    private static void odpleti(String niz) {
        String niz1 = "";
        String niz2 = "";

        for (int i = 0; i < niz.length(); i++) {
            if (i % 2 == 0) {
                niz1 += niz.charAt(i);
            } else {
                niz2 += niz.charAt(i);
            }
        }

        System.out.println(niz1);
        System.out.println(niz2);
    }

    private static String vMorse(String niz) {
        java.util.HashMap<Character, String> abeceda = new java.util.HashMap<>();
        abeceda.put('A', ".-");
        abeceda.put('B', "-...");
        abeceda.put('C', "-.-.");
        abeceda.put('D', "-..");
        abeceda.put('E', ".");
        abeceda.put('F', "..-.");
        abeceda.put('G', "--.");
        abeceda.put('H', "....");
        abeceda.put('I', "..");
        abeceda.put('J', ".---");
        abeceda.put('K', "-.-");
        abeceda.put('L', ".-..");
        abeceda.put('M', "--");
        abeceda.put('N', "-.");
        abeceda.put('O', "---");
        abeceda.put('P', ".--.");
        abeceda.put('Q', "--.-");
        abeceda.put('R', ".-.");
        abeceda.put('S', "...");
        abeceda.put('Š', "----");
        abeceda.put('T', "-");
        abeceda.put('U', "..-");
        abeceda.put('V', "...-");
        abeceda.put('W', ".--");
        abeceda.put('X', "-..-");
        abeceda.put('Y', "-.--");
        abeceda.put('Z', "--..");
        abeceda.put('1', ".----");
        abeceda.put('2', "..---");
        abeceda.put('3', "...--");
        abeceda.put('4', "....-");
        abeceda.put('5', ".....");
        abeceda.put('6', "-....");
        abeceda.put('7', "--...");
        abeceda.put('8', "---..");
        abeceda.put('9', "----.");
        abeceda.put('0', "-----");
        abeceda.put('.', ".-.-.-");
        abeceda.put('-', "-....-");
        abeceda.put('?', "..--..");
        abeceda.put(',', "--..--");
        abeceda.put('\'', ".----.");
        abeceda.put('"', ".--..--.");
        abeceda.put(':', "---...");
        abeceda.put('(', "-.--.-");
        abeceda.put('@', ".--.-.");
        abeceda.put(' ', "");

        StringBuilder pretvorba = new StringBuilder();

        for (int i = 0; i < niz.length(); i++) {
            char crka = Character.toUpperCase(niz.charAt(i));
            pretvorba.append(abeceda.get(crka));
            pretvorba.append(" ");
        }

        return pretvorba.toString();
    }

    private static int fibo(int n) {
        int[][] tabela = new int[n][n];
        int vsota = 0;
        int rezultat = 0, f1 = 1, f2 = 1;
        int k = 0, v = 0;

        for (int i = 1; i <= Math.pow(n, 2); i++) {
            if (i < 3) {
                rezultat = 1;
            } else {
                rezultat = f1 + rezultat;
                f1 = f2;
                f2 = rezultat;
            }
            tabela[v][k] = rezultat;

            k++;

            if (i % n == 0) {
                v++;
                k = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || n - j == i + 1) {
                    vsota += (i == j && n - j == i + 1) ? tabela[i][j] * 2 : tabela[i][j];
                }
            }
        }

        return vsota;
    }

    private static int[] pascal(int n) {
        int[] prejsni = new int[] { 1 };
        for (int i = 2; i <= n; i++) {
            int[] novi = new int[i];
            novi[0] = 1;
            novi[novi.length - 1] = 1;
            for (int j = 1; j < novi.length - 1; j++) {
                novi[j] = prejsni[j - 1] + prejsni[j];
            }
            prejsni = novi;
        }
        return prejsni;
    }

    private static void izpisKoledarja(int leto, int mesec) {
        java.time.YearMonth yearMonth = java.time.YearMonth.of(leto, mesec);
        int steviloDni = yearMonth.lengthOfMonth();
        int prviDan = java.time.LocalDate.of(leto, mesec, 1).getDayOfWeek().getValue();

        System.out.println("PO  TO  SR  ČE  PE  SO  NE");
        String zamik = "";
        for (int i = 0; i < prviDan - 1; i++) {
            zamik += "    ";
        }
        System.out.print(zamik);

        int dan = 1;
        for (int i = 0; dan <= steviloDni; i++) {
            System.out.printf("%2d  ", dan);
            if ((prviDan + dan - 1) % 7 == 0) {
                System.out.println();
            }
            dan++;
        }
    }
}
