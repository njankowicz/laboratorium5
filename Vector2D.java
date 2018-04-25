package com.company;
import static java.lang.Math.sqrt;
public class Vector2D {

    //1
    public double x,y;
    //2
    //konstruktor domyślny
    public Vector2D()
    {
        this.x=0f;
        this.y=0f;
    }
    //konstruktor z parametrami
    public Vector2D(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    //3
    //metoda dodawanie
    public Vector2D dodajWektory(Vector2D w)
    {
        Vector2D wektor = new Vector2D(this.x + w.x, this.y + w.y);
        return wektor;
    }
    //4  odejmowanie
    public Vector2D odejmijWektory(Vector2D w)
    {
        Vector2D wektor = new Vector2D(this.x - w.x, this.y - w.y);
        return wektor;
    }
    //5 mnożenie
    public Vector2D pomnozWektory(double a)
    {
        Vector2D wektor = new Vector2D(this.x * a, this.y * a);
        return wektor;
    }
    //6
    public double dlugoscWektora()
    {
        double dlugosc = (double) sqrt(this.x*this.x+this.y*this.y);
        return dlugosc;
    }
    //7
    public Vector2D normalizacjaWektora()
    {
       Vector2D wektor = new Vector2D(this.x / this.dlugoscWektora(), this.y / this.dlugoscWektora());
        return wektor;
    }
}
