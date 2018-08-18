package someexercise;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 送货员最小送货路径
 * 题目描述：某物流派送员p，需要给a、b、c、d4个快递点派送包裹，请问派送员需要选择什么的路线，才能完成最短路程的派送。
 * 假设如图派送员的起点坐标(0,0)，派送路线只能沿着图中的方格边行驶，每个小格都是正方形，且边长为1，如p到d的距离就是4。
 * 随机输入n个派送点坐标，求输出最短派送路线值（从起点开始完成n个点派送并回到起始点的距离）。
 *
 */
public class ShortestDeliveryPath {
    private static int minPathSum = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pointNum = scanner.nextInt();
        scanner.nextLine();
        Point[] points = new Point[pointNum];
        for (int i = 0; i < pointNum; i++) {
            points[i] = new Point(scanner.nextLine());
        }
        dfs(points, 0, 0, 0, 0);
        System.out.println(minPathSum);
    }

    public static void dfs(Point[] points, int index, int pathLen, int x, int y){
        if (index == points.length){
            minPathSum = Math.min(minPathSum, pathLen + Math.abs(x) + Math.abs(y));
            return;
        }
        for (Point p : points) {
            if (!p.visited){
                p.visited = true;
                //pathLen += Math.abs(p.x - x) + Math.abs(p.y - y);
                dfs(points, index + 1, pathLen + Math.abs(p.x - x) + Math.abs(p.y - y), p.x, p.y);
                p.visited = false;
            }
        }
    }
}

class Point{
    private static Pattern POS_PATTERN = Pattern.compile(",");
    int x;
    int y;
    boolean visited;

    public Point(String pattern) {
        String[] position = POS_PATTERN.split(pattern, 2);
        this.x = Integer.parseInt(position[0]);
        this.y = Integer.parseInt(position[1]);
    }

    public int distance(Point another){
        return Math.abs(this.x - another.x) + Math.abs(this.y - another.y);
    }

}