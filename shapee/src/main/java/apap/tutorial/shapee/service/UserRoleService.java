package apap.tutorial.shapee.service;

import apap.tutorial.shapee.model.UserModel;

public interface UserRoleService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
}
