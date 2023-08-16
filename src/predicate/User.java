package predicate;

class User {
    String name, role;

    User(String a, String b) {
        name = a;
        role = b;
    }

    String getRole() {
        return role;
    }

    String getName() {
        return name;
    }

    public String toString() {
        return "User Name : " + name + ", Role :" + role;
    }
}