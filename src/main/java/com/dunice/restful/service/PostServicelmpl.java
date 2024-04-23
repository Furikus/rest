package com.dunice.restful.service;


import com.dunice.restful.dto.PostDto;
import com.dunice.restful.model.Post;
import com.dunice.restful.repository.ClientRepository;
import com.dunice.restful.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServicelmpl implements PostService {
    private PostRepository postRepository;
    private ClientRepository clientRepository;
    @Override
    public void create(PostDto postDto) {
        var client = clientRepository.findById(postDto.getClientId()).orElseThrow(() ->new EntityNotFoundException("client not found"));
        Post post = Post.builder()
                .title(postDto.getTitle())
                .client(client)
                .build();
        postRepository.save(post);
    }

    @Override
    public List<PostDto> readAll() {
        return postRepository.findAll().stream()
                .map(post -> PostDto.builder()
                        .title(post.getTitle())
                        .build())
                .toList();

    }

    @Override
    public PostDto read(int id) {
        var post = postRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("client not found"));
        return PostDto.builder()
                .title(post.getTitle())
                .build();
    }

    @Override
    public boolean update(PostDto postDto, int id) {
        var post = postRepository.findById(id).orElse(Post.builder().build());
        post.setTitle(postDto.getTitle());
        postRepository.save(post);
        return true;
    }

    @Override
    public boolean delete(int id) {
        var post = postRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("client not found"));;
        postRepository.delete(post);
        return true;
    }
}
