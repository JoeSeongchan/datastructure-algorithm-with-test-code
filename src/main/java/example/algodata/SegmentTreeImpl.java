package example.algodata;

/**
 * 출처: https://yabmoons.tistory.com/431
 * 세그먼트 트리 : 구간을 저장하기 위한 트리
 * {1,2,3,4,5} 배열에서 2번째 값부터 4번째 값까지의 합을 구하여라.
 * 모든 합을 다 구해놓고 계산하는 방식의 문제점 : 값을 바꿀 때 대처하기가 너무 어렵다. 시간복잡도 대폭 상승
 * N: 원소의 개수, M: 연산의 수
 * O(MN)
 * O(logN)만으로 굉장히 효율적으로 해결할 수 있다.
 * 리프노드 : 배열의 값을 그대로 가지고 있다.
 * 브랜치노드 : 자식 노드들이 가지는 값들에 대한 연산 결과를 저장하고 있다.
 * 세그먼트 트리를 대상으로 2가지 연산을 진행해보자
 * 1번째 연산 : i~j 범위의 원소의 총합을 구하라
 * 2번째 연산 : i번째 원소의 값을 바꿔라
 * 세그먼트 트리는 이진 트리의 모습을 가지고 있다.
 * 데이터의 개수가 N일 때,
 *  - 트리의 높이 : ceil(log2(N))
 *  - 필요한 노드 : 2^(높이+1)
 *  * 0번째 index는 사용하지 않는다.
 *      0번째 index를 쓰면 트리의 자식과 부모 관계를 계산하는데 있어서 복잡해지기 때문에
 * 보통은 주어진 배열의 크기 * 4 를 노드의 개수로 사용한다.
 * 재귀를 사용해서 세그먼트 트리를 만든다. (현재 노드 번호, 주어진 배열의 시작 범위, 주어진 배열의 마지막 범위)
 *
 * 세그먼트 트리를 만드는 과정
 *      - 주어진 범위를 반으로 나눈다.
 *      - 나눠진 2개의 범위에 대해서 '왼쪽 범위'에 대한 재귀호출을 한다.
 *      - 나눠진 2개의 범위에 대해서 '오른쪽 범위'에 대한 재귀호출을 한다.
 *      - 위의 과정을 반복한다.
 * 재귀의 기저 조건은 무엇인가? 시작하는 범위 == 끝나는 범위일때이다.
 *
 *
 */
public class SegmentTreeImpl implements SegmentTree{

    private int[] segmentTree;
    private int[] array;

    public SegmentTreeImpl(int[] array) {
        this.segmentTree = new int[array.length*4];
        this.array = array;
        makeSegmentTree();
    }

    void makeSegmentTree(){
        makeSegmentTree(1,0,array.length-1);
    }

    int makeSegmentTree(int node, int start, int end){
        if(start==end) {
            return segmentTree[node] = array[start];
        }
        int mid = (start+end) / 2;
        int leftResult = makeSegmentTree(node * 2, start, mid);
        int rightResult = makeSegmentTree(node * 2 + 1, mid + 1, end);
        segmentTree[node] = leftResult + rightResult;
        return segmentTree[node];
    }

    @Override
    public int sum(int startIdx, int endIdx) {
        return sum(1, 0, array.length - 1, startIdx, endIdx);
    }

    private int sum(int node,int nodeCoverageStart,int nodeCoverageEnd,int targetStart,int targetRight){
        if(targetStart>nodeCoverageEnd || targetRight<nodeCoverageStart) return 0;
        if(targetStart<=nodeCoverageStart && nodeCoverageEnd <=targetRight) return segmentTree[node];
        int nodeCoverageMid = (nodeCoverageStart+nodeCoverageEnd)/2;
        int leftSum = sum(node * 2, nodeCoverageStart, nodeCoverageMid, targetStart, targetRight);
        int rightSum = sum(node * 2 + 1, nodeCoverageMid + 1, nodeCoverageEnd, targetStart, targetRight);
        return leftSum + rightSum;
    }

    @Override
    public void update(int idx, int newValue) {
        update(1, 0, array.length - 1, idx, newValue - array[idx]);
    }

    private void update(int node, int nodeCoverageStart, int nodeCoverageEnd, int index, int diff){
        if (index < nodeCoverageStart || index > nodeCoverageEnd) {
            return;
        }
        segmentTree[node] = segmentTree[node]+diff;
        if(nodeCoverageStart == nodeCoverageEnd) {
            return;
        }
        int nodeCoverageMid = (nodeCoverageStart+nodeCoverageEnd)/2;
        update(node * 2, nodeCoverageStart, nodeCoverageMid, index, diff);
        update(node * 2 + 1, nodeCoverageMid + 1, nodeCoverageEnd, index, diff);
    }
}
