package april2023;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Week5 {

    //1163. 按字典序排在最后的子串
    public String lastSubstring(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        char c = '0';
        int pos = 0;
        //找到字典序最大的字符第一个出现的位置 pos 以及 pos对应的字符c
        for (int i = 0; i < n; i++) {
            if (cs[i] > c) {
                c = cs[i];
                pos = i;
            }
        }
        int right = pos + 1;
        while (right < n) {
            //指针right = pos+1 循环right， 如果right对应的字符不是c，那么right后缀字符串字典序必然不是最大的，直接right++进下一次循环
            if (cs[right] != c) {
                right++;
                continue;
            }
            //此时cs[right] == c，right遍历到了第二个字典序最大的字符 双指针i = pos+1, j = right + 1
            int i = pos + 1;
            int j = right + 1;
            //比较i，j的字符，如果一直相等，就都i, j++，注意j不能超过字符串长度n
            while (j < n && cs[i] == cs[j]) {
                i++; j++;
            }
            if (j >= n) {
                break;
            }
            //如果 j字符>i字符，说明第二个最大字符后缀字符串的字典序大于第一个，令pos = right
            if (cs[i] < cs[j]) {
                pos = right;
            }
            right++;
        }
        //最终的pos后缀字符串就是字典序最大的子串，直接返回subString(pos)
        return s.substring(pos);
    }


    //5. 最长回文子串
    public static String longestPalindrome(String s) {
        //特殊判断，如果s为空或者s长度为1，直接返回s
        if (s.equals("") || s.length() == 1) {
            return s;
        }
        //中心扩展法-枚举每一个可能的中心，向两边扩展
        int maxLen = 0;
        int begin = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int currLen = 0;
            //分别计算奇偶中心扩展回文串最大长度
            int oddLen = expandAroundCenter(s, i, i);
            int evenLen = expandAroundCenter(s, i, i + 1);
            currLen = Math.max(oddLen, evenLen);
            if (currLen > maxLen) {
                maxLen = currLen;
                begin = i - (maxLen - 1) / 2; //begin要在纸上推演一下
            }
        }
        return s.substring(begin, begin + maxLen);

    }
    public static int expandAroundCenter(String s, int left, int right) {
        //奇数 left = right， 偶数 left = right - 1 同时处理了奇偶两种情况
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    //647. 回文子串
    public int countSubstrings(String s) {
        //思路与第五题相同
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int oddCount = expandAroundCenterCount(s, i, i);
            int evenCount = expandAroundCenterCount(s, i, i + 1);
            count  = count + oddCount + evenCount;
        }
        return count;
    }
    public static int expandAroundCenterCount(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    //2418. 按身高排序
    public String[] sortPeople(String[] names, int[] heights) {
        //height[]所有元素互不相同，用一个map key存放height(互不相同保证key不会重复)
        //value 存放对应的坐标i, 给heights[]排倒序
        //遍历排倒序后的heights[]，找到每一个key对应的下标，下标对应到names[]就是对应的人名
        //新开一个res[]数组存放结果，返回res
        Map<Integer, Integer> map = new HashMap<>();
        int n = names.length;
        for (int i = 0; i < n; i++) {
            map.put(heights[i], i);
        }
        Arrays.sort(heights);
        String[] ans = new String[n];
        for (int i = 0; i < names.length; i++) {
            ans[i] = names[map.get(heights[n - i - 1])];
        }
        return ans;
    }



    public static void main(String[] args) {
        String str = "babad";
        System.out.println(longestPalindrome(str));
    }
}
