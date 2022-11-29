package com.example.demo.services;

import com.example.demo.domain.Post;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);
        if (!post.isPresent()) {
            throw new ObjectNotFoundException("Object not found");
        }
        return post.get();
    }

    public List<Post> findByTitle(String text) {
        return postRepository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        int dayInMiliseconds = 24 * 60 * 60 * 1000;
        maxDate = new Date(maxDate.getTime() + dayInMiliseconds);
        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
