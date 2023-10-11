package example.algodata.solution.segmenttree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구간 합 구하기 문제 해답
 * BJ 2042
 */
public class BJ2042 {
    static class SegmentTree{
        long[] segmentTree;
        long[] array;
        SegmentTree(long[] array){
            this.array = array;
            this.segmentTree = new long[array.length * 4];
            makeSegmentTree();
        }

        void makeSegmentTree() {
            makeSegmentTree(1, 0, array.length - 1);
        }

        long makeSegmentTree(int node, int nodeCoverageStart, int nodeCoverageEnd){
            if(nodeCoverageStart == nodeCoverageEnd){
                segmentTree[node] = array[nodeCoverageStart];
                return segmentTree[node];
            }
            int nodeCoverageMid = (nodeCoverageStart+nodeCoverageEnd)/2;
            long leftSum = makeSegmentTree(node*2,nodeCoverageStart,nodeCoverageMid);
            long rightSum = makeSegmentTree(node * 2 + 1, nodeCoverageMid + 1, nodeCoverageEnd);
            segmentTree[node] = leftSum+rightSum;
            return segmentTree[node];
        }

        long sum(int targetStart, int targetEnd){
            return sum(1, 0, array.length-1, targetStart, targetEnd);
        }

        long sum(int node, int nodeCoverageStart, int nodeCoverageEnd,
                int targetStart, int targetEnd){
            if(targetEnd<nodeCoverageStart || nodeCoverageEnd<targetStart){
                return 0;
            }
            if(targetStart<=nodeCoverageStart && nodeCoverageEnd<=targetEnd){
                return segmentTree[node];
            }
            int nodeCoverageMid = (nodeCoverageStart+nodeCoverageEnd)/2;

            long leftSum = sum(node * 2, nodeCoverageStart, nodeCoverageMid, targetStart, targetEnd);
            long rightSum = sum(node * 2 + 1, nodeCoverageMid + 1, nodeCoverageEnd, targetStart, targetEnd);
            return leftSum + rightSum;
        }

        void update(int targetIdx, long newValue){
            update(1,0,array.length-1,targetIdx,
                    newValue-array[targetIdx]);
        }

        void update(int node, int nodeCoverageStart, int nodeCoverageEnd,
                    int targetIdx, long diff){
            if (targetIdx < nodeCoverageStart || nodeCoverageEnd < targetIdx) {
                return;
            }
            segmentTree[node]+=diff;
            if(nodeCoverageStart == nodeCoverageEnd){
                return;
            }
            int nodeCoverageMid = (nodeCoverageStart+nodeCoverageEnd)/2;
            update(node * 2, nodeCoverageStart, nodeCoverageMid, targetIdx, diff);
            update(node * 2 + 1, nodeCoverageMid + 1, nodeCoverageEnd, targetIdx, diff);
        }
    }

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(kb.readLine());
        int valueNum = Integer.parseInt(stk.nextToken());
        int changeNum = Integer.parseInt(stk.nextToken());
        int sumNum = Integer.parseInt(stk.nextToken());
        long[] array = new long[valueNum];
        for(int i=0;i<valueNum;i++){
            array[i] = Long.parseLong(kb.readLine());
        }
        long[][] commandArr = new long[changeNum+sumNum][3];
        for(int i=0;i<changeNum+sumNum;i++){
            stk = new StringTokenizer(kb.readLine());
            for(int k=0;k<3;k++){
                commandArr[i][k] = Long.parseLong(stk.nextToken());
            }
        }
        SegmentTree segmentTree = new SegmentTree(array);
        long[] answer = new long[sumNum];
        int idx = 0;
        for(long[] command: commandArr){
            if(command[0]==1) {
                segmentTree.update((int)command[1]-1, command[2]);
            } else{
                answer[idx++] = segmentTree.sum((int)command[1]-1,(int)command[2]-1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<sumNum;i++){
            sb.append(answer[i]).append('\n');
        }
        System.out.println(sb);
    }
}
