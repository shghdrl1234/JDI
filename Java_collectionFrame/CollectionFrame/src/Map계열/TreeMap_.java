package Map계열;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TreeMap_ {
	/*
	 * 이진 검색 트리의 구조로 키와 값의 쌍으로 이루어진 데이터를 저장.
	 * TreeSet처럼, 데이터를 정렬해서 저장하기 때문에 저장시간이 길다.
	 * TreeSet은 TreeMap을 이용해서 구현되어 있음.
	 * 
	 * 다수의 데이터에서 개별적인 검색은 TreeMap보다 HashMap이 빠르다.
	 * Map이 필요할 때 주로 HashMap을 사용하고,
	 * 정렬이나 범위검색이 필요한 경우 TreeMap을 사용한다.
	 * 
	 * 
	 */
	
	public static void main(String[] args) {
		
		Map map = new HashMap();
		map.put("myId", "1234");
		map.put("aaa", "1111");
		map.put("bbb", "1234");
		map.put("ccc", "1234");
		
		System.out.println(map);
		
		Map map2 = new TreeMap(map);
		System.out.println("key 값의 이름순으로 자동정렬 : " + map2);
		
		
	}

}
