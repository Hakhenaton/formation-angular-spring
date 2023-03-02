package lang;

public class Person {
    
    private final String firstName;

    private final String lastName;

    private final Integer age;

    private final String socialSecurityNumber;

    private Person(String firstName, String lastName, Integer age, String socialSecurityNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public static PersonBuilder builder(){
        return new PersonBuilder();
    }

    public static class PersonBuilder {

        private String firstName;

        private String lastName;

        private Integer age;

        private String socialSecurityNumber;

        private PersonBuilder(){}

        public PersonBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder age(Integer age){
            this.age = age;
            return this;
        }

        public PersonBuilder socialSecurityNumber(String number){
            this.socialSecurityNumber = number;
            return this;
        }


        public Person build(){

            if (this.firstName == null){
                throw new IllegalStateException();
            }

            return new Person(this.firstName, this.lastName, this.age, this.socialSecurityNumber);
        }
    }
}
