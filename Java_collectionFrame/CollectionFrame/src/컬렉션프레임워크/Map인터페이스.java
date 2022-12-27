package 컬렉션프레임워크;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Map인터페이스 {
	
	/*
	 * Map 계열의 컬렉션 프레임워크는 데이터를 쌍(Key,Value)로 관리하는 자료구조이다.
	 * 
	 * Key는 중복이 불가능하며, Value는 중복이 가능하다.
	 * 복수 개의 서랍에 번호 또는 이름을 부여하고
	 * 서랍에 넣은 물건을 서랍 번호 또는 이름으로 찾을 수 있다.
	 * 서로 다른 서랍에는 같은 물건을 넣을 수 있다.
	 * 
	 *  Key와 Value의 데이터 타입은 제한이 없다.
	 *  
	 *  Set, List 계열과는 다른 상속관계를 가지므로 메서드가 대부분 다르다.
	 * 
	 */
	public static void main(String[] args) {
		Map map = new HashMap();
		
		// put(Object key, Object value) : 키에 해당하는 값을 추가
		map.put(1, "자바");
		map.put(2, "JSP");
		map.put(3, "Oracle");
		map.put("람다", "람쥐");
		map.put("람다2", "람쥐");
		
		// toString() 메서드가 오버라이딩 되어 있으므로 모든 키와 값을 문자열로 리턴 가능
		System.out.println("Map 객체의 모든 키, 값 출력 : " + map); // toString() 생략됨
		
		// 중복되는 키(key)를 지정할 경우 해당 키의 값(value)을 덮어씀
		map.put(3, "Android");
		System.out.println("Map 객체의 모든 키, 값 출력 : " + map);
		
		
		// get(Object key) : 키에 해당하는 값을 리턴(키가 존재하지 않을 경우 null 값 리턴)
		System.out.println("정수 2에 해당하는 키의 데이터 : " + map.get(2));
		System.out.println("정수 4에 해당하는 키의 데이터 : " + map.get(4));
		
		// 키 또는 값 전체를 별도로 리턴 가능함
		// Set keySet() : Map 객체의 모든 키 리턴(=> Set 타입 객체로 리턴됨)
		Set keySet = map.keySet();
		System.out.println("모든 키 : " + keySet);
		
		// values() : Map 객체의 모든 값 리턴(=> Collection 타입 객체로 리턴됨)
		// Collection 인터페이스의 서브인터페이스인 Set 또는 List 타입으로 바로 저장 불가
		
		// Key 값은 중복을 허용하지 않기때문에 Set계열로 반환하고,
		// Value 값은 중복을 허용하기 때문에 Collection 계열로 반환한다.
		
		//		Set values = (Set) map.values(); // 문법 오류 발생하지 않지만, ClassCastException 예외 발생
//		List values = (List)map.values(); // 문법 오류 발생하지 않지만, ClassCastException 예외 발생
		
		// Set 또는 List 계열 인스턴스 생성 시 생성자에 values() 리턴값 전달하면 사용 가능!
		Set values = new HashSet(map.values());
		System.out.println("모든 값 : " + values);
		
		// entrySet() : 키와 값을 한 쌍으로 갖는 Map.Entry 타입 객체들의 집합을 Set 타입으로 리턴
		Set entrySet = map.entrySet();
		System.out.println("모든 엔트리 : " + entrySet);
		
		System.out.println("--------------------------");
		
		// 키 또는 값은 Object 타입이므로 모든 타입 사용 가능
		Map map2 = new HashMap();
		map2.put("딸기", 1000);
		map2.put("바나나", 5000);
		map2.put("수박", 8000);
		
		System.out.println("수박의 가격은? " + map2.get("수박") + "원");
		
		System.out.println("과일 중에 바나나가 있습니까? " + map2.containsKey("바나나"));
		System.out.println("과일 중에 사과가 있습니까? " + map2.containsKey("사과"));
		
		System.out.println("과일 중에 500원짜리 과일 있습니까? " + map2.containsValue(500));
		System.out.println("과일 중에 5000원짜리 과일 있습니까? " + map2.containsValue(5000));
		
		System.out.println("--------------------------------");
		
		// put() 메서드 리턴값이 있을 경우 중복된 키가 존재한다는 의미
		System.out.println(map2.put("딸기", 10000)); // 기존 "딸기"에 해당하는 키 1000 리턴됨
		System.out.println(map2.put("복숭아", 10000)); // 중복되는 키가 없으므로 null 리턴됨
		
		Set et = map2.entrySet();
		System.out.println("모든 엔트리" + et);
		
	}


}
