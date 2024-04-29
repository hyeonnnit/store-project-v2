package com.example.store.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public User updateById(int id, UserRequest.UpdateDTO reqDTO){
        User user = findById(id);
        user.setAddress(reqDTO.getAddress());
        user.setBirth(reqDTO.getBirth());
        user.setEmail(reqDTO.getEmail());
        user.setTel(reqDTO.getTel());
        user.setPassword(reqDTO.getPassword());
        return user;
    }
    public User findById(int id) {
        User user = em.find(User.class, id);
        return user;
    }

    public User findByUsernameAndPassword(String username, String password) {
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }
    public User save(User user) {
        em.persist(user);
        return user;
    }
}
