package com.dunice.restful.service;


import com.dunice.restful.dto.PostDto;
import com.dunice.restful.model.Post;
import com.dunice.restful.model.Tags;
import com.dunice.restful.repository.ClientRepository;
import com.dunice.restful.repository.PostRepository;
import com.dunice.restful.repository.TagsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ClientRepository clientRepository;
    private final TagsRepository tagsRepository;
    @Override
    public void create(PostDto postDto) {
        var client = clientRepository.findById(postDto.getClientId()).orElseThrow(() ->new EntityNotFoundException("client not found"));
        var tags = postDto.getTags()
                .stream()
                .map(tagName -> tagsRepository.findByName(tagName).orElseGet(()-> {
                var tag = Tags.builder().name(tagName).build();
                return tagsRepository.save(tag);
                }))
                .toList();

        Post post = Post.builder()
                .title(postDto.getTitle())
                .client(client)
                .tags(tags)
                .build();
        postRepository.save(post);
    }

    @Override
    public List<PostDto> readAll() {
        return postRepository.findAll().stream()
                .map(post -> PostDto.builder()
                        .title(post.getTitle())
                        .tags(post.getTags().stream().map(Tags::getName).toList())
                        .build())
                .toList();

    }

    @Override
    public PostDto read(int id) {
        var post = postRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("client not found"));
        return PostDto.builder()
                .title(post.getTitle())
                .tags(post.getTags().stream().map(Tags::getName).toList())
                .build();
    }

    @Override
    public boolean update(PostDto postDto, int id) {
        var post = postRepository.findById(id).orElse(Post.builder().build());
        var tags = postDto.getTags()
                .stream()
                .map(tagName -> tagsRepository.findByName(tagName).orElseGet(()-> {
                    var tag = Tags.builder().name(tagName).build();
                    return tagsRepository.save(tag);
                }))
                .toList();
        post.setTitle(postDto.getTitle());
        post.setTags(tags);
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
