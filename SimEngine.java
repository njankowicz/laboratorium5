package com.company;

public class SimEngine {

    //1 prywatne pola parametrow symulacji
    private double masa, wspSpr, wspTlu, dlugoscSw, polozenieMasyX, polozenieMasy_Y, predkoscMasy_Y, polozeniePunktuZaczX, polozeniePunktuZaczY;
    public double G=9.81;
    //przyspiesznie masy Y
    private double ay;
    //krok czasowy
    private double t;
    //wektor przechowujacy pozycje masy
    private Vector2D masaPoz;

    //settery

    public void setMasa(double masa)
    {
        this.masa=masa;
    }
    public void setWspSpr(double wspSpr)
    {
        this.wspSpr=wspSpr;
    }
    public void setWspTlu(double wspTlu)
    {
        this.wspTlu=wspTlu;
    }
    public void setDlugosc(double dlugoscSw)
    {
        this.dlugoscSw=dlugoscSw;
    }
    public void setPolozenieMasyX(double polozenieMasyX)
    {
        this.polozenieMasyX=polozenieMasyX;
    }
    public void setPolozenieMasy_Y(double polozenieMasy_Y)
    {
        this.polozenieMasy_Y=polozenieMasy_Y;
    }
    public void setPredkoscMasy_Y(double predkoscMasy_Y)
    {
        this.predkoscMasy_Y=predkoscMasy_Y;
    }
    public void setPolozeniePunktuZaczX(double polozeniePunktuZaczX)
    {
        this.polozeniePunktuZaczX=polozeniePunktuZaczX;
    }
    public void setPolozeniePunktuZaczY(double polozeniePunktuZaczY)
    {
        this.polozeniePunktuZaczY=polozeniePunktuZaczY;
    }
    public void setMasaPoz(double polozenieMasyX, double polozenieMasy_Y)
    {
        this.masaPoz = new Vector2D(polozenieMasyX,polozenieMasy_Y);
    }
    public void setG(double G)
    {
        this.G=G;
    }
    //gettery

    public double getMasa()
    {
        return masa;
    }
    public double getWspSpr()
    {
        return wspSpr;
    }
    public double getWspTlu()
    {
        return wspTlu;
    }
    public double getDlugosc()
    {
        return dlugoscSw;
    }
    public double getPolozenieMasyX()
    {
        return polozenieMasyX;
    }
    public double getPolozenieMasy_Y()
    {
        return polozenieMasy_Y;
    }
    public double getPredkoscMasy_Y()
    {
        return predkoscMasy_Y;
    }
    public double getPolozeniePunktuZaczX()
    {
        return polozeniePunktuZaczX;
    }
    public double getPolozeniePunktuZaczY()
    {
        return polozeniePunktuZaczY;
    }
    public double getMasaPozY() {
        return masaPoz.y;
    }
    public double getMasaPozX() {
        return masaPoz.x;
    }

    //2 Konstruktor z parametrami
    public SimEngine(double masa, double wspSpr,double wspTlu,double dlugoscSw,double polozenieMasyX, double polozenieMasy_Y, double predkoscMasy_Y,double polozeniePunktuZaczX, double polozeniePunktuZaczY) {
        this.masa = masa;
        this.wspSpr = wspSpr;
        this.wspTlu = wspTlu;
        this.dlugoscSw = dlugoscSw;
        this.polozenieMasyX = polozenieMasyX;
        this.polozenieMasy_Y=polozenieMasy_Y;
        this.predkoscMasy_Y = predkoscMasy_Y;
        this.polozeniePunktuZaczX = polozeniePunktuZaczX;
        this.polozeniePunktuZaczY = polozeniePunktuZaczY;
        this.ay = 0;
        this.t = 0;
        masaPoz = new Vector2D(polozenieMasyX, polozenieMasy_Y);
    }


    //3 Metoda z parametrem
    public void PrzebiegSymulacji(double deltaT)
    {
        this.t=deltaT;
        ay = (masa*G - wspSpr*masaPoz.y - wspTlu*predkoscMasy_Y)/masa;
        predkoscMasy_Y = predkoscMasy_Y + ay*t;
        masaPoz.y = masaPoz.y + predkoscMasy_Y*t + ay*t*t/2;
    }

    //4 Metoda bez parametrow
    public void Reset()
    {
        this.predkoscMasy_Y=0;
        this.ay=0;
    }
}
