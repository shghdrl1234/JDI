package Set계열;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HashSet_ {
	/*
	 * HashSet은 Set인터페이스를 구현한 가장 대표적인 컬렉션이다.
	 * Set 인터페이스의 특징대로 HashSet은 중복된 요소를 저장하지 않는다.
	 * 
	 * HashSet에 이미 저장된 요소를 추가하려고 하면, false를 반환함으로써
	 * 중복된 요소이기 때문에 추가에 실패했다는 것을 알린다.
	 * 
	 * 이러한 HashSet의 특징을 이용하면, 컬렉션 내의 중복된 요소를 없앨 수 있다.
	 * 또한 저장순서가 유지되지 않는다.
	 * 
	 * 저장순서를 유지하기 하고자 한다면 LinkedHashSet을 사용해야한다.
	 *
	 */
	public static void main(String[] args) {
		
		// HashSet() : HashSet 객체를 생성한다.
		HashSet h = new HashSet();
		
		List l = new ArrayList();
		
		l.add("가");
		l.add("나");
		l.add("다");
		l.add("다");
		l.add("라");
		
		System.out.println("l 컬렉션 출력 : " + l);
		
		// HashSet(Collection c) : 주어진 컬렉션을 포함하는 HashSet 객체를 생성한다.
		HashSet h2 = new HashSet(l);
		System.out.println("h2 컬렉션 출력 : " + h2);
		// => 컬렉션 객체들중, 중복된 요소들은 1개의 값을 남기고 제외됨.
		
		// HashSet(int initialCapacity) : 주어진 값을 초기용량으로하는 HashSet 객체를 생성한다.
		HashSet h3 = new HashSet(3);
		h3.add("가");
		h3.add("나");
		h3.add("다");
		h3.add("라");
		System.out.println("h3 컬렉션 출력 : " + h3);
		// 용량 초과시 자동으로 늘어남.
		
		// HashSet(int initialCapacity, float loadFactor) : 초기용량과 load factor를 지정하는 생성자.
		HashSet h4 = new HashSet(3, 3);
		
		
		// boolean add(Object o) : 새로운 객체를 저장한다. (성공하면 true, 실패하면 false)
		h.add(1);
		h.add(1);
		h.add(2);
		h.add(2);
		h.add(3);
		System.out.println("add, h 컬렉션 출력 : " + h);
		
		// boolean addAll(Collection c) : 주어진 컬렉션에 저장된 모든 객체들을 추가한다. (합집합)
		h.addAll(h2);
		System.out.println("addAll, h 컬렉션 출력 : " + h);
		
		// void clear() : 저장된 모든 객체를 삭제한다.
		h.clear();
		System.out.println("clear, h 컬렉션 출력 : " + h);
		
		// Object clone() : HashSet을 복제해서 반환한다. (얕은 복사)
		h = (HashSet) h2.clone();
		System.out.println("clone, h 컬렉션 출력 : " + h);
		
		// boolean contains(Object o) : 지정된 객체를 포함하고 있는 지 알려준다.
		System.out.println("h 컬렉션이 정수 3을 포함하고 있는가? : " + h.contains(3));
		System.out.println("h 컬렉션이 문자 '가'를 포함하고 있는가? : " + h.contains("가"));
		
		// boolean cotainsAll(Collection c) : 주어진 컬렉션에 저장된 모든 객체들을 포함하고 있는지 알려준다.
		System.out.println("h 컬렉션이 h3 컬렉션의 전부를 포함하고 있는가? : " + h.containsAll(h3));
		h.remove("가");
		System.out.println("h 컬렉션이 h3 컬렉션의 전부를 포함하고 있는가? : " + h.containsAll(h3));
		
		// bollean isEmpty() : HashSet이 비어있는지 알려준다.
		System.out.println("h 컬렉션이 비어있는가? : " + h.isEmpty());
		
		// Iterator iterator() : Iterator을 반환한다.
		Iterator it = h.iterator();
		
		while(it.hasNext()) {
			System.out.println("h 컬렉션 활용한 iterator 반환 : " + it.next() );
		}
		
		// boolean remove(Object o) : 지정된 객체를 HashSet에서 삭제한다. (성공하면 true, 실패 false)
		System.out.println("h 컬렉션에서 정수 1 삭제 : " + h.remove(1));
		System.out.println("h 컬렉션에서 문자 '다' 삭제 : " + h.remove("다"));
		
		// boolean removeAll(Collection c) : 주어진 컬렉션에 저장된 모든 객체와 동일한 것들을 HashSet에서 모두 삭제한다.(차집합)
		System.out.println("h2 컬렉션 출력 : " + h2);
		System.out.println("h 컬렉션 출력 : " + h);
		System.out.println("h2 컬렉션에서 h 컬렉션과 동일한 것 삭제 : " + h2.removeAll(h));
		System.out.println("h2 컬렉션 출력 : " + h2);
		h2.add("나");
		h2.add("라");
		
		// boolean retainAll(Collection c) : 주어진 컬렉션에 저장된 객체와 동일한 것만 남기고 삭제한다. (교집합)
		System.out.println("h2 컬렉션에서 h 컬렉션과 동일한 것 제외하고 삭제 : " + h2.retainAll(h) );
		System.out.println("h2 컬렉션 출력 : " + h2);
		
		// int size() : 지정된 객체의 개수를 반환한다.
		System.out.println("h의 사이즈 : " + h.size());
		
		// Object[] toArray() : 저장된 객체들을 객체배열의 형태로 반환한다.
		System.out.println("h2 배열화 및 0번째 인덱스 값 : " + h2.toArray()[0] );
		
		// Object[] toArray(Object[] a) : 저장된 객체들을 주어진 객체배열(a)에 담는다.
		
		
		HashSet set = new HashSet();
		set.add("abc");
		set.add("abc");
		set.add(new Person("David", 10));
		set.add(new Person("David", 10));
		
		System.out.println(set);
		}

}

class Person {
	String name;
	int age;
	
	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String toString() {
		
		return name + ":" + age;
	}
	/* Person 클래스를 여기까지만 작성하면, 
	* main 메서드에서 new Person으로 동일한 파라미터를 보내도
	* 중복처리가 되지 않는다.
	*/
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Person)) return false;
		
		Person p = (Person)obj;
		return name.equals(p.name) && age == p.age;
		
	}
	
	public int hashCode() {
		return Objects.hash(name, age);
	}
	/* 여기 까지 클래스에 맞도록 적절히 equals와 hashCode를 작성해주면
	 * 중복처리가 된다.
	 */
	 
}