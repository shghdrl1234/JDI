package Interface.List;

import java.util.Arrays;

public class ArrayList {

	/*
	 * ArrayList는 다른 자료구조와 달리 Object[] 배열을 두고 사용한다.
	 * 
	 * ArrayList는 리스트가 꽉 차면 리스트를 자동으로 늘려주는데 
	 * 
	 * 이를 "동적 할당"이라고 한다. 동적 할당은 모든 자료구조에서 전제를 두고 사용한다.
	 * 
	 * 리스트 계열의 자료구조는 데이터 사이에 빈 공간을 허락하지 않는다.
	 * 
	 * 배열에서는 빈 공간이 생기면 그 공간이 null이 되지만
	 * 
	 * 리스트 계열의 자료 구조에서는 빈 공간이 없어진다.
	 * 
	 * 즉, null 뒤에 있는 모든 데이터를 한 칸씩 끌어와야한다는 것이다.
	 * 
	 * 리스트 계열 자료구조는 데이터들이 항상 연속되어 있어야 한다.
	 * 
	 * 
	 */
	
	public static void main(String[] args) {

	}
	/*
	 * 앞서 만들어 둔 List 인터페이스를 구현해준다.
	 * 
	 */
	public static class ArrayList_<E> implements List_basic<E> {
		
		// 배열이 생성 될 때의 최초 할당 크기이자 최소 할당 용적 (상수)
		private static final int DEFAULT_CAPACITY = 10;	
		
		// 아무 것도 없는 빈 배열 (상수)
		private static final Object[] EMPTY_ARRAY = {};	
	
		// 요소 개수 
		private int size;
		
		// 요소를 담을 배열 
		Object[] array;
	 
		// 생성자1 (초기 공간 할당 X)
		public ArrayList_() {
			this.array = EMPTY_ARRAY;
			this.size = 0;
	 
		}
	 
		// 생성자2 (초기 공간 할당 O)
		public ArrayList_(int capacity) {
			this.array = new Object[capacity];
			this.size = 0;
		}
	
	/*
	 * 동적 할당을 위한 resize() 메서드를 구현한다.
	 * 우리는 들어오는 데이터에 개수에 따라 최적화된 용적을 갖을 필요가 있다.
	 * 데이터는 적은데 용적이 크면 메모리가 낭비 되고, 반대로 용적은 적은데 데이터가 많으면
	 * 넘치는 데이터들을 보관할 수 없게 되는 상황을 마주칠 수 있다.
	 * 
	 * 그렇기 때문데 size가 용적에 얼마만큼 차있는지를 확인하고 적절한 크기에 맞게 배열의 용적을
	 * 변경해야 한다.
	 */
		private void resize() {
			int array_capacity = array.length;
		 
			// 용적이 0인 경우 (=사용자가 용적을 별도로 지정하지 않은 상태)
			if (Arrays.equals(array, EMPTY_ARRAY)) {
				array = new Object[DEFAULT_CAPACITY];
				return;
			}
		 
			// 용량이 꽉 찰 경우  
			if (size == array_capacity) {
				int new_capacity = array_capacity * 2;
		 
				// copy
				array = Arrays.copyOf(array, new_capacity);
				return;
			}
			// 용적의 절반 미만으로 요소가 차지하고 있을 경우 
			if (size < (array_capacity / 2)) {
				int new_capacity = array_capacity / 2;
		 
				// copy
				array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
				return;
			}
		}
		
		/*
		 * ArrayList에 데이터를 추가할 수 있도록하는 메서드를 구현하고자 한다.
		 * 
		 * add 메서드는 크게 3가지로 분류되는데
		 * 1. 가장 마지막 부분에 추가 : addLast(E value)
		 * 2. 가장 앞부분에 추가 : addFirst(E value)
		 * 3. 특정 위치에 추가 : add(int index, E value)
		 * 
		 * 물론 현재 자바에 내장되어있는 ArrayList는 addLast() 역할을 add() 메서드가 하고,
		 * 특정 위치에 추가하는 add(int index, E element)로 오버라이딩된 메서드가 하며, addFirst()는 없다.
		 * 하지만 편의상 3가지로 나눠서 본다.
		 */
		
		@Override
		public boolean add(E value) {
			addLast(value);
			return true;
		}
		 
		public void addLast(E value) {
		 
			// 꽉차있는 상태라면 용적 재할당
			if (size == array.length) {
				resize();
			}
			array[size] = value;	// 마지막 위치에 요소 추가
			size++;	// 사이즈 1 증가
		}
		
		/*
		 * 중간에 넣을 경우의 add메서드 사용이다. 
		 * 
		 * 먼저 index로 들어오는 값이 size를 벗어나는지, 또는 음수가 들어오는지를 확인한다.
		 * 만약에 범위를 벗어나거나 인덱스가 음수가 들어오면 예외를 발생시키도록 한다.
		 * 
		 * 그리고 사용자가 넘겨준 index가 size와 같다는 것은 결국 가장 마지막에 추가하는 것과 같은 의미이므로
		 * 이미 만들어두었던 addLast()를 호출한다.
		 * 
		 * 그 외의 경우가 이제 중간에 삽입되는 경우다.
		 * index와 그 후반에 있는 데이터들을 한 칸씩 뒤로 밀어야 하므로 반복문을 통해 뒤로 밀어주도록 한 뒤
		 * array[index]에는 새로운 요소로 교체해주도록 한다.
		 */
		@Override
		public void add(int index, E value) {
		 
			if (index > size || index < 0) {	// 영역을 벗어날 경우 예외 발생 
				throw new IndexOutOfBoundsException();
			}
			if (index == size) {	// index가 마지막 위치라면 addLast 메소드로 요소추가
				addLast(value);
			} 
			else {
					
				if(size == array.length) {	// 꽉차있다면 용적 재할당
					resize();
				}
					
				// index 기준 후자에 있는 모든 요소들 한 칸씩 뒤로 밀기
				for (int i = size; i > index; i--) {
					array[i] = array[i - 1];
				}
		 
				array[index] = value;	// index 위치에 요소 할당
				size++;
			}
		}
		/*
		 * 첫 번째 인덱스에 넣을 때 사용하는 add 메서드, index값인 0과 value를 전달한다. 
		 */
		public void addFirst(E value) {
			add(0, value);
		}
		
		/*
		 * get()은 index로 들어오는 값을 인덱스삼아 해당 위치에 있는 요소를 반환하는 메소드다.
		 * 배열을 찾아가는 것이기 때문에 반드시 잘못된 위치 참조에 대한 예외처리를 해주어야한다.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public E get(int index) {
			if(index >= size || index < 0) {	// 범위 벗어나면 예외 발생
				throw new IndexOutOfBoundsException();
			}
			// Object 타입에서 E타입으로 캐스팅 후 반환
			return (E) array[index];
		}
		
		/*
		 * set 메서드는 기존 index에 위치한 데이터를 새로운 데이터로 교체하는 것이다.
		 */
		@Override
		public void set(int index, E value) {
			if (index >= size || index < 0) {	// 범위를 벗어날 경우 예외 발생
				throw new IndexOutOfBoundsException();
			} 
			else {
				// 해당 위치의 요소를 교체
				array[index] = value;
			}
		}
		
		/*
		 * indexOf 메소드는 사용자가 찾고자 하는 요소(value)의 '위치(index)'를 반환하는 메소드다.
		 * 찾고자 하는 요소가 없으면 -1을 반환한다.
		 * 
		 * 그리고 중요한 점은 객체끼리 비교할 때는 동등연산자(==)가 아니라 반드시 .equals() 로 비교해야 한다. 
		 * 객체끼리 비교할 때 동등연산자를 쓰면 값을 비교하는 것이 아닌 주소를 비교하는 것이기 때문에 잘못된 결과를 초래한다.
		 */
		@Override
		public int indexOf(Object value) {
			int i = 0;
		    
			// value와 같은 객체(요소 값)일 경우 i(위치) 반환
			for (i = 0; i < size; i++) {
				if (array[i].equals(value)) {
					return i;
				}
			}
			// 일치하는 것이 없을경우 -1을 반환
			return -1;
		}
		
		/*
		 * indexOf메서드는 index가 0부터 시작했다면, 반대로 거꾸로 탐색하는 과정되 있다.
		 * 찾고자 하는 인덱스가 뒤 쪽이라면, 앞에서부터 찾을 필요가 없기 때문이다.
		 */
		public int lastIndexOf(Object value) {
			for(int i = size - 1; i >= 0; i--) {
				if(array[i].equals(value)) {
					return i;
				}
			}
			return -1;
		}

		/*
		 * indexOf 메소드는 사용자가 찾고자 하는 요소(value)의 '위치(index)'를 반환하는 메소드였다면, 
		 * contains는 사용자가 찾고자 하는 요소(value)가 존재 하는지 안하는지를 반환하는 메소드다.
		 * 
		 * 찾고자 하는 요소가 존재한다면 true를, 존재하지 않는다면 false를 반환한다.
		 */
		@Override
		public boolean contains(Object value) {
		 
			// 0 이상이면 요소가 존재한다는 뜻
			if(indexOf(value) >= 0) {
				return true;
			}
			else {
				return false;
			}
		}
		
		/*
		 * remove는 특정 위치에 있는 요소를 제거하는 것이다.
		 * index에 위치한 데이터를 삭제해버리고, 해당 위치 이후의 데이터들을 
		 * 한 칸씩 당겨오는 것이다. 
		 */
		@SuppressWarnings("unchecked")
		@Override
		public E remove(int index) {
		 
			if (index >= size || index < 0) {
				throw new IndexOutOfBoundsException();
			}
		 
			E element = (E) array[index];	// 삭제될 요소를 반환하기 위해 임시로 담아둠
			array[index] = null;
		    
			// 삭제한 요소의 뒤에 있는 모든 요소들을 한 칸씩 당겨옴
			for (int i = index; i < size - 1; i++) {
				array[i] = array[i + 1];
				array[i + 1] = null;
			}
			size--;
			resize();
			return element;
		}
		
		/*
		 * remove(Object value) 메서드는 원하는 특정요소를 리스트에서 찾아 삭제하는 것이다.
		 * 
		 * 결과적으로 이 메소드에서 필요한 동작은 value와 같은 요소가 존재하는지, 
		 * 만약에 존재한다면 몇 번째 위치에 존재하는지를 알아야 하는 것 하나. 
		 * 그리고 그 index의 데이터를 지우고 나머지 뒤의 요소들을 하나씩 당기는 작업 하나. 
		 * 이렇게 총 두가지의 동작이 필요하다.
		 */
		@Override
		public boolean remove(Object value) {
		 
			// 삭제하고자 하는 요소의 인덱스 찾기
			int index = indexOf(value);
		 
			// -1이라면 array에 요소가 없다는 의미이므로 false 반환
			if (index == -1) {
				return false;
			}
		 
			// index 위치에 있는 요소를 삭제
			remove(index);
			return true;
		}
		
		/*
		 * ArrayList가 동적으로 할당되면서 요소들을 삽입, 삭제가 많아지면 
		 * 사용자가 리스트에 담긴 요소의 개수가 몇 개인지 기억하기 힘들다. 
		 *
		 * ArrayList에서 size 변수는 private 접근제한자를 갖고 있기 때문에 외부에서 직접 참조를 할 수 없다.
		 *
		 */
		@Override
		public int size() {
			return size;	// 요소 개수 반환
		}
		
		/*
		 * isEmpty() 메소드는 현재 ArrayList에 요소가 단 하나도 존재하지 않고 비어있는지를 알려준다.
		 */
		@Override
		public boolean isEmpty() {
			return size == 0;	// 요소가 0개일 경우 비어있다는 의미이므로 true반환
		}
		
		/*
		 * clear()는 단어에서 짐작 할 수 있듯 모든 요소들을 비워버리는 작업이다. 
		 * 예로들어 리스트에 요소를 담아두었다가 초기화가 필요할 때 쓸 수 있는 유용한 존재다. 
		 * 또한 모든 요소를 비워버린다는 것은 요소가 0개라는 말로 size 또한 0으로 초기화해주고, 
		 * 배열의 용적 또한 현재 용적의 절반으로 줄일 수 있도록 해준다.
		 * =>현재 용적량의 기대치에 근접할 가능성이 높기 때문에 일단은 용적량을 일단 절반으로만 줄이는 것이다.
		 */
		@Override
		public void clear() {
			// 모든 공간을 null 처리 해준다. 
			for (int i = 0; i < size; i++) {
				array[i] = null;
			}
			size = 0;
			resize();
		}
	}
	}
