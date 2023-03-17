package book.school.airthmatic;

public class SelectSort {
	public static void main(String[] args) {
		int array[]={38,25,66,43,76,99,12};  
	    selectSort(array);
	}
	static void swap(int array[],int i,int j){
		int tmp=array[i];  
	    array[i]=array[j];  
	    array[j]=tmp; 
	}
	public static void selectSort(int array[]){
		int length = array.length;
		System.out.println("原数组:");
		for(int i:array)
			System.out.print(i+" ");
		//
		for(int i=0; i<length-1; i++){
			for(int j=i; j<length; j++){
				if(array[j] < array[i])
					swap(array,i,j);
			}
		}
		//
		System.out.println("\n排序后数组:");
		for(int i:array)
			System.out.print(i+" ");
	}
}
