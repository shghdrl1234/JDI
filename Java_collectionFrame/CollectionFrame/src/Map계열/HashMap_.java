package Map계열;

import java.util.HashMap;
import java.util.Map;

public class HashMap_ {
	/*
	 * HashMap은 HashTable의 신버젼임.
	 * 키는 중복 불가, 값은 중복 허용.
	 * 
	 * 키는 저장된 값을 찾는데 사용되는 것이기 때문에 컬렉션 내에서 유일해야한다.
	 * 즉, HashMap에 저장된 데이터를 하나의 키로 검색했을 때, 결과가 단 하나이어야 함을 뜻한다.
	 * 
	 * Map인터페이스를 구현한 대표적인 컬렉션 클래스.
	 * HashMap은 동기화 되지 않음.
	 * 순서를 유지하려면 LinkedHashMap 클래스를 사용하면 된다.
	 *
	 * 해싱기법으로 데이터를 저장, 데이터가 많아도 검색이 빠르다.
	 * 
	 * HashMap은 Entry라는 내부 클래스를 정의하고 있으며, 다신 Entry 타입의 배열을 선언하고 있다.
	 * 키와 값이 별개의 값이 아니라 서로 관련된 값이기 대문에 각가의 배열로 선언하기 보다는
	 * 하나의 클래스로 정의해서 하나의 배열로 다루는 것이 데이터의 무결성인 측면에서 더 바람직하기 때문이다.
	 * 
	 * 
	 * Entry[] table; => HashMap의 내부 클래스
	 * 
	 * class Entry {
	 * 	Object key;
	 *  Object value;
	 *  } => Entry 클래스의 구조
	 * 
	 */
	public static void main(String[] args) {

		// HashMap() : HashMap 객체를 생성
		Map map = new HashMap();
		Map map2 = new HashMap();
		
		// Object getOrDefault(Object Key, Object defaultValue) : 지정된 키와 값을 반환한다.
		// 키를 못찾으면 기본 값으로 지정된 객체를 반환한다.
		System.out.println("map의 기본값 반환 : " + map.getOrDefault("없음", "기본값"));
		
		// Object put(Object key, object value) : 지정된 키와 값을 HashMap에 저장.
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		
		// void putAll(Map m) : map에 저장된 모든 요소를 hashMap에 저장.
		map2.putAll(map);
		System.out.println("map2의 모든 키-값 반환 : " + map2.entrySet());

		// Object remove(Object key) : HashMap에서 지정된 키로 저장된 값(객체)를 제거.
		map2.remove(1);
		System.out.println("map2의 모든 키-값 반환 : " + map2.entrySet());
		
		// Object replace(Object key, Object value) : 지정된 키의 값을 지정된 객체로 대체.
		// 이때, 지정된 키가 존재 해야 한다.
		map2.replace(1, "one-revive");
		map2.replace(2, "two-diff");
		System.out.println("map2의 모든 키-값 반환 : " + map2.entrySet());
		
		// Object replace(Object Key, Object value, Object newValue) : 지정된 키와 객체가 모두 일치하는 경우
		// 새로운 객체로 대체
		map2.replace(2, "two","two-replaceAll");
		map2.replace(3, "three","three-replace");
		System.out.println("map2의 모든 키-값 반환 : " + map2.entrySet());
		
		// int size() : HashMap에 저장된 요소의 개수를 반환
		System.out.println("map2에 저장된 요소의 개수 : " + map2.size());
		
		// HashMap에 저장된 모든 값을 컬렉션의 형태로 반환
		
		System.out.println("map2의 모든 키-값 반환 : " + map2.entrySet());
		System.out.println("map2의 모든 값 반환 : " + map2.values());
		
		// 컬렉션프레임워크 패키지의 Map인터페이스 메서드 예시 참고.
		// 비슷한 부분이 많아서 필요하다고 판단되는 부분만 여기에 정리함.
		
		
		
		
	}

}
