import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleI {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build the Directed Adjacency List and calculate In-Degrees
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];

            // Directed Edge: prerequisite -> course
            adjList.get(prerequisite).add(course);
            inDegree[course]++;
        }

        // Step 2: Push all courses with 0 prerequisites (in-degree 0) to Queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int count = 0; // Number of courses successfully finished

        // Step 3: BFS level-order execution
        while (!q.isEmpty()) {
            int currentCourse = q.poll();
            count++;

            for (int neighborCourse : adjList.get(currentCourse)) {
                inDegree[neighborCourse]--;

                // If all prerequisites for neighbor course are fulfilled
                if (inDegree[neighborCourse] == 0) {
                    q.offer(neighborCourse);
                }
            }
        }

        // If count == numCourses, all courses were finished (No Cycle!)
        return count == numCourses;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        boolean possible = canFinish(numCourses, prerequisites);
        System.out.println("Can finish all courses? " + possible);
        // Output: true
    }
}