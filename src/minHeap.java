public class minHeap {
    int[] data;
    int heapSize;

    minHeap(int size) {
        data = new int[size];
        heapSize = 0;
    }

    void heapify(int i) {
        if (i * 2 + 1 > heapSize - 1)
            return;
        int smallest = i;
        if (data[i] > data[2 * i + 1])
            smallest = 2 * i + 1;
        else if (2 * i + 2 <= heapSize - 1 && data[i] > data[2 * i + 2])
            smallest = 2 * i + 2;

        if (smallest != i) {
            int temp = data[smallest];
            data[smallest] = data[i];
            data[i] = temp;
            heapify(smallest);
        }

    }

    void heapifyUp(int i) {
        int parent = (i - 1) / 2;
        //check that the parent is still in the heap
        if (parent >= 0) {
            //if parent is bigger make a swap
            if (data[parent] > data[i]) {
                int temp = data[parent];
                data[parent] = data[i];
                data[i] = temp;
                //check if the parent should go up as well
                heapifyUp(parent);
            }
        }
    }

    void add(int k) {
        data[heapSize++] = k;
        heapifyUp(heapSize - 1);
    }

    void delete(int index) {
        //swap the element with the last element in the heap
        //then fix the heap and resize the heap to n-1
        data[index] = data[heapSize-- - 1];
        heapify(index);
    }

}


