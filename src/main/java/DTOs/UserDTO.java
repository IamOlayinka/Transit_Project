package DTOs;

public class UserDTO {
	
        private int id;
        private String name;
        private String email;
        private String password;
        private String userType;
        

        public void setId(int id) {
        	this.id = id; 
        	}
        public void setName(String name) { 
        	this.name = name;
        	}
        public void setEmail(String email) { 
        	this.email = email; 
        }
        public void setPassword(String password) { 
        	this.password = password;
        	}
        public void setUserType(String userType) {
        	this.userType = userType; 
        	}

       
        // Getters...
        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getUserType() { return userType; }

}
