package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.UserModel;
import apap.tutorial.shapee.repository.UserRoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDb userRoleDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userRoleDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword  = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel getByUsername(String username) {
        return userRoleDb.findByUsername(username);
    }

    @Override
    public void updatePassword(UserModel user, String newPassword) {
        String password = encrypt(newPassword);
        user.setPassword(password);

        userRoleDb.save(user);
    }

    @Override
    public boolean validatePassword(String password) {
        return password.length() >= 8 && Pattern.compile("[0-9]").matcher(password).find() && Pattern.compile("[a-zA-Z]").matcher(password).find();
    }

}
