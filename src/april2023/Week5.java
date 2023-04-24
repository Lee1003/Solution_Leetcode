package april2023;

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
}
