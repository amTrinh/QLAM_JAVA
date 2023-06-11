package net.codejava.QLAM.Repositories;

import net.codejava.QLAM.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    User findByPassword(String password);
    @Query(nativeQuery= true, value="SELECT * FROM tblUser WHERE UserName = ? and Password = ?")
    User findByUserNameAndPassword(String userName, String password);
}

