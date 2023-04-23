package april2023;

import java.util.*;

public class Week2 {

    //2023.04.03
    //1053. 交换一次的先前排列
    public static int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 2; i >= 0; i--) { //为了保证交换后的字典序最大，交换的两个数组的位置要尽量在后面，所以从后向前遍历
            if (arr[i] > arr[i + 1]) {  //找到一个满足条件的i
                int j = n - 1; //从后到i遍历j
                while (arr[j] == arr[j - 1] || arr[j] >= arr[i]) { //当出现同一个j的时候取前面的那个，因为字典序最大j的位置也要靠后，所以找到第一个小于i的j就可以交换
                    j--;
                }
                int temp = arr[i]; //交换
                arr[i] = arr[j];
                arr[j] = temp;
                break;
            }
        }
        return arr;
    }

    //205. 同构字符串
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        char[] cs1 = s.toCharArray();
        char[] cs2 = t.toCharArray();
        for (int i = 0; i < cs1.length; i++) {
            if(map1.containsKey(cs1[i]) && map1.get(cs1[i]) != cs2[i] || map2.containsKey(cs2[i]) && map2.get(cs2[i]) != cs1[i]){
                return false;
            }else{
                map1.put(cs1[i], cs2[i]);
                map2.put(cs2[i], cs1[i]);
            }
        }
        return true;
    }

    //599. 两个列表的最小索引总和
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map1.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            map2.put(list2[i], i);
        }

        int minIndexSum = Integer.MAX_VALUE;
        List<String> list = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : map1.entrySet()){
            String resName = entry.getKey();
            int index = entry.getValue();
            if(map2.containsKey(resName)){
                int indexSum = map2.get(resName) + index;
                minIndexSum = Math.min(minIndexSum, indexSum);
            }
        }


        for(Map.Entry<String, Integer> entry : map1.entrySet()){
            String resName = entry.getKey();
            int index = entry.getValue();
            if(map2.containsKey(resName)){
                int indexSum = map2.get(resName) + index;
                if(indexSum == minIndexSum){
                    list.add(resName);
                }
            }
        }

        String[] ans = new String[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    //219. 存在重复元素 II
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){ //遍历到重复的数字了
                int index = map.get(nums[i]);
                if(i - index <= k){
                    return true;
                }else{
                    map.put(nums[i], i);
                }
            }else{
                map.put(nums[i], i);
            }
        }
        return false;
    }


    //594. 最长和谐子序列
    public static int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int maxLen = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int k = entry.getKey();
            if(map.containsKey(k + 1)){
                maxLen = Math.max(maxLen, map.get(k) + map.get(k + 1));
            }
        }
        return maxLen;
    }


    //2023.04.04
    //TODO 优化
    //350. 两个数组的交集 II
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for(int i : nums1){
            map1.put(i, map1.getOrDefault(i, 0) + 1);
        }
        for(int i : nums2){
            map2.put(i, map2.getOrDefault(i, 0) + 1);
        }

        List<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map1.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            if(map2.containsKey(key)){
                int value2 = map2.get(key);
                if(value >= value2){
                    for (int i = 0; i < value2; i++) {
                        list.add(key);
                    }
                }else{
                    for (int i = 0; i < value; i++) {
                        list.add(key);
                    }
                }
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    //2427. 公因子的数目
    public int commonFactors(int a, int b) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < a + 1; i++) {
            if(a % i == 0){
                set.add(i);
            }
        }

        int count = 0;
        for (int i = 1; i < b + 1; i++) {
            if(b % i == 0){
                if(set.contains(b)){
                    count++;
                }
            }
        }
        return count;
    }

    //554. 砖墙
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < wall.size(); i++) {
            List<Integer> list = wall.get(i);
            int sumEachRow = 0;
            for(int j = 0; j < list.size() - 1; j++){
                sumEachRow += list.get(j);
                map.put(sumEachRow, map.getOrDefault(sumEachRow, 0) + 1);
            }
        }
        int max = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int value = entry.getValue();
            max = Math.max(max, value);
        }
        return wall.size() - max;
    }

    //609. 在系统中查找重复文件
    public static List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : paths){
            String[] strArr = str.split(" ");
            for(String str1 :strArr){
                if(str1.contains("(")){
                    String name = str1.substring(str1.indexOf("("));
                    if(map.containsKey(name)){
                        map.get(name).add(strArr[0] + "/" + str1.substring(0, str1.indexOf("(")));
                    }else{
                        List<String> list = new ArrayList<>();
                        list.add(strArr[0] + "/" + str1.substring(0, str1.indexOf("(")));
                        map.put(name, list);
                    }
                }
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            String str = entry.getKey();
            List<String> list = entry.getValue();
            if(list.size() > 1){
                ans.add(list);
            }
        }

        return ans;
    }


    //454. 四数相加 II
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            for (int j : nums2) {
                int num = i + j;
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        for (int k : nums3) {
            for (int l : nums4) {
                int num = -k - l;
                if (map.containsKey(num)) {
                    count += map.get(num);
                }
            }
        }
        return count;
    }


    //14. 最长公共前缀
    public static String longestCommonPrefix(String[] strs) {
        int minLen = 300;
        for(String str : strs){
            minLen = Math.min(minLen, str.length());
        }

        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < minLen; i++){
            for(int j = 0; j < strs.length - 1; j++){
                if(strs[j + 1].charAt(i) != strs[j].charAt(i)){
                    return new String(ans);
                }
            }
            ans.append(strs[0].charAt(i));
        }
        return new String(ans);
    }

    //2023.04.06
    //1017. 负二进制转换
    public static String baseNeg2(int n) {
        if(n == 1 || n == 0){
            return String.valueOf(n);
        }

        StringBuilder ans = new StringBuilder();
        while(n != 0){
            int remainder = n % -2; //商
            n /= -2;
            if(remainder < 0){
                ans.append(-remainder);
                n += 1;
            }else{
                ans.append(remainder);
            }
        }
        ans.reverse();
        return new String(ans);
    }

    //434. 字符串中的单词数
    public int countSegments(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' '){
                count++;
            }
        }
        return count;
    }

    //58. 最后一个单词的长度
    public static int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while(s.charAt(index) == ' '){
            index--;
        }
        int len = 0;
        while(index >= 0 && s.charAt(index) != ' '){
            len++;
            index--;
        }
        return len;
    }

    //557. 反转字符串中的单词 III
    public static String reverseWords(String s) {
         String[] strArr = s.split(" ");
         StringBuilder ans = new StringBuilder();
         for (int i = 0; i < strArr.length; i++) {
            char[] cs = strArr[i].toCharArray();
            int left = 0;
            int right = cs.length - 1;
            while(left <= right){
                char temp = cs[left];
                cs[left] = cs[right];
                cs[right] = temp;
                left++;
                right--;
            }
            String str = new String(cs);
            if(i == strArr.length - 1){
                ans.append(str);
            }else{
                ans.append(str);
                ans.append(" ");
            }

         }
         return ans.toString();
    }

    //541. 反转字符串 II
    public static String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();
        int n = s.length();
        int quotient = n / (k * 2);
        int remainder = n % (k * 2);
        if(remainder < k){ //全部反转
            reversePreK(cs, quotient * k * 2, s.length() -1 );
        }else{  //反转前k个
            reversePreK(cs, quotient * k * 2, quotient * k * 2 + k - 1);
        }
        for (int i = 0; i < quotient; i++) {
            reversePreK(cs, i * 2 * k, i * 2 * k + k - 1);
        }
        return new String(cs);
    }

    public static void reversePreK(char[] cs, int left, int right){
        while(left <= right){
            char temp = cs[left];
            cs[left] = cs[right];
            cs[right] = temp;
            left++;
            right--;
        }
    }

    //151. 反转字符串中的单词
    public static String reverseWords151(String s) {
        char[] cs = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        int start = 0;
        int end = cs.length - 1;
        //处理头和尾巴，保证头尾都是单词，不会是空格
        for (int i = 0; i < cs.length; i++) {
            if(Character.isLetterOrDigit(cs[i])){
                start = i;
                break;
            }
        }
        for (int i = cs.length - 1; i >= 0 ; i--) {
            if(Character.isLetterOrDigit(cs[i])){
                end = i;
                break;
            }
        }
        for (int i = start; i <= end; i++) {
            if(Character.isLetterOrDigit(cs[i])){ //是字母或者数字，肯定要加入
                stringBuilder.append(cs[i]);
            }else{ //如果是空格，分情况讨论
                //只有cs[i-1]是字母的情况下才要加空格
                if(cs[i-1] != ' '){
                    stringBuilder.append(' ');
                }
            }
        }
        String str = new String(stringBuilder);
        String[] strArr = str.split(" ");
        int left = 0, right = strArr.length - 1;
        while(left <= right){
            String temp = strArr[left];
            strArr[left] = strArr[right];
            strArr[right] = temp;
            left++;
            right--;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            if(i == strArr.length - 1){
                ans.append(strArr[i]);
            }else{
                ans.append(strArr[i]);
                ans.append(" ");
            }
        }
        return ans.toString();
    }

    //387. 字符串中的第一个唯一字符
    public int firstUniqChar(String s) {
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for(char c : cs){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for(int i = 0; i < cs.length; i++){
            if(map.get(cs[i]) == 1){
                return i;
            }
        }
        return -1;
    }

    //389. 找不同
    public char findTheDifference(String s, String t) {
        int as = 0, at = 0;
        for (int i = 0; i < s.length(); ++i) {
            as += s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            at += t.charAt(i);
        }
        return (char) (at - as);
    }


    //2023.04.07
    //1040. 移动石子直到连续 II
    //看不懂最小值是怎么取的
    public static int[] numMovesStonesII(int[] stones) {
        int n = stones.length;
        Arrays.sort(stones);
        if (stones[n - 1] - stones[0] + 1 == n) {
            return new int[]{0, 0};
        }
        int ma = Math.max(stones[n - 2] - stones[0] + 1, stones[n - 1] - stones[1] + 1) - (n - 1);
        int mi = n;
        for (int i = 0, j = 0; i < n && j + 1 < n; ++i) {
            while (j + 1 < n && stones[j + 1] - stones[i] + 1 <= n) {
                ++j;
            }
            if (j - i + 1 == n - 1 && stones[j] - stones[i] + 1 == n - 1) {
                mi = Math.min(mi, 2);
            } else {
                mi = Math.min(mi, n - (j - i + 1));
            }
        }
        return new int[]{mi, ma};
    }


    //49. 字母异位词分组
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs){
            char[] cs = str.toCharArray();
            Arrays.sort(cs);
            String newStr = new String(cs);
            if(map.containsKey(newStr)){
                map.get(newStr).add(str);
            }else{
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(newStr, list);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            ans.add(entry.getValue());
        }
        return ans;
    }


    //451. 根据字符出现频率排序
    public String frequencySort(String s) {
        Map<Character, Integer> map1 = new HashMap<>();
        char[] cs = s.toCharArray();
        for(char c : cs){
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        Map<Integer, List<Character>> map2 = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry : map1.entrySet()){
            char c = entry.getKey();
            int i = entry.getValue();
            if(!list1.contains(i)){
                list1.add(i);
            }

            if(map2.containsKey(i)){
                map2.get(i).add(c);
            }else{
                List<Character> list = new ArrayList<>();
                list.add(c);
                map2.put(i, list);
            }
        }

        Collections.sort(list1);
        Collections.reverse(list1);
        StringBuilder ans = new StringBuilder();
        for (int freq : list1) {
            List<Character> list = map2.get(freq);
            for (char c : list) {
                for (int i = 0; i < freq; i++) {
                    ans.append(c);
                }
            }
        }
        return ans.toString();
    }

    //423. 从英文中重建数字
    public static String originalDigits(String s) {
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        Map<Integer, Integer> ansMap = new HashMap<>();
        for(char c : cs){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        if(map.containsKey('z') && map.get('z') > 0){
            ansMap.put(0, map.get('z'));
            map.put('e', map.get('e') - map.get('z'));
            map.put('r', map.get('r') - map.get('z'));
            map.put('o', map.get('o') - map.get('z'));
        }
        if(map.containsKey('w') && map.get('w') > 0){
            ansMap.put(2, map.get('w'));
            map.put('t', map.get('t') - map.get('w'));
            map.put('o', map.get('o') - map.get('w'));
        }
        if(map.containsKey('u') && map.get('u') > 0){
            ansMap.put(4, map.get('u'));
            map.put('f', map.get('f') - map.get('u'));
            map.put('o', map.get('o') - map.get('u'));
            map.put('r', map.get('r') - map.get('u'));
        }

        if(map.containsKey('x') && map.get('x') > 0){
            ansMap.put(6, map.get('x'));
            map.put('s', map.get('s') - map.get('x'));
            map.put('i', map.get('i') - map.get('x'));
        }
        if(map.containsKey('g') && map.get('g') > 0){
            ansMap.put(8, map.get('g'));
            map.put('e', map.get('e') - map.get('g'));
            map.put('i', map.get('i') - map.get('g'));
            map.put('h', map.get('h') - map.get('g'));
            map.put('t', map.get('t') - map.get('g'));
        }

        if(map.containsKey('f') && map.get('f') > 0){
            ansMap.put(5, map.get('f'));
            map.put('i', map.get('i') - map.get('f'));
            map.put('v', map.get('v') - map.get('f'));
            map.put('e', map.get('e') - map.get('f'));
        }


        if(map.containsKey('v') && map.get('v') > 0){
            ansMap.put(7, map.get('v'));
            map.put('s', map.get('s') - map.get('v'));
            map.put('e', map.get('e') - map.get('v') - map.get('v'));
            map.put('n', map.get('n') - map.get('v'));
        }

        if(map.containsKey('i') && map.get('i') > 0){
            ansMap.put(9, map.get('i'));
            map.put('n', map.get('n') - map.get('i') - map.get('i'));
            map.put('e', map.get('e') - map.get('i'));
        }

        if(map.containsKey('o') && map.get('o') > 0){
            ansMap.put(1, map.get('o'));
            map.put('n', map.get('n') - map.get('o'));
            map.put('e', map.get('e') - map.get('o'));
        }

        if(map.containsKey('t') && map.get('t') > 0){
            ansMap.put(3, map.get('t'));
            map.put('h', map.get('h') - map.get('t'));
            map.put('r', map.get('r') - map.get('t'));
            map.put('e', map.get('e') - map.get('t') - map.get('t'));
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if(ansMap.containsKey(i)){
                for (int j = 0; j < ansMap.get(i); j++) {
                    ans.append(i);
                }
            }
        }
        return ans.toString();
    }

    //657. 机器人能否返回原点
    public boolean judgeCircle(String moves) {
        Map<Character, Integer> map = new HashMap<>();
        char[] cs = moves.toCharArray();
        for(char c: cs){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        if(map.containsKey('U')){
            up = map.get('U');
        }
        if(map.containsKey('D')){
            down = map.get('D');
        }
        if(map.containsKey('L')){
            left = map.get('L');
        }
        if(map.containsKey('R')){
            right = map.get('R');
        }
        return up - down == 0 && left - right == 0;
    }

    //551. 学生出勤记录 I
    public static boolean checkRecord(String s) {
        char[] cs = s.toCharArray();
        int countA = 0;
        int countLate = 1;
        int maxLate = 1;
        if(cs[cs.length - 1] == 'A'){
            countA++;
        }
        for (int i = 0; i < cs.length - 1; i++) {
            if(cs[i] == 'A'){
                countA++;
            }
            if(cs[i] == 'L'){
                if(cs[i + 1] == 'L'){
                    countLate++;
                    maxLate = Math.max(maxLate, countLate);
                }else{
                    countLate = 1;
                }
            }
        }
        System.out.println(countA);
        System.out.println(maxLate);
        return countA < 2 && maxLate < 3;
    }

    //696. 计数二进制子串


    //506. 相对名次
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        int[] scoreCopy = new int[score.length];
        System.arraycopy(score, 0, scoreCopy, 0, scoreCopy.length);

        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(score);
        for(int i = 0; i < n; i++){
            map.put(score[i], n - i);
        }

        String[] ans = new String[score.length];
        for(int i = 0; i < score.length; i++){
            if(map.get(scoreCopy[i]) == 1){
                ans[i] = "Gold Medal";
            }else if(map.get(scoreCopy[i]) == 2){
                ans[i] = "Silver Medal";
            }else if(map.get(scoreCopy[i]) == 3){
                ans[i] = "Bronze Medal";
            }else{
                ans[i] = String.valueOf(map.get(scoreCopy[i]));
            }
        }
        return ans;
    }
    public static void main(String[] args) {


    }
}
