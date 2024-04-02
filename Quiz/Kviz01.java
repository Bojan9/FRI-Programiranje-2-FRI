public class Kviz01 {

    public static void main(String[] args) {
        java();
        kalkulator(42, 13);
        kalkulator(2, 0);
        krog(7.5);
        krog(-177013);
        obsegKroga(7.5, 3);
        obsegKroga(-177013, 3);
        System.out.println(pretvoriSekunde(65));
        System.out.println(pretvoriSekunde(49330));
        System.out.println(pretvoriSekunde(-49330));
        javaJavaJava(3);
        javaJavaJava(-1);
        System.out.println(modulo(19, 7));
        System.out.println(modulo(20, 2));
        System.out.println(jePrastevilo(13));
        System.out.println(jePrastevilo(42));
        System.out.println(jePrastevilo(-13));
        izrisiZastavo(1);
        izrisiZastavo(2);
        izrisiZastavo(3);
        vDesetisko(120);
        vDesetisko(505);
        vDesetisko(129);
        System.out.println(pretvoriVDesetisko("101010", 2));
        System.out.println(pretvoriVDesetisko("FF", 16));
        System.out.println(pretvoriVDesetisko("101021010", 2));
        System.out.println(vsotaPrvih(100));
        pitagoroviTrojcki(25);
        narisiDrevo(7);
        System.out.println(izracunajRazliko("08:23:10", "10:10:05"));
    }

    private static void java() {
        System.out.println(
                "   J    a   v     v  a\n" +
                "   J   a a   v   v  a a\n" +
                "J  J  aaaaa   V V  aaaaa\n" +
                " JJ  a     a   V  a     a"
        );
    }

    private static void kalkulator(int a, int b) {
        if (b != 0) {
            System.out.printf("%d + %d = %d\n", a, b, a + b);
            System.out.printf("%d - %d = %d\n", a, b, a - b);
            System.out.printf("%d x %d = %d\n", a, b, a * b);
            System.out.printf("%d / %d = %d\n", a, b, a / b);
            System.out.printf("%d %% %d = %d\n", a, b, a % b);
        } else {
            System.out.println("Napaka: deljenje z 0");
        }
    }

    private static void krog(double r) {
        if (r >= 0) {
            System.out.println("Obseg = " + 2 * Math.PI * r);
            System.out.println("Ploscina = " + Math.PI * r * r);
        } else {
            System.out.println("Napaka: negativen polmer");
        }
    }

    private static void obsegKroga(double r, int d) {
        if (r >= 0 && d >= 0) {
            System.out.printf("Obseg kroga s polmerom r=%.2f je %." + d + "f\n", r, 2 * Math.PI * r);
            System.out.printf("Ploscina kroga s polmerom r=%.2f je %." + d + "f\n", r, Math.PI * r * r);
        } else if (r < 0) {
            System.out.println("Napaka: negativen polmer");
        } else if (d < 0) {
            System.out.println("Napaka: negativen d");
        }
    }

    private static String pretvoriSekunde(int sekunde) {
        if (sekunde < 0) {
            return "Število sekund ne more biti negativno";
        }
        int h = sekunde / 60 / 60 % 60;
        int m = sekunde / 60 % 60;
        int s = sekunde % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    private static void javaJavaJava(int n) {
        if (n < 0) {
            System.out.println("Napaka: negativen n");
            return;
        }
        for (int i = 0; i < n; i++) {
            System.out.print("     J    a   v     v  a   ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("     J   a a   v   v  a a  ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("  J  J  aaaaa   V V  aaaaa ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print("   JJ  a     a   V  a     a");
        }
    }

    private static int modulo(int deljenec, int delitelj) {
        if (delitelj == 0) {
            return -1;
        }
        while (deljenec >= delitelj) {
            deljenec -= delitelj;
        }
        return deljenec;
    }

    private static boolean jePrastevilo(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static void izrisiZastavo(int n) {
        for (int i = 0; i < n * 5; i++) {
            if (i < n * 3) {
                for (int j = 0; j < n * 4 - 1; j++) {
                    if (j % 2 == i % 2) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.print(" ");
                for (int j = 0; j < n * 12 - (n - 1); j++) {
                    System.out.print("=");
                }
            } else {
                for (int j = 0; j < n * 16 - (n - 1); j++) {
                    System.out.print("=");
                }
            }
            System.out.println();
        }
    }

    private static void vDesetisko(int n) {
        String osmisko = String.valueOf(n);
        int desetisko = 0;
        for (int i = 0; i < osmisko.length(); i++) {
            int stevka = Character.getNumericValue(osmisko.charAt(osmisko.length() - i - 1));
            if (stevka >= 8) {
                System.out.printf("Število %d ni število v osmiškem sistemu (števka %d)", n, stevka);
                return;
            }
            desetisko += stevka * Math.pow(8, i);
        }
        System.out.printf("%d(8) = %d(10)", n, desetisko);
    }

    private static String pretvoriVDesetisko(String n, int b) {
        int desetisko = 0;
        for (int i = 0; i < n.length(); i++) {
            char c = n.charAt(n.length() - i - 1);
            int stevka = Character.getNumericValue(c);
            if (stevka >= b) {
                return String.format("Napaka pri pretvorbi sistema - števka %c", c);
            }
            desetisko += stevka * Math.pow(b, i);
        }
        return String.format("%s(%d)=%d(10)", n, b, desetisko);
    }

    private static int vsotaPrvih(int n) {
        int vsota = 0;
        int dobljenih = 0;
        for (int i = 2;;i++) {
            if (jePrastevilo(i)) {
                vsota += i;
                dobljenih++;
            }
            if (dobljenih >= 100) {
                break;
            }
        }
        return vsota;
    }

    private static void pitagoroviTrojcki(int x) {
        for (int a = 1; a <= x; a++) {
            for (int b = 1; b <= x; b++) {
                for (int c = 1; c <= x; c++) {
                    if (a <= b && b <= c && c <= x) {
                        if (a * a + b * b == c * c) {
                            System.out.printf("%d %d %d\n", a, b, c);
                        }
                    }
                }
            }
        }
    }

    private static void narisiDrevo(int n) {
        switch (n) {
            case 0:
                System.out.println(" . ");
                break;
            case 1:
                System.out.println(" | ");
                break;
            case 2:
                System.out.println(" | ");
                System.out.println(" | ");
                break;
            default:
                if (n % 2 == 1) {
                    System.out.println(" * ");
                    n--;
                }
                for (int i = 1; i <= n - 2; i++) {
                    System.out.print("* ");
                    if (i % 2 == 0) {
                        System.out.println();
                    }
                }
                System.out.println(" | ");
                System.out.println(" | ");
        }
    }

    private static String izracunajRazliko(String prviCas, String drugiCas) {
        String[] cas1 = prviCas.split(":");
        String[] cas2 = drugiCas.split(":");
        int sekundePrviCas = (Integer.parseInt(cas1[0]) * 60 * 60 + Integer.parseInt(cas1[1]) * 60 + Integer.parseInt(cas1[2]));
        int sekundeDrugiCas = (Integer.parseInt(cas2[0]) * 60 * 60 + Integer.parseInt(cas2[1]) * 60 + Integer.parseInt(cas2[2]));

        int sekundeRazlika = Math.abs(sekundePrviCas - sekundeDrugiCas);
        int h = sekundeRazlika / 60 / 60 % 60;
        int m = sekundeRazlika / 60 % 60;
        int s = sekundeRazlika % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
