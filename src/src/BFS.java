package src;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BFS extends AbstractSearch {

    public BFS(int width, int height) {
        super(width, height);
        performBFS();
    }

    private void performBFS() {
        // method to complete
        PositionQueue queue = new PositionQueue();
        boolean[][] alreadyVisited = new boolean[maze.getHeight()][maze.getWidth()];        
        Position[][] predecessor = new Position[maze.getHeight()][maze.getWidth()];
        
        // set already visited flag for the starting location to true value
        alreadyVisited[startPos.x][startPos.y] = true;
        
        // add starting location to the back of the queue
        queue.add(startPos);
        
        // Outer loop
        search : while(!queue.isEmpty() && currentPos != goalPos) {
            currentPos = queue.getFrontPosition();
            
            for(Position pos : getPossibleMoves(currentPos)) {
                
                // break both loops or just the inner one ?
                //System.out.println("Array : "+Arrays.toString(alreadyVisited));
                
                
                if(pos != null && !alreadyVisited[pos.x][pos.y]) {
                    queue.add(pos);
                    // set the predecessor array for the new location to the last visited cell (i.e. variable "front")
                    predecessor[pos.x][pos.y] = currentPos;
                    if(pos == goalPos)
                        break search;
                }
            }
            alreadyVisited[currentPos.x][currentPos.y] = true;
            queue.remove();
            
            /*for(boolean[] arr : alreadyVisited)
            {
                System.out.println("Array alreadyVisited : " + Arrays.toString(arr));
            }
            System.out.println();*/
        }
        
        int i = 0;
        while(currentPos != startPos) {
            searchPath[i] = currentPos;
            currentPos = predecessor[currentPos.x][currentPos.y];
            i++;
        }
        
        System.out.println("Path found : " + Arrays.toString(searchPath));
        // fill the searchPath array (in reverse order, i.e. starting by the goal position) using the shortest path in the predecessor array
    }
    
    protected class PositionQueue {
            // Class to manage the queue       
            private Queue<Position> q = new ArrayDeque<>();
            
            public boolean add(Position p) { return q.add(p); }
            public Position remove() { return q.remove(); }
            public Position getFrontPosition() { return q.element(); }
            public boolean isEmpty() { return q.isEmpty(); }
        }
}
