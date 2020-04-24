package brickbreaker;
import java.awt.Color;
import javax.swing.JFrame;

public class main {
    
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Gameplay gameplay = new Gameplay();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        obj.setBackground(Color.BLACK);
        obj.add(gameplay);
        
    }
    
}
