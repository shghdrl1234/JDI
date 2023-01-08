package Interface.Stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/*
	 * 기본적으로 Stack 클래스는 내부에서 최상위 타입 배열인 Object[] 배열을 사용하여
	 * 데이터들을 관리하고 있다.
	 * 
	 * 스택은 후입선출, 선입후출 방식이다.
	 * 
	 * a, b, c를 순차적으로 입력한 뒤 출력하면 c, b, a 순으로 출력된다.
	 * 
	 * 즉, 가장 먼저 들어간 것이 나중에 빠져나오게 된다.
	 * 
	 * 스택의 구조는 Bottom, Top, Capacity, size로 되어있다.
	 * Bottom : 가장 밑에 있는 데이터 또는 그 데이터의 인덱스를 의미한다.
	 * Top : 가장위에 있는 데이터 또는 그데이터의 인덱스를 의미한다.
	 * Capacity : 데이터를 담기 위한 목적
	 * size : 데이터의 개수
	 * 
	 * 스택의 특징이라면  기존의 리스트와 같은 메커니즘으로 인덱스가 증가하면서 추가하지만, 
	 * 삭제는 리스트와는 달리 가장 마지막 데이터를 삭제한다.
	 * 
	 * 모든 자료구조는 "동적 할당"을 전제로 한다.
	 * 
	 */

public class Stack<E> implements Stack_basic<E> {
	
	// 배열이 생성 될 때의 최초 할당 크기이자 최소 할당 용적 (상수)
	private static final int DEFAULT_CAPACITY = 10;	
	
	// 아무 것도 없는 빈 배열 (상수)
	private static final Object[] EMPTY_ARRAY = {};	
	
	// 요소들을 담을 배열
	private Object[] array;	

	// 배열에 담긴 요소의 개수 변수
	private int size; 
	
	
	// 생성자1 (초기 공간 할당 X) 
	public Stack() {
		this.array = EMPTY_ARRAY;
		this.size = 0;
	}
	
	// 생성자2 (초기 공간 할당 O) 
	public Stack(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
	}
	
	/*
	 * 동적 할당을 위한 resize 메서드 구현
	 * 
	 * 데이터는 적은데 용량이 크면 메모리가 낭비되고, 용량은 적은데 데이터가 많으면 넘치는 데이터들을
	 * 보관할 수 없게 되는 상황을 마주할 수 있다.
	 * 
	 * 그렇기 때문에 size가 capacity에 얼마만큼 차있는지를 확인하고, 적절한 크기에 맞게 배열의 용적을
	 * 변경해야한다.
	 * 
	 * 또한 용적은 외부에서 마음대로 접근하면 데이터의 손상을 야기할 수 있기 때문에
	 * private로 접근을 제한해준다.
	 * 
	 */
	private void resize() {
		/*
		 * 사용자가 별도로 용적을 설정하지 않은 경우에는 용적이 0이 된다.
		 * 생성자를 통하여 기본 용적의 크기만큼 배열을 생성해주고 메서드롤 종료한다.
		 * 
		 * 또한 주소가 아닌 값을 비교해야하기 때문에 Arrays.equals() 메소드를 사용한다.
		 */
		if(Arrays.equals(array, EMPTY_ARRAY)) {
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		int arrayCapacity = array.length; // 현재 용적 크기
		
		/*
		 * 데이터가 꽉 찰 경우에는 데이터를 더 받아오기 위해서 용적을 늘려야 한다.
		 * 즉, 데이터의 개수가 용적과 같을 경우는 꽉 차있는 경우를 말한다.
		 * 
		 * 이 때는 새롭게 용적을 늘릴 필요가 있으므로 현재 용적의 2배로 설정하도록 한다.
		 * 
		 * 용적을 늘린 후, 기존에 담겨있던 요소들을 새로 복사해와햐 한다. 이 때 편리하게
		 * 사용할 수 잇는 것이 Arrays.copyOf() 메서드가.
		 * 
		 * 첫 번째 파라미터로 복사할 배열을 넣어주고, 두 번째 파라미터는 용적의 크기를 넣어주면 된다.
		 * 만약 복사할 배열보다 용적이 크기가 클 경우, 배열을 복사한 뒤, 남은 빈공간은 null로 채워진다.
		 * 
		 */
		if(size == arrayCapacity) {
			int newSize = arrayCapacity * 2;
			
			// 배열 복사
			array = Arrays.copyOf(array, newSize);
			return;
		}
		
		/*
		 * 용적의 절반 미만으로 요소가 차지하고 있을 경우, 데이터는 적은데 빈 공간이 메모리를 차지하고 있어
		 * 불필요한 공간을 낭비할 뿐이다. 이럴 때에는 사이즈를 적절하게 줄여 주는 것이 좋다. 
		 */
		if(size < (arrayCapacity / 2)) {
			
			int newCapacity = (arrayCapacity / 2);
		
		// 배열 복사
		array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY , newCapacity));

		}
		
	}
	
	/*
	 * push() 메서드는 Stack에 데이터를 추가할 수 있도록 해준다.
	 * Stack의 최상단에 데이터를 추가해야하므로 한 종류 밖에 없다.
	 * 
	 * 리스트로 치면 add(E value)와 같은 역할이다.
	 * 
	 * !! 현재 자바에 내장되어있는 Vector을 상속받다보니 중간 삽인같은 특정 위치의 삽입도 가능하지만,
	 * 여기에서는 push 단일 메소드만 구현 할 것이다.
	 * 
	 */
	@Override
	public E push(E item) {
			
		// 용적이 꽉 차있다면 용적을 재할당 해준다.
		// => resize() 메서드를 호출하여 용적 재할당 및 배열을 복사한다.
		if (size == array.length) {
			resize();
		}
		 
		array[size] = item;	// 마지막 위치에 요소 추가 
		size++;	// 사이즈 1 증가 
			
		return item;
	}
	
	/*
	 * pop() 메서드는 push() 메서드의 메커니즘을 반대로 구현하면 된다.
	 * 
	 * 다만 중요한 점은 "삭제할 요소가 없는 경우" 즉, 스택이 비어있는 경우를 주의해야한다.
	 * 
	 */
	@Override
	public E pop() {
			
		// 만약 삭제할 요소가 없다면 Stack이 비어있다는 의미이므로 예외 발생시키기
		if(size == 0) {
			throw new EmptyStackException();
		}
		
		/*
		 * @SuppressWarnings("unchecked")란 형 안정성이 보장될 경우
		 * ClassCastExeption이 뜨지 않으니 경고들을 무시하겠다는 것이다.
		 * 형 변환시 예외 가능성이 없을 확실한 경우에 최소한의 범위에서 써주는 것이 좋다.
		 */
		@SuppressWarnings("unchecked")
		E obj = (E) array[size - 1];	// 삭제될 요소를 반환하기 위한 임시 변수 
			
		array[size - 1] = null;	// 요소 삭제 
			
		size--;	// 사이즈 1 감소 
		resize();	// 용적 재할당 
			
		return obj;
	}
	
	/*
	 * peek() 메서드는 가장 산단에 있는 데이터를 삭제하지 않고 "확인"만 할 때 사용한다.
	 * 
	 * pop() 메서드에서 삭제과정만 없는 것이 peek()이다.
	 * 
	 * 스택에 데이터가 없는 경우가 있으니 예외를 던져 준다.
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		// 만약 삭제할 요소가 없다면 Stack이 비어있다는 의미이므로 예외 발생시키기
		if(size == 0) {
			throw new EmptyStackException();
		}
			
		return (E) array[size - 1];
	}
	
	/*
	 * search(Object o) 메서드는 찾으려는 데이터가 상단의 데이터로부터 얼마만큼 떨여져 있는지에 대한
	 * 상대적인 위치 값이다.
	 * 
	 * Top으로부터 떨어져있는 거리를 의미한다. 보통 인덱스는 0부터 시작하지만
	 * stack의 search는 1부터 시작한다.
	 * 
	 * 수식으로 표현하면 size-index이다.
	 * 
	 * 일치하는 데이터를 찾지 못했을 경우는 -1을 반환한다.
	 * 
	 */
	@Override
	public int search(Object value) {
		// value가 null일 때는 eqauls(null)이되어 null pointer exception이 발생할 수 있으니,
		// == 로 null값을 비교해준다.
		if(value == null) {
			for(int idx = size - 1; idx >= 0; idx--) {
				if(array[idx] == null) {
					return size - idx;
				}
			}
		} else {
			for(int idx = size - 1; idx >= 0; idx--) {
		
				// 같은 객체를 찾았을 경우 size - idx 값을 반환 
				if(array[idx].equals(value)) {
					return size - idx;
				}
			}
		}
		return -1;
	}
	
	/*
	 * stack의 크기 확인.
	 * psuh() 와 pop() 등의 메서드로 인해 size가 변한다.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/*
	 * clear() 메서드는 모든 요소들을 비워버리는 작업이다.
	 * 모든 요소를 비워버린다는 것은 요소가 0개라는 말로 size 또한 0으로 초기화 해주며
	 * 배열의 용적 또한 현재의 절반으로 줄일 수 있도록 해준다.
	 * 
	 * 절반인 이유는 블로그에 글을 쓴 분의 의견으로, 데이터를 사용한 만큼
	 * 앞으로 들어올 데이터 또한 그 정도의 용적일 가능성이 높으며, 용적량의 기대치에 근접할
	 * 가능성이 높기 때문에 절반으로만 줄인다고 한다.
	 * 
	 * 추가로 데이터를 쓰지 않더라도 삭제 과정에서 용적량을 줄이기 때문에 크게 문제되진 않는다.
	 * 
	 */
	@Override
	public void clear() {
			
		// 저장되어있던 모든 요소를 null 처리 해준다.
		for(int i = 0; i < size; i++) {
			array[i] = null;
		}
		size = 0;
		resize();
	}
	
	/*
	 * empty() 메서드는 단어 그대로 stack이 비어있는지 확인하여 boolean 값을 반환하는 메서드이다.
	 * 
	 * 모든 요소를 검사할 필요없이, size가 0 인지 확인하면 된다.
	 */
	
	@Override
	public boolean empty() {
		return size == 0;
	}
}