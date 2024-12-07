public class QuickSort extends SortAlgorithm {
    //Complexity of Quick Sort is O(n^2) in worst case.
    //Complexity of Quick Sort is O(n log n) in best case.
    //Complexity of Quick Sort is O(n log n) in average case.

	public QuickSort(int input_array[]) {
		super(input_array);
	}
	
    private int partition(int first, int last){
        // fill this method
        int pivot = arr[last]; // // Use the last element as the pivot.
        int i = first - 1; // Initialize the pointer for the smaller element.

        // Iterate over each element in the array, comparing them to the pivot.
        for (int j = first; j < last; j++) {
            comparison_counter++; // Increment the comparison counter for each comparison.
            if (arr[j] <= pivot) {
                i++; // Increment the pointer for the smaller element.
                swap(i, j); // Swap the elements if the current element is less than or equal to the pivot.
            }
        }
        swap(i + 1, last); // Swap the pivot with the element at the position of the smaller element pointer.
        return i + 1; // Return the position of the pivot. So pivot is in the correct position.

    }

    // Recursively sort the sub-arrays partitioned by the pivot.
    private void sort(int first, int last) {
        // fill this method
        if (first < last) { // If the first index is less than the last index, then the array has more than one element.
            int pivotIndex = partition(first, last); // Partition the array and get the pivot index.
            sort(first, pivotIndex - 1); // Sort the left sub-array. Recursively call the sort method.
            sort(pivotIndex + 1, last); // Sort the right sub-array. Recursively call the sort method.
        }
       
    }

    @Override
    public void sort() {
    	sort(0, arr.length - 1); // Call the sort method with the first and last index.
    }

    // Print the comparison counter, swap counter and the sorted array.
    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}
