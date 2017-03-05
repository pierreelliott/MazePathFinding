package src;

import java.util.ArrayList;

public class BFS extends AbstractSearch {

    public BFS(int width, int height) {
        super(width, height);
        performBFS();
    }

    private void performBFS() {
        // method to complete
        PositionQueue queue = new PositionQueue();
        boolean[][] alreadyVisited = new boolean[maze.getHeight()][maze.getWidth()];
        System.out.println(alreadyVisited);
        Position[][] predecessor = new Position[maze.getHeight()][maze.getWidth()];
        
        // set already visited flag for the starting location to true value
        alreadyVisited[startPos.x][startPos.y] = true;
        
        // add starting location to the back of the queue
        queue.add(startPos);
        
        // Outer loop
        while(!queue.isEmpty() || currentPos != goalPos) {
            currentPos = queue.head();
            
            for(Position pos : getPossibleMoves(currentPos)) {
                if(pos == goalPos) { break; }
                // break both loops or just the inner one ?
                
                if(!alreadyVisited[pos.x][pos.y]) {
                    queue.add(pos);
                    // set the predecessor array for the new location to the last visited cell (i.e. variable "front")
                    predecessor[pos.x][pos.y] = currentPos;
                }
            }
            alreadyVisited[currentPos.x][currentPos.y] = true;
            queue.remove();
        }
        
        int i = 0;
        while(currentPos != startPos) {
            searchPath[i] = currentPos;
            currentPos = predecessor[currentPos.x][currentPos.y];
            i++;
        }
        // fill the searchPath array (in reverse order, i.e. starting by the goal position) using the shortest path in the predecessor array
    }
    
    protected class PositionQueue {
        // Class to manage the queue       
        private final ArrayList<Position> queue;
        
        public PositionQueue() {
            queue = new ArrayList<>();
        }

        public void add(Position pos) {
            queue.add(pos);
        }
        
        public Position remove() {
            return queue.remove(0);
        }

        public Position head() {
            return queue.get(0);
        }
        
        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
}
