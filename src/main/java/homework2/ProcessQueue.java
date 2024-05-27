package homework2;

public class ProcessQueue {
    public Process[] pq = new Process[2];
    public int length = 0;

    /* Add a new process into the priority queue */
    public void addProcess(Process process) {
        if(pq.length == length + 1){
            resize(pq.length * 2);
        }
        pq[++length] = process;
        swim(length);
    }

    /* Return and remove the next Process that should be run */
    public Process runNextProcess() {
        Process min = pq[1];
        swap(1, length--);
        pq[length + 1] = null;

        if(length > 0 && length == pq.length/4){
            resize(pq.length / 2);
        }
        sink(1);
        return min;
    }

    /* Return the next Process that should be run (but do not delete it) */
    public Process peekNextProcess() {
        return pq[1];
    }

    /* Implement any other helper methods, if you need them. */
    public boolean less(int a, int b){
        return pq[a].compareTo(pq[b]) < 0;
    }

    public void swap(int a, int b){
        Process temp = pq[a];
        pq[a] = pq[b];
        pq[b] = temp;
    }

    public void resize(int capacity){
        Process[] copy = new Process[capacity];
        for(int i = 1; i <= length; i++){
            copy[i] = pq[i];
        }
        pq = copy;
    }
    public void swim(int k){
        while(k > 1 && less(k, k/2)){
            swap(k, k/2);
            k = k/2;
        }
    }
    public void sink(int k){
        while(2*k <= length){
            int j = 2*k;
            if(j < length && less(j+1, j)){
                j++;
            }
            if(less(k, j)){
                break;
            }
            swap(k, j);
            k = j;
        }
    }
    public boolean isEmpty(){
        return length == 0;
    }
    public int size(){
        return length;
    }
}