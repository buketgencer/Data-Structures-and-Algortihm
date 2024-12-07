public class SelectionSort extends SortAlgorithm {
    //Complexity of Selection Sort is O(n^2) in worst case.
    //Complexity of Selection Sort is O(n^2) in best case.
    //Complexity of Selection Sort is O(n^2) in average case.

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    public void sort() {
        // fill this method
        int size = arr.length; // size of the array
        for (int i = 0; i < size - 1; i++){ // loop through the array
            int min = i; // set the minimum value to the current index

            for (int j = i + 1; j < size; j++){ // loop through the array starting from the next index
                comparison_counter++;
                if(arr[j] < arr[min]){ //compare the current value with the minimum value
                    min = j; // if the current value is smaller than the minimum value, set the minimum value to the current index
                }
            }
            swap(min, i); // swap the minimum value with the current index
        }
    }

    @Override
    public void print() { // print the comparison counter, swap counter and the sorted array
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}
