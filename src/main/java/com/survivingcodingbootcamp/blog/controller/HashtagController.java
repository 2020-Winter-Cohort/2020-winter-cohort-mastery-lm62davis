package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import com.survivingcodingbootcamp.blog.storage.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.storage.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

    public class HashtagController {

        private HashtagStorage hashtagStorage;
        private PostStorage postStorage;
        private HashtagRepository hashtagRepo;
        private PostRepository postRepo;

        public HashtagController(HashtagStorage hashtagStorage, PostStorage postStorage, HashtagRepository hashtagRepo, PostRepository postRepo){
            this.hashtagStorage = hashtagStorage;
            this.postStorage = postStorage;
/*            this.hashtagRepo = hashtagRepo;
            this.postRepo = postRepo;*/
        }

    @GetMapping("/hashtag/{id}")
    public String displaySingleHashtag(@PathVariable long id, Model model) {
        model.addAttribute("hashtag", hashtagStorage.retrieveSingleHashtag(id));
        return "single-hashtag-template";
        }

    @GetMapping ("/hashtags")
    public String displayAllHashtags(Model model){
        model.addAttribute("allHashtags", hashtagStorage.retrieveAllHashtags());
        return "/all-hashtags-template";
    }

        @PostMapping("/addHashtag")
        public String addHashtag(@RequestParam String newHashtag, @RequestParam String postId){
            Hashtag hashtagToSave = new Hashtag(newHashtag);
            hashtagStorage.save(hashtagToSave);
            long id = Long.parseLong(postId);
            postStorage.addHashtagToPost(id, hashtagToSave);
            return "redirect:/posts/" + postId;
        }

        @PostMapping("/hashtag/post/{id}")
        public String displayPostsInHashtag(Model model, @PathVariable long id){
            model.addAttribute("post", postStorage.retrievePostById(id));
            model.addAttribute("allHashtags", hashtagStorage.retrieveAllHashtags());
            model.addAttribute("postInHash", postStorage.retrieveAllPosts());
            return "single-hashtag-template";
        }
}
