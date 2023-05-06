package may2023;

import java.util.*;

public class week1 {

    //970. 强整数
    public static List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        int index1 = 0, index2 = 0;
        if (x == 1) {
            index1 = 1;
        } else {
            while ((int)Math.pow(x, index1) <= bound) {
                index1++;
            }
        }
        if (y == 1) {
            index2 = 1;
        } else {
            while ((int)Math.pow(y, index2) <= bound) {
                index2++;
            }
        }
        for (int i = 0; i < index1; i++) {
            for (int j = 0; j < index2; j++) {
                int res = (int)Math.pow(x, i) + (int)Math.pow(y, j);
                if (res <= bound) {
                    set.add(res);
                }
            }
        }
        return new ArrayList<>(set);
    }


    //TODO 1376. 通知所有员工所需的时间

    //TODO 2106. 摘水果

    //1003. 检查替换后的词是否有效
    public boolean isValid(String s) {
        StringBuilder stk = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stk.append(c);
            if (stk.length() >= 3 && stk.substring(stk.length() - 3).equals("abc")) {
                stk.delete(stk.length() - 3, stk.length());
            }
        }
        return stk.length() == 0;
    }


    //2432. 处理用时最长的那个任务的员工
    public int hardestWorker(int n, int[][] logs) {
//        List<Integer> list = new ArrayList<>();
//        list.add(logs[0][1]);
//        for (int i = 1; i < logs.length; i++) {
//            list.add(logs[i][1] - logs[i-1][1]);
//        }
//        for (int i = 1; i < logs.length; i++) {
//            logs[i][1] = list.get(i);
//        }
//        Collections.sort(list);
//        int temp = list.get(list.size()-1);
//        List<Integer> ans = new ArrayList<>();
//        for (int[] log : logs) {
//            if (log[1] == temp) {
//                ans.add(log[0]);
//            }
//        }
//        Collections.sort(ans);
//        return ans.get(0);
        int ans = logs[0][0]; int maxCost = logs[0][1];
        for (int i = 1; i < logs.length; i++) {
            int index = logs[i][0];
            int cost = logs[i][1] - logs[i-1][1];
            if (cost > maxCost || (cost == maxCost && index < ans)) {
                maxCost = cost;
                ans = index;
            }
        }
        return ans;
    }

    //1419. 数青蛙
    public int minNumberOfFrogs(String croakOfFrogs) {
        int ans = 0;
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        int n = croakOfFrogs.length();
        for (int i = 0; i < n; i++) {
            char fc = croakOfFrogs.charAt(i);
            if (fc == 'c') {
                if (k > 0) {
                    k--;
                } else {
                    ans++;
                }
                c++;
            } else if (fc == 'r') {
                c--;
                r++;
            } else if (fc == 'o') {
                r--;
                o++;
            } else if (fc == 'a') {
                o--;
                a++;
            } else if (fc == 'k') {
                a--;
                k++;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) {
                break;
            }
        }
        if (c != 0 || r != 0 || o != 0 || a != 0) {
            return -1;
        }
        return ans;
    }


    //567. 字符串的排列
    public boolean checkInclusion(String s1, String s2) {
        //滑动窗口
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < m; i++) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = m; i < n; i++) {
            cnt2[s2.charAt(i) - 'a']++;
            cnt2[s2.charAt(i - m) - 'a']--;
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char c = 'b';
        System.out.println(c - 'a');
    }
}
