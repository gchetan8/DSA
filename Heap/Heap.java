// Min Heap implementation (1-indexed array)
// parent(i) = i/2 | left(i) = 2i | right(i) = 2i+1
public class Heap {

    int[] arr;
    int size;

    public Heap(int capacity) {
        arr = new int[capacity + 1];
        size = 0;
    }

    // ─── 4. Insert ───────────────────────────────────────────────────────────
    public void insert(int val) {
        arr[++size] = val;
        heapifyUp(size);
    }

    private void heapifyUp(int i) {
        while (i > 1 && arr[i] < arr[i / 2]) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    // ─── 5. Heapify Down ─────────────────────────────────────────────────────
    public void heapifyDown(int i) {
        int smallest = i;
        int left  = 2 * i;
        int right = 2 * i + 1;

        if (left  <= size && arr[left]  < arr[smallest]) smallest = left;
        if (right <= size && arr[right] < arr[smallest]) smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // ─── 6. Extract Min ──────────────────────────────────────────────────────
    public int extractMin() {
        if (size == 0) throw new RuntimeException("Heap is empty");
        int min = arr[1];
        arr[1] = arr[size--];
        heapifyDown(1);
        return min;
    }

    // ─── 7. Decrease Key ─────────────────────────────────────────────────────
    public void decreaseKey(int i, int newVal) {
        if (newVal > arr[i]) throw new IllegalArgumentException("New val is larger");
        arr[i] = newVal;
        heapifyUp(i);
    }

    // ─── 8. Delete Key ───────────────────────────────────────────────────────
    public void deleteKey(int i) {
        decreaseKey(i, Integer.MIN_VALUE);
        extractMin();
    }

    // ─── 9. Build Heap (from unsorted array) — O(n) ──────────────────────────
    public void buildHeap(int[] input) {
        size = input.length;
        arr = new int[size + 1];
        for (int i = 0; i < input.length; i++) arr[i + 1] = input[i];
        for (int i = size / 2; i >= 1; i--) heapifyDown(i);
    }

    // ─── 10. Heap Sort (ascending) — uses max-heap logic ─────────────────────
    public static int[] heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap (0-indexed)
        for (int i = n / 2 - 1; i >= 0; i--) maxHeapify(arr, n, i);

        // Move current root (max) to end, shrink heap
        for (int i = n - 1; i > 0; i--) {
            int tmp = arr[0]; arr[0] = arr[i]; arr[i] = tmp;
            maxHeapify(arr, i, 0);
        }
        return arr;
    }

    private static void maxHeapify(int[] arr, int n, int i) {
        int largest = i;
        int left  = 2 * i + 1;
        int right = 2 * i + 2;
        if (left  < n && arr[left]  > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;
        if (largest != i) {
            int tmp = arr[i]; arr[i] = arr[largest]; arr[largest] = tmp;
            maxHeapify(arr, n, largest);
        }
    }

    private void swap(int i, int j) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    public void print() {
        System.out.print("Heap: ");
        for (int i = 1; i <= size; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    // ─── Driver ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Heap h = new Heap(20);

        // Insert
        System.out.println("=== Insert ===");
        for (int v : new int[]{5, 3, 8, 1, 4, 7, 2}) h.insert(v);
        h.print();

        // Extract Min
        System.out.println("\n=== Extract Min ===");
        System.out.println("Extracted: " + h.extractMin());
        h.print();

        // Decrease Key
        System.out.println("\n=== Decrease Key (index 3 → 0) ===");
        h.decreaseKey(3, 0);
        h.print();

        // Delete Key
        System.out.println("\n=== Delete Key (index 2) ===");
        h.deleteKey(2);
        h.print();

        // Build Heap
        System.out.println("\n=== Build Heap ===");
        Heap h2 = new Heap(10);
        h2.buildHeap(new int[]{10, 20, 15, 40, 50, 100, 25, 45});
        h2.print();

        // Heap Sort
        System.out.println("\n=== Heap Sort ===");
        int[] sorted = heapSort(new int[]{5, 3, 8, 1, 4, 7, 2});
        System.out.print("Sorted: ");
        for (int x : sorted) System.out.print(x + " ");
        System.out.println();
    }
}
