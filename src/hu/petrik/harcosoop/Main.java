package hu.petrik.harcosoop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.err;

public class Main {
    public static List<Harcos> harcosok = new ArrayList<>();
    public static void main(String[] args) {
        harcosok = new ArrayList<>();
        kettoa();
        bekeres();
        System.out.println(harcosok);
    }

    //Fájl beolvasás
    public static void bekeres() {
        String fajlNev = "harcosok.csv";
        try {
            beolvas(fajlNev);
        } catch (FileNotFoundException e) {
            err.printf("Hiba miatt nem található az %s fájl\n", fajlNev);
        } catch (IOException e) {
            err.println("Ismeretlen hiba történt a fájl beolvasása során");
        }
    }

    public static void beolvas(String fajlNev) throws IOException {
        FileReader fr = new FileReader(fajlNev);
        BufferedReader br = new BufferedReader(fr);
        String sor = br.readLine();
        while (sor != null && !sor.equals("")) {
            String[] tomb = sor.split(";");
            Harcos harcos = new Harcos(tomb[0], Integer.parseInt(tomb[1]));
            harcosok.add(harcos);
            sor = br.readLine();
        }
        br.close();
        fr.close();
    }

    //Feladatok -----------------------------------------------------------------

    public static void kettoa(){
        Harcos h1 = new Harcos("Kiss Miklós", 2);
        Harcos h2 = new Harcos("Ágozs", 3);
        Harcos h3 = new Harcos("Szandi", 1);
    }
}
