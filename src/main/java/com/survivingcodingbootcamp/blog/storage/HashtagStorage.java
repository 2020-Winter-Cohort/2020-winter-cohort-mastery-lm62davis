package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Hashtag;

public interface HashtagStorage {
    Iterable<Hashtag> retrieveAllHashtags();

    Hashtag retrieveSingleHashtag(long l);

    void save(Hashtag hashtagToSave);
}
