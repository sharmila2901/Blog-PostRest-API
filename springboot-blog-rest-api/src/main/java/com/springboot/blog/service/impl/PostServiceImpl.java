package com.springboot.blog.service.impl;

import com.springboot.blog.Payload.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
// when bean class has one constructor we can omit @Autowired
    private PostRepository postRepository;
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Override
    public PostDto createPost(PostDto postDto) {
        //convert DTO to entity
        Post post =mapToEntity(postDto);
        Post newpost=postRepository.save(post);

        //convert entity to Dto for response from respository after saving
        PostDto newpostResponse=mapToDto(newpost);


        return newpostResponse;
    }

    @Override
    public List<PostDto> getAllPost() {
       List<Post> posts= postRepository.findAll();
       //convert post to list postDto
       return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(long id) {
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Posts","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Posts","id",id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedpost =postRepository.save(post);
        return mapToDto(updatedpost);
    }

    @Override
    public void deletePost(Long id) {
        Post post=postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Posts","id",id));

         postRepository.delete(post);
    }

    //convert entity to Dto
    private PostDto mapToDto(Post post){
        PostDto postDto =new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;

    }
    //convert DTO to entity
    private  Post mapToEntity(PostDto postDto){
        Post post =new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }


}
