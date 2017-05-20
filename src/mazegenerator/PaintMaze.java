package mazegenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class PaintMaze extends JPanel{
    private final MazeGenerator mazeGenerator;
    private final int gameSize;
    private final int squareSize;
    
    public PaintMaze(){
        mazeGenerator = new MazeGenerator();
        gameSize = 900;
        squareSize = gameSize / (mazeGenerator.getSize() - 2);
        setPreferredSize(new Dimension(gameSize, gameSize));
        setBackground(Color.GRAY);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        int[][] maze = mazeGenerator.getMaze();
        for (int i = 1; i < mazeGenerator.getSize() - 1; i++){
            for (int j = 1; j < mazeGenerator.getSize() - 1; j++){
                if (i == 2 && j == 2){
                    g2d.setColor(Color.BLUE);
                }
                else if (i == mazeGenerator.getSize() - 3 &&
                        j == mazeGenerator.getSize() - 3){
                    g2d.setColor(Color.RED);
                }
                else {
                    g2d.setColor(Color.WHITE);
                }
                if (maze[i][j] == 1){
                    g2d.fillRect((j-1)*squareSize, (i-1)*squareSize, squareSize, squareSize);
                }
            }
        }
    }
}
