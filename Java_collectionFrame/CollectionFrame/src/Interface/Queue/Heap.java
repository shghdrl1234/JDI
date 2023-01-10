package Interface.Queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/*
	 * Heap 자료구조를 이용하여 우선순위 큐가 구현된다.
	 * 
	 * 그래서 haep을 Queue패키지에 넣었다.
	 * 
	 * Heap이란? 최솟값 또는 최댓값을 빠르게 찾아내기 위해 완전이진트리로 만들어진 자료구조다.
	 * 
	 * 완전이진트리는 트리구조의 이진트리에서 
	 * 1. 마지막 레벨을 제외한 모든 노드가 채워져야하며
	 * 2. 모든 노드들은 왼쪽부터 채워진 것 이다.
	 * (트리, 이진트리 등은 추후 정리 예정)
	 * 
	 * Heap을 구현할 때는 최댓값 or 최솟값을 어떻게 빠르게 찾아낼 수 있는가를 고민해야한다.
	 * 
	 * 기존 정렬 방식은 데이터가 입력될 때마다 리스트에 있는 원소들과 비교를 한다.
	 * 이렇게 하면 전체 순회를 하게 되므로 효율이 떨어진다.
	 * 
	 * 하지만 완전이진트리에서는 "부모 노드는 항상 자식 노드 보다 우선순위가 높다"
	 * 즉, 트리의 최상단에 있는 단 하나의 root 노드는 항상 우선순위가 높다는 말이 된다.
	 * 
	 * 모든 요소들을 고려하여 우선순위를 정할 필요 없이, 부모 노드는 자식 노드보다 항상
	 * 우선순위가 앞선다는 조건만 만족시키며 완전이진트리 형태로 채워나가는 것이다.
	 * 
	 * Heap을 통하여 정렬을 하면, 형제노드의 우선순위는 고려되지 않으며
	 * 이러한 정렬 상태를 반 정렬 상태, 느슨한 정렬 상태, 약한 힙 등으로 불린다.
	 * 
	 * 형제간 대소관계가 필요없는 이유는, 우선순위가 높은 순서대로 뽑는다는 것에 중점을 두었기 때문이다.
	 * 
	 * 원소를 넣을때도 우선순위가 높은 순서대로 나올 수 있도록 유지해야하며, 
	 * 뽑을 때 또한 우선 순위가 높은 순서대로 차례대로 나오기만 하면 된다.
	 * 
	 * Heap은 우선순위가 높은 순서대로 나온다고 했다. 
	 * 이 말은 어떻게 우선순위를 매기느냐에 따라 달라지겠지만,
	 * 기본적으로 정수, 문자, 문자열 같은 경우 언어에서 지원하는 기본 정렬 기준들이 있다.
	 * 
	 * Heap에서도 기본적인 정렬은 오름차순, 내림차순이 있으며, (부모 - 자식 관계에 해당, 형제는 해당안됨) 
	 * 최소힙 (root노드가 최솟값), 최대합(root노드가 최댓값)이라고 한다. 
	 * 
	 * 최소힙 : 부모 노드의 값 <= 자식 노드의 값 => 오름차순
	 * 최대힙 : 부모 노드의 값 >= 자식 노드의 값 => 내림차순
	 */


	/*
	 * 가장 표준적으로 구현되는 방식은 배열이다. 물론 연결리스트로 구현이 가능하긴 하지만,.
	 * 문제는 특정 노드의 검색, 이동 과정이 조금 더 번거롭기 때문이다.
	 * 배열의 경우는 특정 인덱스에 바로 접근할 수가 있기 때문에 좀 더 효율적이기도 하다.
	 *	
	 * 배열로 구현하게되면 특징 및 장점들이 있다.
	 * 1. 구현의 용이함을 위해 시작 인덱스(root)는 1부터 시작한다.
	 * 2. 각 노드와 대응되는 배열의 인덱스는 "불변한다"
	 * 
	 * 위 특징을 기준으로 각 인덱스별로 채워넣으면 특이한 성질이 나타난다.
	 * 
	 * 1. 왼쪽 자식 노드 인덱스 = 부모 인덱스 X 2
	 * 2. 오른쪽 자식 노드 인덱스 = 부모 노드 인덱스 X 2 + 1
	 * 3. 부모 노드 인덱스 = 자식 노드 인덱스 / 2
	 * 
	 * 위 세 개의 법칙은 절대 변하지 않는다.
	 */
public class Heap<E> {
	 
	// 객체를 정렬하고자 할 때, 혹은 임의의 순서로 정렬할 때 Comparator를 파라미터로 받아 설정할 수 있다.
	private final Comparator<? super E> comparator;
	private static final int DEFAULT_CAPACITY = 10;	// 최소(기본) 용적 크기 
    
	private int size;	// 요소 개수 
 
	private Object[] array;	// 요소를 담을 배열 
 
	
	/*
	 * 총 4개의 생성자가 있다. 
	 * 파라미터가 없을때, 정렬 기준을 줄 때,
	 * 초기 공간을 할당했을 때의 경우를 조합하여 총 4가지가 나온다.
	 */
	// 생성자 Type 1 (초기 공간 할당 X)
	public Heap() {
		this(null);
	}
	
	public Heap(Comparator<? super E> comparator) {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.comparator = comparator;
	}
    
	// 생성자 Type 2 (초기 공간 할당 O)
	public Heap(int capacity) {
		this(capacity, null);
	}
	
	public Heap(int capacity, Comparator<? super E> comparator) {
		this.array = new Object[capacity];
		this.size = 0;
		this.comparator = comparator;
	}
    
 
	// 받은 인덱스의 부모 노드 인덱스를 반환
	private int getParent(int index) {
		return index / 2;
	}
	
	// 받은 인덱스의 왼쪽 자식 노드 인덱스를 반환 
	private int getLeftChild(int index) {
		return index * 2;
	}
	
	// 받은 인덱스의 오른쪽 자식 노드 인덱스를 반환
	private int getRightChild(int index) {
		return index * 2 + 1;
	}
	
	/*
	 * 모든 자료구조는 동적으로 만들 수 있어야 한다.
	 * resize 메서드를 newCapacity 만큼 용적을 할당 받는다.
	 */
	private void resize(int newCapacity) {
		
		// 새로 만들 배열
		Object[] newArray = new Object[newCapacity];
			
		// 새 배열에 기존에 있던 배열의 요소들을 모두 복사해준다.
		for(int i = 1; i <= size; i++) {
			newArray[i] = array[i];
		}
			
		/*
		 *  현재 배열은 GC 처리를 위해 null로 처리한 뒤, 
		 *  새 배열을 연결해준다.  
		 */
		this.array = null;
		this.array = newArray;
	}
	
	/*
	 * add 메서드 구현에 있어서 comparator의 구현에 따라 데이터 삽입의 방식이 달라진다.
	 * 
	 * 1. 사용자가 comparator을 사용하여 정렬 방법을 Heap 생성 단계에서 넘겨 받은 경우 (comparator != null)
	 * 2. 클래스 내에 정렬 방식을 Comparable로 구현했거나 기본 정렬 방식을 따르는 경우 (comparator == null)
	 *
	 * 배열의 마지막 부분에 원소를 넣고 부모노드를 찾아가면서 부모 노드가 삽입 노드보다 작을 때까지 요소를
	 * 교환해가면서 올라간다. 위와 같은 과정을 흔히 위로 올라가면서 선별한다고 하여, sift-up(상향 선별) 이라고도 한다.
	 * 
	 * 즉, 값을 추가할 때는 size+ 1위치에 새로운 값을 추가하고 상향 선별 과정을 거쳐 '재배치' 해준다고 생각하면 된다.
	 * 이 때, 재배치 되는 노드를 타겟노드(target) 라고 생각하면된다. 
	 *
	 */
	public void add(E value) {
		
		// 배열 용적이 꽉 차있을 경우 용적을 두 배로 늘려준다. 
		if(size + 1 == array.length) {
			resize(array.length * 2);
		}
			
		siftUp(size + 1, value);	// 가장 마지막에 추가 되는 위치와 넣을 값(타겟)을 넘겨줌
		size++;	// 정상적으로 재배치가 끝나면 사이즈를 증가
	}
		
	// 상향 선별
	/**
	 * @param idx	추가할 노드의 인덱스 
	 * @param target	재배치 할 노드
	 */
	private void siftUp(int idx, E target) {	
		// comparator가 존재할 경우 comparator 을 인자로 넘겨준다.
		if(comparator != null) {
			siftUpComparator(idx, target, comparator);
		}
		else {
			siftUpComparable(idx, target);
		}
	}
	 
	// Comparator을 이용한 sift-up
	@SuppressWarnings("unchecked")
	private void siftUpComparator(int idx, E target, Comparator<? super E> comp) {		
	 
		// root노드보다 클 때까지만 탐색한다.
		while(idx > 1) {
			int parent = getParent(idx);	// 삽입노드의 부모노드 인덱스 구하기
			Object parentVal = array[parent];	// 부모노드의 값
			
			// 타겟 노드 값이 부모노드보다 크면 반복문 종료
			if(comp.compare(target, (E) parentVal) >= 0) {
				break;
			}
				
			/*
			 * 부모노드가 타겟노드보다 크므로
			 * 현재 삽입 될 위치에 부모노드 값으로 교체해주고
			 * 타겟 노드의 위치를 부모노드의 위치로 변경해준다. 
			 */
			array[idx] = parentVal;
			idx = parent;
		}
			
		// 최종적으로 삽입될 위치에 타겟 노드 값을 저장해준다.
		array[idx] = target;
	}
	 
	// 삽입 할 객체의 Comparable을 이용한 sift-up
	@SuppressWarnings("unchecked")
	private void siftUpComparable(int idx, E target) {
			
		// 타겟노드가 비교 될 수 있도록 한 변수를 만든다. 
		Comparable<? super E> comp = (Comparable<? super E>) target;
			
		while(idx > 1) {
			int parent = getParent(idx);
			Object parentVal = array[parent];
				
			if(comp.compareTo((E)parentVal) >= 0) {
				break;
			}
			array[idx] = parentVal;
			idx = parent;
		}
		array[idx] = comp;
	}
	
	/*
	 * remove() 메서드 구현
	 * 원소(데이터) 삭제는 add와 정반대로 하면된다.
	 * 
	 * add의 경우 맨 마지막 노드에 추가하고, 부모노드와 비교하면서 자리를 찾아갔다.
	 * 이를 거꾸로하는 삭제 연산의 경우 root에 있는 노드를 삭제하고, 마지막에 위치해있던 노드를 
	 * root노드로 가져와서, 현재 위치(root)의 자식노드가 크거나, 자식노드가 없을 때 까지
	 * 자신의 위치를 찾아가면 된다.
	 * 
	 * 다른 노드와 비교하면서 타겟 노드가 배치될 자리를 찾아가야한다.
	 * 중요한 점은 왼쪽 자식 노드와 오른쪽 자식 노드 중 "더 작은 값"을 가진 노드와 비교해야한다.
	 * 
	 * 이렇게 아래로 내려가면서 재배치 하는 과정을 sif-down(하향선별)이라고 한다.
	 * 
	 * 삽입 과정과 마찬가지로 Comparator를 사용하느냐, Comparable을 사용하느냐를 나누면서 구현한다.
	 *  
	 */
	
	@SuppressWarnings("unchecked")
	public E remove() {
		if(array[1] == null) {	// 만약 root가 비어있을경우 예외를 던지도록 함
			throw new NoSuchElementException();
		}
	    
		E result = (E) array[1];	// 삭제된 요소를 반환하기 위한 임시 변수 
		E target = (E) array[size];	// 타겟이 될 요소
		array[size] = null;	// 타겟 노드를 비운다.
			
		// 삭제할 노드의 인덱스와 이후 재배치 할 타겟 노드를 넘겨준다.
		siftDown(1, target);	// 루트 노드가 삭제되므로 1을 넘겨준다.
			
		return result;
	}
		
		
	/**
	 * @param idx	삭제할 노드의 인덱스 
	 * @param target	재배치 할 노드
	 */
	private void siftDown(int idx, E target) {
		// comparator가 존재할 경우 comparator 을 인자로 넘겨준다.
		if(comparator != null) {
			siftDownComparator(idx, target, comparator);
		}
		else {
			siftDownComparable(idx, target);
		}
	}
		
	// Comparator을 이용한 sift-down
	@SuppressWarnings("unchecked")
	private void siftDownComparator(int idx, E target, Comparator<? super E> comp) {
			
		array[idx] = null;	// 삭제 할 인덱스의 노드를 삭제
		size--;	
				
		int parent = idx;	// 삭제노드부터 시작 할 부모를 가리키는 변수
		int child;	// 교환 될 자식을 가리키는 변수
			
		// 왼쪽 자식 노드의 인덱스가 요소의 개수보다 작을 때 까지 반복
		while((child = getLeftChild(parent)) <= size) {
				
			int right = getRightChild(parent);	// 오른쪽 자식 인덱스
				
			Object childVal = array[child];	// 왼쪽 자식의 값 (교환 될 값) 
				
			/*
			 *  오른쪽 자식 인덱스가 size를 넘지 않으면서
			 *  왼쪽 자식이 오른쪽 자식보다 큰 경우
			 *  재배치 할 노드는 작은 자식과 비교해야하므로 child와 childVal을
			 *  오른쪽 자식으로 바꿔준다. 
			 */
			if(right <= size && comp.compare((E) childVal, (E) array[right]) > 0) {
				child = right;
				childVal = array[child];
			}
				
			// 재배치 할 노드가 자식 노드보다 작을경우 반복문을 종료한다. 
			if(comp.compare(target ,(E) childVal) <= 0){
				break;
			}
				
			/*
			 *  현재 부모 인덱스에 자식 노드 값을 대체해주고 
			 *  부모 인덱스를 자식 인덱스로 교체
			 */
			array[parent] = childVal;
			parent = child;
		}
			
		// 최종적으로 재배치 되는 위치에 타겟이 된 값을 넣어준다.
		array[parent] = target;
			
		/*
		 *  용적의 사이즈가 최소 용적보다는 크면서 요소의 개수가 전체 용적의 1/4일경우 
		 *  용적을 반으로 줄임(단, 최소용적보단 커야함)
		 */
		if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
	 
	}
		
	// Comparable을 이용한 sift-down
	@SuppressWarnings("unchecked")
	private void siftDownComparable(int idx, E target) {
			
		Comparable<? super E> comp = (Comparable<? super E>) target;
			
		array[idx] = null;
		size--;
			
		int parent = idx;
		int child;
	 
		while((child = getLeftChild(parent)) <= size) {
				
			int right = getRightChild(parent);
				
			Object childVal = array[child];
			
			if(right <= size && ((Comparable<? super E>)childVal).compareTo((E)array[right]) > 0) {
				child = right;
				childVal = array[child];
			}
				
			if(comp.compareTo((E) childVal) <= 0){
				break;
			}
			array[parent] = childVal;
			parent = child;
				
		}
		array[parent] = comp;
			
		if(array.length > DEFAULT_CAPACITY && size < array.length / 4) {
			resize(Math.max(DEFAULT_CAPACITY, array.length / 2));
		}
			
	}
	
	
	/*
	 * 이외 유용하게 사용되는 메서드
	 */
	public int size() {
		return this.size;
	}
		
	@SuppressWarnings("unchecked")
	public E peek() {
		if(array[1] == null) {
			throw new NoSuchElementException();
		}		
		return (E)array[1];
	}
	 
	public boolean isEmpty() {
		return size == 0;
	}
	 
	public Object[] toArray() {
		return Arrays.copyOf(array, size + 1);
	}
	
	
}




