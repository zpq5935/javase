package book.school.airthmatic;

public class InsertSort {
	public static void main(String[] args) {
		int arr[] = { 38, 49, 97, 65, 76, 13, 55, 66, 76, 23 };
		sheelSort(arr);
		//ad(arr);
		
	}

	public static void sheelSort(int arr[]) {
		int length = arr.length;
		System.out.println("原数组:");
		for (int i : arr)
			System.out.print(i + " ");
		
		for (int i = 1; i < length ; i++) {
			if (arr[i] < arr[i - 1]) {
				int temp = arr[i];
				int j = i-1 ;
				for (; j >= 0 && arr[j] > temp; j--) {
					arr[j + 1] = arr[j];
				}
				arr[j + 1] = temp;
			}
		}
		System.out.println("\n排序后:");
		for (int i : arr)
			System.out.print(i + " ");
	
	}
	
	public static void ad(int arr[]){
		for(int i=0; i<arr.length; i++)
			arr[i] = 0;
		for (int i : arr)
			System.out.print(i + " ");
	}
}
