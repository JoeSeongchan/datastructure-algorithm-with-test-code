package example.algodata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LineSweepingImpl implements LineSweeping{
    static class Line implements Comparable<Line>{
        int start, end;
        Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line other) {
            if(start<other.start) return -1;
            if(start>other.start) return 1;
            return Integer.compare(other.end - other.start, end - start);
        }
    }

    private List<Line> lineList;
    public LineSweepingImpl(int[][] lineArr) {
        lineList = new ArrayList<>();
        for (int[] line : lineArr) {
            lineList.add(new Line(line[0], line[1]));
        }
        Collections.sort(lineList);
    }

    @Override
    public int getLength() {
        int end = 0;
        int totalLength = 0;
        for(Line line:lineList){
            if(end>=line.start && end<line.end){
                totalLength += (line.end - end);
                end = line.end;
            } else if(end<line.start) {
                totalLength += (line.end - line.start);
                end = line.end;
            }
        }
        return totalLength;
    }
}
