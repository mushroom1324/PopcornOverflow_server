package com.example.demo.Controller;

import com.example.demo.Exception.PostNotFoundException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Model.Post;
import com.example.demo.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/list")
    public List<Post> getAllPost() {
        List<Post> posts = postRepository.findAll();
        // if (posts.size() > 5)
        // implement to split list by size of 5
        return posts;
    }

    @GetMapping("/{id}")
    Post getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(()-> new PostNotFoundException(id));
    }

    @PostMapping("/new")
    Post newPost(@RequestBody Post newPost) {
        return postRepository.save(newPost);
    }

    @PutMapping("/edit/{id}")
    Post updatePost(@RequestBody Post newPost, @PathVariable Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(newPost.getTitle());
                    post.setDescription(newPost.getDescription());
                    post.setDate(newPost.getDate());
                    return postRepository.save(post);
                }).orElseThrow(() -> new PostNotFoundException(id));
    }

    @DeleteMapping("/delete/{id}")
    String deletePost(@PathVariable Long id) {
        if(!postRepository.existsById(id)) {
            throw new PostNotFoundException(id);
        }
        postRepository.deleteById(id);
        return "Post with id " + id + " has been deleted successfully.";
    }


}