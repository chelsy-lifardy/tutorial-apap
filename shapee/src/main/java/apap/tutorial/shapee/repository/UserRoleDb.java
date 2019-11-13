package apap.tutorial.shapee.repository;

import apap.tutorial.shapee.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDb extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
