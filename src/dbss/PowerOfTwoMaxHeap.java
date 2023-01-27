package dbss;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class PowerOfTwoMaxHeap {

    private int x;
    private List<Integer> data;

    private PowerOfTwoMaxHeap(int x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must be greater than 0");
        }
        this.x = x;
        data = new ArrayList<>();
    }

    public void insert(int value) {
        data.add(value);
        int currentIndex = data.size() - 1;
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / (int) Math.pow(2, x);
            if (data.get(currentIndex) > data.get(parentIndex)) {
                Collections.swap(data, currentIndex, parentIndex);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public int popMax() {
        if (data.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        int max = data.get(0);
        data.set(0, data.get(data.size() - 1));
        data.remove(data.size() - 1);
        int currentIndex = 0;
        while (currentIndex < data.size()) {
            int maxChildIndex = currentIndex;
            for (int i = 1; i <= (int) Math.pow(2, x); i++) {
                int childIndex = (int) Math.pow(2, x) * currentIndex + i;
                if (childIndex < data.size() && data.get(childIndex) > data.get(maxChildIndex)) {
                    maxChildIndex = childIndex;
                }
            }
            if (data.get(currentIndex) < data.get(maxChildIndex)) {
                Collections.swap(data, currentIndex, maxChildIndex);
                currentIndex = maxChildIndex;
            } else {
                break;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        PowerOfTwoMaxHeap heap = new PowerOfTwoMaxHeap(2);
        heap.insert(1);
        heap.insert(6);
        heap.insert(8);
        heap.insert(9);
        heap.insert(5);

        System.out.println(heap.popMax()); // 9
        System.out.println(heap.popMax()); // 8
        System.out.println(heap.popMax()); // 6
        System.out.println(heap.popMax()); // 5
        System.out.println(heap.popMax()); // 1
    }
}