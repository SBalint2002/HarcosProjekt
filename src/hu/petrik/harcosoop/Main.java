package hu.petrik.harcosoop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
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
        harom();
        fight();
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

    //Hármas feladat

    public static void harom() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nAdjon meg a másik harcos nevét és Státusz sablonját");
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
    }

    public static void fight(){
        System.out.print("Mit szeretne csinálni?\n\ta, Megküzdeni egy harcossal\n\tb, Gyógyulni\n\tc, Kilépni\nVálasza: ");
        Scanner sc = new Scanner(System.in);
        String valasz = sc.next();
        while(valasz != "c"){
            if (valasz != "a" && valasz != "b"){             //NEM JÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓÓ
                while(valasz.equals("a") || valasz.equals("b") || valasz.equals("c")){
                    System.out.print("a, b vagy c válasz fogadható el!\nPróbálja meg újra: ");
                    valasz = sc.next();
                }
            }
            if (valasz.equals("a")){
                System.out.print("\nHanyadik harcossal szeretne megküzdeni(sorszám): ");
                int sorszam = sc.nextInt();
            }else if (valasz.equals("b")){
                System.out.println("heal");
            }
            valasz = sc.next();
        }
    }
}
