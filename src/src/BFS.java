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
        
        // ** set already visited flag for the starting location to true value
        // ** add starting location to the back of the queue
        
        Position front;
        boolean[][] alreadyVisited = new boolean[maze.getHeight()][maze.getWidth()];
        // ** Array[][] predecessor
        // Outer loop
        while(!queue.isEmpty() || currentPos == goalPos) {
            front = queue.head();
            
            for(Position pos : getPossibleMoves(front)) {
                if(pos == goalPos) { break; }
                // break both loops or just the inner one ?
                
                if(!alreadyVisited.contain(pos)) {
                    queue.add(pos);
                    // ** set the predecessor array for the new location
                    // to the last visited cell (i.e. variable "front")
                }
            }
            
            queue.remove();
        }
        
        // fill the searchPath array (in reverse order)
        // using the shortest path in the predecessor array
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
