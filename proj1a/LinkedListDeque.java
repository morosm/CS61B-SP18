public class LinkedListDeque<T> implements Deque<T>{
	public class Node{
		Node previous;
		T current;
		Node next;
			
		public Node(T current) {
			this.current = current;};
	}
	
	private Node sentinel;
	private int size;
	
	public LinkedListDeque() {
		sentinel = new Node(null);
		sentinel.previous = sentinel;
		sentinel.next = sentinel;
		size = 0;
	}
	
	
	public LinkedListDeque(LinkedListDeque<T> other){
		sentinel = new Node(null);
		size = 0;
		
		for(int i = 0; i < other.size(); i ++) {
			addLast(other.get(i));
		}
	}
	
	@Override
	public void addFirst(T item) {
		var temp = new Node(item);
		var first = sentinel.next;
		
		temp.previous = sentinel;
		temp.next = first;
		
		sentinel.next = temp;
		first.previous = temp;
		
		size += 1;
	}

	@Override
	public void addLast(T item) {
		var temp = new Node(item);
		var last = sentinel.previous;
		
		temp.previous = last;
		temp.next = sentinel;
		
		sentinel.previous = temp;
		last.next = temp;
		
		size += 1;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void printDeque() {
		Node current = sentinel.next;
		for(int i = 0; i< size; i++) {
			var content = i + ": " + current.current;
			System.out.println(content);
			current = current.next;
		}
	}

	@Override
	public T removeFirst() {
		var first = sentinel.next;
		
		var newFirst = first.next;
		sentinel.next = newFirst;
		newFirst.previous = sentinel;
		
		size -= 1;
		
		return first.current;
	}

	@Override
	public T removeLast() {
		var last = sentinel.previous;
		
		var newLast = last.previous;
		sentinel.previous = newLast;
		newLast.next = sentinel;
		
		size -= 1;
		
		return last.current;
	}

	@Override
	public T get(int index) {
		Node current = sentinel.next;
		for(int i = 1; i< index; i++) {
			current = current.next;
		}
		return current.current;
	}
	
	public T getRecursive(int index) {
		Node current = sentinel.next;
		if(index != 1){
			current = current.next;
			index --;
		}
		return current.current;
	}
	
}
