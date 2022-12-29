package 컬렉션저장요소접근;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Iterator_ {
	
	/*
	 * Iterator은 컬렉션에 저장된 요소에 접근하는데 사용되는 클래스이다.
	 * 
	 * ListIterator은 양방향 조회기능이 추가된 인터페이스이다.
	 * 단, List를 구현한 경우에만 사용가능하다.
	 * 
	 * 컬렉션 프레임워크에서는 컬렉션에 저장된 요소들을 읽어오는 방법을 표준화 하였다.
	 * 컬렉션에 저장된 각 요소에 접근하는 기능을 가진 Iterator 인터페이스를 정의하고,
	 * Collection 인터페이스에는 "Iterator를 구현한 클래스의 인스턴스를 반환하는 iterator()"를 정의하고 있다.
	 * 
	 * iterator()는 Collection 인터페이스에 정의된 메서드이므로, List와 Set에도 포함되어있다.
	 * 그래서 iterator()가 각 클래스에 알맞게 작성되어 있다.
	 * 
	 * 컬렉션 클래스에 대해, iterator()를 호출하여 Iterator를 얻고,
	 * 반복문을 통하여 컬렉션 클래스의 요소들을 읽어올 수 있다.
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		
		// boolean hasNext() : 읽어 올 요소가 남아있는지 확인한다. 있으면 true, 없으면 false를 반환
		// Object next() : 다음 요소를 읽어온다. next()를 호출하기 전에 hasNext()를 호출해서 읽어올 요소가
		// 있는지 확인하는 것이 안전하다.
		
		// void remove() : next()로 읽어 온 요소를 삭제한다. next()를 호출한 다음에 remove()를 호출해야한다.
		
		// void forEachRemaining(Consumner<? super E>action) : 컬렉션에 남아있는 요소들에 대해 지정된 작업(action)을 수행한다.
		// 람다식을 사용하는 디폴트 메서드.
		
		
		ArrayList list = new ArrayList();
		Set s = new HashSet();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		s.add(1);
		s.add(11);
		s.add(111);
		s.add(1111);
		s.add(11111);
		
		Iterator it = list.iterator();
		Iterator it2 = s.iterator();
		
		while(it.hasNext()) {
			Object obj = it.next();
			System.out.println(obj);
		}

		while(it2.hasNext()) {
			Object obj = it2.next();
			System.out.println(obj);
		}
		
		/*
		 * Map 인터페이스를 구현한 클래스는 키와 값을 쌍으로 저장하고 있기 때문에
		 * iterator를 직접 호출할 수 없고, 그 대신 KeySet()이나, entrySet()과 같은
		 * 메서드를 통해서 키와 값을 각각 따로 Set의 형태로 얻어 온 후에
		 * 다시 iterator()를 호출해야 Iterator를 얻을 수 있다.
		 */
		
		Map map = new HashMap();
		map.put(1, "one");		
		map.put(2, "two");		
		map.put(3, "three");		
		map.put(4, "four");		
		
		Iterator it3 = map.entrySet().iterator(); 
		
		while(it3.hasNext()) {
			Object obj = it3.next();
			System.out.println(obj);
		}
		
	}
	
	
	

}
