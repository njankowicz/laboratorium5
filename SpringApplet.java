package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;

public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener, ActionListener {

    private SimEngine simEngine;
    private SimTask simTask;
    private Timer timer;

    private int szerokosc=600;
    private int wysokosc=600;

    //2
    boolean mouse_dragging;
    //wspolrzedne polozenia kursora
    private int mysz_x=0;
    private int mysz_y=0;

    //pola do interfejsu graficznego
    //przycisk do resetu
    private Button reset;
    private TextField masa, wspol_spr, wspol_tlum, G, L0;

    @Override
    public void init() {

    setSize(szerokosc, wysokosc);
    //tworzenie obiekt SimEngine
    simEngine = new SimEngine(15,0.45,0.35,100,300,250,0,300,10);
    //nowy obiekt SimTask
    simTask = new SimTask(simEngine, this, 0.1);
    //nowy obiekt timer
    timer = new Timer();
    //metoda addActionListener()
    timer.scheduleAtFixedRate(simTask,0,15);

    //nadawanie wartosci 0
    this.mouse_dragging=false;
    addMouseListener(this);
    addMouseMotionListener(this);

    //GUI
    reset = new Button("RESET");
    masa = new TextField("15", 1);
    wspol_spr = new TextField("0.45", 1);
    wspol_tlum = new TextField("0.35", 1);
    G = new TextField("9.81", 1);
    L0 = new TextField("100", 1);
    //dodawanie elemetnow
        add(reset);
        add(masa);
        add(wspol_spr);
        add(wspol_tlum);
        add(G);
        add(L0);
     //dodawanie sluchacza
     reset.addActionListener(this);
    }

    @Override
    public void paint(Graphics g) {
    //czyszczenie ekranu
        g.fillRect(0,0,szerokosc,wysokosc);
    //wybieranie koloru tla
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,szerokosc,wysokosc);
     //rysowanie przyciskow
        g.setColor(Color.WHITE);
        g.drawString("Masa ukladu", 30, 30);
        masa.setBounds(120, 18, 20, 20);
        g.drawString("Wspol spr", 30, 60);
        wspol_spr.setBounds(120, 50, 30, 20);
        g.drawString("Wspol tlum", 30, 90);
        wspol_tlum.setBounds(120,80,30,20);
        g.drawString("L0", 30, 120);
        L0.setBounds(120, 110, 30, 20);
        g.drawString("Przysp ziemskie", 30,150);
        G.setBounds(120, 140, 30, 20);
        reset.setBounds(60, 185, 60, 35);

     //narysowanie punktu utwierdzenia
        g.setColor(Color.CYAN);
        g.drawLine(250,(int)simEngine.getPolozeniePunktuZaczY(),350,(int)simEngine.getPolozeniePunktuZaczY());
     //rysowanie masy
        g.setColor(Color.PINK);
        g.fillOval((int)simEngine.getPolozenieMasyX()-25, (int)simEngine.getMasaPozY(), 50,50);
     //rysowanie linii
        g.setColor(Color.GREEN);
        g.drawLine((int)simEngine.getPolozeniePunktuZaczX(), (int)simEngine.getPolozeniePunktuZaczY(), (int)simEngine.getPolozenieMasyX(), (int)simEngine.getMasaPozY());
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    //przypisanie wspol kursora
    this.mysz_x = e.getX();
    this.mysz_y = e.getY();
    //sprawdzenie pozycji kursora w elipsie
    if ((mysz_x >= (int)simEngine.getPolozenieMasyX()-25 && mysz_x <= (int)simEngine.getPolozenieMasyX() +25) &&
            (mysz_y >= (int)simEngine.getPolozenieMasy_Y()) && (mysz_y <= (int)simEngine.getPolozenieMasy_Y()+100))
        {
        //wylaczenie timera
        timer.cancel();
        //reset
        simEngine.Reset();
        //zmiana wart log
        mouse_dragging=true;
        }
    //metoda consume
        e.consume();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    //sprawdzenie przeciagania kursora
    if (mouse_dragging==true)
        {
            simEngine.Reset();
            simTask = new SimTask(simEngine, this, 0.1);
            //nowy obiekt timer
            timer = new Timer();
            //metoda
            timer.scheduleAtFixedRate(simTask,0,15);
            //zmiana wartosci logicznej
            mouse_dragging=false;
        }
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    if (mouse_dragging==true)
    {
        this.mysz_y=e.getY();
        this.mysz_x=e.getX();
        //ustawienie pozycji masy
        simEngine.setMasaPoz(simEngine.getMasaPozX(), mysz_y);
        //wywolanie repaint
        repaint();
    }
    e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource()==reset)
    {
        //zatrzymanie timera
        timer.cancel();
        //reset
        simEngine.Reset();
        simEngine = new SimEngine(Double.parseDouble(masa.getText()),Double.parseDouble(wspol_spr.getText()), Double.parseDouble(wspol_tlum.getText()), Double.parseDouble(L0.getText()), 300,
                250, 0, 300, 10);
        simEngine.setG(Double.parseDouble(G.getText()));
        simTask = new SimTask(simEngine, this, 0.1);
        //nowy obiekt timer
        timer = new Timer();
        //metoda
        timer.scheduleAtFixedRate(simTask,0,15);
        repaint();

    }

    }
}
