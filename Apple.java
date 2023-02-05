package Snake;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Apple {
    
    static int radius = 5;
    static int x = 250+5;
    static int y = 250+5;
    static boolean onScreen = false;

    public Apple(){

    }


    public void respawn(){
        if(!onScreen){
            Random rand = new Random();
            x = rand.nextInt(MyPanel.panelWidth)/10 *10 + 5;
            y = rand.nextInt(MyPanel.panelHeight)/10 *10 + 5;
        }
    }


    public void paintApple(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(x-radius,y-radius,radius*2,radius*2);
    }
}
