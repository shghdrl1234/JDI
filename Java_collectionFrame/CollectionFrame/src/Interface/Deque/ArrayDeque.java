
package Interface.Deque;

import java.util.NoSuchElementException;

import Interface.Queue.Queue_basic;

/*
 * 덱(Double-ended queue)이란 ?
 * 
 * 두 개의 종료 지점이 있다.
 * 양 쪽 방향으로 삽입 삭제가 이루어질 수 있도록 구현된 것이다.
 *
 * 양방향 연결리스트와 유사한 매커니즘이다.
 * 
 * 장점으로는 스택처럼 사용할 수도 있고, 큐 처럼 사용할 수도 있다.
 *
 */

/*
 * 지금 구현하는 덱은 배열을 이용한다. 
 * 
 */
public class ArrayDeque<E> implements Queue_basic<E>{
	 
	private static final int DEFAULT_CAPACITY = 64; // 최소(기본) 용적 크기
 
	private Object[] array; // 요소를 담을 배열
	private int size; // 요소 개수
 
	private int front; // 시작 인덱스를 가리키는 변수(빈 공간임을 유의)
	private int rear; // 마지막 요소의 인덱스를 가리키는 변수
 
	// 생성자1 (초기 용적 할당을 안할 경우)
	public ArrayDeque() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
 
	// 생성자2 (초기 용적 할당을 할 경우)
	public ArrayDeque(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	/*
	 * 동적 할당을 위한 resize() 메서드
	 */
	private void resize(int newCapacity) {
		 
		int arrayCapacity = array.length; // 현재 용적 크기
	 
		Object[] newArray = new Object[newCapacity]; // 용적을 변경한 배열
	 
		/*
		 * i = new array index 
		 * j = original array 
		 * index 요소 개수(size)만큼 새 배열에 값 복사
		 */
		for (int i = 1, j = front + 1; i <= size; i++, j++) {
			newArray[i] = array[j % arrayCapacity];
		}
	 
		this.array = null;
		this.array = newArray; // 새 배열을 기존 array의 배열로 덮어씌움
	 
		front = 0;
		rear = size;
	 
	}
	
	/*
	 * 기본적인 데이터 저장 메서드 offer() 
	 */
	@Override
	public boolean offer(E item) {
		return offerLast(item);
	}
		
	public boolean offerLast(E item) {
			
		// 용적이 가득 찼을 경우 
		if((rear + 1) % array.length == front) {
			resize(array.length * 2);	// 용적을 두 배 늘려준다. 
		}
			
		rear = (rear + 1) % array.length;	// rear을 rear의 다음 위치로 갱신 
			
		array[rear] = item;
		size++;	// 사이즈 1 증가 
			
		return true;
	}
	
	/*
	 * 전방 삽입 offerFirst() 메서드
	 * 
	 * OfferLast()는 후방 변수를 이용하였다면, 이 번에는 반대로 전방변수를 이용한다.
	 * 
	 * front를 감소시켜야 하는 것에 주의하자.
	 * front = 0 일 때 앞 부분에 원소를 추가하면 front 값을 새로운 값으로 갱신 시키게 된다.
	 * 
	 * front = (front - 1) % array.length 수식에서 1이 0이면, 결과적으로
	 * -1 이라는 값이 나온다.
	 * 
	 * 음수가 나와버리게 되어 배열 인덱스를 참조할 수 없다.
	 * 이러한 문제를 해결하기 위해서는 나누려는 값만큼 먼저 더해주면 된다.
	 * front = (front - 1 + array.length) % array.length
	 * 
	 */
	public boolean offerFirst(E item) {
		// 용적이 가득 찼을 경우
		if((front - 1 + array.length) % array.length == rear) {
			resize(array.length * 2);	// 용적을 두 배 늘려준다. 
		}
			
		array[front] = item;	// front위치는 빈 공간이기 때문에 추가 해준 뒤 front 값을 갱신한다.
		front = (front - 1 + array.length) % array.length;
		size++;	// 사이즈 1 증가
			
		return true;
	}
	
	/*
	 * poll() 메서드
	 * ArrayQueue를 구현할 때와 유사하니 전체적인 설명은 생략.
	 */
	@Override
	public E poll() {		
		return pollFirst();
	}
		
	public E pollFirst() {
		if(size == 0) {	// 삭제할 요소가 없을 경우 null 반환 
			return null;
		}
			
		front = (front + 1) % array.length; // front 를 한 칸 옮긴다.
		
		@SuppressWarnings("unchecked")
		E item = (E) array[front];	// 반환할 데이터 임시 저장 
		
		array[front] = null;	// 해당 front의 데이터 삭제
		size--;	// 사이즈 감소 
		
		
		// 용적이 최소 크기(64)보다 크고 요소 개수가 1/4 미만일 경우
		if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
				
			// 아무리 작아도 최소용적 미만으로 줄이지는 않도록 한다. 
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
			
		return item;
	}
		
	public E remove() {
		return removeFirst();	// 예외는 removeFirst()에서 던져준다.
	}
	 
	public E removeFirst() {
		E item = pollFirst();	// pollFirst()을 호출한다.
		
		if(item == null) {
			throw new NoSuchElementException();
		}
		
		return item;
	}
	
	/*
	 * 후방 데이터를 삭제하는 pollLast()메서드
	 */
	public E pollLast() {
		if(size == 0) {	// 삭제할 요소가 없을 경우 null 반환 
			return null;
		}
			
		@SuppressWarnings("unchecked")
		E item = (E) array[rear];	// 반환할 데이터 임시 저장 
			
		array[rear] = null;	// 해당 rear의 데이터 삭제
		
		rear = (rear - 1 + array.length) % array.length; // rear 를 한 칸 옮긴다.
		size--;	// 사이즈 감소 
			
			
		// 용적이 최소 크기(64)보다 크고 요소 개수가 1/4 미만일 경우
		if(array.length > DEFAULT_CAPACITY && size < (array.length / 4)) {
				
			// 아무리 작아도 최소용적 미만으로 줄이지는 않도록 한다. 
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
			
		return item;
	}
		
	public E removeLast() {
		E item = pollLast();
			
		if(item == null) { // 데이터가 없다면 예외를 던진다.
			throw new NoSuchElementException();
		}
			
		return item;
	}
	
	/*
	 * peek() 및 peekLast()
	 */
	@Override
	public E peek() {
		return peekFirst();
	}
		
	public E peekFirst() {
		// 요소가 없을 경우 null 반환
		if(size == 0) {
			return null;
		}
			
		@SuppressWarnings("unchecked")
		E item = (E) array[(front + 1) % array.length];
		return item;
	}
	
	public E peekLast() {
		// 요소가 없을 경우 null 반환
		if(size == 0) {
			return null;
		}
			
		@SuppressWarnings("unchecked")
		E item = (E) array[(rear)];
		return item;
	}
	
	/*
	 * peek()의 역할을 하지만, 값이 없을때는 null이 아닌 예외를 던지는
	 * element() 메서드 및 getFirst() 메서드.
	 */
	public E element() { 
		return getFirst(); 
	} 
	 
	public E getFirst() { 
		E item = peek(); 
	 
		// 앞의 원소가 null 이라면(size = 0) 예외를 던진다.  
		if(item == null) { 
			throw new NoSuchElementException(); 
		} 
		return item; 
	}
	
	/*
	 * 마지막 원소를 반환하는 getLast() 메서드,
	 * peekLast()를 활용하여, null이면 예외를 던져준다.
	 */
	public E getLast() {
		E item = peekLast();
	 
		// 앞의 원소가 null 이라면(size = 0) 예외를 던진다.
		if(item == null) {
			throw new NoSuchElementException();
		}
		return item;
	}
	
	/*
	 * 이하 대부분의 컬렉션에서 단순하고, 변형이 별로 없는 메서드
	 * 크기, 데이터가 비었는지 등.
	 */
	
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;	
	}
	public boolean contains(Object value) {
		
		int start = (front + 1) % array.length;
			
		/**
		 *  i : 요소 개수만큼만 반복한다. 
		 *  idx : 원소 위치로, 매 회 (idx + 1) % array.length; 의 위치로 갱신 
		 */
		for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
			if(array[idx].equals(value)) {
				return true;
			}
		}
		return false;
	}
	public void clear() {
		
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
			
		front = rear = size = 0;
	}
	
}
