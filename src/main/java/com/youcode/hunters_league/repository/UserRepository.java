package com.youcode.hunters_league.repository;

import com.youcode.hunters_league.domain.AppUser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID>, JpaSpecificationExecutor<AppUser> {

    List<AppUser> findAll(Specification<AppUser> specification);
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsernameOrEmail(String username, String email);
    Optional<AppUser> findByCin(String cin);

    List<AppUser> findByUsernameContainingOrEmailContaining(String username, String email);


    // Check available username
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AppUser u WHERE u.username = :username AND u.id != :excludeId")
    boolean existsByUsernameAndIdNot(@Param("username") String username, @Param("excludeId") UUID excludeId);

    // Check available email
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AppUser u WHERE u.email = :email AND u.id != :excludeId")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("excludeId") UUID excludeId);

    // Check available CIN
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AppUser u WHERE u.cin = :cin AND u.id != :excludeId")
    boolean existsByCinAndIdNot(@Param("cin") String cin, @Param("excludeId") UUID excludeId);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AppUser u WHERE u.email = :email AND u.username != :excludeUsername")
    boolean existsByEmailAndUsernameNot(@Param("email") String email, @Param("excludeUsername") String excludeUsername);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM AppUser u WHERE u.cin = :cin AND u.username != :excludeUsername")
    boolean existsByCinAndUsernameNot(@Param("cin") String cin, @Param("excludeUsername") String excludeUsername);
}
