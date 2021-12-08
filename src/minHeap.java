import api.NodeData;

import java.util.ArrayList;

public class minHeap {
    ArrayList<pair> data;
    int heapSize;

    minHeap() {
        data = new ArrayList<>();
        heapSize = 0;
    }

    void heapify(int i) {
        if (i * 2 + 1 > heapSize - 1)
            return;
        int smallest = i;
        if (data.get(i).getDist() > data.get(2 * i + 1).getDist())
            smallest = 2 * i + 1;
        else if (2 * i + 2 <= heapSize - 1 && data.get(i).getDist() > data.get(2 * i + 2).getDist())
            smallest = 2 * i + 2;

        if (smallest != i) {
            pair temp = data.get(smallest);
            data.set(smallest, data.get(i));
            data.set(i, temp);
            heapify(smallest);
        }

    }

    void heapifyUp(int i) {
        int parent = (i - 1) / 2;
        //check that the parent is still in the heap
        if (parent >= 0) {
            //if parent is bigger make a swap
            if (data.get(parent).getDist() > data.get(i).getDist()) {
                pair temp = data.get(parent);
                data.set(parent, data.get(i));
                data.set(i, temp);
                //check if the parent should go up as well
                heapifyUp(parent);
            }
        }
    }

    pair getPair(int key){
        for(int i = 0; i < this.data.size(); i++){
            if(i == key){
                return this.data.get(i);
            }
        }
        return null;
    }

    int isIn(pair p){
        int index = -1;
        for(int i = 0; i < this.getData().size(); i++){
            if(p.getNode() == this.getData().get(i).getNode()){
                index = i;
            }
        }
        return index;
    }
    void update(pair p, double distance){
        if(p == null) {
            System.out.println("ERROR");
            return;
        }
        int index = isIn(p);
        if(index != -1){
            this.data.get(index).setDist(distance);
            heapifyUp(heapSize);
        }
        else{
            add(p);
        }
    }

    void add(pair k) {
        data.set(heapSize++, k);
        heapifyUp(heapSize - 1);
    }

    void delete(int index) {
        //swap the element with the last element in the heap
        //then fix the heap and resize the heap to n-1
        data.set(index, data.get(heapSize-- - 1));
        heapify(index);
    }

    int getSize(){
        return this.heapSize;
    }

    ArrayList<pair> getData(){
        return this.data;
    }

}

class pair implements Comparable<pair>{
    private Integer node;
    private double dist;

    public pair(int n, double dist){
        this.node = n;
        this.dist = dist;
    }

    public pair(){
        this.node = null;
        this.dist = 0;
    }

    public int getNode() {
        return node;
    }

    public double getDist() {
        return dist;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    @Override
    public int compareTo(pair other) {
        if(other.getDist() > this.dist) return -1;
        else if(other.getDist() == this.dist) return 0;
        else{return 1;}
    }
}


