package Interface.Queue;

import java.util.NoSuchElementException;

import Interface.LinkedList.Node;

public class SLinkedQueue {
	/*
	 * 자바에서는 큐의 경우 LinkedList로 구현한 큐가 쓰이는 만큼
	 * 가장 대중적이로, 배열로 구현하는 큐에 비해 쉽다.
	 * 
	 * 이유는 배열로 구현한 큐의 경우 내부에서 Object[] 배열을 담고있고, 요소가
	 * 배열에 들어있는 양에 따라 용적을 줄이거나 늘려주어야 함과, 큐를 선형적인 큐로
	 * 구현했을 경우 요소들이 뒤로 쏠리기 때문에 이러한 문제를 효율적으로 극복하고자
	 * 원형 형태로 구현하는데 이 구현이 고려해야 할 점도 많고 복잡하다.
	 * 
	 * 하지만 배열 대신 연결리스트로 구현하게 될 경우 위와 같은 단점들이 해결된다.
	 * 각 데이터들을 노드 객체에 담고 노드간 서로 연결해주기 때문에 배열처럼 요소 개수에 따라
	 * 늘려주거나 줄여줄 필요도 없고 삽입, 삭제 때는 연결된 링크만 끊어주거나 이어주면 되기 때문에
	 * 관리면에서도 편하다.
	 * 
	 * 
	 */

		
	/*
	 * 이번 큐에서 쓰이는 연결리스트는 단일 연결리스트로 구현하게 된다.
	 */
	public class LinkedQueue<E> implements Queue_basic<E> {
		 
		private Node<E> head; // 큐에서 가장 앞에 있는 노드 객체를 가리키는 변수
		private Node<E> tail; // 큐에서 가장 뒤에 있는 노드 객체를 가리키는 변수
		private int size; // 큐에 담긴 요소의 개수
		
		public LinkedQueue() {
			this.head = null;
			this.tail = null;
			this.size = 0;
		}
	
	/*
	 * offer 메서드는 데이터를 추가하는 메서드이다.
	 * 환형 큐와 마찬가지로 가장 후방에 데이터를 추가한다.
	 * 리스트의 add(E value)와 같다. 
	 */
	@Override
	public boolean offer(E value) {
		Node<E> newNode = new Node<E>(value);
			
		// 비어있을 경우에는 새 요소가 head이자 tail이다.
		if(size == 0) {
			head = newNode;
		}
		// 그 외의 경우 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 한다.
		else {
			tail.next = newNode;
		}
		/**
		 * tail이 가리키는 노드를 새 노드로 바꿔준다.
		 */
		tail = newNode;
		size++;
		return true;
	}
	
	/*
	 * poll 메서드는 리스트의 remove와 같은 역할로 가장 앞에있는 위치에 있는 요소인 head를 삭제하면된다.
	 * 
	 * 중요한 점은 삭제할 요소가 없는 경우이다.
	 */
	@Override
	public E poll() {
			
		// 삭제할 요소가 없을 경우 null 반환
		if(size == 0) {
			return null;
		}
			
		// 삭제될 요소의 데이터를 반환하기 위한 임시 변수 
		E element = head.data;
			
		// head 노드의 다음노드
		Node<E> nextNode = head.next;
			
		// head의 모든 데이터들을 삭제 
		head.data = null;
		head.next = null;
			
		// head 가 가리키는 노드를 삭제된 head노드의 다음노드를 가리키도록 변경 
		head = nextNode;
		size--;
			
		return element;
	}
	
	/*
	 * poll과 같다. 하지만 반환할 요소가 없으면 예외를 던진다. 
	 */
	public E remove() {
		
		E element = poll();
			
		if(element == null) {
			throw new NoSuchElementException();
		}
			
		return element;
	}
	
	/*
	 * 가장 앞에 있는 데이터(head.data)를 삭제하지 않고 확인만 하고싶을 때가 있다. 
	 * 그럴 때 쓰는 것이 peek() 메소드다. 
	 * 한마디로 poll() 메소드에서 삭제과정만 없는 것이 peek() 이다.
	 */
	@Override
	public E peek() {	
			
		// 요소가 없을 경우 null 반환
		if(size == 0) {
			return null;
		}
		return head.data;
	}
	
	/*
	 * peek 메서드와 기능은 비슷하지만, 확인할 요소가 없으면
	 * 예외를 던짐.
	 */
	public E element() {
		
		E element = peek();
			
		if(element == null) {
			throw new NoSuchElementException();
		}
		return element;
	}
	
	/*
	 * 이하 메서드는 설명 생략
	 */
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;	
	}
	
	public boolean contains(Object value) {
		
		/**
		 * head 데이터부터 x가 null이 될 때까지 value랑 x의 데이터(x.data)랑
		 * 같은지를 비교하고 같을 경우 true를 반환한다.
		 */
		for(Node<E> x = head; x != null; x = x.next) {
			if(value.equals(x.data)) {
				return true;
			}
		}
		return false;
	}
	
	public void clear() {
		
		for(Node<E> x = head; x != null; ) {
				
			Node<E> next = x.next;
			x.data = null;
			x.next = null;
			x = next;
		}
		size = 0;
		head = tail = null;
			
	}
	
}
	
}

