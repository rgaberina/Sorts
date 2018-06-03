/**
 * 
 */

/**
 * @author berina
 * Merge function with auxillary array of size N/2
 */
public class Merge {

	public static void main(String[] args) {
		int[] arr = {5,6,7,8,1,2,3,9};
		Merge m = new Merge();
		m.merge(arr,0,4,7,8);
	}

	void merge (int[] a, int aStart, int bStart, int bEnd, int n) {
		int[] aux = new int[n/2];
		for (int i = 0; i < bStart; i++) 
			aux[i] = a[i];
		int arrPos = 0;
		for (int i = 0, j = bStart; i < bStart && j <= bEnd; arrPos++) {
			if (aux[i] > a[j]) 
				a[arrPos] = a[j++];
			else 
				a[arrPos] = aux[i++];
		}
	}
}
