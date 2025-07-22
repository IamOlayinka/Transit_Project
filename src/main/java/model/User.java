package model;


public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String userType;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.userType = builder.userType;
    }

    public static class UserBuilder {
        private int id;
        private String name;
        private String email;
        private String password;
        private String userType;

        public UserBuilder setId(int id) { this.id = id; return this; }
        public UserBuilder setName(String name) { this.name = name; return this; }
        public UserBuilder setEmail(String email) { this.email = email; return this; }
        public UserBuilder setPassword(String password) { this.password = password; return this; }
        public UserBuilder setUserType(String userType) { this.userType = userType; return this; }

        public User build() { return new User(this); }
    }

    // Getters...
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getUserType() { return userType; }
}

