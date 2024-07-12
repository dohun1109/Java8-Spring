package lambda;

import java.util.*;
import java.util.stream.Collectors;

public class anonymous {
    /**
     * lambda : 자바8 이전에는 잠깐 사용되고 마는 메서드를 추가하기 위해 익명 클래스를 선언해야 했다.
     * 이는 불필요한 코드를 만드렁 내 코드를 만들어 내 코드 가독성을 해치는 요소였다.
     * @param args
     */
    public static void main(String[] args) {

        List list = new ArrayList<>();

        list.add("public");
        list.add("static");
        list.add("void");

        //익명 클래스
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });

        //람다 표현식 코드
        list.sort((Comparator<String>) (str1, str2) -> str1.compareTo(str2));

        //for 문
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        list.stream().forEach(str -> System.out.println(str));

        //List 1~10 까지의 수를 넣고, 리스트에서  2의 배수만으로 이루어진 리스트를 뽑기
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list_i = Arrays.asList(integers);

        List evenList = new ArrayList<Integer>();

        for (int i = 0; i < list_i.size(); i++) {
            Integer number = list_i.get(i);
            if (number % 2 == 0) {
                evenList.add(number);
            }
        }
        evenList.stream().forEach(value -> System.out.println(value));

        List<Integer> evenLists = list_i.stream()
                .filter(value -> value % 2 ==0).collect(Collectors.toList());
        evenLists.stream().forEach(value -> System.out.println(value));

        Integer[] integersArray = new Integer[]{1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
        List<Integer> list_distinct = Arrays.asList(integersArray).stream().distinct().toList();
        list_distinct.stream().forEach(value -> System.out.println(value));

        String[] lowercaseArray = {"public", "static", "void"};
        List<String> uppercaseList = Arrays.asList(lowercaseArray).stream().map(value -> value.toUpperCase()).toList();
        uppercaseList.stream().forEach(value -> System.out.println(value));

        //Optional -> Optional은 null이 들어있는 레퍼런스 변수의 멤버에 접근하려고 할 때 발생하는 NullPointerException
        //을 우아하게? 해결하기 위해서 등장했다.

        String isThisNull = getrSomeString();
        if (null != isThisNull) {
            System.out.println(isThisNull.toUpperCase());
        }

        Optional<String> isThis_optionalEmpty = getSomeString();
        isThis_optionalEmpty.ifPresent(str -> System.out.println(str.toUpperCase()));
        //ifPresent -> empty가 아닌 경우 인자로 들어간 람다 표현식을 실행하고, 비어 있는 경우에는 실행되지 않는다.
        //앞에서 실행한 예제에서 null을 체크하던 if문과 동일하게 동작한다.

        Optional<String> isThis_optionalEmpty1 = getSomeString1();
        isThis_optionalEmpty1.ifPresent(str -> System.out.println(str.toUpperCase()));  //PUBLIC STATIC VOID

        /**
         * 안티패턴 : isPresent() method 를 마치 if 문처럼 잘못 사용한 사례
         */

        
    }

    private static String getrSomeString() {
        return null; //이 메서드는 항상 null을 반환한다. 
    }

    private static Optional<String> getSomeString() {
        return Optional.empty();    //null을 반환하는게 아니라 비어있는 Optional 을 반환한다.
    }

    private static Optional<String> getSomeString1() {
        return Optional.ofNullable("public static void");
    }



}
