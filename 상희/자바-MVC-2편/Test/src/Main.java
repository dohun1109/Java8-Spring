import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Cat cat1 = new Cat("나비", "먼치킨", "2");
        Cat cat2 = new Cat("보리", "먼치킨", "4");

        System.out.println(cat1.equals(cat2));
    }

    static class Cat {
        private String name; // 고양이 이름
        private String type; // 고양이 종
        private String age; // 고양이 나이

        // 생성자
        public Cat(String name, String type, String age) {
            this.name = name;
            this.type = type;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Cat cat = (Cat) obj;
            return Objects.equals(type, cat.type);
        }
    }
}