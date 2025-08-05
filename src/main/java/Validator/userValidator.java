package Validator;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import DaoImpl.UserDaoImp;

public class userValidator {


    public static Map<String, String> validate(HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        UserDaoImp userDao = new UserDaoImp();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String userType = req.getParameter("role");


        if (isBlank(name)) {
            errors.put("name", "Name is required.");
        }

        if (email != null && userDao.emailExists(email)) {
            errors.put("email", "Email is already registered.");
        }
        
        if (isBlank(email)) {
            errors.put("email", "Email is required.");
        } else if (email != null && userDao.emailExists(email)) {
            errors.put("email", "Email is already registered.");
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            errors.put("email", "Invalid email format.");
        }


        if (isBlank(password)) {
            errors.put("password", "Password is required.");
        } else if (password.length() < 6) {
            errors.put("password", "Password must be at least 6 characters.");
        }


        if (isBlank(userType)) {
            errors.put("role", "User role is required.");
        } else if (!userType.equalsIgnoreCase("Manager") && !userType.equalsIgnoreCase("Operator")) {
            errors.put("role", "User role must be either 'Manager' or 'Operator'.");
        }

        return errors;
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
