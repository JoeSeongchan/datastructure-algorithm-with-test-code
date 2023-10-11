package example.algodata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 참고 : https://sskl660.tistory.com/59
public class DijkstraImpl implements Dijkstra{
    static class Node implements Comparable<Node> {
        int idx, cost;
        Node(int idx, int cost){
            this.idx = idx;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
    List<Node> [] graph;
    int vertexNum;
    int[] distanceArr;
    int startVertex;

    public DijkstraImpl(List<int[]>[] rawGraph, int startVertex) {
        this.vertexNum = rawGraph.length;
        graph = new ArrayList[vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int stVert = 0; stVert < vertexNum; stVert++) {
            for(int[] info: rawGraph[stVert]){
                int edVert=info[0];
                int dist=info[1];
                graph[stVert].add(new Node(edVert, dist));
            }
        }
        this.distanceArr = new int[graph.length];
        Arrays.fill(distanceArr, Integer.MAX_VALUE);
        this.startVertex = startVertex;
        findAllShortestRouteLength();
    }

    private void findAllShortestRouteLength() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distanceArr[startVertex] = 0;
        pq.offer(new Node(startVertex, 0));
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            if (distanceArr[curNode.idx] < curNode.cost) {
                continue;
            }
            for (int i = 0; i < graph[curNode.idx].size(); i++) {
                Node nextNode = graph[curNode.idx].get(i);
                if (distanceArr[nextNode.idx] > curNode.cost + nextNode.cost) {
                    distanceArr[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.idx, distanceArr[nextNode.idx]));
                }
            }
        }
    }
    @Override
    public int findShortestRouteLength(int endIdx) {
        return distanceArr[endIdx];
    }
}
