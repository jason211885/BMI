/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author LENOWO
 */
public class PanelGry extends javax.swing.JPanel implements ActionListener  {

    /**
     * Creates new form PanelGry
     */
    private final  int OKNO_SZER = 600;
    private final  int OKNO_WYSO = 500;
    private int szerPaletki = 100;
    private int wysoPaletki = 10;
    private int xp = (OKNO_SZER- szerPaletki)/2;
    private int yp = OKNO_WYSO - wysoPaletki;
    private int srednicaPilki = 20;
    private int xPilki =(OKNO_SZER-srednicaPilki)/2;
    private int yPilki =(OKNO_WYSO-srednicaPilki)/2;
    private Random generator = new Random();
    private int wiersze = generator.nextInt(5)+4;
    private int kolumny = generator.nextInt(5)+4;
    private int szerCegla = 50;
    private int wysCegla = 10;
    private int ceglyOdstep = 5;
    private int ceglyOdstepLewa = (OKNO_SZER-(szerCegla*kolumny+ceglyOdstep*(kolumny-1)))/2;
    private int ceglaOdstepGora = 10;
    private int ceglyX[][] = new int [kolumny] [wiersze];
    private int ceglyY[][] = new int [kolumny] [wiersze];
    private boolean czyZbite[][] = new boolean [kolumny][wiersze];
    private int dx = 2;
    private int dy = -2;
    private Timer timer;
    private String stanGry = "trwa";
    
    
    
    
    
    
    
    public PanelGry() {
        initComponents();
        setBackground(Color.black);
        setPreferredSize(new Dimension(OKNO_SZER, OKNO_WYSO));
        
        
        for ( int i=0; i< kolumny; i++){
            for (int j=0; j< wiersze;j++){
                ceglyX[i][j] = i*(szerCegla+ceglyOdstep)+ ceglyOdstepLewa;
                ceglyY[i][j] = j*(wysCegla+ceglyOdstep) +ceglaOdstepGora;
                czyZbite[i][j] = false;
                
                
            }
        }
        
        timer = new Timer(10, this);
        timer.start();
        addKeyListener(new Sterowanie());
        setFocusable(true);
    }
   @Override
   public void paintComponent(Graphics g){
       super.paintComponent(g);
       rysuj(g);
       
   }
   private void rysuj(Graphics g){
      if (stanGry =="trwa")
      {
       rysujPaletke(g);
       rysujPilke(g);
      rysujCegly(g);
      }
      else{
         koniec(g);
      }
      
   }
   private void rysujPaletke(Graphics g)
   {
        g.setColor(Color.red);
        g.fillRect(xp, yp, szerPaletki, wysoPaletki);
        g.setColor(Color.white);
        g.drawRect(xp, yp, szerPaletki, wysoPaletki);
                
   }
   private void rysujPilke(Graphics g)
   {
       g.setColor(Color.red);
       g.fillOval(xPilki, yPilki, srednicaPilki, srednicaPilki);
       g.setColor(Color.white);
       g.drawOval(xPilki, yPilki, srednicaPilki, srednicaPilki);
   }
   private void rysujCegly(Graphics g)
   {
   
   for (int i=0; i<kolumny; i++){
       for (int j =0; j< wiersze; j++ ){
           if(!czyZbite[i][j]){
           
           g.setColor(Color.red);
           g.fillRect(ceglyX[i][j],ceglyY[i][j],szerCegla,wysCegla);
           g.setColor(Color.white);
           g.drawRect(ceglyX[i][j], ceglyY[i][j], szerCegla, wysCegla);
           }
       }
   }
   }
   private void ruchPilki()
   {
       if(xPilki <=0 || xPilki > OKNO_SZER -srednicaPilki){
           dx = dx*-1;
       }
       if(yPilki<=0 || yPilki+srednicaPilki >= yp && xPilki> xp && xPilki< xp +szerPaletki  )
       {
           dy = dy*-1;
       }
       
       {
       
   }
   xPilki = xPilki + dx;

   yPilki = yPilki + dy;
   }
   private void sprawdzStan(){
       if(yPilki > OKNO_WYSO)
       {
           stanGry = "PRZEGRYWASZ";
       }
       int niezbite =0;
       for (int i=0; i< kolumny; i++){
           for (int j=0; j< wiersze; j++){
               if(czyZbite[i][j] == false)
               {
                   niezbite += 1;
               }
           }
       }
       if (niezbite == 0)
       {
           stanGry = "WYGRYWASZ";
       }
   }
   private void koniec(Graphics g)
   {
       Font czcionka = new Font ("Helvetica",Font.BOLD,14);
       FontMetrics metr = getFontMetrics(czcionka);
       g.setColor(Color.white);
       g.setFont(czcionka);
       g.drawString(stanGry,(OKNO_SZER-metr.stringWidth(stanGry))/2, OKNO_WYSO/2);
   }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actionPerformed(ActionEvent e) {
        sprawdzStan();
        zderzenieZCegla();
        ruchPilki();
        repaint();
    }
    
    private class Sterowanie extends KeyAdapter{
    
        @Override
        public void keyPressed(KeyEvent e){
        
        int key= e.getKeyCode();
        
        if(key == KeyEvent.VK_LEFT && xp>0)
        {
            xp = xp-10;
        }
        if(key == KeyEvent.VK_RIGHT && xp<OKNO_SZER-szerPaletki)
        {
            xp = xp+10;
        }
        }
}
    private void zderzenieZCegla (){
    
        for (int i =0; i <kolumny; i++){
        for (int j =0; j < wiersze; j++){
             if(czyZbite[i][j] == false){
             if(xPilki > ceglyX[i][j] 
                  &&   xPilki < ceglyX[i][j]+szerCegla     
                   && yPilki+ srednicaPilki >  ceglyY[i][j]
                    &&  yPilki<  ceglyY[i][j]+ wysCegla ){
                       dy=-dy;
                       czyZbite[i][j] = true;
                         
                        
             }else if(yPilki>ceglyY[i][j]
                     && yPilki < ceglyY[i][j] + wysCegla
                      && xPilki + srednicaPilki > ceglyX[i][j]
                      && xPilki < ceglyX[i][j] + szerCegla ){
                     
                    dx=-dx;
                    czyZbite[i][j] = true;
             }
            }    
        }
        }
    }
        
       
}    
   
        

       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

       
