package com.dunice.restful.service;

import com.dunice.restful.dto.ClientDto;
import com.dunice.restful.dto.PostDto;

import java.util.List;

public interface PostService {
    void create(PostDto post);

    List<PostDto> readAll();

    PostDto read(int id);

    boolean update(PostDto post, int id);

    boolean delete(int id);

}
