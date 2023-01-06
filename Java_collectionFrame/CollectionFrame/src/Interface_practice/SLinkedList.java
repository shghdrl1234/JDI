package Interface_practice;

import java.util.NoSuchElementException;

public class SLinkedList<E> implements List_basic<E> {

		private Node<E> head;	// 리스트의 가장 첫 노드를 가리키는 변수.
		private Node<E> tail;	// 리스트의 가장 마지막 노드를 가리키는 변수.
		private int size;	// 리스트에 있는 요소으 개수(= 연결된 노드의 개수)
	 
		// 생성자
		public SLinkedList() {
			this.head = null;
			this.tail = null;
			this.size = 0;
			/*
			 * 처음 단일 연결리스트를 생성할 때에는 아무런 데이터가 없으므로
			 * head와 tail이 가리킬 노드가 없기에 null로 초기화 및 size는 0으로
			 * 초기화 해주도록 한다.
			 */
		}
		
		/*
		 * search() 메서드를 작성한다. 단일 연결리스트는 특정 위치의 데이터를
		 * 삽입, 삭제, 검색하기 위해서는 처음 노드부터 next 변수를 통해
		 * 특정 위치까지 찾아가야 하기 때문이다.
		 */
		private Node<E> search(int index) {
			
			// 범위 밖의 경우 예외를 던진다.
			if(index < 0 || index >= size) {
				throw new IndexOutOfBoundsException();
			}
			
			Node<E> x = head; // head가 가리키는 노드부터 시작
			
			for(int i = 0; i < index; i++) {
				x = x.next;  // x노드의 다음 노드를 x에 저장한다.
			}
			
			return x;
			
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
		// 데이터를 리스트 가장 앞부분에 추가할 때 사용
		public void addFirst(E value) {
			 
			Node<E> newNode = new Node<E>(value);	// 새 노드 생성
			newNode.next = head;	// 새 노드의 다음 노드로 head 노드를 연결
			head = newNode;	// head가 가리키는 노드를 새 노드로 변경
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
		
		//데이터를 리스트 가장 뒷부분에 추가할 때 사용
		//기존의 add() 메서드를 통하여 addLast 메서드를 호출시킨다.
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
			tail = newNode;
			size++;
		}
		
		// 삽입하려고 하는 위치에 있는 노드와, 이전의 노드를 찾아야한다.
		// 이 때, 우리가 이전에 만들었던 search()를 사용하여 노드들을 찾는다.
		// 이후 노드의 링크를 변경해주면 된다.
		@Override
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
				
			/**
			 * 이전 노드가 가리키는 노드를 끊은 뒤
			 * 새 노드로 변경해준다. 
			 * 또한 새 노드가 가리키는 노드는 next_Node로
			 * 설정해준다. 
			 */
			prev_Node.next = null;
			prev_Node.next = newNode;
			newNode.next = next_Node;
			size++;
		 
		}
		
		/*
		 * remove()는 가장 앞에 있는 요소를 제거하는 것이다.
		 * 즉, head가 가리키는 요소만 없애주면 된다.
		 * 
		 * head가 가리키는 노드의 링크와 데이터를 null로 지워준 뒤, head를 다음 노드로
		 * 업데이트를 해주는 것이다.
		 * 
		 * 그리고 삭제하려는 노드가 리스트에서의 유일한 노드였을 경우 해당 노드를 삭제하면
		 * tail이 가리키는 노드 또한 없어지게 된다.ㄴ
		 */
		public E remove() {
			 
			Node<E> headNode = head;
		 
			// 리스트에 아무런 요소가 없는데 삭제를 시도하면, 예외를 던져준다.
			if (headNode == null)
				throw new NoSuchElementException();
				
			// 삭제된 노드를 반환하기 위한 임시 변수
			E element = headNode.data;
				
			// head의 다음 노드 
			Node<E> nextNode = head.next;
				
			// head 노드의 데이터들을 모두 삭제
			head.data = null;
			head.next = null;
				
			// head 가 다음 노드를 가리키도록 업데이트
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
		 * 특정 위치에 있는 데이터를 리스트에서 찾아서 삭제할 때 사용하는 메서드.
		 * add 메서드와 반대로 구현해주면 된다.
		 * 
		 * 삭제하려는 노드의 이전 노드의 next변수를 삭제하려는 노드의 다음 노드를 가리키도록
		 * 해준다.
		 */
		@Override
		public E remove(int index) {
		 
			// 삭제하려는 노드가 첫 번째 원소일 경우 
			if (index == 0) {
				return remove();
			}
		 
			// 잘못된 범위에 대한 예외 
			if (index >= size || index < 0) {
				throw new IndexOutOfBoundsException();
			}
				
			Node<E> prevNode = search(index - 1);	// 삭제할 노드의 이전 노드 
			Node<E> removedNode = prevNode.next;	// 삭제할 노드 
			Node<E> nextNode = removedNode.next;	// 삭제할 노드의 다음 노드 
		 
			E element = removedNode.data;	// 삭제되는 노드의 데이터를 반환하기 위한 임시변수
			
			// 이전 노드가 가리키는 노드를 삭제하려는 노드의 다음노드로 변경 
			prevNode.next = nextNode;
		 
			// 만약 삭제했던 노드가 마지막 노드라면 tail을 prevNode로 갱신
			if(prevNode.next == null) {
				tail = prevNode;
			}
			// 데이터 삭제 
			removedNode.next = null;
			removedNode.data = null;
			size--;
		 
			return element;
		}
		
		/*
		 * remove(Object value) 메서드는 사용자가 원하는 특정요소를 리스트에서 찾아서 삭제하는 것이다.
		 * remove(int index)와 동일한 메커니즘으로 작동한다.
		 * 
		 * 다만 고려해야할 점은 "삭제하려는 요소가 존재하는지"를 염두해두어야한다.
		 * 예로들어 삭제하려는 요소를 찾지 못했을 경우 false를 반환해주고, 만약 찾았을 경우
		 * remove(int index)와 동일한 삭제 방식을 사용하면 된다.
		 */
		@Override
		public boolean remove(Object value) {
		 
			Node<E> prevNode = head;
			boolean hasValue = false;
			Node<E> x = head;	// removedNode 
				
			// value 와 일치하는 노드를 찾는다.
			for (; x != null; x = x.next) {
				if (value.equals(x.data)) {
					hasValue = true;
					break;
				}
				prevNode = x;
			}
		 
			// 일치하는 요소가 없을 경우 false 반환
			if(x == null) {
				return false;
			}
		 
			// 만약 삭제하려는 노드가 head라면 기존 remove()를 사용 	
			if (x.equals(head)) {
				remove();
				return true;
			}
		 
			else {
				// 이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
				prevNode.next = x.next;
		 
				// 만약 삭제했던 노드가 마지막 노드라면 tail을 prevNode로 갱신
				if(prevNode.next == null) {
					tail = prevNode;
				}
				x.data = null;
				x.next = null;
				size--;
				return true;
			}
		}
		
		/*
		 *  그 이외의 필수 구현 메서드는
		 *  메서드별로 간단하게 설명하고 구현하겠다,
		 *  
		 *  이유는 코드가 짧고, 이해하기 쉽기 때문이다.
		 */
		
		@Override
		public E get(int index) {
			// 원하는 데이터를 찾기 위해 search를 호출하고, 그 데이터를 가져옴.
			return search(index).data;
		}
		
		@Override
		public void set(int index, E value) {
			// 지정된 index 번호의 데이터를 변경함.
			Node<E> replaceNode = search(index);
			replaceNode.data = null;
			replaceNode.data = value;
		}
		
		@Override
		public int indexOf(Object value) {
			int index = 0;
				// 처음부터 끝까지 순회하여, 일치하는 데이터가 있을 때 까지 순회.
			for (Node<E> x = head; x != null; x = x.next) {
				if (value.equals(x.data)) {
					return index;
				}
				index++;
			}
			// 찾고자 하는 요소를 찾지 못했을 경우 -1 반환
			return -1;
		}
		
		@Override
		public boolean contains(Object item) {
			// 컬렉션 객체가 item을 가지고 있는지 indexOf 호출 후, 리턴 값으로 확인.
			return indexOf(item) >= 0;
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
			// 반복문을 통하여 모든 데이터를 삭제
			for (Node<E> x = head; x != null;) {
				Node<E> nextNode = x.next;
				x.data = null;
				x.next = null;
				x = nextNode;
			}
			head = tail = null;
			size = 0;
		}
}
