import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] req : prerequisites) {
            int course = req[0];
            int prereq = req[1];
            adj.get(prereq).add(course);
            indegree[course]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int[] order = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[index++] = current;
            
            for (int neighbor : adj.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        return index == numCourses ? order : new int[0];
    }
}