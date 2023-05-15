package may2023;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class week2 {

    //2437. 有效时间的数目
    public int countTime(String time) {
        int count1, count2;

        char h1 = time.charAt(0);
        char h2 = time.charAt(1);

        char m1 = time.charAt(3);
        char m2 = time.charAt(4);

        if (h1 == '?' && h2 == '?') {
            count1 = 24;
        } else if (h1 != '?' && h2 == '?') {
            if (h1 == '0' || h1 == '1') {
                count1 = 10;
            } else {
                count1 = 3;
            }
        } else if (h1 == '?' && h2 != '?'){
            if (h2 - '0' < 4) {
                count1 = 3;
            } else {
                count1 = 2;
            }
        } else {
            count1 = 1;
        }

        if (m1 == '?' && m2 == '?') {
            count2 = 60;
        } else if (m1 != '?' && m2 == '?') {
            count2 = 10;
        } else if (m1 == '?' && m2 != '?'){
            count2 = 6;
        } else {
            count2 = 1;
        }
        return count1 * count2;
    }


    //1015. 可被 K 整除的最小整数
    public int smallestRepunitDivByK(int k) {
        Set<Integer> set = new HashSet<>();
        int x = 1 % k;
        while (x > 0 && set.add(x)) {
            x = (x * 10 + 1) % k;
        }
        return x > 0 ? -1 : set.size() + 1;
    }


    //2441. 与对应负数同时存在的最大正整数
    public int findMaxK(int[] nums) {
        //哈希加速计算
        Set<Integer> set = new HashSet<>();
        int ans = -1;
        for (int num : nums) {
            if (set.contains(-num)) {
                ans = Math.max(ans, Math.max(num, -num));
            }
            set.add(num);
        }
        return ans;
    }


    //1072. 按列翻转得到最大值等行数
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        //翻转数组中的某几列，翻转后，求行与行之间所有值都相等的最大行数
        //两行之间元素全部相同或者相反，称为等价行
        //遍历矩阵每一行，如果开头第一个数字是0，保持当前行不变，如果是1，翻转这一行
        //用哈希表统计翻转后每一行转换成字符串的出现频率
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            char[] cs = new char[n];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                cs[j] = (char) (matrix[i][0] ^ matrix[i][j]);
                sb.append(cs[j]);
            }
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ans = Math.max(ans, entry.getValue());
        }
        return ans;
    }



    public static void main(String[] args) {
        System.out.println(111 % 7);
    }
}
