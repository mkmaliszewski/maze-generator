package mazegenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PaintMaze extends JPanel{
    private final MazeGenerator mazeGenerator;
    private final int size = 30;
    public PaintMaze(){
        mazeGenerator = new MazeGenerator();
        setPreferredSize(new Dimension((mazeGenerator.getColumns() - 2) * size, 
                (mazeGenerator.getRows() - 2) * size));
        setBackground(Color.GRAY);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        int[][] maze = mazeGenerator.getMaze();
        for (int i = 1; i < mazeGenerator.getRows() - 1; i++){
            for (int j = 1; j < mazeGenerator.getColumns() - 1; j++){
                if (maze[i][j] == 1){
                    g2d.fillRect((j-1)*size, (i-1)*size, size, size);
                }
            }
        }
    }
    
    public void paintAgain(){
        repaint();
    }
}
