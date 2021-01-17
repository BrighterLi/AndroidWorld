package com.xiaoming.a005algorithm.other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

//https://www.nowcoder.com/practice/e3769a5f49894d49b871c09cadd13a61?tpId=194&&tqId=35802&rp=1&ru=/activity/oj&qru=/ta/job-code-high-client/question-ranking
//设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
//set(key, value)：将记录(key, value)插入该结构
//get(key)：返回key对应的value值
//[要求]
//set和get方法的时间复杂度为O(1)
//某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
//当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
//若opt=1，接下来两个整数x, y，表示set(x, y)
//若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
//对于每个操作2，输出一个答案
public class LRU {

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    //方法1：使用LinkedHashMap，仅仅使用LinkedHashMap链表属性
    public int[] LRU (int[][] operators, int k) {
        // write code here
        LinkedHashMap linkedMap = new LinkedHashMap<Integer, Integer>();
        ArrayList resultList = new ArrayList();
        for(int i= 0; i<operators.length;i++) {
            if(operators[i][0] == 1) {
                if(linkedMap.size() < k) {
                    linkedMap.put(operators[i][1], operators[i][2]);//
                } else {
                    Iterator it = linkedMap.keySet().iterator();
                    linkedMap.remove(it.next()); //链表去掉头部节点
                    linkedMap.put(operators[i][1], operators[i][2]); //在链表尾部重新放入该节点
                }
            } else if(operators[i][0] == 2) {
                if(linkedMap.containsKey(operators[i][1])) {
                    int result = (int)linkedMap.get(operators[i][1]);
                    linkedMap.remove(operators[i][1]); //先一处之前存在的该节点
                    linkedMap.put(operators[i][1], result); //再在链表尾部添加该节点，成为最近的节点
                    resultList.add(result);
                } else {
                    resultList.add(-1);
                }
            }
        }
        int[] resultArray = new int[resultList.size()];
        for(int i=0;i<resultList.size();i++) {
            resultArray[i] = (int)resultList.get(i);
        }
        return resultArray;
    }

    //方法2：使用LinkedHashMap，本身具有LRU特性
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU2 (int[][] operators, int k) {
        LRUCache<Integer, Integer> lru = new LRUCache<>(k);
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0; i<operators.length; i++){
            if(operators[i][0] == 1)
                lru.put(operators[i][1], operators[i][2]);
            else if(operators[i][0] == 2) {
                if(lru.get(operators[i][1]) != null)
                    list.add(lru.get(operators[i][1]));
                else
                    list.add(-1);
            }
        }

        int[] result = new int[list.size()];
        for(int i=0; i<list.size(); i++)
            result[i] = (int)list.get(i);

        return result;
    }

    class LRUCache<K, V> extends LinkedHashMap<K, V> {
        //容量
        private int max;

        public LRUCache(int max) {
            //调用父类LinkedHashMap的构造方法，true表示实现访问有序
            super(16, 0.75f, true);
            this.max = max;
        }

        //重写删除策略，当结点数量大于容量，启用删除
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > max;
        }
    }

    //方法3：自己设计数据结构
}
