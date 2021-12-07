package com.example.loverbe.repository;

import com.example.loverbe.model.IUserBuyerDetail;
import com.example.loverbe.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select u.id,\n" +
            "       u.first_name,\n" +
            "       u.last_name,\n" +
            "       u.height,\n" +
            "       u.weight,\n" +
            "       u.year_of_birth,\n" +
            "       u.bust,\n" +
            "       u.waist,\n" +
            "       u.hips,\n" +
            "       u.avatar,\n" +
            "       u.joined_at,\n" +
            "       u.rented_counter\n" +
            "from user u\n" +
            "join reservation r on u.id = r.rentee_id\n" +
            "where u.status = 'Active' and r.status = 'Completed'\n" +
            "group by u.id\n" +
            "order by u.rented_counter desc limit ?1 offset 0", nativeQuery = true)
    Iterable<IUserBuyerDetail> findUserHighestRanking(Long limit);
}
