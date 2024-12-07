public class BubbleSort extends SortAlgorithm {
    //Complexity of Bubble Sort is O(n^2) in worst case.
    //Complexity of Bubble Sort is O(n) in best case.
    //Complexity of Bubble Sort is O(n^2) in average case.

	public BubbleSort(int input_array[]) {
		super(input_array);
	}
	
    @Override
    public void sort() {
    	// fill this method
        int size = arr.length; // size of the array
        boolean in_order;// boolean to check if the array is in order

        for (int i = size - 1; i > 0; i--) {
            in_order = true; // assume the array is in order at the beginning of each iteration
            for (int j = 0; j < i; j++) {
                comparison_counter++;
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                    in_order = false; //if a swap occurs, that means the array is not in order
                }
            }
            if (in_order) { // if array is in order, break the loop
                break; //
            }
        }
    }
    
    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}
