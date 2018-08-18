import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int[] count = new int[8];
        Scanner sc = new Scanner(System.in);
        String time = sc.nextLine();
        while (!"-1,-1".equals(time)){
            String[] split = time.split(",");
            int arrive = Integer.parseInt(split[0]);
            arrive = arrive < 12 ? 12 : arrive;
            int goAway = Integer.parseInt(split[1]);
            goAway = goAway > 20 ? 20 : goAway;
            for (int i = arrive - 12; i < goAway - 12; i++) {
                count[i]++;
            }
            time = sc.nextLine();
        }
        for (int i = 0; i < count.length; i++) {
            System.out.println("[" + (i + 12) + "," + (i + 13) + "):" + count[i]);
        }

    }
}
/*Stack<Character> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        String regx = sc.nextLine();*/
/*
public void get() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int score[] = new int[n+1];
        for (int i = 0; i < n; i++) {
            score[i] = sc.nextInt();
        }

        int isAwake[] = new int[n];
        for (int i = 0; i < n; i++) {
            isAwake[i] = sc.nextInt();
        }

        int sumOfConAwake[] = new int[n+1];
        int sumOfDir[] = new int[n+1];
        sumOfDir[n-1] = score[n-1] * isAwake[n-1];
        for (int i = n-2; i >=0; i--) {
            sumOfDir[i] = sumOfDir[i+1] + score[i] * isAwake[i];
        }

        sumOfConAwake[0] = 0;
        k = k >= n ? n : k;
        for (int i = 0; i < k; i++) {
            sumOfConAwake[0] += score[i];
        }
        for (int i = 1; i <= n-k; i++) {
            int tmp= (i+k-1) >= n ? 0 : score[i+k-1];
            sumOfConAwake[i] = sumOfConAwake[i-1] - score[i-1] + tmp;
        }

        int max = 0,pre = 0;
        if(k >= n){
            max = sumOfConAwake[0];
        }else{
            for (int i = 0; i <= n-k; i++) {
                int tmp = pre + sumOfConAwake[i] + sumOfDir[i+k];
                if(tmp > max) max = tmp;
                pre += score[i] * isAwake[i];
            }
        }
        System.out.println(max);

    }
* */