package work;

import java.util.Arrays;

public class Greedy {
    public static void main(String[] args) {
    int[] g =  {1, 5, 3, 3, 4};
    int[] s= {4, 2, 1, 2, 1, 3};
    int ans = findContentChildren(g,s);
        System.out.println(ans);
    }
    public static int findContentChildren(int[] g, int[] s) {
        int i = 0; int j =0 ;
        if(s.length==0) return 0;
        int c=0;
        Arrays.sort(g);
        Arrays.sort(s);
        while(i<g.length&&j<s.length){
            if(g[i]<=s[j]){
                c++;
                i++;
                j++;
            }else{
                j++;
            }

        }
        return c;
    }
}
