package pattern_builder;

public class Main {
    public static void main(String[] args) {
        Person myPerson = new Person.Builder()
                .withAge(16)
                .withName("Jane")
                .withSurname("Smith")
                .build();
    }
}
