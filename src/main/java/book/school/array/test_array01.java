package book.school.array;

import org.junit.Test;

public class test_array01 {
	//ArrayIndexOutOfBoundException,Java���������б߽���
	
	@Test
	public void test01(){
	    int[] intArray = new int[10];
	    float[] floatArray = new float[10];
	    System.out.println(intArray.getClass());
	    System.out.println(floatArray.getClass());
	}
	
	@Test
	public void test_createArray(){
		//1.
		int array1[];
		int[] array2[];
		//2.
		int[] array3 = new int[10];
		for(int i:array3)
			System.out.print(i+" ");
		System.out.println();
		//3.
		int[] array4 = new int[]{1,2,4,5};
		for(int i:array4)
			System.out.print(i+" ");
		System.out.println();
		
		
	}
	
	@Test
	public void test_arrayCopy(){
		int[] arr1 = new int[10];
		int[] arr2 = new int[]{1,2,0,89};
		System.out.println("Before:");
		for(int i : arr1)
			System.out.print(i+" ");
		
		System.arraycopy(arr2, 0, arr1, 0, 4);
		
		System.out.println("\nAfterCopy:");
		for(int i : arr1)
			System.out.print(i+" ");
		
	}
	
	
}
