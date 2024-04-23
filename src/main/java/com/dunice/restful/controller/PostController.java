package com.dunice.restful.controller;

import com.dunice.restful.dto.ClientDto;
import com.dunice.restful.dto.PostDto;
import com.dunice.restful.model.Post;
import com.dunice.restful.service.ClientService;
import com.dunice.restful.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class PostController {
    private final PostService postService;

    @PostMapping(value = "/posts")
    public ResponseEntity<?> create(@RequestBody @Valid PostDto post) {
        postService.create(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<List<PostDto>> read() {
        final List<PostDto> posts = postService.readAll();

        return posts != null &&  !posts.isEmpty()
                ? new ResponseEntity<>(posts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<?> read(@PathVariable @Min(0) int id) {
        final PostDto postDto = postService.read(id);

        return postDto != null
                ? new ResponseEntity<>(postDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/posts/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody @Valid PostDto post) {
        final boolean updated = postService.update(post, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = postService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
