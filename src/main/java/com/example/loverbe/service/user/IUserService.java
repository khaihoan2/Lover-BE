package com.example.loverbe.service.user;

import com.example.loverbe.model.IUserBuyerDetail;
import com.example.loverbe.model.entity.User;
import com.example.loverbe.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    User findByUsername(String username);

    Page<User> findAll(Pageable pageable);

    Iterable<IUserBuyerDetail> findUserByJoinedAtDesc(Long page);

    Iterable<IUserBuyerDetail> findUserHighestRanking();

    Iterable<IUserBuyerDetail> findUserLimitFemaleLimitMale();

    Long getTotalEntityDescJoinedAt();


    Iterable<IUserBuyerDetail> findUserSuitable();

    Page<IUserBuyerDetail> findByNameFull(String userName, String firstName, Long viewCounterMin, Long viewCounterMax, Pageable pageable);

}
