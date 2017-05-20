package mazegenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import javax.swing.JPanel;

public class MazeGenerator extends JPanel{
    private int rows, columns;
    private int[][] maze;
    private boolean[][] visited;
    
    public MazeGenerator(){
        rows = 19;
        columns = 29;
        maze = new int[rows][columns];
        visited = new boolean[rows][columns];
        int width = columns  * 30;
        int height = rows * 30;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.GRAY);
        
        initializeMaze();
        generateMaze();
    }
    
    private void initializeMaze(){
        for (int i = 2; i < rows - 2; i++){
            for (int j = 2; j < columns - 2; j++){
                if (i % 2 == 0 && j % 2 == 0){
                    maze[i][j] = 1;
                }
            }
        }
        
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (i == 0 || i == 1 || i == rows - 1 || i == rows - 2||
                        j == 0 || j == 1 || j == columns - 1 || j == columns - 2){
                    visited[i][j] = true;
                }
            }
        }
    }
    
    private void generateMaze(){
        int currentCellX = 2, currentCellY = 2;
        visited[currentCellX][currentCellY] = true;
        Stack<Integer> stack = new Stack<>();
        
        while (true){
            int[] wall = makeNewPassage(currentCellX, currentCellY);
            if (wall[0] != -1 && wall[1] != -1){
                stack.push(currentCellY);
                stack.push(currentCellX);
                currentCellX = wall[0];
                currentCellY = wall[1];
                visited[currentCellX][currentCellY] = true;
            }
            else if (!stack.isEmpty()){
                currentCellX = stack.pop();
                currentCellY = stack.pop();
            }
            else {
                break;
            }
        }
    }
    
    private int[] makeNewPassage(int x, int y){
        List<String> unvisitedNeighbours = new ArrayList<>();
        if (!visited[x - 2][y]){
            unvisitedNeighbours.add("left");
        }
        if (!visited[x + 2][y]){
            unvisitedNeighbours.add("right");
        }
        if (!visited[x][y - 2]){
            unvisitedNeighbours.add("up");
        }
        if (!visited[x][y + 2]){
            unvisitedNeighbours.add("down");
        }
        
        String wallToRemove;
        if (!unvisitedNeighbours.isEmpty()){
            int randomIndex = new Random().nextInt(unvisitedNeighbours.size());
            wallToRemove = unvisitedNeighbours.get(randomIndex);
            
            switch (wallToRemove) {
                case "right":
                    maze[x + 1][y] = 2;
                    return new int[] {x + 2, y};
                case "left":
                    maze[x - 1][y] = 2;
                    return new int[] {x - 2, y};
                case "up":
                    maze[x][y - 1] = 2;
                    return new int[] {x, y - 2};
                case "down":
                    maze[x][y + 1] = 2;
                    return new int[] {x, y + 2};
                default:
                    break;
            }
        }
        return new int[] {-1, -1};
    }
}