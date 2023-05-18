package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Paramets extends JPanel implements ActionListener {

private final int saiz=320;
private final int dot_size=16;
private final int all_dots=400;
private Image dot;
private Image aples;
private int aples_x;
private int aples_y;
private int[] x=new int[all_dots];
private int[] y=new int[all_dots];
private int dots;
private boolean left=false;
private boolean right=true;
private boolean up=false;
private boolean down=false;
private boolean inGeim=true;
private Timer timer;
private JButton bat;

/*public void Res() {


    bat = new JButton("res");
    bat.setSize(200, 50);

}*/


     public Paramets(){
setBackground(Color.black);
loudetImeges();
iniGeim();

addKeyListener(new FieldKeyListener());
setFocusable(true);
addKeyListener(new Nygame());

    }

    public void loudetImeges(){
         ImageIcon ap=new ImageIcon("apple.png");
         aples=ap.getImage();
        ImageIcon d=new ImageIcon("dot.png");
        dot=d.getImage();
     }


     public void iniGeim(){
         dots=3;
         for (int i=0;i<dots;i++){
             x[i]=48-i*all_dots;
             y[i]=48;
         }
     timer=new Timer(250,this);
         timer.start();
         creetAples();
     }


     public void creetAples(){
         aples_x=new Random().nextInt(20)*dot_size;
         aples_y=new Random().nextInt(20)*dot_size;
     }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGeim) {
            g.drawImage(aples, aples_x, aples_y, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        }
        else {
            String over ="Game over";
            g.setColor(Color.white);

            g.drawString(over, 125,110);


        }
    }


    public void chekAples (){
         if (x[0]==aples_x && y[0]==aples_y){
             dots++;
             creetAples();
         }
    }

    public void moov(){
         for (int i = dots; i >0 ; i--) {
             x[i]=x[i-1];
             y[i]=y[i-1];
         }
         if (left){
             x[0]-=dot_size;
         }
         if (right){
             x[0]+=dot_size;
         }
         if (up){
             y[0]-=dot_size;
         }
         if (down){
             y[0]+=dot_size;
         }
     }

     public  void chekPole(){
         for (int i = dots; i >0 ; i--) {
            if (i>4 && x[0]==x[i] && y[0]==y[i]){
                inGeim=false;
            }
         }
         if (x[0]>saiz){
             inGeim=false;
         }
         if (x[0]<0){
             inGeim=false;
         }
         if (y[0]>saiz){
             inGeim=false;
         }
         if (y[0]<0){
             inGeim=false;
         }
     }
     public void resy(){

         if (!inGeim){
             bat=new JButton("Restart");
             bat.setSize(100,40);
             
         }
     }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (inGeim){
          moov();
          chekPole();
         chekAples();
         }
        repaint();
    }
class Nygame extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int ney = e.getKeyCode();

        if (inGeim == false && ney == KeyEvent.VK_ENTER) {


        }


    }
}

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                up = true;
                left = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                down = true;
                left = false;
            }
        }
    }



}
