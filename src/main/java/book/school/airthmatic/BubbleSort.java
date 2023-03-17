package book.school.airthmatic;

public class BubbleSort {

	public static void main(String[] args) {
		int array[]={47,36,66,97,77,13,23};  
		bubbleSort(array);  
	}
	public static void bubbleSort(int[] arr){
		int length = arr.length;
		System.out.println("原数组:");
		for (int i : arr)
			System.out.print(i + " ");
		//
		for(int i=0; i<length-2; i++){
			for(int j=length-2; j>=0; j--){
				if(arr[j]>arr[j+1]){
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		System.out.println("\n排序后数组:");
		for (int i : arr)
			System.out.print(i + " ");
	}
}
