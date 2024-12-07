public class SortAlgorithm {
	static int arr[]; // Array to be sorted
	static int comparison_counter; // Counter to keep track of the number of comparisons
	static int swap_counter; // Counter to keep track of the number of swaps

	public SortAlgorithm(int input_array[]) { // Constructor to initialize the array
		arr = input_array.clone(); 
		comparison_counter = 0;
		swap_counter = 0;
	}
	
	public void sort() {
		// this method should be empty.
	}
	
	protected static void swap(int index_1, int index_2) { // Method to swap two elements in the array
		int temp = arr[index_1];
		arr[index_1] = arr[index_2];
		arr[index_2] = temp;
		swap_counter += 1;
	}
	
	public void print() { // Method to print the comparison counter, swap counter and the sorted array
		System.out.print("Comparison Counter: " + comparison_counter);
		System.out.print("   \t Swap Counter: " + swap_counter);
		System.out.print("   \t Sorted Array: ");
		for(int e: arr)
			System.out.print(e + " ");
		System.out.println();		
	}
	
	public void sort_and_print() { // Method to sort the array and print the comparison counter, swap counter and the sorted array
		sort();
		print();
	}
}