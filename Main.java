package Snake;

import java.awt.Container;

import javax.swing.JFrame;

public class Main extends JFrame{
    

    public Main(){
        setTitle("Snake");

        Container pane = getContentPane();
		MyPanel mp = new MyPanel();
        mp.addKeyListener(mp);
        mp.setFocusable(true);
		pane.add(mp);
		
		pack();
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Main();
    
    
    }
}



