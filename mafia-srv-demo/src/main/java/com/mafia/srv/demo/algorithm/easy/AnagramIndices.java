package com.mafia.srv.demo.algorithm.easy;

import com.mafia.core.util.JsonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shaolin on 2017/6/6.
 */
public class AnagramIndices {

    /**
     * anagram变位词（变换或颠倒字母顺序而成另一词）
     * 查找haystack中所有needle变位词的index
     * @param haystack
     * @param needle
     * @return
     */
    public static List<Integer> solution(String haystack, String needle)
    {
        // check
        if(haystack == null || haystack.length() == 0 || needle == null || needle.length() == 0 || needle.length() > haystack.length()) {
            return null;
        }

        List<Integer> result = new ArrayList<>();
        int hLength = haystack.length();
        int nLength = needle.length();
        String nCompFlag = genCompFlag(needle);
        for(int i = 0; i < hLength; i ++) {
            if(i + nLength > hLength) break;
            String temp = haystack.substring(i, i + nLength);
            if(genCompFlag(temp).equals(nCompFlag)) {
                result.add(i);
            }
        }
        return result;
    }

    private static String genCompFlag(String s) {
        char[] charArr = s.toCharArray();
        Arrays.sort(charArr);
        return new String(charArr);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(JsonUtil.writeValueQuite(AnagramIndices.solution("bbbbbaabba", "ab")));
        System.out.println(JsonUtil.writeValueQuite(AnagramIndices.solution("adbccaadgcbad", "dbca")));
    }
}
