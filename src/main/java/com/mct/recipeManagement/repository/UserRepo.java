package com.mct.recipeManagement.repository;

import com.mct.recipeManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findFirstById(Long id);
}
