package example.algodata;

import java.util.*;

// https://bcp0109.tistory.com/21

/**
 * DAG (Directed Acyclic Graph)에서만 사용할 수 있는 알고리즘
 * 순서에 맞게 실행되고 있는지 여부를 확인할 수 있다.
 * 인접 리스트와 indegree array를 통해 알고리즘을 코드로 구현할 수 있다.
 */
public class TopologicalSortImpl<T> implements TopologicalSort<T>{
    Map<T,Integer> indexMap;
    List<T> revIndexList;
    List<Map.Entry<T,T>> list;
    List<Integer>[] graph;
    int[] indegreeArr;

    public TopologicalSortImpl(List<Map.Entry<T,T>> list) {
        this.list = list;
        int idx=0;
        indexMap = new HashMap<>();
        revIndexList = new ArrayList<>();
        for(Map.Entry<T,T> entry: list){
            if(!indexMap.containsKey(entry.getKey())){
                indexMap.put(entry.getKey(), idx++);
                revIndexList.add(entry.getKey());
            }
            if (!indexMap.containsKey(entry.getValue())) {
                indexMap.put(entry.getValue(), idx++);
                revIndexList.add(entry.getValue());
            }
        }
        graph = new ArrayList[idx];
        indegreeArr = new int[idx];
        for (int i = 0; i < idx; i++) graph[i] = new ArrayList<>();
        for(Map.Entry<T,T> entry: list){
            int keyIdx = indexMap.get(entry.getKey());
            int valueIdx = indexMap.get(entry.getValue());
            graph[keyIdx].add(valueIdx);
            indegreeArr[valueIdx]++;
        }
    }
    @Override
    public boolean correct(List<T> list) {
        int[] backup = Arrays.copyOf(indegreeArr, indegreeArr.length);
        boolean isCorrect = true;
        for (T t : list) {
            int idx = indexMap.get(t);
            if(indegreeArr[idx]!=0) {
                isCorrect = false;
                break;
            }
            for(int next:graph[idx]){
                indegreeArr[next]-=1;
            }
        }
        indegreeArr = backup;
        return isCorrect;
    }
}
