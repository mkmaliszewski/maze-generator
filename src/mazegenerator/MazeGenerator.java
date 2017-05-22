package mazegenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public final class MazeGenerator {
    private final int size;
    private final int[][] maze;
    private final boolean[][] visited;
    
    public MazeGenerator(){
        size = 91;
        maze = new int[size][size];
        visited = new boolean[size][size];        
        initializeMaze();
        generateMaze();
        markWayToStartPoint();
        findTheExit();
    }
    
    private void initializeMaze(){
        for (int i = 2; i < size - 2; i++){
            for (int j = 2; j < size - 2; j++){
                if (i % 2 == 0 && j % 2 == 0){
                    maze[i][j] = 1;
                }
            }
        }
        
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (i == 0 || i == 1 || i == size - 1 || i == size - 2||
                        j == 0 || j == 1 || j == size - 1 || j == size - 2){
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
                    maze[x + 1][y] = 1;
                    return new int[] {x + 2, y};
                case "left":
                    maze[x - 1][y] = 1;
                    return new int[] {x - 2, y};
                case "up":
                    maze[x][y - 1] = 1;
                    return new int[] {x, y - 2};
                case "down":
                    maze[x][y + 1] = 1;
                    return new int[] {x, y + 2};
                default:
                    break;
            }
        }
        return new int[] {-1, -1};
    }
    
    public int[][] getMaze(){
        return maze;
    }
    
    public int getSize(){
        return size;
    }
    
    private void markWayToStartPoint(){
        int startX = 2, startY = 2;
        int finishX = size - 3, finishY = size - 3;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startX);
        queue.add(startY);
        
        while (!queue.isEmpty()){
            int x = queue.poll();
            int y = queue.poll();
            
            if (x == finishX && y == finishY){
                break;
            }
            
            if (maze[x - 1][y] == 1){
                maze[x - 1][y] = 'd';
                maze[x - 2][y] = 'd';
                queue.add(x - 2);
                queue.add(y);
            }
            if (maze[x + 1][y] == 1){
                maze[x + 1][y] = 'u';
                maze[x + 2][y] = 'u';
                queue.add(x + 2);
                queue.add(y);
            }
            if (maze[x][y - 1] == 1){
                maze[x][y - 1] = 'r';
                maze[x][y - 2] = 'r';
                queue.add(x);
                queue.add(y - 2);
            }
            if (maze[x][y + 1] == 1){
                maze[x][y + 1] = 'l';
                maze[x][y + 2] = 'l';
                queue.add(x);
                queue.add(y + 2);
            }
        }
    }
    
    private void findTheExit(){
        int finishX = size - 3, finishY = size - 3;
        
        while ((finishX != 2) || (finishY != 2)){
            int direction = maze[finishX][finishY];
            maze[finishX][finishY] = 2;

            switch (direction){
                case 100:   finishX++;
                            break;
                case 108:   finishY--;
                            break;
                case 114:   finishY++;
                            break;
                case 117:   finishX--;
                            break;
            }
        }
        
        // clear the unused paths
        maze[2][2] = 2;
        for (int i = 1; i < size - 1; i++){
            for (int j = 1; j < size - 1; j++){
                if (maze[i][j] == 100 || maze[i][j] == 108 ||
                        maze[i][j] == 114 || maze[i][j] == 117){
                    maze[i][j] = 1;
                }
            }
        }
    }
}