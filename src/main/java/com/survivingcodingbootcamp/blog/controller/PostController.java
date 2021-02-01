package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import com.survivingcodingbootcamp.blog.storage.TopicStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostStorage postStorage;
    private TopicStorage topicStorage;

    public PostController(PostStorage postStorage, TopicStorage topicStorage) {
        this.postStorage = postStorage;
        this.topicStorage = topicStorage;
    }

    @GetMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postStorage.retrievePostById(id));
        return "single-post-template";
    }

    @PostMapping("/addPost/")
    public String addPost(@RequestParam String title, @RequestParam String topicId, @RequestParam String author, @RequestParam String content) {
        Long id = Long.parseLong(topicId);
        Topic addToTopic = topicStorage.retrieveSingleTopic(id);
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        Post postToAdd = new Post(title, addToTopic, author, content, formattedDate);
        postStorage.save(postToAdd);
        topicStorage.addPostToTopic(id, postToAdd);
        return "redirect:/" ;
    }
}
