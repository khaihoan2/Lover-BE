package com.example.loverbe.repository;

import com.example.loverbe.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select u.firstName,u.yearOfBirth,u.gender,u.nationality.id,u.viewCounter,u.rentedCounter from User u where " +
            "(:username is null or lower(u.username)" +
            " like %:username%) and " +
            "(:firstName is null " +
            "or lower(u.firstName) " +
            "like %:firstName%) and" +
            " (:viewCounterMin is null or (:viewCounterMin < u.viewCounter))" +
            " and (:viewCounterMax is null or (u.viewCounter < :viewCounterMax))")
    Page<User> findByNameFull(@Param("username") String userName,
                          @Param("firstName") String firstName,
                          @Param("viewCounterMin") Long viewCounterMin,
                          @Param("viewCounterMax") Long viewCounterMax,
                          Pageable pageable);

}
