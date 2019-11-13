package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.UserModel;

public interface UserRoleService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    UserModel getByUsername(String username);
    void updatePassword(UserModel user, String newPassword);
    boolean validatePassword(String password);
}
