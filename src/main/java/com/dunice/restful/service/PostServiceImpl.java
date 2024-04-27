package com.dunice.restful.service;


import com.dunice.restful.dto.PostDto;
import com.dunice.restful.mapper.PostMapper;
import com.dunice.restful.model.Post;
import com.dunice.restful.model.Tags;
import com.dunice.restful.repository.ClientRepository;
import com.dunice.restful.repository.PostRepository;
import com.dunice.restful.repository.TagsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ClientRepository clientRepository;
    private final TagsRepository tagsRepository;
    private final PostMapper postMapper;
    @Override
    public void create(PostDto postDto) {
        postRepository.save(postMapper.mapToEntity(postDto));
    }

    @Override
    public List<PostDto> readAll() {
        return postRepository.findAll().stream()
                .map(post -> postMapper.mapToDto(post))
                .toList();
    }

    @Override
    public PostDto read(int id) {
        var post = postRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("post not found"));
        return postMapper.mapToDto(post);
    }

    @Override
    public boolean update(PostDto postDto, int id) {
        var post = postRepository.findById(id).orElse(Post.builder().build());
        var removedTags =  post.getTags()
                .stream()
                .filter(tag -> !postDto.getTags().contains(tag))
                .toList();


        var tags = postDto.getTags()
                .stream()
                .map(tagName -> tagsRepository.findByName(tagName).orElseGet(()-> {
                    var tag = Tags.builder().name(tagName).build();
                    return tagsRepository.save(tag);
                }))
                .collect(Collectors.toCollection(ArrayList::new));

        post.setTitle(postDto.getTitle());
        post.setTags(tags);

        var client = clientRepository.findById(postDto.getClientId()).orElseThrow(() ->new EntityNotFoundException("client not found"));;
        post.setClient(client);

        postRepository.save(post);
        removedTags.stream()
                .filter(tag -> postRepository.countAllByTags(tag) == 0)
                .forEach(tagsRepository::delete);
        return true;
    }

    @Override
    public boolean delete(int id) {
        var post = postRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("post not found"));;
        postRepository.delete(post);
        post.getTags().stream()
                .filter(tags -> postRepository.countAllByTags(tags) == 0)
                .forEach(tagsRepository::delete);
        return true;
    }
}
