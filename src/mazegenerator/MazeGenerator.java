package mazegenerator;

import java.awt.Dimension;
import javax.swing.JPanel;

public class MazeGenerator extends JPanel{
    private int rows, columns;
    private int[][] maze;
    private boolean[][] visited;
    
    public MazeGenerator(){
        rows = 19;
        columns = 29;
        maze = new int[rows][columns];
        int width = columns  * 30;
        int height = rows * 30;
        setPreferredSize(new Dimension(width, height));
        
        initializeMaze();
        generateMaze();
    }
    
    private void initializeMaze(){
        for (int i = 1; i < rows - 1; i++){
            for (int j = 1; j < columns - 1; j++){
                if (i % 2 == 1 && j % 2 == 1){
                    maze[i][j] = 1;
                }
            }
        }
        
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    private void generateMaze(){
        
    }
}