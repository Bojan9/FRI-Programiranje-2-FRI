package Vaje.Teden1;

public class Zanke {
    public static void pravokotnikStevilVrstice(int sirina, int visina) {
        // napišemo visina vrstic
        for (int vrstica = 0; vrstica < visina; vrstica++) {
            // v vsaki vrstici napišemo sirina številk,
            // vse izpisane številke v vrstici so enake zaporedni številki vrstice (enicam tega števila)
            for (int stolpec = 0; stolpec < sirina; stolpec++) {
                System.out.print((vrstica + 1) % 10);
            }
            // zaključimo vrstico
            System.out.println();
        }
    }

    public static void pravokotnikStevilStolpci(int sirina, int visina) {
        // napišemo visina vrstic
        for (int vrstica = 0; vrstica < visina; vrstica++) {
            // v vsaki vrstici napišemo sirina številk, tj. zaporedne številke od 1 do širine pravokotnika (števila stolpcev)
            for (int stolpec = 0; stolpec < sirina; stolpec++) {
                // izpisana številka je enaka zaporedni številki stolpca (enicam tega števila)
                System.out.print((stolpec + 1) % 10);
            }
            // zaključimo vrstico
            System.out.println();
        }
    }

    public static void pravokotnik(int odmik, int sirina, int visina) {
        // narišemo visina vrstic
        for (int vrstica = 0; vrstica < visina; vrstica++) {
            // v vsaki vrstici najprej narišemo odmik presledkov
            for (int stolpec = 0; stolpec < odmik; stolpec++) {
                System.out.print(" ");
            }
            // in nato narišemo še sirina znakov X
            for (int stolpec = 0; stolpec < sirina; stolpec++) {
                System.out.print("X");
            }
            // zaključimo vrstico
            System.out.println();
        }
    }

    public static void trikotnikStevilVrstice(int visina) {
        for (int vrstica = 1; vrstica <= visina; vrstica++) {
            for (int stolpec = 0; stolpec < vrstica; stolpec++) {
                System.out.print(vrstica % 10);
            }
            System.out.println();
        }
    }

    public static void trikotnikStevilObrnjenVrstice(int visina) {
        for (int vrstica = visina; vrstica > 0; vrstica--) {
            for (int stolpec = 0; stolpec < vrstica; stolpec++) {
                System.out.print(vrstica % 10);
            }
            System.out.println();
        }
    }

    public static void trikotnikStevilStolpci(int visina) {
        for (int vrstica = 1; vrstica <= visina; vrstica++) {
            for (int stolpec = 0; stolpec < vrstica; stolpec++) {
                System.out.print((stolpec + 1) % 10);
            }
            System.out.println();
        }
    }

    public static void trikotnikStevilObrnjenStolpci(int visina) {
        for (int vrstica = visina; vrstica > 0; vrstica--) {
            for (int stolpec = 0; stolpec < vrstica; stolpec++) {
                System.out.print((stolpec + 1) % 10);
            }
            System.out.println();
        }
    }

    public static void trikotnikStevil(int visina) {
        for (int vrstica = 0; vrstica < visina; vrstica++) {
            for (int stolpec = 0; stolpec < visina - vrstica - 1; stolpec++) {
                System.out.print(" ");
            }
            for (int stolpec = 0; stolpec < 2 * vrstica + 1; stolpec++) {
                System.out.print((stolpec + 1) % 10);
            }
            System.out.println();
        }
    }

    public static void trikotnik(int odmik, int visina) {
        for (int vrstica = 0; vrstica < visina; vrstica++) {
            for (int stolpec = 0; stolpec < odmik + (visina - vrstica - 1); stolpec++) {
                System.out.print(" ");
            }
            for (int stolpec = 0; stolpec < 2 * vrstica + 1; stolpec++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void trikotnikObrnjen(int odmik, int visina) {
        for (int vrstica = visina - 1; vrstica >= 0; vrstica--) {
            for (int stolpec = 0; stolpec < odmik + (visina - vrstica - 1); stolpec++) {
                System.out.print(" ");
            }
            for (int stolpec = 0; stolpec < 2 * vrstica + 1; stolpec++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void romb(int odmik, int velikost) {
        // nariši zgornjo polovico
        for (int vrstica = 0; vrstica < velikost; vrstica++) {
            for (int stolpec = 0; stolpec < odmik + (velikost - vrstica - 1); stolpec++) {
                System.out.print(" ");
            }
            for (int stolpec = 0; stolpec < 2 * vrstica + 1; stolpec++) {
                System.out.print("*");
            }
            System.out.println();
        }
        // nariši spodnjo polovico: obrnjen trikotnik, za ena manjše velikosti
        for (int vrstica = velikost - 2; vrstica >= 0; vrstica--) {
            for (int stolpec = 0; stolpec < odmik + (velikost - vrstica - 1); stolpec++) {
                System.out.print(" ");
            }
            for (int stolpec = 0; stolpec < 2 * vrstica + 1; stolpec++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // ker imamo že napisani metodi trikotnik() in trikotnikObrnjen(),
    // lahko romb naredimo enostavneje z uporabo obeh metod
    public static void romb1(int odmik, int velikost) {
        trikotnik(odmik, velikost);
        trikotnikObrnjen(odmik + 1, velikost - 1);
    }

    public static void smreka(int velikost) {
        // krošnjo sestavlja velikost trikotnikov
        // prvi je velikosti 2, vsak naslednji je za dva večji
        for (int i = 1; i <= velikost; i++) {
            trikotnik(2 * (velikost - i), 2 * i);
        }
        // širina debla je velikost
        int sirina = velikost;
        // oziroma za ena večja, če je velikost sodo število
        if (velikost % 2 == 0) {
            sirina += 1;
        }
        pravokotnik((4 * velikost - 1 - sirina) / 2, sirina, 2 * velikost);
    }

    //
    // REŠITVE DODATNIH IZZIVOV
    //
    public static void rombA(int odmik, int velikost) {
        for (int vrstica = 0; vrstica < velikost; vrstica++) {
            for (int stolpec = 0; stolpec < odmik + velikost - vrstica - 1; stolpec++) {
                System.out.print("  ");
            }
            for (int stolpec = 0; stolpec < 2 * vrstica + 1; stolpec++) {
                System.out.print("# ");
            }
            System.out.println();
        }
        for (int vrstica = velikost - 2; vrstica >= 0; vrstica--) {
            for (int stolpec = 0; stolpec < odmik + velikost - vrstica - 1; stolpec++) {
                System.out.print("  ");
            }
            for (int stolpec = 0; stolpec < 2 * vrstica + 1; stolpec++) {
                System.out.print("# ");
            }
            System.out.println();
        }
    }

    public static void rombPrazen(int odmik, int velikost) {
        for (int vrstica = 0; vrstica < velikost; vrstica++) {
            for (int stolpec = 0; stolpec < odmik; stolpec++) {
                System.out.print("  ");
            }
            for (int stolpec = 0; stolpec < 2 * velikost - 1; stolpec++) {
                if ((stolpec > velikost - vrstica - 1) && (stolpec < velikost + vrstica - 1)) {
                    System.out.print("  ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
        for (int vrstica = velikost - 2; vrstica >= 0; vrstica--) {
            for (int stolpec = 0; stolpec < odmik; stolpec++) {
                System.out.print("  ");
            }
            for (int stolpec = 0; stolpec < 2 * velikost - 1; stolpec++) {
                if ((stolpec > velikost - vrstica - 1) && (stolpec < velikost + vrstica - 1)) {
                    System.out.print("  ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    public static void dvaPravokotnika(int odmik, int sirina, int visina, int razmik) {
        for (int i = 0; i < visina; i++) {
            for (int k = 0; k < odmik; k++) {
                System.out.print(" ");
            }
            for (int k = 0; k < sirina; k++) {
                System.out.print("X");
            }
            for (int k = 0; k < razmik; k++) {
                System.out.print(" ");
            }
            for (int k = 0; k < sirina; k++) {
                System.out.print("X");
            }
            System.out.println();
        }
    }

    public static void iks(int n) {
        for (int i = 0; i < n - 1; i++) {
            dvaPravokotnika(5 * i, 5, 3, 5 * (2 * ((n - 1) - i) - 1));
        }
        pravokotnik(5 * (n - 1), 5, 3);
        for (int i = n - 2; i >= 0; i--) {
            dvaPravokotnika(5 * i, 5, 3, 5 * (2 * ((n - 1) - i) - 1));
        }
    }

    //
    // REŠITVE UGANK: A) SNEG, B) SNEŽINKE, C) DREVO
    //

    public static void main(String[] args) {
        pravokotnikStevilVrstice(7, 3);
        System.out.println();
        pravokotnikStevilStolpci(12, 3);
        System.out.println();
        pravokotnik(5, 7, 3);
        System.out.println();
        trikotnikStevilVrstice(3);
        System.out.println();
        trikotnikStevilStolpci(3);
        System.out.println();
        trikotnikStevilObrnjenVrstice(3);
        System.out.println();
        trikotnikStevilObrnjenStolpci(3);
        System.out.println();
        trikotnikStevil(5);
        System.out.println();
        trikotnik(5, 3);
        System.out.println();
        trikotnik(1, 5);
        System.out.println();
        trikotnikObrnjen(1, 5);
        System.out.println();
        romb(2, 5);
        System.out.println();
        smreka(1);
        System.out.println();
        smreka(2);
        System.out.println();
        smreka(3);
        System.out.println();
        smreka(4);
        System.out.println();
        smreka(5);
        System.out.println();

        // dodatni izzivi
        rombA(1, 5);
        System.out.println();
        rombPrazen(3, 5);
        System.out.println();
        iks(3);
        System.out.println();
        iks(4);
        System.out.println();
        iks(5);
        System.out.println();
    }
}