/**
 * 缺点是会暴露内部方法
 */
public class IntList{
	public int first;
	public IntList rest;

	public IntList(int f, Intli r){
		first = f;
		rest = r;
	}

	/**
	 * recursion version
	 * @return
	 */
	public int size(){
		if(rest = null){
			return 1;
		}		
		return this.rest.size() + 1;
	}

	/**
	 * iterative version
	 * @return
	 */
	public int iterativeSize(){
		/*p for pointer */
		IntList p = this;
		int totalSize = 0;
		while (p != null) {
			totalSize += 1;
			p = p.rest;
		}
		return totalSize;
	}

	/**
	 * return the ith item
	 * @param i
	 * @return
	 */
	public int get(int i){
		if(i == 0){
			return first;
		}

		return rest.get(i - 1);
	}

	public static void main(String[] args) {
		IntList L = new IntList(15, null);
		L = new IntList(10 , L);
		L = new IntList(5, L);

		System.out.println(L.size());
	}
}