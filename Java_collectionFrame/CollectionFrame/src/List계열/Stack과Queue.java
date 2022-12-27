package List계열;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Stack과Queue {

	/*
	 * Stack은 하나의 물건을 아래쪽에서부터 차례대로 쌓는 자료구조이다.
	 * 데이터가 한 쪽에서만 삽입, 삭제가 이루어진다.
	 * FILO(First In Last Out), LIFO(Last In First Out) 구조라고도 함.
	 * 
	 * Queue는 파이프와 같은 구조로 이루어져 있다.
	 * 데이터를 한 방향으로 넣고, 반대 방향으로 뺄 수 있는 구조이다.
	 * FIFO(First In First Out0) 구조라고 함. 
	 *
	 * 배열의 관점으로보면
	 * Stack은 데이터를 삭제할 때 마지막에 들어간 데이터부터 순차적으로 삭제되고,
	 * Queue는 데이터를삭제할 때 처음 들어간 데이터부터 순차적으로 삭제된다.
	 * 
	 * 즉, Stack은 뒤에서 부터 삭제가 되니 배열기반 컬렉션이 적절할 것이고
	 * Queue는 앞에 있는 데이터부터 삭제되니 LinkedList가 적절할 것이다.
	 * 
	 * 자바에서는 Stack을 클래스로 구현해놓았지만, Queue는 인터페이스로 정의만 해놓았다.
	 * 대신, 다양한 컬렉션 클래스들을 이용하여 구현하면 됨.
	 * 
	 * 대표적으로 사용되는 곳
	 * Stack : 수식계산, 괄호검사, 워드프로세서의 undo/redo, 웹브라우저 앞,뒤로 이동 등
	 * Queue : 최근사용문서, 인쇄작업 대기목록, 버퍼 등
	 *  
	 */
	
	public static void main(String[] args) {

			Stack s = new Stack();
			
			// boolean empty() : Stack이 비어있는지 알려준다.
			System.out.println("s가 비어있는가? : " + s.isEmpty());
			
			// Object push(Object item) : Stack에 객체(item)을 저장한다.
			s.push("안녕하세요");
			s.push("제 이름은");
			s.push("스택입니다.");
			
			// Object pop() : Stack의 맨 위에 저장된 객체를 꺼낸다.
			System.out.println("s에서 값 꺼내기 : " + s.pop());
			System.out.println("s에서 값 꺼내기 : " + s.pop());
			System.out.println("s에서 값 꺼내기 : " + s.pop());
			
			s.push("안녕하세요");
			s.push("제 이름은");
			s.push("스택입니다.");
			// Object peek() : Stack의 맨 위에 저장된 객체를 반환한다. pop()과 달리 Stack에서 객체를 꺼내지 않음.
			System.out.println("s에서 값 보기 : " + s.peek());
			System.out.println("s에서 값 보기 : " + s.peek());
			System.out.println("s에서 값 보기 : " + s.peek());
			
			// int search(Object o) : Stack에서 주어진 객체(o)를 찾아서 그 위치를 반환, 못찾으면 -1을 반환한다.
			// 배열과 달리 반환하는 값은 1부터 시작한다.
			// 가장 위에 있는 것(=pop시 바로 반환되는 것)이 1이다.
			System.out.println("s에서 값의 위치 찾기: " + s.search("안녕하세요"));
			System.out.println("s에서 값의 위치 찾기: " + s.search("안녕하세요"));
			System.out.println("s에서 값의 위치 찾기: " + s.search("스택입니다."));
			System.out.println("s에서 값의 위치 찾기: " + s.search("자바입니다."));
			
			
			
			Queue q = new LinkedList();
			
			// boolean add(Object o) : 지정된 객체를 Queue에 추가한다. 성공하면 true를 반환.
			// 저장 공간이 부족하면 예외 발생
			q.add("안녕!");
			q.add("내 이름은");
			q.add("큐 야!");
			
			// Object remove() : Queue에서 객체를 꺼내 반환. 비어있으면 예외발생
			System.out.println("q에서 값 꺼내기 : " + q.remove());
			System.out.println("q에서 값 꺼내기 : " + q.remove());
			System.out.println("q에서 값 꺼내기 : " + q.remove());
			
			q.add("안녕!");
			q.add("내 이름은");
			q.add("큐 야!");
			// Object element() : 삭제없이 요소를 읽어온다.
			System.out.println("q에서 값의 위치 찾기: " + q.element());
			System.out.println("q에서 값의 위치 찾기: " + q.element());
			System.out.println("q에서 값의 위치 찾기: " + q.element());
			
			// boolean offer(Object o) : Queue에 객체를 저장, 성공하면 true, 실패하면 false.
			System.out.println("q에 객체 저장 : " + q.offer("offer로 저장"));
			
			// Object poll() : Queue에서 객체를 꺼내서 반환, 비어있으면 null 반환.
			System.out.println("q에서 값 꺼내기 : " + q.poll());
			System.out.println("q에서 값 꺼내기 : " + q.poll());
			System.out.println("q에서 값 꺼내기 : " + q.poll());
			System.out.println("q에서 값 꺼내기 : " + q.poll());
			System.out.println("q에서 값 꺼내기 : " + q.poll());
			
			// Object peek() : 삭제없이 요소를 읽어온다. 요소가 없으면 null 반환.
			System.out.println("q에서 값 보기 : " + q.peek());
			
	}

}
