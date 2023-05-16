package may2023;

import java.util.Arrays;

public class week3 {

    //2446. 判断两个事件是否存在冲突
    public static boolean haveConflict(String[] event1, String[] event2) {
        int e1Start = hourToMin(event1[0]);
        int e1End = hourToMin(event1[1]);
        int e2Start = hourToMin(event2[0]);
        int e2End = hourToMin(event2[1]);
        if (e2Start > e1End || e1Start > e2End) {
            return false;
        } else {
            return true;
        }
    }

    public static int hourToMin(String str) {
        return Integer.parseInt(str.substring(0,2)) * 60 + Integer.parseInt(str.substring(3,5));
    }


    //TODO 1335. 工作计划的最低难度
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        //
        return 1;
    }


    //413. 等差数列划分
    public int numberOfArithmeticSlices(int[] nums) {
        //dp[i]表示以i结尾的等差子数组的个数
        //结果返回sum(dp[])
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 2; i < n; i++) {
            if ((nums[i] - nums[i - 1]) == (nums[i - 1] - nums[i - 2])) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return Arrays.stream(dp).sum();
    }




    public static void main(String[] args) {
        String str = "02:00";
        System.out.println(Integer.parseInt(str.substring(0,2)));
        System.out.println(Integer.parseInt(str.substring(3,5)));
        System.out.println(hourToMin(str));
    }
}
