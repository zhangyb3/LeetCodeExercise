import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    /**
     * 直接BFS暴力求解
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size(), num = 0;
        boolean[] canReachRooms = new boolean[n];
        Queue<Integer> queue = new LinkedList<Integer>();
        canReachRooms[0] = true;
        queue.offer(0);
        while (!queue.isEmpty()) {
            int x = queue.poll();
            num++;
            for (int roomNumber : rooms.get(x)) {
                if (!canReachRooms[roomNumber]) {
                    canReachRooms[roomNumber] = true;
                    queue.offer(roomNumber);
                }
            }
        }
        return num == n;
    }
}
