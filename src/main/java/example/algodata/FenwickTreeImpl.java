package example.algodata;

public class FenwickTreeImpl implements FenwickTree{
    int[] array;
    int[] fenwickTree;
    int elementNum;

    public FenwickTreeImpl(int[] array) {
        this.array = new int[array.length+1];
        this.elementNum = array.length;
        System.arraycopy(array, 0, this.array, 1, array.length);
        fenwickTree = new int[array.length+1];
        makeTree();
    }

    @Override
    public int sum(int start, int end) {
        return sum(end) - sum(start-1);
    }

    int sum(int idx){
        int result = 0;
        while(idx>0){
            result = result + fenwickTree[idx];
            idx = idx - (idx & -idx);
        }
        return result;
    }

    void makeTree() {
        for (int i = 1; i <= elementNum; i++) {
            update(i, array[i]);
        }
    }

    void update(int idx, int newValue) {
        while (idx <= elementNum) {
            fenwickTree[idx] = fenwickTree[idx] + newValue;
            idx = idx + (idx & -idx);
        }
    }


}
