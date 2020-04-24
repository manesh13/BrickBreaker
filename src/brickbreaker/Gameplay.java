package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;
    private int score = 0;
    
    private int totalBrick = 32;
    private Timer timer;
    private int delay = 8;
    
    private int playerX = 320;
    
    private int ballposx = 120;
    private int ballposy = 350;
    private int balldirx = -1;
    private int balldiry = -2;
    private mapgen map;
    
    public Gameplay(){
        map =new mapgen(4,8);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }
    public void paint(Graphics g){
        //background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        //draw map
        map.draw((Graphics2D)g);
        
        //borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(692,0,3,592);
        
        //padle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550,100,8);
        
        //ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballposx, ballposy,20,20);
        
        //game over
        if(ballposy > 570){
            play = false;
            balldiry = 0;
            balldiry = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 26));
            g.drawString("GAME OVER , Score : " + score, 190, 300);
            
            g.setFont(new Font("serif", Font.PLAIN, 15));
            g.drawString("PRESS ENTER TO RESTART", 240, 350);
        }
        //bricks over
        if(totalBrick <= 0){
             play = false;
            balldiry = 0;
            balldiry = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 26));
            g.drawString("YOU WON , Score : " + score, 230, 300);
            
            g.setFont(new Font("serif", Font.PLAIN, 15));
            g.drawString("PRESS ENTER TO RESTART", 240, 350);
        }
         
        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 26));
        g.drawString(""+score, 590, 30);
        
        g.dispose();

    }
    @Override
    public void actionPerformed(ActionEvent ae) { 
        timer.start();
        if(play){
            //ball and padle coll.
             if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerX,550,100,8))){
                 balldiry =- balldiry;
            }
             //detect ball and bricks coll.
             A : for (int i = 0; i < map.map.length ; i++) {
                 for (int j = 0; j < map.map[0].length; j++) {
                     if(map.map[i][j] > 0){
                         int brickx = j * map.bw +80;
                         int bricky =  i * map.bh +50;
                         int bw = map.bw;
                         int bh = map.bh;
                         
                         Rectangle rect = new Rectangle(brickx,bricky,bw,bh);
                         Rectangle ballrect = new Rectangle(ballposx,ballposy,20,20);
                         Rectangle brickrect = rect;
                         
                         if(ballrect.intersects(brickrect)){
                             map.setbv(0, i, j);
                             totalBrick --;
                             score += 5;
                             if(ballposx + 19 <= brickrect.x || ballposx + 1 >= brickrect.x + brickrect.width){
                                 balldirx = -balldirx;
                             }
                             else {
                                 balldiry = -balldiry;
                             }
                             break A;
                         }
                     }
                 }                
            }
            ballposx += 2*balldirx;
            ballposy += 2*balldiry;
            if(ballposx < 0){
                balldirx = - balldirx; //left border
            }
            if(ballposy < 0){
                balldiry = - balldiry; //top border
            }
            if(ballposx > 670){
                balldirx = - balldirx; //rght border
            }
        }
                
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {}
    @Override
    public void keyPressed(KeyEvent ke) {
        
        if(ke.getKeyCode()== KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }else{
                moveRight();
            }
        }
        if(ke.getKeyCode()== KeyEvent.VK_LEFT){
            if(playerX <= 10){
                playerX = 10;
            }else{
                moveLeft();
            }
        }
        if(ke.getKeyCode()== KeyEvent.VK_ENTER){
            if(!play)
                play = true;
                ballposx = 300;
                ballposy = 450;
                balldirx = -1;
                balldiry = -2;
                playerX  = 310;
                score    = 0;
                totalBrick = 32;
                map = new mapgen(4,8);
                
                repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) {}
   public void moveRight(){
       play = true;
       playerX += 20;
   }
   public void moveLeft(){
       play = true;
       playerX -= 20;
   }
}

