package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;

public interface TopicStorage {

    void addPostToTopic(long id, Post postToAdd);

    Iterable<Topic> retrieveAllTopics();

    void save(Topic topicToSave);

    Topic retrieveSingleTopic(long id);

}
