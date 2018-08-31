package datastructure.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 判断这张｀无向｀图是否是一棵树

 样例
 给出n = 5 并且 edges = [[0, 1], [0, 2], [0, 3], [1, 4]], 返回 true.
 给出n = 5 并且 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], 返回 false.

 注意事项
 你可以假设我们不会给出重复的边在边的列表当中. 无向边　[0, 1] 和 [1, 0]　是同一条边， 因此他们不会同时出现在我们给你的边的列表当中
 */
public class GraphIsTree {
    /**
     * 判断条件
     * 树中e = n - 1：n 个点若不存在 n-1 条边一定存在环
     * 连通性：由一个点可以访问到其它所有点，点的总数为 n
     */
    public boolean validTree(int n, int[][] edges){
        if (n < 1 || edges.length != n - 1)
            return false;
        if (n == 1)
            return true;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>(n);
        queue.offer(edges[0][0]);
        while (!queue.isEmpty()){
            int cur = queue.poll();
            if (visited[cur])
                continue;
            visited[cur] = true;
            for (int[] edge : edges) {
                if (edge[0] == cur)
                    queue.offer(edge[1]);
                if (edge[1] == cur) //注意 8 [[0,1],[1,2],[3,2],[4,3],[4,5],[5,6],[6,7]]的用例，边的描述中结点编号位置可能交换
                    queue.offer(edge[0]);
            }
        }
        for (boolean v : visited) {
            if (!v)
                return false;
        }
        return true;
    }
}
