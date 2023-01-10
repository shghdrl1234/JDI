package Interface.Queue;

import java.util.Comparator;

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
	
	
}
