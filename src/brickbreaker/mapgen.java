package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class mapgen {
    public int map[][];
    public int bh,bw;
    public mapgen(int row,int col){
        map= new int [row][col];
        for (int i = 0; i < map.length ; i++) {
            for (int j=0 ; j < map[0].length ;j++ ){
                map[i][j] = 1;
            }
        }
        bw = 540/col;
        bh = 150/row;
    }
    public void draw(Graphics2D g){
         for (int i = 0; i < map.length ; i++) {
            for (int j=0 ; j < map[0].length ;j++ ){
                if(map[i][j] > 0 ){
                    g.setColor(Color.GRAY);
                    g.fillRect(j* bw +80, i * bh + 50,bw,bh);
                    //bricks
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j* bw +80, i * bh + 50,bw,bh);
                }
            }
              
        }                     
    }
    public void setbv(int v,int row ,int col){
        map[row][col] = v;
    }
}

