package com.example.loverbe.repository;

import com.example.loverbe.model.IUserBuyerDetail;
import com.example.loverbe.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "" +
            "SELECT\n" +
            "    u.id,\n" +
            "    u.height,\n" +
            "    u.weight,\n" +
            "    u.year_of_birth yearOfBirth,\n" +
            "    u.bust,\n" +
            "    u.waist,\n" +
            "    u.hips,\n" +
            "    u.avatar avatar,\n" +
            "    u.joined_at joinedAt\n" +
            "FROM user u\n" +
            "order by\n" +
            "    u.joined_at desc limit ?1 offset ?2", nativeQuery = true)
    Iterable<IUserBuyerDetail> findUserByJoinedAtDesc(Long limit, Long offset);
}
