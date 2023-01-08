package Interface.Queue;

import java.util.NoSuchElementException;

public class CircularQueue {
	/*
	 * 배열을 이용한 큐를 구현할 것이다.
	 * 
	 * 큐는 기본 원칙으로 선입선출이다.
	 * 
	 * poll : 큐 원소를 삭제할 경우 사용
	 * offer : 큐에 원소를 추가할 경우 사용
	 * 
	 * 선입선출의 방식으로 원소를 채우고, 삭제하면
	 * 
	 * "배열 구조"안에서는 0,1,2 를 채우고 0을 삭제하고 다음에 0을 넣으면
	 * null, 1, 2, 0으로 되면서 원소들이 뒤로 치우치게 되는 현상이 발생한다.
	 * 
	 * 이렇게 선형적으로 접근하게되면 쏠림현상이 발생하는데, 그렇다고 매 번 삭제 연산 때마다
	 * 삭제된 원소 뒤의 모든 원소들을 한 자리씩 앞으로 이동시키는 것은 매우 비효율적이고,
	 * 그렇다고 배열 크기를 늘리자니 배열이 꽉 찬 상태도 아니라 빈 공간을 낭비하게 된다.
	 * 
	 * 이를 해결하기 위한 아주 간단한 방법은 앞의 빈 자리에 다시 채워 넣는 것이다.
	 * 
	 * 배열을 원형이라고 생각하자.
	 * 
	 * 가장 마지막 원소의 위치를 가리키는 변수 front와 rear가 필요하다.
	 * 
	 *  0		1	 2(front)	 3		 4	 	 5	  6(rear)
	 *  "", 	"", 	"", 	"m", 	"o", 	"p", 	"q"
	 *  
	 *  이 배열에서 원소를 추가한다면
	 *  
	 *  0(rear)	1	 2(front)	 3	     4       5 	  	 6
	 *  "r", 	"",		"",		 "m",	 "o",	 "p",   "q"
	 * 
	 *  이런 방법으로 배열의 요소 채우게 되고
	 *  rear과 front를 지정해주어, 배열의 시작과 끝을 알 수 있게 해준다.
	 *  
	 *  쉽게 생각하면 rear를 따라 원소가 추가되고, front를 따라 원소가 삭제된다.
	 *  
	 *  위와 같은 방식으로 원소들을 채워나가면 효율적으로 배열을 관리할 수 있다.
	 *  
	 *  만약, "더 이상 빈 자리가 없을 경우"에는 배열의 크기를 늘려주면 된다.
	 *  => if 분기를 사용하여 배열의 용적을 늘린 후 배열을 복사하는 방향으로 메서드를 생성하자.
	 *  
	 *  ! 여기서 front에 값이 없고, rear에는 값이 있는 것이 궁금할 것이다.
	 *  그 이유는 조금만 생각해보면 이해가 쉽게 된다.
	 *  
	 *  front를 비워두지 않게 된다면, 원소를 하나씩 삭제해 갈 경우, 배열이 비어있는지, 가득차 있는지
	 *  구분할 방법이 없기 때문이다.
	 *  => front가 비어있다면, front와 rear이 모두 가득 찼을 경우, 배열이 꽉 찬 것을 확인 할 수 있다.
	 *  => front가 비어있다면, front와 rear이 모두 비워졌을 경우, 배열이 비어있는 것을 확인할 수 있다.
	 *  
	 *  이러한 구조를 Circular Queue라 부르며, 원형 큐 또는 환형 큐라고 한다.
	 *  
	 *  stack, list를 구현하면서 모든 자료구조는 동적할당을 전제로 한다고 하였다.
	 *  그렇다면 Circular Queue를 구현해보자.
	 *  
	 *  
	 */ 
	
	/*
	 * 지금 구현하는 큐는 배열을 이용하므로 ArrayQueue라는 이름으로 생성한다.
	 */
	public class ArrayQueue<E> implements Queue_basic<E> {
		 
		private static final int DEFAULT_CAPACITY = 64;	// 최소(기본) 용적 크기 
		
		private Object[] array;	// 요소를 담을 배열 
		private int size;	// 요소 개수 
		
		private int front;	// 시작 인덱스를 가리키는 변수(빈 공간임을 유의)
		private int rear;	// 마지막 요소의 인덱스를 가리키는 변수 
		
		
		// 생성자1 (초기 용적 할당을 안할 경우)  
		public ArrayQueue() {
			this.array = new Object[DEFAULT_CAPACITY];
			this.size = 0;
			this.front = 0;
			this.rear = 0;
		}
		
		// 생성자2 (초기 용적 할당을 할 경우) 
		public ArrayQueue(int capacity) {
			this.array = new Object[capacity];
			this.size = 0;
			this.front = 0;
			this.rear = 0;
		}
		/*
		 * 동적할당을 위한 resize 메서드를 구현한다. 
		 * 
		 * 용적이, 데이터보다 많아 메모리 낭비가 심하거나
		 * 데이터보다 적어, 데이터를 수용할 수 없는 경우를 판단하여
		 * 여유롭게 용적을 확보할 수 있도록 하자.
		 * 
		 * 용적을 증가시키는 경우 : ((rear + 1) % arrayCapacity == front)
		 * 용적이 가득 찰 경우다. 이 의미는 rear의 다음 인덱스(rear +1) 이 front 랑 같다는 말과 동일하다. \
		 * 다만 (rear+1)에 % arrayCapacity 
		 * 즉, 기존 배열의 길이를 나누는 이유는 front 가 rear보다 작을경우를 고려해야 하기 때문이다.
		 * 
		 * 예로들어 rear가 7이고, front가 0이라면 7+1 = 8이므로, 0과 같지 않다. 
		 * 이러한 이유로 길이(예시에서는 8)로 나눠준 나머지 (7+1)%8 = 0 을 해야 
		 * 정확한 조건 때 용적을 증가 시킬 수 있다.
		 * 
		 * 
		 * 용적을 줄여야 하는 경우 : (size < (arrayCapacity / 4))
		 * 데이터가 1/4 미만으로 떨어질 경우에는 필요없는 공간이 너무 많아지게 되므로 절반정도로만 줄인다.
		 * 
		 * 코드를 보면 알겠지만 새로운 용적의 배열에 값을 복사하는 과정 자체는 똑같기 때문에 
		 * 따로 두 경우의 조건문은 달지 않고 파라미터(newCapacity)로 받은 새 용적을 이용하여 용적의 사이즈를 변경할 것이다.
		 * 
		 */
		private void resize(int newCapacity) {
			 
			int arrayCapacity = array.length; // 현재 용적 크기
		 
			Object[] newArray = new Object[newCapacity]; // 용적을 변경한 배열
		 
			/*
			 * i = new array index 
			 * j = original array 
			 * index 요소 개수(size)만큼 새 배열에 값 복사
			 * 
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
		 * offer메서드는 항상 배열이 가진 마지막 요소 후방에 데이터를 추가한다.
		 * 리스트에서는 add(E value)의 역할을 한다. 
		 * 
		 * 여기서 중요한 점은 배열이 가득찼을 경우이다.
		 */
		@Override
		public boolean offer(E item) {
				
			// 용적이 가득 찼을 경우 
			if((rear + 1) % array.length == front) {
				resize(array.length * 2);	// 용적을 두 배 늘려준다. => 동적 할당
			}
				
			rear = (rear + 1) % array.length;	// rear을 rear의 다음 위치로 갱신 
				
			array[rear] = item;
			size++;	// 사이즈 1 증가 
				
			return true;
		}
		
		/*
		 * poll 메서드는 리스트의 remove(E value)의 역할을 한다.
		 * 큐에서는 front를 기준으로 +1의 인덱스에 있는 값을 지운다.
		 * 
		 * 여기서 중요한 점은 반환할 요소가 없을 경우이다.
		 */
		@Override
		public E poll() {
				
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
		
		/*
		 * remove 호출시 poll를 호출할 수 있도록 하였다.
		 * 대신, null 값일 때 예외를 던지도록 구현함. 
		 */
		public E remove() {
			
			E item = poll();
				
			if(item == null) {
				throw new NoSuchElementException();
			}
				
			return item;
		}
		
		/*
		 * peek() 메서드는 stack에서 구현한 것과 같이 가장 앞에 있는 데이터를 
		 * 삭제하지 않고 확인만 하고 싶을 때 사용하도록 구현한다.
		 * 
		 * poll() 메서드에서 삭제 과정만 없도록 구현한다.
		 * 
		 * 큐에서는 데이터가 없으면 null을 던진다. 반대로 이와 유사한 element() 메서드는
		 * 큐에 요소가 없을 경우 remove() 메서드와 마찬가지로 예외를 던진다.
		 */
		@Override
		public E peek() {
				
			// 요소가 없을 경우 null 반환
			if(size == 0) {
				return null;
			}
				
			@SuppressWarnings("unchecked")
			E item = (E) array[(front + 1) % array.length];
			return item;
		}
		/*
		 * remove()와 같은 과정으로 peek() 메서드를 호출하게 함.
		 * 
		 */
		public E element() {
			
			E item = peek();
				
			if(item == null) {
				throw new NoSuchElementException();
			}		
			return item;
		}
	 
		/*
		 * 현재 배열의 크기를 반환.
		 * => 용적이 아니다. 
		 */
		public int size() {
			return size;
		}

		/*
		 * 현재 찾고자 하는 요소가 큐에 있는지 확인하는 contains 메서드.
		 *  
		 * 이 때 0번째 인덱스부터 용적크기까지 모두 검사할 수도 있지만, 기본적으로
		 * 용적 크기에 비해 요소의 개수가 훨씬 적은 경우가 많다.
		 *  
		 * 그래서 모든 공간을 찾기보다는 요소의 개수만큼 
		 * 정확히 범위를 짚어서 반복해주는 것이 더욱 효율적이다.
		 */
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
		
		/*
		 * 모든 큐의 요소를 비울 때 사용
		 */
		public void clear() {
			
			for(int i = 0; i < array.length; i++) {
				array[i] = null;
			}
				
			front = rear = size = 0;
		}
	}
	
}

