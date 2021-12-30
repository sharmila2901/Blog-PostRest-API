package com.springboot.blog.controller;

import com.springboot.blog.Payload.PostDto;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class Postcontroller {

    private PostService postService;
    public Postcontroller(PostService postService) {
        this.postService = postService;
    }

//create blog post Restapi
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

// get all post api
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPost();
    }
    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostBYId(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }
    //update post by id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id")long id){
        PostDto postDto1 =postService.updatePost(postDto,id);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePost(id);
       
        return  new ResponseEntity<>("Post deleted",HttpStatus.OK);
    }

}
