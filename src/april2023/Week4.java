package april2023;

import javax.swing.*;
import java.util.*;

public class Week4 {


    //2409. 统计共同度过的日子数
    public static int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int arriveA = dateToDay(arriveAlice);
        int leaveA = dateToDay(leaveAlice);
        int arriveB = dateToDay(arriveBob);
        int leaveB = dateToDay(leaveBob);

        if(leaveA < arriveB || leaveB < arriveA){
            return 1;
        }

        return Math.min(leaveA, leaveB) - Math.max(arriveA, arriveB) + 1;
    }

    public static int dateToDay(String date){
        String[] strs = date.split("-");
        int month = Integer.parseInt(strs[0]);
        int day = Integer.parseInt(strs[1]);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 31);
        map.put(2, 28);
        map.put(3, 31);
        map.put(4, 30);
        map.put(5, 31);
        map.put(6, 30);
        map.put(7, 31);
        map.put(8, 31);
        map.put(9, 30);
        map.put(10, 31);
        map.put(11, 30);
        map.put(12, 31);
        int days = 0;
        for (int i = 1; i <= month - 1; i++) {
            days += map.get(i);
        }
        days += day;
        return days;
    }


    //299. 猜数字游戏
    public static String getHint(String secret, String guess) {
        //Bulls secret和guess的字符与位置必须完全匹配，每找到一个bull，就用一个list记录当前的坐标
        List<Integer> list = new ArrayList<>();
        int cow = 0;
        for (int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)){
                cow++;
                list.add(i);
            }
        }
        System.out.println(list);
        //重新遍历secret和guess，把除了bulls的位置全部加入两个HashMap<数字（Character），出现的次数（Integer）>，数字只能是0-9，
        Map<Integer, Integer> mapSecret = new HashMap<>();
        Map<Integer, Integer> mapGuess = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            if(!list.contains(i)){
                char s = secret.charAt(i);
                int num = s - '0';
                mapSecret.put(num, mapSecret.getOrDefault(num, 0) + 1);
            }
        }
        for (int i = 0; i < guess.length(); i++) {
            if(!list.contains(i)){
                char g = guess.charAt(i);
                int num = g - '0';
                mapGuess.put(num, mapGuess.getOrDefault(num, 0) + 1);
            }
        }
        //遍历其中一个HashMap，找到每个数字对于secret和guess较小的那个出现次数，就是cow
        int bull = 0;
        for (int i = 0; i <= 9; i++) {
            if(mapSecret.containsKey(i) && mapGuess.containsKey(i)){
                int freqSecret = mapSecret.get(i);
                int freqGuess = mapGuess.get(i);
                bull += Math.min(freqSecret, freqGuess);
            }
        }
        //返回答案
        return new StringBuilder().append(cow).append("A").append(bull).append("B").toString();
    }


    //539. 最小时间差
    public int findMinDifference(List<String> timePoints) {
        List<Integer> list = new ArrayList<>();
        for(String str : timePoints){
            list.add(toMinutes(str));
        }
        Collections.sort(list);
        int res = 1440;
        for (int i = 0; i < list.size() - 1; i++) {
            int diff1 = list.get(i + 1) - list.get(i);
            int diff2 = 1440 - diff1;
            res = Math.min(res, Math.min(diff1, diff2));
        }

        int diff3 = 1440 - (list.get(list.size() - 1) - list.get(1));
        System.out.println(res);
        System.out.println(diff3);
        return Math.min(res, diff3);
    }

    public static int toMinutes(String str){
        String[] strs = str.split(":");
        int hour = Integer.parseInt(strs[0]);
        int minute = Integer.parseInt(strs[1]);
        return 60 * hour + minute;
    }

    //TODO 1026. 节点与其祖先之间的最大差值


    //553. 最优除法
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        if (n == 1){
            return String.valueOf(nums[0]);
        }
        if (n == 2){
            return String.valueOf(nums[0]) + "/" + String.valueOf(nums[1]);
        }
        StringBuilder ans = new StringBuilder();
        ans.append(nums[0]);
        ans.append("/");
        ans.append("(");
        ans.append(nums[1]);
        for (int i = 2; i < n; i++) {
            ans.append("/");
            ans.append(nums[i]);
        }
        ans.append(")");
        return ans.toString();
    }


    //537. 复数乘法
    public static String complexNumberMultiply(String num1, String num2) {
        String[] s1 = num1.split("\\+");
        String[] s2 = num2.split("\\+");
        int n1Real = Integer.parseInt(s1[0]);
        int n2Real = Integer.parseInt(s2[0]);
        int n1Unreal = Integer.parseInt(s1[1].substring(0, s1[1].length() - 1));
        int n2Unreal = Integer.parseInt(s2[1].substring(0, s2[1].length() - 1));

        int i1 = n1Real * n2Real - n1Unreal * n2Unreal;
        int i2 = n1Real * n2Unreal + n2Real * n1Unreal;

        StringBuilder ans = new StringBuilder();
        ans.append(i1);
        ans.append("+");
        ans.append(i2);
        ans.append("i");
        return ans.toString();
    }


    //TODO  没看懂题解
    //1043. 分隔数组以得到最大和
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int maxValue = arr[i - 1];
            for (int j = i - 1; j >= 0 && j >= i - k; j--) {
                d[i] = Math.max(d[i], d[j] + maxValue * (i - j));
                if (j > 0) {
                    maxValue = Math.max(maxValue, arr[j - 1]);
                }
            }
        }
        return d[n];
    }


    //300. 最长递增子序列
    public static int lengthOfLIS(int[] nums) {
        int maxSubSequence = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            int perSubSequence = 1;
            for (int j = i + 1; j < nums.length; j++){
                if(nums[j] > list.get(list.size() - 1)){
                    list.add(nums[j]);
                    perSubSequence++;
                }
            }
            System.out.println(perSubSequence);
            maxSubSequence = Math.max(maxSubSequence, perSubSequence);
        }
        return maxSubSequence;
    }


    //592. 分数加减运算
    public String fractionAddition(String expression) {
        //把 - 替换成 +- ，用split("\\+")分离expression
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if(expression.charAt(i) == '-'){
                stringBuilder.append("+-");
            }else{
                stringBuilder.append(expression.charAt(i));
            }
        }
        String str = stringBuilder.toString();
        String[] strs = str.split("\\+");
        //遍历字符串数组，用split("/")分割每一个分数，除号前面的就是分子，后面就是分母
        //定义第一个分数为 0/1 x, y 第二个分数是x1/y1， 那么相加的结果就是 (xy1+yx1)/yy1
        //把每一个分数都相加
        long numerator = 0;
        long denominator = 1;
        for (int i = 0; i < strs.length; i++) {
            if(!strs[i].equals("")){
                String[] fraction = strs[i].split("/");
                long numerator1 = Integer.parseInt(fraction[0]);
                long denominator1 = Integer.parseInt(fraction[1]);
                numerator = numerator * denominator1 + denominator * numerator1;
                denominator = denominator * denominator1;
            }
        }
        //计算最大公约数，判断正负，输出答案
        if(numerator == 0){
            return "0/1";
        }
        long g = gcd(Math.abs(numerator), denominator);
        return Long.toString(numerator / g) + "/" + Long.toString(denominator / g);
    }
    //计算最大公约数
    public long gcd(long a, long b) {
        long remainder = a % b;
        while(remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }


    //640. 求解方程
    public static String solveEquation(String equation) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < equation.length(); i++) {
            if(equation.charAt(i) == '-'){
                stringBuilder.append("+-");
            }else{
                stringBuilder.append(equation.charAt(i));
            }
        }
        String str = stringBuilder.toString();
        String[] strs = str.split("=");
        int coeLeft = 0;
        int coeRight = 0;
        String[] left = strs[0].split("\\+");
        String[] right = strs[1].split("\\+");
        for (int i = 0; i < left.length; i++) {
            if(!left[i].equals("")) {
                if(left[i].indexOf('x') == -1){
                    coeRight -= Integer.parseInt(left[i]);
                }else{
                    if (left[i].equals("-x")){
                        coeLeft -= 1;
                    }else if(left[i].equals("x")){
                        coeLeft += 1;
                    }else{
                        coeLeft += Integer.parseInt(left[i].substring(0, left[i].length() - 1));
                    }
                }
            }
        }
        for (int i = 0; i < right.length; i++) {
            if(!right[i].equals("")) {
                if(right[i].indexOf('x') == -1){
                    coeRight += Integer.parseInt(right[i]);
                }else{
                    if (right[i].equals("-x")){
                        coeLeft += 1;
                    }else if(right[i].equals("x")){
                        coeLeft -= 1;
                    }else{
                        coeLeft -= Integer.parseInt(right[i].substring(0, right[i].length() - 1));
                    }
                }
            }
        }
        if (coeLeft == 0) {
            if (coeRight == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }else{
            if (coeRight == 0){
                return "x=0";
            } else {
                if (coeRight / coeLeft == 0){
                    return "No solution";
                } else if (coeRight % coeLeft != 0) {
                    return "No solution";
                }else {
                    return new StringBuilder().append("x").append("=").append(coeRight / coeLeft).toString();
                }
            }
        }
    }

    //TODO 1187. 使数组严格递增


    //38. 外观数列
    public static String countAndSay(int n) {
        if (n == 1){
            return "1";
        }
        String[] ss = new String[n];
        ss[0] = "1";
        ss[1] = "11";
        for (int i = 2; i < n; i++) {
            String str = ss[i - 1];
            StringBuilder sb = new StringBuilder();
            char c;
            int count = 1;
            for (int j = 0; j < str.length() - 1; j++) {
                if (j == str.length() - 2) {
                    if (str.charAt(j + 1) == str.charAt(j)){
                        count++;
                        c = str.charAt(j);
                        sb.append(count);
                        sb.append(c);
                        break;
                    } else {
                        c = str.charAt(j);
                        sb.append(count);
                        sb.append(c);
                        sb.append(1);
                        sb.append(str.charAt(str.length() - 1));
                        break;
                    }
                }
                if (str.charAt(j + 1) == str.charAt(j)){
                    count++;
                } else {
                    c = str.charAt(j);
                    sb.append(count);
                    sb.append(c);
                    count = 1;
                }
            }
            ss[i] = sb.toString();
        }
        return ss[n - 1];
    }



    public static int compress(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                System.out.println(write);
                int num = read - left + 1;
                if (num > 1) {
                    int anchor = write;
                    while (num > 0) {
                        chars[write++] = (char) (num % 10 + '0');
                        num /= 10;
                    }

                    reverse(chars, anchor, write - 1);

                }
                left = read + 1;
            }

        }
        return write;
    }

    //443.压缩字符串
    public static void reverse(char[] cs, int left, int right) {
        while (left < right) {
            char temp = cs[left];
            cs[left] = cs[right];
            cs[right] = temp;
            left++;
            right--;
        }
    }


    //392. 判断子序列
    public static boolean isSubsequence(String s, String t) {
        //遍历t，同时用一个指针index对着s，如果对应的字符相同，index++，如果对应的字符不同，向后遍历，如果index走到末尾
        //说明是子序列，如果t遍历完全后index还没有指向末尾，说明不是子序列
        int m = s.length();
        int n = t.length();
        int i = 0;
        int j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }


    //524. 通过删除字母匹配到字典里最长单词
    public static String findLongestWord(String s, List<String> dictionary) {
        //判断dic里面的元素是否为s的子序列，如果是的话，加入list
        List<String> list = new ArrayList<>();
        for (String str : dictionary) {
            if (isSubsequence(str, s)) {
                list.add(str);
            }
        }
        //如果list里面没有元素，说明没有符合的，返回空字符串
        if (list.size() == 0) {
            return "";
        }
        //如果有元素，重新遍历，获得最长长度maxLen
        int maxLen = 0;
        for (String str : list) {
            maxLen = Math.max(str.length(), maxLen);
        }
        //重新遍历list，如果有长度为maxLen的字符串，加入新的list
        List<String> newList = new ArrayList<>();
        for (String str : list) {
            if (str.length() == maxLen) {
                newList.add(str);
            }
        }
        //如果newList里面只有一个元素，直接输出第一个元素
        if (newList.size() == 1) {
            return newList.get(0);
        }

        String old = newList.get(0);
        //如果newList里面不止一个元素，则输出字母序最小的字符串
        for (int i = 0; i < newList.get(0).length(); i++) {
            for (int j = 0; j < newList.size() - 1; j++) {
                for (int k = j + 1; k < newList.size(); k++) {
                    String str1 = newList.get(j);
                    String str2 = newList.get(k);
                    char c1 = str1.charAt(i);
                    char c2 = str2.charAt(i);
                    if (c1 > c2) {
                        newList.set(j, str2);
                        newList.set(k, str1);
                    }
                }
            }
            if (i == 1 && newList.get(0).equals(old)) {
                break;
            }
            if (!newList.get(0).equals(old)) {
                break;
            }
        }
        //newList中第一个元素就是字典序最小的，直接返回
        return newList.get(0);
    }


    //1105. 填充书架
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1000000);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int currWidth = 0;
            int maxHeight = 0;
            for (int j = i; j >= 0; j--) {
                currWidth += books[j][0];
                if (currWidth > shelfWidth) {
                    break;
                }
                maxHeight = Math.max(maxHeight, books[j][1]);
                dp[i + 1] = Math.min(dp[i + 1], dp[j] + maxHeight);
            }
        }
        return dp[n];
    }


    //482. 密钥格式化
    public static String licenseKeyFormatting(String s, int k) {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                cnt++;
                ans.append(Character.toUpperCase(s.charAt(i)));
                if (cnt % k == 0) {
                    ans.append("-");
                }
            }
        }
        if (ans.length() > 0 && ans.charAt(ans.length() - 1) == '-') {
            ans.deleteCharAt(ans.length() - 1);
        }

        return ans.reverse().toString();
    }

    //1163. 按字典序排在最后的子串
    //TODO 超时待优化
    public String lastSubstring(String s) {
        //遍历s,找到字母表靠后的字母，记录其下标，加入一个list
        //如果只有一个，直接返回subString（下标）
        //如果有很多个，新建一个strList，把所有对应下表的subString（下标）都放进去
        //从第二个开始循环，如果循环到的元素的字母序比第一个大，交换
        //循环完成后保证strList第一个元素的字母序是最大的，直接返回
        int maxAsc = 0;
        for (int i = 0; i < s.length(); i++) {
            maxAsc = Math.max(s.charAt(i), maxAsc);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == maxAsc) {
                list.add(i);
            }
        }
        if (list.size() == 1) {
            return s.substring(list.get(0));
        }
        List<String> strList = new ArrayList<>();
        for (int i : list) {
            strList.add(s.substring(i));
        }
        for (int i = 1; i < strList.size(); i++) {
            if (strList.get(0).compareTo(strList.get(i)) < 0) {
                strList.set(0, strList.get(i));
                strList.set(i, strList.get(0));
            }
        }
        return strList.get(0);
    }


    //28. 找出字符串中第一个匹配项的下标
    //TODO 了解TMP算法
    public static int strStr(String haystack, String needle) {
        //遍历haystack，从第一个字符开始，截取长度等于needle的字符串
        //如果截取的字符串equals needle 直接返回i
        //最后返回-1， 表示没有符合的子串
        int len = needle.length();
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.substring(i).length() < len) {
                break;
            }
            String str = haystack.substring(i, i + len);
            if (str.equals(needle)) {
                return i;
            }
        }
        return -1;
    }


    //686. 重复叠加字符串匹配
    //TODO 超时
    public int repeatedStringMatch(String a, String b) {
        //循环 0至(b / a + 1) * 2 次
        //判断循环后，b是否为a叠加的子串，如果是返回次数
        if (isSubString(b, a)) {
            return 0;
        }
        int times = (b.length() / a.length() + 1) * 2;
        for (int i = 1; i <= times; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(a);
            for (int j = 1; j <= i; j++) {
                sb.append(a);
            }
            if (isSubString(b, sb.toString())) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isSubString(String a, String b) {
        //判断a是否为b的子串
        int len = a.length();
        for (int i = 0; i < b.length(); i++) {
            if (b.substring(i).length() < len) {
                break;
            }
            String str = b.substring(i, i + len);
            if (str.equals(a)) {
                return true;
            }
        }
        return false;
    }

    //500. 键盘行
    public String[] findWords(String[] words) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('q', 1);
        map.put('w', 1);
        map.put('e', 1);
        map.put('r', 1);
        map.put('t', 1);
        map.put('y', 1);
        map.put('u', 1);
        map.put('i', 1);
        map.put('o', 1);
        map.put('p', 1);

        map.put('a', 2);
        map.put('s', 2);
        map.put('d', 2);
        map.put('f', 2);
        map.put('g', 2);
        map.put('h', 2);
        map.put('j', 2);
        map.put('k', 2);
        map.put('l', 2);

        map.put('z', 3);
        map.put('x', 3);
        map.put('c', 3);
        map.put('v', 3);
        map.put('b', 3);
        map.put('n', 3);
        map.put('m', 3);

        List<String> ans = new ArrayList<>();
        for (String s : words) {
            int correctSum = map.get(Character.toLowerCase(s.charAt(0))) * s.length();
            int sum = 0;
            for (int i = 0; i < s.length(); i++) {
                sum += map.get(Character.toLowerCase(s.charAt(i)));
            }
            if (sum == correctSum) {
                ans.add(s);
            }
        }
        String[] strs = new String[ans.size()];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = ans.get(i);
        }
        return strs;
    }






    public static void main(String[] args) {
//        String a = "cacacb";
//        String b = "cb";
//        System.out.println(a.compareTo(b));
        String haystack = "llaaa";
        String needle = "hello";
        System.out.println(isSubString(haystack, needle));


    }
}
