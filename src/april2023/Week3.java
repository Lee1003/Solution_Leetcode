package april2023;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Week3 {

    public static int[] nextLargerNodes(ListNode head) {
        ListNode temp = head;
        List<Integer> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }

        int[] ans = new int[list.size()];
        loop:
        for (int i = 0; i < ans.length; i++) {
            for (int j = i + 1; j < ans.length; j++) {
                if (list.get(j) > list.get(i)) {
                    ans[i] = list.get(j);
                    continue loop;
                }
                System.out.println(ans[i]);
            }
        }
        return ans;
    }

    //1041. 困于环中的机器人
    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int directionIndex = 0;
        int[][] directions = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; //向上，向左，向下，向右
        char[] cs = instructions.toCharArray();
        for(char c : cs){
            if(c == 'G'){
                x += directions[directionIndex][0];
                y += directions[directionIndex][1];
            }else if(c == 'L'){
                if(directionIndex == 3){
                    directionIndex = 0;
                }else{
                    directionIndex++;
                }
            }else{
                if(directionIndex == 0){
                    directionIndex = 3;
                }else{
                    directionIndex--;
                }
            }
        }
        return directionIndex != 0 || x == 0 && y == 0;
    }

    //203. 移除链表元素
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while(temp.next != null){
            if(temp.next.val == val){
                temp.next = temp.next.next;
            }else{
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }

    //237. 删除链表中的节点
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //19. 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //求链表长度
        int len = getLength(head);
        //哑节点
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        for (int i = 1; i < len - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    public static int getLength(ListNode head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }

    //83. 删除排序链表中的重复元素
    public ListNode deleteDuplicates(ListNode head){
        if(head == null){
            return null;
        }
        ListNode dummy = new ListNode(0, head);
        while(head.next != null){
            if(head.next.val == head.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return dummy.next;
    }


    //2404. 出现最频繁的偶数元素
    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : nums){
            if(i % 2 == 0){
                map.put(i, map.getOrDefault(i, 0) + 1);
            }
        }
        if(map.size() == 0){
            return -1;
        }

        int maxFreq = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int value = entry.getValue();
            maxFreq = Math.max(maxFreq, value);
        }

        int ans = Integer.MAX_VALUE;;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();
            if(value == maxFreq){
                ans = Math.min(ans, key);
            }
        }

        return ans;
    }

    //1147. 段式回文
    public static int longestDecomposition(String text) {
        int k = 1;
        int leftStart = 0;
        int leftEnd = 0;
        int rightStart = text.length() - 1;
        int rightEnd = text.length() - 1;

        while(leftEnd < rightEnd){
            String str1 = text.substring(leftStart, leftEnd + 1);
            String str2 = text.substring(rightEnd, rightStart + 1);
            if(str1.equals(str2)){
                k += 2;
                leftStart = leftEnd + 1;
                rightStart = rightEnd - 1;
                leftEnd = leftStart;
                rightEnd = rightStart;
            }else{
                leftEnd++;
                rightEnd--;
            }
        }
        if(leftStart == leftEnd && leftStart > rightEnd){
            return k - 1;
        }
        return k;
    }

    //82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates82(ListNode head) {
        if(head == null){
            return  null;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while(cur.next != null && cur.next.next != null){
            //将cur.next 以及所有后面拥有相同元素值的链表节点全部删除
            if(cur.next.val == cur.next.next.val){
                int x = cur.next.val;
                while(cur.next != null && cur.next.val == x){
                    cur.next = cur.next.next;
                }
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    //206. 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        int m = k %= list.size();
        rotate(list, 0, list.size() - 1);
        rotate(list, 0, m - 1);
        rotate(list, m, list.size() - 1);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (Integer integer : list) {
            cur.next = new ListNode(integer);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void rotate(List<Integer> list, int left, int right){
        while(left <= right){
            int a = list.get(left);
            int b = list.get(right);
            int temp = a;
            a = b;
            b = temp;
            list.set(left, a);
            list.set(right, b);
            left++;
            right--;
        }
    }

    //24. 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    //1023. 驼峰式匹配
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        int n = queries.length;
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            int p = 0;
            for (int j = 0; j < queries[i].length(); j++) {
                char c = queries[i].charAt(j);
                if(p < pattern.length() && pattern.charAt(p) == c){
                    p++;
                }else if(Character.isUpperCase(c)){
                    flag = false;
                    break;
                }
            }
            if(p < pattern.length()){
                flag = false;
            }
            res.add(flag);
        }
        return res;
    }


    //696. 计数二进制子串
    public static int countBinarySubstrings(String s) {
        List<Integer> list = new ArrayList<Integer>();
        int ptr = 0, n = s.length();
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 0;
            while (ptr < n && s.charAt(ptr) == c) {
                ++ptr;
                ++count;
            }
            list.add(count);
        }

        int ans = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            ans += Math.min(list.get(i), list.get(i + 1));
        }
        return ans;
    }


    //1042. 不邻接植花
    //TODO 思考为什么boolean数组长度为5，如果改成4会如何？
    public static int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] path : paths){
            adj[path[0] - 1].add(path[1] - 1);
            adj[path[1] - 1].add(path[0] - 1);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] colored = new boolean[5];
            for(int vertex : adj[i]){
                colored[ans[vertex]] = true;
            }

            for (int j = 1; j <= 4 ; j++) {
                if(!colored[j]){
                    ans[i] = j;
                    break;
                }
            }
        }
        return ans;
    }







    public static void main(String[] args) {
        int[][] garden = new int[][]{{1,2}, {2,3}, {3,1}};
        gardenNoAdj(3, garden);
    }
}
