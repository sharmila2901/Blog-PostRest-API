package com.springboot.blog.service;

import com.springboot.blog.Payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPost();
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto,long id);
    void deletePost(Long id);
}
