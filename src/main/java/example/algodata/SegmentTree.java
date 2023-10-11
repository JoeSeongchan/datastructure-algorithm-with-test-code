package example.algodata;

public interface SegmentTree {
    int sum(int startIdx, int endIdx);
    void update(int idx, int newValue);
}
