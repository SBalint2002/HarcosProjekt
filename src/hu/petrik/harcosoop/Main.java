package hu.petrik.harcosoop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.err;

public class Main {
    public static List<Harcos> harcosok = new ArrayList<>();
    public static int index = 0;
    public static void main(String[] args) {
        harcosok = new ArrayList<>();
        kettoa();
        bekeres();
        //System.out.println(harcosok);
        Scanner sc = new Scanner(System.in);
        System.out.println("\nAdja meg a saját harcosának nevét és Státusz sablonját");
        System.out.print("Név: ");
        String masikNev = sc.next();
        System.out.print("Státusz sablon: ");
        int masikStatusz = sc.nextInt();
        Harcos masik = new Harcos(masikNev, masikStatusz);
        System.out.print("\nSaját harcos:");
        System.out.print(masik);
        System.out.println("\n\nTöbbi harcos:");
        for (int i = 0; i < harcosok.size(); i++) {
            System.out.println((i+1) + ". Harcos: " + harcosok.get(i));
        }

        Random r =  new Random();
        int szamlalo = 0;
        System.out.print("\nMit szeretne csinálni?\n\ta, Megküzdeni egy harcossal\n\tb, Gyógyulni\n\tc, Kilépni\nVálasza: ");
        String valasz = sc.next();
        while(!valasz.equals("c")){
            boolean jo = false;
            while(!jo){
                if (valasz.equals("a") || valasz.equals("b") || valasz.equals("c")){
                    jo = true;
                }else {
                    System.out.print("\nNem jó betű! Adjon meg egy másikat (a, b, c): ");
                    valasz = sc.next();
                }
            }
            if (valasz.equals("a")){
                System.out.print("\nHanyadik harcossal szeretne megküzdeni(sorszám): ");
                String indexSzam = sc.next();
                boolean isInt = false;
                int index = 0;
                while (!isInt){
                    if (indexSzam.equals("")){
                        System.out.print("\nAdjon meg értéket: ");
                        indexSzam = sc.next();
                    }
                    try {
                        index = Integer.parseInt(indexSzam);
                        if (!(index < 1 || index > harcosok.size())){
                            isInt = true;
                            System.out.println(index + ". Ellenfél kiválasztva!");
                            masik.megkuzd(harcosok.get(index-1));
                            System.out.println("\nHarc véget ért!");
                            System.out.print("\nSaját harcos:");
                            System.out.print(masik);
                            System.out.println();
                            for (int i = 0; i < harcosok.size(); i++) {
                                System.out.println((i+1) + ". Harcos: " + harcosok.get(i));
                            }
                        }else {
                            System.out.print("Érvényes index szám legyen!\n Adja meg a számot: ");
                            indexSzam = sc.next();
                        }
                    }catch (NumberFormatException nfe){
                        System.out.print("Index számot adjon meg: ");
                        indexSzam = sc.next();
                    }
                }

                //harc

                System.out.print("\nMi egyebet szeretne csinálni?\n\ta, Megküzdeni egy harcossal\n\tb, Gyógyulni\n\tc, Kilépni\nVálasza: ");
            }else if (valasz.equals("b")){
                System.out.println("Gyógyítás...");
                masik.gyogyul();

                System.out.print("\nMi egyebet szeretne csinálni?\n\ta, Megküzdeni egy harcossal\n\tb, Gyógyulni\n\tc, Kilépni\nVálasza: ");
            }
            valasz = sc.next();
            if (valasz.equals("c")){
                System.out.println("Kilépés...");
            }
            szamlalo++;
            if (szamlalo == 3){
                szamlalo = 0;
                masik.megkuzd(harcosok.get(r.nextInt(harcosok.size())));
                for (int i = 0; i < harcosok.size(); i++) {
                    harcosok.get(i).gyogyul();
                }
                System.out.println("\nEgymás után harmadik kör véget ért, ellenfelek gyógyulnak!\nRandom ellenfél megtámadott");
                System.out.print("\nSaját harcos:");
                System.out.print(masik);
                System.out.println();
                for (int i = 0; i < harcosok.size(); i++) {
                    System.out.println((i+1) + ". Harcos: " + harcosok.get(i));
                }
            }
        }
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

    //Kettes feladat -----------------------------------------------------------------

    public static void kettoa(){
        Harcos h1 = new Harcos("Kiss Miklós", 2);
        Harcos h2 = new Harcos("Ágozs", 3);
        Harcos h3 = new Harcos("Szandi", 1);
        harcosok.add(h1);
        harcosok.add(h2);
        harcosok.add(h3);
    }
}
