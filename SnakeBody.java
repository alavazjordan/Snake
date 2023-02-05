package Snake;

import java.awt.Graphics;
import java.awt.Color;

public class SnakeBody {
    
    static int numOfBody = 1;
    static private int size = 10;

    int currentX, currentY;
    //int changeAtX, changeAtY;
    //int speedAtChangeX, speedAtChangeY;
    int speedX = 0;
    int speedY = -10;


    public SnakeBody(){

    }


    public void paintSnakeBody(Graphics g){
        // (x-cordinate, y-cordinate, width, height)
        g.setColor(Color.GREEN);
        g.fillRect(currentX, currentY, size, size);
    }


    public void move(){
        currentX += speedX;
        currentY += speedY;
    }
}
