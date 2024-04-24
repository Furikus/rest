package com.dunice.restful.repository;

import com.dunice.restful.model.Post;
import com.dunice.restful.model.Tags;
import org.aspectj.apache.bcel.generic.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;

public interface TagsRepository extends JpaRepository<Tags,Integer> {
   Optional<Tags> findByName(String name);
}
