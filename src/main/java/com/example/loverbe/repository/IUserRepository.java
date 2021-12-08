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
            "    u.joined_at joinedAt,\n" +
            "    u.rented_counter rentedCounter\n" +
            "FROM user u\n" +
            "join user_role ur on u.id = ur.user_id\n" +
            "where u.status = 'Active' and ur.role_id = 2\n" +
            "order by u.joined_at desc limit ?1 offset ?2", nativeQuery = true)
    Iterable<IUserBuyerDetail> findUserByJoinedAtDesc(Long limit, Long offset);

    @Query(value = "SELECT count(u.id) totalElement\n" +
            "FROM user u\n" +
            "join user_role ur on u.id = ur.user_id\n" +
            "where u.status = 'Active' and ur.role_id = 2\n" +
            "order by u.joined_at desc", nativeQuery = true)
    Long getTotalEntityDescJoinedAt();

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
            "    u.joined_at joinedAt,\n" +
            "    u.rented_counter rentedCounter\n" +
            "from user u\n" +
            "order by u.rented_counter desc limit ?1 offset 0", nativeQuery = true)
    Iterable<IUserBuyerDetail> findUserHighestRanking(Long limit);

    @Query(value = "(SELECT\n" +
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
            "    u.joined_at joinedAt,\n" +
            "    u.rented_counter rentedCounter\n" +
            "from user u\n" +
            "where u.gender = 'Female'\n" +
            "order by u.rented_counter desc limit ?1 offset 0)\n" +
            "union\n" +
            "(SELECT\n" +
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
            "    u.joined_at joinedAt,\n" +
            "    u.rented_counter rentedCounter\n" +
            " from user u\n" +
            "where u.gender = 'Male'\n" +
            " order by u.rented_counter desc limit ?2 offset 0)", nativeQuery = true)
    Iterable<IUserBuyerDetail> findUserLimitFemaleLimitMale(Long limitFemale, Long limitMale);

    @Query(value = "SELECT u.id,\n" +
            "       u.first_name firstName,\n" +
            "       u.last_name lastName,\n" +
            "       u.height,\n" +
            "       u.weight,\n" +
            "       u.year_of_birth yearOfBirth,\n" +
            "       u.bust,\n" +
            "       u.waist,\n" +
            "       u.hips,\n" +
            "       u.avatar avatar,\n" +
            "       u.joined_at joinedAt,\n" +
            "       u.rented_counter rentedCounter\n" +
            "FROM user u\n" +
            "join user_role ur on u.id = ur.user_id\n" +
            "where u.status = 'Active'\n" +
            "  and ur.role_id = 2 and u.gender = ?1\n" +
            "order by u.joined_at desc\n" +
            "limit ?2 offset 0", nativeQuery = true)
    Iterable<IUserBuyerDetail> findUserSuitable(String genderSearch, Long limit);
}
