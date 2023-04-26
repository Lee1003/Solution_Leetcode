package april2023;

import java.util.*;

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


    //28. 找出字符串中第一个匹配项的下标
    //TODO 了解KMP算法
    public static int strStr(String haystack, String needle) {
        //遍历haystack，从第一个字符开始，截取长度等于needle的字符串
        //如果截取的字符串equals needle 直接返回i
        //最后返回-1， 表示没有符合的子串
//        int len = needle.length();
//        for (int i = 0; i < haystack.length(); i++) {
//            if (haystack.substring(i).length() < len) {
//                break;
//            }
//            String str = haystack.substring(i, i + len);
//            if (str.equals(needle)) {
//                return i;
//            }
//        }
//        return -1;

        //KMP算法
        int[] next = buildNext(needle);
        int i = 0, j = 0;
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) { //字符匹配，指针后移
                i++;
                j++;
            } else if (j > 0) { //不匹配，根据next跳过pattern串前面的一些字符
                j = next[j - 1];
            } else { //pattern串第一个字符就不匹配
                i++;
            }

            if (j == needle.length()) { //匹配成功，返回坐标
                return i - j;
            }
        }
        return -1;
    }

    public static int[] buildNext(String pattern) {
        int n = pattern.length();
        int[] next = new int[n];
        next[0] = 0;
        int prefix_len = 0;
        int i = 1;
        while (i < n) {
            if (pattern.charAt(prefix_len) == pattern.charAt(i)) {
                prefix_len++;
                next[i] = prefix_len;
                i++;
            } else {
                if (prefix_len == 0) {
                    next[i] = 0;
                    i++;
                } else {
                    prefix_len = next[prefix_len - 1];
                }
            }
        }
        return next;
    }



    //686. 重复叠加字符串匹配
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ans = 0;
        //下界c1：复制过后的长度必须要大于b，上界c2 = c1 + 1
        while (sb.length() < b.length()) {
            ans++;
            sb.append(a);
        }
        sb.append(a);
        int index = strStr(sb.toString(), b);
        if (index == -1) {
            return -1;
        }
        //index + b.length() 为匹配成功后的结束位置，如果结束位置超过了n * c1，那么返回上界ans+1，否则返回下界ans
        return index + b.length() > a.length() * ans ? ans + 1 : ans;
    }


    //459. 重复的子字符串
    public static boolean repeatedSubstringPattern(String s) {
        //枚举前缀，如果s长度除前缀长度等于0，且前缀乘商等于原字符串s，返回true
        //至少重复两次，前缀只要遍历到中间就行了
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            String prefix = s.substring(0, i + 1);
            if (len % prefix.length() == 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < len / prefix.length(); j++) {
                    sb.append(prefix);
                }
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }


    //461. 汉明距离
    //TODO 优化
    public static int hammingDistance(int x, int y) {
        String s1 = toBinary(x);
        String s2 = toBinary(y);
        int m = s1.length();
        int n = s2.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.abs(m - n); i++) {
            sb.append(0);
        }
        sb.append(m < n ? s1 : s2);
        if (m < n) {
            s1 = sb.toString();
        } else {
            s2 = sb.toString();
        }
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count;

    }
    public static String toBinary(int i) {
        if (i == 0) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        int remainder = i / 2;
        while (i / 2 > 0) {
            ans.append(i % 2);
            i /= 2;
        }
        ans.append(1);
        return ans.reverse().toString();
    }


    //1031. 两个非重叠子数组的最大和
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        //枚举长度为secondLen的数组b的同时，用maxSumA维护长度为firstLen的数组的最大值
        //用前缀和数组加速计算
        //b开始的下标 firstLen+secondLen， 下标范围[i-secondLen,i)
        //每一次循环都更新maxSumA s[i-secondLen] - s[i-secondLen-firstLen]
        int n = nums.length;
        int[] s  = new int[n + 1]; //前缀和数组
        for (int i = 0; i < s.length; i++) {
            s[i + 1] = s[i] + nums[i];
        }
        return Math.max(f(s, firstLen, secondLen), f(s, secondLen, firstLen));
    }

    public static int f(int[] s, int firstLen, int secondLen) {
        int maxSumA = 0, res = 0;
        for (int i = firstLen + secondLen; i < s.length; i++) {
            maxSumA = Math.max(maxSumA, s[i - secondLen] - s[i - secondLen - firstLen]);
            res = Math.max(res, maxSumA + s[i] - s[i - secondLen]);
        }
        return res;
    }


    //209. 长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        //双指针，滑动窗口
        //固定right先计算sum
        //枚举left，如果sum>=target，移动左端点，直到不符合条件位置
        //sum减去左端点，同时向右移动左指针
        int n = nums.length;
        int sum = 0;
        int left = 0;
        int ans = n + 1;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
//            while (sum - nums[left] >= target) {
//                sum -= nums[left];
//                left++;
//            }
//            if (sum >= target) {
//                ans = Math.min(ans, right - left + 1);
//            }
            while (sum >= target) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return ans <= n ? ans : 0;
    }


    //713. 乘积小于 K 的子数组
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //nums数组元素都是正数，肯定越乘越大，固定右端点，计算sum，枚举左端点
        if (k == 0 || k == 1) {
            return 0;
        }
        int n = nums.length;
        int count = 0;
        int prod = 1;
        int left = 0;
        for (int right = 0; right < n; right++) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }

    //1048. 最长字符串链
    public int longestStrChain(String[] words) {
        //将words数组中的单词按照长度从短到长排序
        //写一个算法，判断两个String是否为子序列
        //dp[i] 为以i为下标的单词能构成的最长词链长度
        //对于每一个dp[i]，枚举dp[j] 如果words[i]和words[j]满足词链关系
        //dp[i] = dj[j] + 1，对于每一个i，dp[i] = max(dp[i], dp[j]) + 1);
        //返回dp[wordsLen - 1];
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (judgeWords(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static boolean judgeWords(String s1, String s2) {
        //默认s2长度大于s1, 判断s1是否为s2的子序列
        int m = s1.length(), n = s2.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }

    //3. 无重复字符的最长子串
    public static int lengthOfLongestSubstring(String s) {
        //枚举右端点，如果加入的右端点后，这个字符有重复，则向右移动左端点，直到没有重复的字符为止
        int n = s.length();
        int ans = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < n; right++) {
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
            while (map.get(s.charAt(right)) > 1) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }




    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);


        set.remove(2);
        System.out.println(set);
    }
}
