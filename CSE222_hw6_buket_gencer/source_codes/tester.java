public class tester {

	public static void main(String[] args) {
		int my_array[] = {4, 2, 6, 5, 1, 8, 7, 3}; // this array will be edited by TA during assessment...
		//sorted array:
		//int my_array[] = {1, 2, 3, 4, 5, 6, 7, 8};
		//reverse sorted array:
		//int my_array[] = {8, 7, 6, 5, 4, 3, 2, 1};
		
		System.out.print("Initial Array: "); // print the initial array
		for(int e: my_array)
			System.out.print(e + " ");
		System.out.println("\n");		
		
		SelectionSort ss = new SelectionSort(my_array); // create an object of SelectionSort class 
		ss.sort_and_print(); // sort the array using Selection Sort and print the comparison counter, swap counter and the sorted array 
		BubbleSort bs = new BubbleSort(my_array); // create an object of BubbleSort class
		bs.sort_and_print();
		QuickSort qs = new QuickSort(my_array); // create an object of QuickSort class
		qs.sort_and_print();
		MergeSort ms = new MergeSort(my_array); // create an object of MergeSort class
		ms.sort_and_print();
	}
}