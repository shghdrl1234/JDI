package Interface.Deque;

import java.util.NoSuchElementException;

import Interface.LinkedList.Node;
import Interface.Queue.Queue_basic;

/*
 * ArrayQueue가 배열을 이용하였다면,
 * LinkedListDeque는 DounblyLinkedList를 이용하여 구현한 것이다.
 * 
 * 양방향으로 접근이 가능하다.
 * 
 */
public class LinkedListDeque<E> implements Queue_basic<E> {
	 
	private Node<E> head;	// 가장 앞에 있는 노드를 가리키는 변수 
	private Node<E> tail;	// 가장 뒤에 있는 노드를 가리키는 변수 
	private int size;	// 요소(노드)의 개수 
 
	public LinkedListDeque() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/*
	 * 전방 삽입
	 */
	public boolean offerFirst(E value) {	
		Node<E> newNode = new Node<E>(value);	// 새 노드 생성
		newNode.next = head;	// 새 노드의 다음 노드로 head 노드를 연결 
	 
		/**
		 * head가 null이 아닐 경우에만 기존 head노드의 prev 변수가
		 * 새 노드를 가리키도록 한다. 
		 * 이유는 기존 head노드가 없는 경우(null)는 데이터가 
		 * 아무 것도 없던 상태였으므로 head.prev를 하면 잘못된 참조가 된다. 
		 */
		if (head != null) {
			head.prev = newNode;
		}
			
		head = newNode;	// head가 가리키는 노드가 새 노드를 가리키도록 한다. 
		size++;
	 
		/**
		 * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우 = 이전의 head가 null인경우)
		 * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자
		 * 마지막 노드다. 즉 tail = head 다.
		 */
		if (head.next == null) {
			tail = head;
		}
		return true;
	}
	
	/*
	 * 후방 삽입
	 */
	@Override
	public boolean offer(E value) {
		return offerLast(value);
	}
	 
	public boolean offerLast(E value) {
	 
		// 데이터가 없을 경우 offerFirst()로 인자를 넘겨줌 
		if (size == 0) {
			return offerFirst(value);
		}
	 
		Node<E> newNode = new Node<E>(value);
			
		tail.next = newNode;	// tail이 가리키는 노드의 다음 노드를 새 노드를 가리키도록 연결 
		newNode.prev = tail;	// 새 노드가 가리키는 이전노드 또한 tail이 가리키는 노드로 연결 
		tail = newNode;		// tail이 가리키는 노드를 새 노드로 가리키도록 변경 
		size++;
	 
		return true;
	}
	
	/*
	 * 전방 삭제
	 */
	@Override
	public E poll() {
		return pollFirst();
	}
	 
	public E pollFirst() {
		if (size == 0) {
			return null;
		}
	 
		E element = head.data;	// 반환하기 위한 데이터
	 
		Node<E> nextNode = head.next;	// head의 다음노드 
			
		// head가 가리키는 모든 데이터들 삭제
		head.data = null;
		head.next = null;
			
		// 삭제하기전 데이터가 두 개 이상 있을 경우(head와 tail이 같지 않은 경우)
		if (nextNode != null) {
			nextNode.prev = null;
		}
		head = null;
		head = nextNode;	// head가 가리키는 노드를 삭제한 노드의 다음 노드로 갱신
		size--;
			
		/**
		 * 삭제된 요소가 덱의 유일한 요소였을 경우
		 * 그 요소는 head 이자 tail이었으므로 
		 * 삭제되면서 tail도 가리킬 요소가 없기 때문에
		 * size가 0일경우 tail도 null로 변환
		 */
		if(size == 0) {
			tail = null;
		}
			
		return element;
	}
	 
	public E remove() {
		return removeFirst();
	}
		
	public E removeFirst() {
		E element = poll();
		
		if(element == null) {
			throw new NoSuchElementException();
		}
		return element;
	}
	
	/*
	 * 후방 삭제
	 */
	public E pollLast() {
		if (size == 0) {
			return null;
		}
			
		E element = tail.data;	// 반환하기 위한 데이터
			
		Node<E> prevNode = tail.prev;
			
		// tail이 가리키는 노드의 데이터와 링크 삭제
		tail.data = null;
		tail.prev = null;
			
		// 삭제하기전 데이터가 두 개 이상 있을 경우(head와 tail이 같지 않을 경우)
		if (prevNode != null) {
			prevNode.next = null;
		}
			
		tail = null;
		tail = prevNode;
		size--;
			
		/**
		 * 삭제된 요소가 덱의 유일한 요소였을 경우
		 * 그 요소는 head 이자 tail이었으므로 
		 * 삭제되면서 head도 가리킬 요소가 없기 때문에
		 * size가 0일경우 head도 null로 변환
		 */
		if(size == 0) {
			head = null;
		}
		return element;
	}
		
	public E removeLast() {
		E element = pollLast();
		
		if(element == null) {
			throw new NoSuchElementException();
		}
		return element;
	}
	
	@Override
	public E peek() {
		return peekFirst();
	}
		
	public E peekFirst() {
		// 요소가 없을 경우 null 반환
		if(size == 0) {
			return null;
		}
			
		return head.data;
	}
	
	public E peekLast() {
		// 요소가 없을 경우 null 반환 
		if(size == 0) {
			return null;
		}
			
		return tail.data;
	}
	
	public E element() {
		return getFirst();
	}
		
	public E getFirst() {
		E item = peek();
			
		// 앞의 원소 null 이라면(size = 0) 예외를 던진다. 
		if(item == null) {
			throw new NoSuchElementException();
		}
		return item;	
	}
	
	public E getLast() {
		E item = peekLast();
			
		// 앞의 원소 null 이라면(size = 0) 예외를 던진다. 
		if(item == null) {
			throw new NoSuchElementException();
		}
		return item;
	}
	
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
		for (Node<E> x = head; x != null;) {
			Node<E> next = x.next;
	 
			x.data = null;
			x.next = null;
			x.prev = null;
			x = next;
		}
		size = 0;
		head = tail = null;
	}
}

