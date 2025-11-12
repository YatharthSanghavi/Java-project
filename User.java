class User {
    int id;
    String name;
    String email;

    User() {}
    
    User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    void showProfile() {
        System.out.println("User ID: " + id + " Name: " + name + " Email: " + email);
    }
}