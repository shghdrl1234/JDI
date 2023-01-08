package Interface.LinkedList;

public class Node<E> {
	/*
	 * LinkedList를 구현하기에 앞서 가장 기본적인 데이터를 담을 Node 클래스가 필요하다.
	 * 
	 */
	
	E data;
	Node<E> next; // 다음 노드 객체를 가리키는 래퍼런스 변수.
	 				// 노드 자체를 가리키기 때문에 타입 또한 Node<E>타입으로 지정해준다.
	Node<E> prev;
	
	Node(E data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}

}
