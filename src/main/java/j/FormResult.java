package j;

public class FormResult {

    private final String name;

    private final String surname;

    private final int age;

    private final boolean termsAgreed;

    public static FormResult FormResult(String name, String surname, int age, boolean termsAgreed) {
        return new FormResult(name, surname, age, termsAgreed);
    }

    public FormResult(String name, String surname, int age, boolean termsAgreed) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.termsAgreed = termsAgreed;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public boolean isTermsAgreed() {
        return termsAgreed;
    }

    @Override
    public String toString() {
        return "FormResult{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", termsAgreed=" + termsAgreed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormResult that = (FormResult) o;

        if (age != that.age) return false;
        if (termsAgreed != that.termsAgreed) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (termsAgreed ? 1 : 0);
        return result;
    }
}
