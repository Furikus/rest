package com.dunice.restful.mapper;

import com.dunice.restful.dto.PostDto;
import com.dunice.restful.model.Post;
import com.dunice.restful.model.Tags;
import com.dunice.restful.repository.ClientRepository;
import com.dunice.restful.repository.TagsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostMapper {
    private final TagsRepository tagsRepository;
    private final ClientRepository clientRepository;

    public Post mapToEntity(PostDto postDto) {
        var client = clientRepository.findById(postDto.getClientId()).orElseThrow(() ->new EntityNotFoundException("client not found"));
        var tags = postDto.getTags()
                .stream()
                .map(tagName -> tagsRepository.findByName(tagName).orElseGet(()-> {
                    var tag = Tags.builder().name(tagName).build();
                    return tagsRepository.save(tag);
                }))
                .toList();

        return Post.builder()
                .title(postDto.getTitle())
                .client(client)
                .tags(tags)
                .build();
    }
    public PostDto mapToDto(Post post) {
        return PostDto.builder()
                .title(post.getTitle())
                .tags(post.getTags().stream().map(Tags::getName).toList())
                .build();
    }


}
