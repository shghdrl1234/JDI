package Interface_practice;

import java.util.NoSuchElementException;

public class DLinkedList<E> implements List_basic<E> {
	
	private Node<E> head;	// 리스트의 가장 첫 노드를 가리키는 변수
	private Node<E> tail;	// 리스트의 가장 마지막 노드를 가리키는 변수
	private int size;		// 리스트에 있는 요소의 개수(=연결 된 노드의 개수)
 
	public DLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		/*
		 * 처음 양방향 연결리스트를 생성할 때에는 아무런 데이터가 없으므로
		 * head와 tail이 가리킬 노드가 없기에 null로 초기화 및 size는 0으로 초기화
		 * 해주도록 한다.
		 */
	}
	/*
	 *	특정 데이터를 검색하는 메서드를 구현한다. 
	 * 
	 *  단방향 연결리스트와는 달리 검색 과정에서 효율적으로 tail과 head중에 어디에
	 *  더 가까운지를 구분하여 효율적인 탐색을 진행할 수 있다.
	 * 
	 */
	private Node<E> search(int index) {
	 
		// 범위 밖(잘못된 위치)일 경우 예외 던지기 
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
			
		// 뒤에서부터 검색 
		if (index > size / 2) {
			Node<E> x = tail;
			for (int i = size - 1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	    
		// 앞에서부터 검색
		else {
			Node<E> x = head;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		}
	}

	
	/*
	 * 이제 본격적으로 데이터를 추가할 수 있도록 add 메서드를 한다.
	 * 리스트 인터페이스에 있는 메서드를 반드시 구현해야하므로 Override를 한다고
	 * 보면 된다.
	 * 
	 * 1. 가장 앞부분에 추가 : addFirst(E value)
	 * 2. 가장 마지막 부분에 추가 : addLast(E value)
	 * 3. 특정 위치에 추가 : add(int index, E value)
	 *
	 * 자바에 내장되어있는 LinkedList에서는 add() 역할을 addLast() 메서드가 하고,
	 * 특정 위치에 추가는 add(int index, E element) 메서드, 가장 첫 부분에 추가는 addFirst()가 한다.
	 * 
	 */
	public void addFirst(E value) {
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
	    
		head = newNode;
		size++;
			
		/**
		 * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우)
		 * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자
		 * 마지막 노드다. 즉 tail = head 다.
		 */
		if (head.next == null) {
			tail = head;
		}
	}
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	 
	public void addLast(E value) {
		Node<E> newNode = new Node<E>(value);	// 새 노드 생성 
	 
		if (size == 0) {	// 처음 넣는 노드일 경우 addFisrt로 추가
			addFirst(value);
			return;
		}
	 
		/**
		 * 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고
		 * tail이 가리키는 노드를 새 노드로 바꿔준다. 
		 */
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		size++;
	}

	/*
	 * 원하는 위치에 데이터를 추가하기 위한 메서드이다.
	 * add 메서드 및 타 메서드도 전반적으로 비슷하다.
	 * 
	 * 다른 점이 있다면 prev Node에 대해서 링크 추가, 삭제를 추가해준다.
	 * 
	 */
	public void add(int index, E value) {
		 
		// 잘못된 인덱스를 참조할 경우 예외 발생
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		// 추가하려는 index가 가장 앞에 추가하려는 경우 addFirst 호출 
		if (index == 0) {
			addFirst(value);
			return;
		}
		// 추가하려는 index가 마지막 위치일 경우 addLast 호출
		if (index == size) {
			addLast(value);
			return;
		}
				
		// 추가하려는 위치의 이전 노드 
		Node<E> prev_Node = search(index - 1);
			
		// 추가하려는 위치의 노드
		Node<E> next_Node = prev_Node.next;
	 
		// 추가하려는 노드
		Node<E> newNode = new Node<E>(value);	
			
		// 링크 끊기
		prev_Node.next = null;
		next_Node.prev = null;
			
		// 링크 연결하기
		prev_Node.next = newNode;
			
		newNode.prev = prev_Node;
		newNode.next = next_Node;
			
		next_Node.prev = newNode;
		size++;
	}
	/*
	 * 가장 앞에 있는 요소를 제거하는 remove메서드이다.
	 * head가 가리키는 노드의 링크와 데이터를 null로 지워준 뒤 head를 다음 노드로 업데이트 해준다.
	 * 그리고 삭제하려는 노드가 리스트에서의 유일한 노드였을 경우 해당 노드를 삭제하면 tail이 가리키는
	 * 노드 또한 없어지게 된다.
	 */
	public E remove() {
		 
		Node<E> headNode = head;
			
		if (headNode == null) {
			throw new NoSuchElementException();
		}
			
		// 삭제된 노드를 반환하기 위한 임시 변수
		E element = headNode.data;
				
		// head의 다음 노드 
		Node<E> nextNode = head.next;
				
		// head 노드의 데이터들을 모두 삭제
		head.data = null;
		head.next = null;
			
		/**
		 * head의 다음노드(=nextNode)가 null이 아닐 경우에만 
		 * prev 변수를 null로 업데이트 해주어야 한다.
		 * 이유는 nextNode가 없는 경우(null)는 데이터가 
		 * 아무 것도 없던 상태였으므로 nextNode.prev를 하면 잘못된 참조가 된다. 
		 */
		if(nextNode != null) {
			nextNode.prev = null;
		}
			
		head = nextNode;
		size--;
			
		/**
		 * 삭제된 요소가 리스트의 유일한 요소였을 경우
		 * 그 요소는 head 이자 tail이었으므로 
		 * 삭제되면서 tail도 가리킬 요소가 없기 때문에
		 * size가 0일경우 tail도 null로 변환
		 */
		if(size == 0) {
			tail = null;
		}
	 
		return element;
	}
	/*
	 * 지정한 위치의 데이터를 제거할 경우의 remove를 구현하였다.
	 * 여기서는 고려해야할 것들이 많다.
	 * 
	 * 첫 번째 요소가 삭제되는 경우와 마지막 요소가 삭제되는 경우,
	 * 
	 * 요소가 한 개가 남았을 때 삭제하려고 하는 경우를 고려해주어야한다.
	 */
	@Override
	public E remove(int index) {
	 
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		// 삭제하려는 노드가 첫 번째 노드일 경우
		if (index == 0) {
			E element = head.data;
			remove();
			return element;
		}
	 
		Node<E> prevNode = search(index - 1);	// 삭제할 노드의 이전 노드 
		Node<E> removedNode = prevNode.next;	// 삭제할 노드 
		Node<E> nextNode = removedNode.next;	// 삭제할 노드의 다음 노드 
	 
		E element = removedNode.data;	// 삭제되는 노드의 데이터를 반환하기 위한 임시변수
	 
		/**
		 * index == 0 일 때의 조건에서 이미 head노드의 삭제에 대한 분기가 있기 때문에
		 * prevNode는 항상 존재한다.
		 * 
		 * 그러나 nextNode의 경우는 null일 수 있기 때문에 (= 마지막 노드를 삭제하려는 경우)
		 * 이전처럼 반드시 검사를 해준 뒤, nextNode.prev에 접근해야 한다.
		 */
		
		prevNode.next = null;
		removedNode.next = null;
		removedNode.prev = null;
		removedNode.data = null;
			
		if(nextNode != null) {
			nextNode.prev = null;
				
			nextNode.prev = prevNode;
			prevNode.next = nextNode;
		}
		/**
		 *  nextNode가 null이라는 것은 마지막 노드를 삭제했다는 의미이므로
		 *  prevNode가 tail이 된다. (연결 해줄 것이 없음)
		 */
		else {	 
			tail = prevNode;
		}
	 
		size--;
	 
		return element;
	}
	/*
	 * 사용자가 원하는 특정 요소를 리스트에서 찾아 삭제하는 것이다.
	 * 삭제하는 요소가 존재하는지를 염두해두고 사용해야한다.
	 * 
	 *  삭제하려는 요소를 찾지 못했을 경우 false를 반환해주고, 만약 찾았을 경우
	 *  remove(int index)와 동일환 삭제 방식을 사용하면된다.
	 */
	@Override
	public boolean remove(Object value) {
	 
		Node<E> prevNode = head;
		Node<E> x = head;		// removedNode 
			
		// value 와 일치하는 노드를 찾는다.
		for (; x != null; x = x.next) {
			if (value.equals(x.data)) {
				break;
			}
			prevNode = x;
		}
	 
		// 일치하는 요소가 없을 경우 false 반환 
		if(x == null) {
			return false;
		}
	 
		// 삭제하려는 노드가 head일 경우 remove()로 삭제
		if (x.equals(head)) {
			remove();
			return true;
		}
	    
		// remove(int index)와 같은 메커니즘으로 삭제
		else {
			Node<E> nextNode = x.next;
				
			prevNode.next = null;
			x.data = null;
			x.next = null;
			x.prev = null;
				
			if(nextNode != null) {
				nextNode.prev = null;
				
				nextNode.prev = prevNode;
				prevNode.next = nextNode;
			}
			else {
				tail = prevNode;
			}
	 
			size--;
			return true;
		}
	}
	
	@Override
	public E get(int index) {
		return search(index).data;
	}

	@Override
	public void set(int index, E value) {
		// 데이터만 바꾸는 작업이다. 전, 후의 노드 레퍼런스에 영향을 주지 않는다.
		Node<E> replaceNode = search(index);
		replaceNode.data = null;
		replaceNode.data = value;
	}

	@Override
	public boolean contains(Object item) {
		return indexOf(item) >= 0;
	}

	@Override
	public int indexOf(Object o) {	// 정방향 탐색
		int index = 0;
	 
		for (Node<E> x = head; x != null; x = x.next) {
			if (o.equals(x.data)) {
				return index;
			}
			index++;	
		}
		return -1;
	}
	 
	public int lastIndexOf(Object o) {	// 역방향 탐색 
		int index = size;
		
		for (Node<E> x = tail; x != null; x = x.prev) {
			index--;
			if (o.equals(x.data)) {
				return index;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public void clear() {
		for (Node<E> x = head; x != null;) {
			Node<E> nextNode = x.next;
			x.data = null;
			x.next = null;
			x.prev = null;
			x = nextNode;
		}
		head = tail = null;
		size = 0;
	}

}
