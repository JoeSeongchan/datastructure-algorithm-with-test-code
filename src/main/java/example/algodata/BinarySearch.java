package example.algodata;

import java.util.List;

public class BinarySearch {
    private final List<Integer> list;

    public BinarySearch(List<Integer> list) {
        this.list = list;
    }

    public int search(int value){
        int idx = recurse(0, list.size() - 1, value);
        if(idx>=list.size()){
            return Integer.MAX_VALUE;
        }
        return idx;
    }

    private int recurse(int st, int ed, int value){
        if(st>ed){
            return st;
        }
        int mid = (st+ed)/2;
        if(list.get(mid)==value){
            return mid;
        } else if(list.get(mid)>value){
            return recurse(st,mid-1,value);
        } else {
            return recurse(mid + 1, ed, value);
        }
    }
}
