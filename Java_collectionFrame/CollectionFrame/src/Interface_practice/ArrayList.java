package Interface_practice;

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
	public class ArrayList<E> implements List<E> {
		
		// 배열이 생성 될 때의 최초 할당 크기이자 최소 할당 용적 (상수)
		private static final int DEFAULT_CAPACITY = 10;	
		
		// 아무 것도 없는 빈 배열 (상수)
		private static final Object[] EMPTY_ARRAY = {};	
	
		// 요소 개수 
		private int size;
		
		// 요소를 담을 배열 
		Object[] array;
	 
		// 생성자1 (초기 공간 할당 X)
		public ArrayList() {
			this.array = EMPTY_ARRAY;
			this.size = 0;
	 
		}
	 
		// 생성자2 (초기 공간 할당 O)
		public ArrayList(int capacity) {
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
	
}
