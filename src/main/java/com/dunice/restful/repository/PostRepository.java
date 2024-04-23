package com.dunice.restful.repository;

import com.dunice.restful.model.Client;
import com.dunice.restful.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Integer> {
    List<Post> findAllByTitle(String title);
    @Query("select '*' from Post ")
    List<Post> findAll();
    @Query("select '*' from Post where id = :id ")
    Optional<Post> findById(Integer id);

}
