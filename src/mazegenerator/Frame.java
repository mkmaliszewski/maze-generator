package mazegenerator;

import java.awt.EventQueue;
import javax.swing.JFrame;

public final class Frame extends JFrame{
    public Frame(){
        super("Maze Generator");
        setResizable(false);
        
        PaintMaze paintMaze = new PaintMaze();
        add(paintMaze);
        pack();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);       
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Frame();
            }
        });  
    }
}
