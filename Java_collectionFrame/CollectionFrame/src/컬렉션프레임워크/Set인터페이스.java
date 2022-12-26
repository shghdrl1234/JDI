package 컬렉션프레임워크;

import java.util.HashSet;
import java.util.Set;

public class Set인터페이스 {
	/*
	 * Set 계열의 컬렉션 프레임워크는 데이터 저장 순서가 유지되지 않고,
	 * 데이터의 중복을 허용하지 않는다.
	 * 
	 * 중복 제거 수단으로서 효율적으로 사용된다.
	 * 
	 * 인덱스를 사용하지 못한다.
	 * (반복문에는 iterator로 사용 => 각 시행마다 순서가 다르게 나옴)
	 * 
	 */

	public static void main(String[] args) {

		// 업캐스팅을 활용하여 HashSet 객체 생성.
		Set set = new HashSet();
		
		// boolean isEmpty() : 컬렉션 객체가 비어있는지 여부 확인.
		System.out.println("set 객체가 비어 있는가? " + set.isEmpty());
		
		// int size() : 컬렉션 객체에 저장된 요소 개수 리턴.
		System.out.println("set 객체에 저장된 요소 개수 : " + set.size());
		
		// boolean add(Object O) : 컬렉션 객체에 요소 추가.
		// 파라미터가 Object 타입으로 모든 데이터 타입 추가 가능.
		// 이미 존재하는 데이터를 추가하면, false를 반환하고 저장이 안됨 
		// => 중복 방지.
		System.out.println("=====set 데이터 저장 시작=====");
		set.add(1);
		set.add("set");
		
		System.out.println("정수 1을 set 객체에 추가 가능한가? :" + set.add(1));
		System.out.println("정수 2를 set 객체에 추가 가능한가? :" + set.add(2));
		
		System.out.println("=====set 데이터 저장 종료=====");

		
		// String toString() : 컬렉션 객페에 저장된 모든 요소를 출력.
		// toString() 생략 가능, 객체만 호출하여도 모든 요소가 출력됨.
		System.out.println("set 객체의 모든 요소 출력 :" + set);
		
		// boolean contains(Object o) : 객체 내의 요소 중 o가 포함되는지 여부 확인.
		// => add와 다른 점은 데이터의 추가가 아닌 "확인" 여부만 알려줌.
		System.out.println("set 객체에 정수 2가 포함되었는가? : " + set.contains(2));
		System.out.println("set 객체에 정수 3이 포함되었는가? : " + set.contains(3));
		
		System.out.println("set 객체의 모든 요소 출력 :" + set);
	
		// boolean remove(Object o) : 객체 내의 요소 o 제거 후 결과 리턴
		// => 제거하고자 하는 파라미터가 객체에 존재하면 제거, 그렇지 않으면 false 반환
		// => removeAll(Collection o) : 객체 내의 Collection객체의 모든 요소 삭제
		System.out.println("set 객체 내의 실수 2 제거 결과 : " + set.remove(2)); // true
		System.out.println("set 객체 내의 실수 3 제거 결과 : " + set.remove(3)); // false
		
		System.out.println("set 객체의 모든 요소 출력 : " + set);
		
		Set set2 = new HashSet();
		
		// boolean addAll(Collection c) : 파라미터의 요소들을 현재 객체에 추가
		// => 서로 같은 데이터를 가지고 있으면 중복되지 않는 요소만 추가.
		set2.add(0);
		set2.add("set2");
		set2.addAll(set);
		
		System.out.println("set2 객체의 모든 요소 출력 : " + set2);
	
		// set2의 모든 요소 삭제 = void clear()
		set2.removeAll(set2);
		System.out.println("set2의 모든 요소 삭제" + set2);
				
		set2.add(1);
		set2.add(1);
		set2.add(3);
		set2.add(4);
		
		/*
		 * set 계열은 인덱스가 없기 때문에 기본 반복문을 사용할 수 없다.
		 * 향상된 for문 또는 iterator객체를 활용해야함.
		 * 
		 * => itreator은 추후 설명할 것임.
		 */
		
		for(Object o : set2) {
			System.out.println(o);
		}
		
		Object[] setArr = set2.toArray();
		System.out.println("set2 요소를 배열화 : " + set2.toArray());
		
		for(Object i : setArr) {
			System.out.println(i);
		}
		
		
	}

}
