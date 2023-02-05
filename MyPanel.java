package Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.net.ssl.SSLHandshakeException;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener{
    
    Timer timer = new Timer();
    ArrayList<SnakeBody> sB = new ArrayList<SnakeBody>();
    static int panelWidth = 500;
    static int panelHeight = 500;
    Apple a = new Apple();
    int currentPopulation = 1;
    boolean gameOver = false;

    public MyPanel(){
        setPreferredSize(new Dimension(panelWidth,panelHeight));
        setBackground(Color.BLACK);

        sB.add(new SnakeBody());
        sB.get(0).currentX = 250-10;
        sB.get(0).currentY = 250-10;

        update();

    }

    
    private void update(){
        timer.schedule(new TimerTask() {
			public void run() {
              
              checkCollision();
              populate();
              entireBodyMove();
			  repaint();
			}
		  }, 0, 150);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        if(gameOver){
            g2d.setColor(Color.RED);
            paintGameOver(g2d);
        }
        else{
        for(int i = 0; i<sB.size(); i++){
            sB.get(i).paintSnakeBody(g2d);
        }
        a.paintApple(g2d);
        }
    }

    //
    public void populate(){

        while(currentPopulation < SnakeBody.numOfBody){
                sB.add(new SnakeBody());
                if(sB.get(0).speedX == 10){
                    sB.get(currentPopulation).currentX = sB.get(currentPopulation-1).currentX-10;
                }
                else if(sB.get(0).speedX == -10){
                    sB.get(currentPopulation).currentX = sB.get(currentPopulation-1).currentX+10;
                }
                else if(sB.get(0).speedY == 10){
                    sB.get(currentPopulation).currentY = sB.get(currentPopulation-1).currentY-10;
                }
                else if(sB.get(0).speedY == -10){
                    sB.get(currentPopulation).currentY = sB.get(currentPopulation-1).currentY+10;
                }
                currentPopulation++; 

        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_W){
        sB.get(0).speedX = 0;
        sB.get(0).speedY = -10;

      }
      else if(e.getKeyCode() == KeyEvent.VK_S){
        sB.get(0).speedX = 0;
        sB.get(0).speedY = 10;
        
      }
      else if(e.getKeyCode() == KeyEvent.VK_A){
        sB.get(0).speedX = -10;
        sB.get(0).speedY = 0;
        
      }
      else if(e.getKeyCode() == KeyEvent.VK_D){
        sB.get(0).speedX = 10;
        sB.get(0).speedY = 0;
        
      }
    }


    private void entireBodyMove(){
        for(int i = sB.size()-1; i>0; i--){
            sB.get(i).currentX = sB.get(i-1).currentX;
            sB.get(i).currentY = sB.get(i-1).currentY;

        }
        sB.get(0).move();
        
    }


    private void checkCollision(){
        if(sB.get(0).currentX < 0){
            gameOver();
        }
        else if(sB.get(0).currentX > panelWidth){
            gameOver();
        }
        else if(sB.get(0).currentY < 0){
            gameOver();
        }
        else if(sB.get(0).currentY > panelHeight){
            gameOver();
        }


        if(sB.get(0).currentX == Apple.x-5 && sB.get(0).currentY == Apple.y-5){
            SnakeBody.numOfBody++;
            a.respawn();
        }

        for(int i = 1; i<sB.size(); i++){
            if((sB.get(0).currentX == sB.get(i).currentX) && (sB.get(0).currentY == sB.get(i).currentY)){
                gameOver();
            }
        }
    }


    private void gameOver(){
        gameOver = true;
        setBackground(Color.BLUE);
        repaint();
    }

    private void paintGameOver(Graphics g){
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("GAME OVER", 100,200);
    }


    @Override
    public void keyReleased(KeyEvent e) {
      //System.out.println("Key Released: " + e.getKeyChar());
    }
  
    @Override
    public void keyTyped(KeyEvent e) {
      //System.out.println("Key Typed: " + e.getKeyChar());
    }
}
