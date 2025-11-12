class AdminLogin {
    String username = "admin";
    String password = "admin123";

    void login(String user, String pass) {
        boolean res = user.equals(username) && pass.equals(password);
        if(res) {
            System.out.println("Login successful.");
            AdminDashboard dashboard = new AdminDashboard();
            dashboard.display();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
}