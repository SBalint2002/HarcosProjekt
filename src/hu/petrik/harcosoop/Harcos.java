package hu.petrik.harcosoop;

import java.time.LocalDateTime;

public class Harcos {
    private String nev;
    private int szint;
    private int tapasztalat;
    private int eletero;
    private int alapEletero;
    private int alapSebzes;

    public Harcos(String nev, int statuszSablon) {
        this.nev = nev;
        this.szint = 1;
        this.tapasztalat = 0;
        if (statuszSablon == 1) {
            this.alapEletero = 15;
            this.alapSebzes = 3;
        } else if (statuszSablon == 2) {
            this.alapEletero = 12;
            this.alapSebzes = 4;
        } else if (statuszSablon == 3) {
            this.alapEletero = 8;
            this.alapSebzes = 5;
        } else {
            System.out.println("Hibás statuszSablon érték!");
        }
        this.eletero = this.getMaxEletero();
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getSzint() {
        return szint;
    }

    public void setSzint(int szint) {
        this.szint = szint;
    }

    public int getTapasztalat() {
        return tapasztalat;
    }

    public void setTapasztalat(int tapasztalat) {
        this.tapasztalat = tapasztalat;
        if (this.tapasztalat >= this.getSzintLepeshez()){
            this.setSzint(this.getSzint()+1);
            this.setTapasztalat(this.getTapasztalat()-this.getSzintLepeshez());
            this.setEletero(this.getMaxEletero());
        }
    }

    public int getAlapEletero() {
        return alapEletero;
    }

    public int getAlapSebzes() {
        return alapSebzes;
    }

    public int getEletero() {
        return eletero;
    }

    public void setEletero(int eletero) {
        this.eletero = eletero;
        if (this.eletero < 1){
            this.setTapasztalat(0);
        }
        if (this.eletero > this.getMaxEletero()){
            this.eletero = this.getMaxEletero();
        }
    }

    public int getSebzes() {
        return alapSebzes + szint;
    }

    public int getSzintLepeshez() {
        return 10 + szint * 5;
    }

    public int getMaxEletero() {
        return alapEletero + szint * 3;
    }

    public void megkuzd(Harcos masikharcos) {
        if (!masikharcos.nev.equals(this.nev)) {
            if (!(masikharcos.eletero == 0 || this.eletero == 0)) {
                masikharcos.setEletero(masikharcos.getEletero() - this.getSebzes());
                if (masikharcos.eletero > 0) {
                    this.setEletero(this.getEletero() - masikharcos.getSebzes());
                }
                if (masikharcos.getEletero() > 0 && this.getEletero() > 0) {
                    masikharcos.setTapasztalat(masikharcos.getTapasztalat() + 5);
                    this.setTapasztalat(this.getTapasztalat() + 5);
                }
                if (masikharcos.getEletero() > 0 && this.getEletero()<1) {
                    masikharcos.setTapasztalat(masikharcos.getTapasztalat() + 15);
                } else if (this.getEletero() > 0 && masikharcos.getEletero() < 1) {
                    this.setTapasztalat(this.getTapasztalat() + 15);
                }
            } else {
                System.out.println("Az egyik harcos életereje 0");
            }
        } else {
            System.out.println("A harcos nem harcolhat saját magával!");
        }
    }

    public void gyogyul() {
        if (this.eletero < 1){
            this.setEletero(this.getMaxEletero());
        } else {
            this.setEletero(this.getMaxEletero()+(szint+3));
        }
    }

    @Override
    public String toString() {
        return String.format("\n%s - LVL: %s - EXP: %s/%s HP:%s/%s - DMG: %s", nev, szint, tapasztalat, getSzintLepeshez(), eletero, getMaxEletero(), getSebzes());
    }
}
