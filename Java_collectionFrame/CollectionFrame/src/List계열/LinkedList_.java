package List계열;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedList_ {
	
	/*
	 * ArrayList는 배열의 구조이며,
	 * 배열은 구조가 간단하고 데이터를 읽는데 걸리는 시간이 짧다.
	 * 즉, 접근 시간이 짧다.
	 * 
	 * 하지만, 앞서 정리한 ArrayList_.java 파일을 참고하면, 
	 * 비순차적인 데이터를 삭제하거나 추가하게 되면 새로운 배열을 만들고,
	 * 이후의 데이터들을 복사하는 과정을 통하여 작업시간이 오래 걸리게 된다.
	 * 
	 * 이러한 단점을 보완하기 위하여 LinkedList가 고안되었다.
	 * 
	 * LinkedList는 불연속적으로 존재하는 데이터를 연결한 것이다.
	 * 
	 * LinkedList의 각 요소는 node클래스로 구성된다. 
	 * node에는 다음 요소의 주소값을 저장한다.
	 * 
	 * class Node {
	 * 	Node next; => 다음 노드의 주소값
	 *  Obejct obj; => 데이터를 저장하기 위한 참조변수
	 * }
	 * 
	 * LinkedList는 node 간의 결합이므로, 삭제와 추가에 있어
	 * 해당 요소들의 결합만 바꿔주면 된다.
	 * => 한 번의 Node 객체 생성과 두 번의 참조변경 만으로 가능하다.
	 * 
	 * 하지만, 단방향이기 때문에 데이터 접근성이 나쁘다.
	 * 이러한 단점을 보완한 것이 doubly linked list와 doubly circular linked list
	 * Node 클래스에 이전의 주소값을 참조할 수 있는 변수를 추가했다.
	 * 
	 * 1. 순차적으로 데이터 추가/ 삭제 => ArrayList가 빠름
	 * 2. 비순차적으로 데이터를 추가 / 삭제 => LinkedList가 빠름
	 * 3. 접근시간 => ArrayList가 빠름
	 * 
	 * 
	 * 
	 */

	public static void main(String[] args) {
		ArrayList al = new ArrayList(200000);
		LinkedList ll = new LinkedList();
		
		System.out.println("==== 순차적으로 추가 ====");
		System.out.println("ArrayList : " + add1(al));
		System.out.println("LinkedList : " + add1(ll));
		System.out.println("");
		System.out.println("==== 중간에서 추가 ====");
		System.out.println("ArrayList : " + add2(al));
		System.out.println("LinkedList : " + add2(ll));
		System.out.println("");
		System.out.println("==== 중간에서 삭제 ====");
		System.out.println("ArrayList : " + remove2(al));
		System.out.println("LinkedList : " + remove2(ll));
		System.out.println("");
		System.out.println("==== 순차적으로 삭제 ====");
		System.out.println("ArrayList : " + remove1(al));
		System.out.println("LinkedList : " + remove1(ll));
		System.out.println("");
		
		add(al);
		add(ll);
		
		
		System.out.println("==== 접근시간 테스트 ====");
		System.out.println("ArrayList : " + access(al));
		System.out.println("LinkedList : " + access(ll));
		
	}
	
	public static long add1(List list) { // 순차적
		long start = System.currentTimeMillis();
		for(int i = 0; i <1000000; i++) {
			list.add(i+"");
		}
		long end = System.currentTimeMillis();
		return end-start;
		
	}
	
	public static long add2(List list) { // 비순차적
		long start = System.currentTimeMillis();
		for(int i = 0; i <1000000; i++) {
			list.add(500+"x");
		}
		long end = System.currentTimeMillis();
		return end-start;
		
	}
	
	public static long remove1(List list) { // 순차적
		long start = System.currentTimeMillis();
		for(int i = list.size()-1; i >=0; i--) {
			list.remove(i);
		}
		long end = System.currentTimeMillis();
		return end-start;
		
	}
	
	public static long remove2(List list) { // 비순차적
		long start = System.currentTimeMillis();
		for(int i = 0; i <10000; i++) {
			list.remove(i);
		}
		long end = System.currentTimeMillis();
		return end-start;
		
	}
	
	public static void add(List list) {
		for(int i = 0; i < 100000; i++) list.add(i+""); 
	}
	
	public static long access(List list) {
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < 10000; i++) list.get(i);
		
		long end = System.currentTimeMillis();
		return end-start;
	}

}
