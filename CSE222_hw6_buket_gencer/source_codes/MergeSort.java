public class MergeSort extends SortAlgorithm {
	
    //Complexity of Merge Sort is O(n log n) in all cases.
	public MergeSort(int input_array[]) {
		super(input_array);
	}
	
	private void merge(int left, int mid, int right){
        // fill this method
        int n1 = mid - left + 1; // Left subarray size
        int n2 = right - mid; // Right subarray size

        // Temporary arrays for the left and right subarrays
        int[] Left = new int[n1];
        int[] Right = new int[n2];

        // Copy data to temporary arrays Left[] and Right[]
        for (int i = 0; i < n1; ++i)
            Left[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            Right[j] = arr[mid + 1 + j];

        int i = 0, j = 0; // Initial indexes of first and second subarrays
        int k = left; // Initial index of merged subarray

        // Merge the temporary arrays back into the original array
        while (i < n1 && j < n2) {
            comparison_counter++; // Increment the comparison counter for each comparison.
            if (Left[i] <= Right[j]) { // Compare the elements of the left and right subarrays.
                arr[k] = Left[i]; // if the left element is less than or equal to the right element, copy the left element to the original array.
                i++; 
            } else {
                arr[k] = Right[j]; // if the right element is less than the left element, copy the right element to the original array.
                j++;
            }
            k++; // Increment the index of the merged subarray.
        }

        // Copy remaining elements of Left[] if any
        while (i < n1) { 
            arr[k] = Left[i];
            i++;
            k++;
        }

        // Copy remaining elements of Right[] if any
        while (j < n2) {
            arr[k] = Right[j];
            j++;
            k++;
        }
    }

    private void sort(int left, int right){
        // fill this method
        if (left < right) { // if left is less than right, then the array has more than one element and can be split 
            int mid = left + (right - left) / 2; // find the middle index

            // Recursively sort the left and right halves
            sort(left, mid); // sort the left half  
            sort(mid + 1, right); // sort the right half

            merge(left, mid, right); // merge the sorted halves
        }
    }
    
    @Override
    public void sort() {
    	sort(0, arr.length - 1); // call the sort method with the left and right index
    }
    
    @Override
    public void print() {
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}
