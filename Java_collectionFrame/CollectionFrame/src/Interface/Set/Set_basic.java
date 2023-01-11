package Interface.Set;

/*
 * Set은 쉽게 말하면 집합이라고 보면된다.
 * 
 * Set 혹은 Set계열을 구현하는 클래스들은 
 * 1. 중복되는 원소를 허용하지 않는다.
 * 2. 저장 순서를 유지하지 않는다. (예외 : LinkedHashSet)
 *
 * Set은 크게 HashSet, LinkedHashSet, TreeSet으로 나뉜다.
 * 
 * HashSet은 가장 기본적은 Set 컬렉션의 클래스이며, 입력 순서를 보장하지 않고,
 * 순서도 마찬가지로 보장되지 않는다. 이는 데이터가 정렬되어 있을 필요도 없고,
 * 중복되는 값인지만 빠르게 찾아야 할 필요가 있을 때 사용한다.
 * => 닉네임의 중복확인 등에 사용.
 * 
 * LinkedHashSet의 경우 Link + Hash + Set이 결합된 형태이다.
 * 첫번째 요소부터 차례대로 출력하면 입력했던 순서대로 출력된다.(= 저장 순서 유지)
 * Set 계열의 컬렉션들은 중복을 허용하지 않기때문에, 순서를 보장받고 싶을때 LinkedHashSet을
 * 사용하면 된다.
 * => LRU 알고리즘에 대부분 쓰인다. Least Recently Used Algorithm
 * 
 * TreeSet은 HashSet과 마찬가지로 저장 순서를 유지하지 않으며, 중복 데이터를 넣지 못한다.
 * 특별한 점은 데이터의 "가중치에 따른 순서"대로 정렬되어 보장한다는 것이다.
 * 즉, 중복되지 않으면서 특정 규칙에 의해 정렬된 형태의 집합을 사용하고 싶을 때 사용한다.
 * 정렬된 형태로 있다보니 특정 구간의 집합 요소들을 탐색할 때 매우 유용하다.
 * 
 */

public interface Set_basic<E> {

	/**
	 * 지정된 요소가 Set에 없는 경우 요소를 추가합니다. 
	 * 
	 * @param e 집합에 추가할 요소
	 * @return {@code true} 만약 Set에 지정 요소가 포함되지 않아 정상적으로 추가되었을 경우,
	 *         else, {@code false}
	 */
	boolean add(E e);
	
	/**
	 * 지정된 요소가 Set에 있는 경우 해당 요소를 삭제합니다.
	 * 
	 * @param o 집합에서 삭제할 특정 요소
	 * @return {@code true} 만약 Set에 지정 요소가 포함되어 정상적으로 삭제되었을 경우,
	 *         else, {@code false}
	 */
	boolean remove(Object o);
	
	/**
	 * 현재 집합에 특정 요소가 포함되어있는지 여부를 반환합니다.
	 * 
	 * @param o 집합에서 찾을 특정 요소
	 * @return {@code true} Set에 지정 요소가 포함되어 있을 경우,
	 *         else, {@code false}
	 */
	boolean contains(Object o);
	
	/**
	 * 지정된 객체가 현재 집합과 같은지 여부를 반환합니다.
	 * 
	 * @param o 집합과 비교할 객체
	 * @return {@code true} 비교할 집합과 동일한 경우,
	 *         else, {@code false}
	 */
	boolean equals(Object o);
	
	/**
	 * 현재 집합이 빈 상태(요소가 없는 상태)인지 여부를 반환합니다.
	 * 
	 * @return {@code true} 집합이 비어있는 경우,
	 *         else, {@code false}
	 */
	boolean isEmpty();
	
	/**
	 * 현재 집합의 요소의 개수를 반환합니다. 만약 들어있는 요소가
	 * {@code Integer.MAX_VALUE}를 넘을 경우 {@code Integer.MAX_VALUE}를 반환합니다.
	 * 
	 * @return 집합에 들어있는 요소의 개수를 반환
	 */
	int size();
	
	/**
	 * 집합의 모든 요소를 제거합니다.
	 * 이 작업을 수행하면 비어있는 집합이 됩니다.
	 */
	void clear();
}
