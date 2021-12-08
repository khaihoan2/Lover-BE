package com.example.loverbe.repository;

import com.example.loverbe.model.IUserBuyerDetail;
import com.example.loverbe.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "SELECT\n" +
            "    u.id,\n" +
            "    u.first_name firstName,\n" +
            "    u.last_name lastName,\n" +
            "    u.height,\n" +
            "    u.weight,\n" +
            "    u.year_of_birth yearOfBirth,\n" +
            "    u.bust,\n" +
            "    u.waist,\n" +
            "    u.hips,\n" +
            "    u.avatar avatar,\n" +
            "    u.joined_at joinedAt\n" +
            "FROM user u\n" +
            "join user_role ur on u.id = ur.user_id\n" +
            "where u.status = 'Active' and ur.role_id = 2\n" +
            "order by u.joined_at desc limit ?1 offset ?2", nativeQuery = true)
    Iterable<IUserBuyerDetail> findUserByJoinedAtDesc(Long limit, Long offset);

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
