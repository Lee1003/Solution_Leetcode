package may2023;

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

    public static void main(String[] args) {
        char c = '1';
        System.out.println(c - '0');
    }
}
