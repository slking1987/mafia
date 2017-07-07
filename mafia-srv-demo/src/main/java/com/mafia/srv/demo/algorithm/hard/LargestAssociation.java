package com.mafia.srv.demo.algorithm.hard;

import java.util.*;

/**
 * Created by shaolin on 2017/6/6.
 */
public class LargestAssociation {

    /**
     * 简单推荐算法, 购买过相同商品的客户的兴趣一致
     * A客户与B客户都购买过商品item1, 则认为A客户也会对B客户购买的商品item4, item5感兴趣
     * @param itemMatrix String[][] 各个客户购买商品的二维数组
     * @return String[] 最大关联商品数组
     * eg. [["item1","item2"],["item2","item3"],["item2","item4","item5"]] => ["item1","item2","item3","item4","item5"]
     * eg. [["item1","item2"],["item3","item4"],["item4","item5"]] => ["item3","item4","item5"]
     * eg. [["item1","item2"],["item3","item4"],["item5"]] => ["item1","item2"]
     */
    public String[] solution2(String[][] itemMatrix) {
        // check
        // 获取所有关联组合
        // 排序获得最大关联组合
        return null;
    }

    private String[] getAsso(String[] itemArr, String[][] itemMatrix) {
        return null;
    }

    private String[] getAssoEasy(String[] itemArr, String[][] itemMatrix) {
        return null;
    }

    public String[] solution(String[][] itemAssociation) {
        // check
        if(itemAssociation == null || itemAssociation.length == 0) {
            return null;
        }

        String[] result = null;

        Map<Integer, Set<String>> assoMap = new LinkedHashMap<>();
        for(int i = 0; i < itemAssociation.length; i ++) {
            Set<String> tempSet = new HashSet<>();
            for(String tempItem : itemAssociation[i]) {
                tempSet.add(tempItem);
            }

            assoMap.put(i, tempSet);
        }

        Map<Integer, Set<String>> newAssoMap = new LinkedHashMap<>();
        Map<Integer, Integer> keyMap = new HashMap<>();
        for(Map.Entry<Integer, Set<String>> entry : assoMap.entrySet()) {
            Set<String> tempItemSet = new HashSet<>();
            Set<String> itemSet = entry.getValue();
            for(String item : itemSet) {
                for(Set<String> tempSet : assoMap.values()) {
                    if(tempSet.contains(item)) tempItemSet.addAll(tempSet);
                }
            }

            newAssoMap.put(entry.getKey(), tempItemSet);
            keyMap.put(entry.getKey(), tempItemSet.size());
        }



        return result;
    }

}
