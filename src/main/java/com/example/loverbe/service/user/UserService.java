package com.example.loverbe.service.user;

import com.example.loverbe.model.IUserBuyerDetail;
import com.example.loverbe.model.entity.User;
import com.example.loverbe.model.UserPrincipal;
import com.example.loverbe.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return UserPrincipal.build(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Iterable<IUserBuyerDetail> findUserByJoinedAtDesc(Long page) {
        Long limit = 12l;
        Long offset = page * limit;
        return userRepository.findUserByJoinedAtDesc(limit, offset);
    }

    @Override
    public Iterable<IUserBuyerDetail> findUserHighestRanking() {
        Long limit = 6l;
        return userRepository.findUserHighestRanking(limit);
    }

    @Override
    public Iterable<IUserBuyerDetail> findUserLimitFemaleLimitMale() {
        Long limitMale = 4l;
        Long limitFemale = 8l;
        return userRepository.findUserLimitFemaleLimitMale(limitFemale, limitMale);
    }

}
