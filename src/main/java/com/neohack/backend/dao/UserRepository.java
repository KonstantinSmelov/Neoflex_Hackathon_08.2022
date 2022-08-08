package com.neohack.backend.dao;

import com.neohack.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * "+
            "FROM neohack.users "+
            "WHERE neohack.users.username = :username " +
            "OR neohack.users.email = :email "
            , nativeQuery = true)
    List<User> findUserWithUsernameOrEmail(@Param("username") String username, @Param("email") String email);

}
