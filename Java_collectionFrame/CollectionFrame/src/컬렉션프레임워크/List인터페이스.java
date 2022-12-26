package 컬렉션프레임워크;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class List인터페이스 {
	/*
	 * List 계열의 컬렉션 프레임워크는 데이터 저장 순서가 유지되며.
	 * 데이터의 중복을 허용한다.
	 * 
	 * 저장되는 데이터에는 자동으로 인덱스가 부여되며 0번부터 시작한다.
	 * => 배열의 구조와 동일함.
	 * 
	 * 제공되는 메서드 대부분이 Set 계열과 동일하다.
	 * => 부모가 같기 때문이다.
	 * 
	 */

	public static void main(String[] args) {

		// 업캐스팅을 활용하여 HashSet 객체 생성.
		List list = new ArrayList();
		
		// boolean isEmpty() : 컬렉션 객체가 비어있는지 여부 확인.
		System.out.println("list 객체가 비어 있는가? " + list.isEmpty());
		
		// int size() : 컬렉션 객체에 저장된 요소 개수 리턴.
		System.out.println("list 객체에 저장된 요소 개수 : " + list.size());
		
		// boolean add(Object O) : 컬렉션 객체에 요소 추가.
		// 파라미터가 Object 타입으로 모든 데이터 타입 추가 가능.
		// 이미 존재하는 데이터를 추가하면, false를 반환하고 저장이 안됨 
		// => 중복 방지.
		System.out.println("=====list 데이터 저장 시작=====");
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(1);
		list.add(1);
		list.add(2);
		list.add("list");
		
		System.out.println("정수 1을 list 객체에 추가 가능한가? :" + list.add(1));
		System.out.println("정수 2를 list 객체에 추가 가능한가? :" + list.add(2));
		
		System.out.println("=====list 데이터 저장 종료=====");
		
		System.out.println("list의 0번 인덱스 출력 : " + list.get(0));

		
		// String toString() : 컬렉션 객페에 저장된 모든 요소를 출력.
		// toString() 생략 가능, 객체만 호출하여도 모든 요소가 출력됨.
		System.out.println("list 객체의 모든 요소 출력 :" + list);
		
		// boolean contains(Object o) : 객체 내의 요소 중 o가 포함되는지 여부 확인.
		// => add와 다른 점은 데이터의 추가가 아닌 "확인" 여부만 알려줌.
		System.out.println("list 객체에 정수 2가 포함되었는가? : " + list.contains(2));
		System.out.println("list 객체에 정수 3이 포함되었는가? : " + list.contains(3));
		
		System.out.println("list 객체의 모든 요소 출력 :" + list);
	
		// Object remove(int i) : 지정된 위치에 있는 객체를 제거한다.
		// => removeAll(Collection o) : 객체 내의 Collection객체의 모든 요소 삭제.
		System.out.println("list 객체 내의 실수 3.14 제거 결과 : " + list.remove(0)); // true
		
		System.out.println("list 객체의 모든 요소 출력 : " + list);
		
		List subList = list.subList(1, 3);
		
		// List subList(int fromIndex, int toIndex) : 지정된 범위에 있는 요소를 반환.
		System.out.println("list 객체의 부분 리스트 요소 출력 : " + subList);
		
		Object[] arr = list.toArray(); // 배열로 추출 가능
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		// List aslist(object o) : 객체를 List로 변환 
		List list2 = Arrays.asList(arr);
		System.out.println("배열을 List로 변환 : " + list2);
		
		List list3 = Arrays.asList(1,2,5,"가");
		System.out.println("aslist()에 파라미터를 주어 List 생성 : " + list3);
		
		
	}

}
