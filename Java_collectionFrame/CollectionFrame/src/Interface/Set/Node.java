package Interface.Set;
/*
 * Node를 기반으로 하는 배열을 이용한 HashSet을 구현하기에 앞서
 * 가장 기본적인 데이터를 담을 Node 클래스를 먼저 구현해야한다.
 * 
 */
public class Node<E> {
	
	/*
	 * hash와 key값은 변하지 않으므로
	 * final로 선언해준다.
	 */
	
	final int hash;	// 해싱된 ket의 고유값
	final E key; // HashSet 에서는 data(value) 자체가 ket값이 된다.
	Node<E> next; // 다음 노드를 가리키는 변수
	
	public Node(int hash, E key, Node<E> next) {
		this.hash = hash;
		this.key = key;
		this.next = next;
	}
	
}
