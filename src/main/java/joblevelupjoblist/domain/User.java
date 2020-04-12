package joblevelupjoblist.domain;

public class User {
    private int id;
    private String name;
    private String lastName;
    private String passport;

    public User() {

    }

    public User(int id, String name, String lastName, String passport) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.passport = passport;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
