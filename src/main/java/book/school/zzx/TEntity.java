package book.school.zzx;

public class TEntity {
	public int ss;
	int b = 0;
	int a = b + 2;

	public static void main(String[] args) {
		TEntity tt = new TEntity();
		System.out.println(tt.a + " " + tt.b);
		// System.out.println(solve(3));
		/*
		 * 
		 */
		int arr_1_1_[] = new int[] { 3, 2, 1, 4, 5 };
		int arr_1_2_[] = new int[] { 1, 2, 3, 4, 5 };
		System.out.println(calcuate(arr_1_2_, 5));
		System.out.println(calcuate(arr_1_1_, 5));
		/*
		 * 
		 */
		int arr_2_2_[] = new int[] { 1, 2, 3, 4, 5 };
		System.out.println(solution(arr_2_2_, 3, 5));
		System.out.println(solution(arr_2_2_, 4, 5));
		System.out.println(solution(arr_2_2_, 8, 5));
		System.out.println(solution(arr_2_2_, 1, 5));
		// System.out.printf("%s and %<S","Hello");
	}

	static int solve(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i * i;
		}
		return sum;
	}

	/**
	 * 
	 * @param A
	 * @param N
	 * @return
	 */
	static long calcuate(int[] A, int N) {
		long start = System.currentTimeMillis();
		long max = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 2; j < N; j++) {
				int t = 0;
				if ((t = (Math.min(A[i], A[j]) * Math.abs(j - i - 1))) > max)
					max = t;
			}
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		return max;
	}

	static int solution(int[] chocolates, int M, int N) {
		long start = System.currentTimeMillis();
		System.out.println("起：" + start);
		int each = 0;
		if (0 == M || 0 == N)
			return 0;
		for (int i = 0; i < N; i++) {
			int sum = chocolates[i];
			for (int j = i + 1; j < N; j++) {
				sum += chocolates[j];
				if (sum >= M) {
					int t = sum % M;
					int res = sum / M;
					if (0 == t && res > each) {
						each = res;
					}
				}
			}
		}
		System.out.println("终：" + System.currentTimeMillis());
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		return each;
	}

	@Override
	public String toString() {
		return "tt [ss=" + ss + ", b=" + b + ", a=" + a + "]";
	}

	public int getSs() {
		return ss;
	}

	public void setSs(int ss) {
		this.ss = ss;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

}
