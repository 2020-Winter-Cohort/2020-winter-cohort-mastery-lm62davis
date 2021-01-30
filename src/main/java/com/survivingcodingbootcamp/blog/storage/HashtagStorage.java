package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Hashtag;

public interface HashtagStorage {
    Iterable<Hashtag> retrieveAllHashtags();

    Hashtag retrieveSingleHashtag(long l); // changed from long id to long l

    void save(Hashtag hashtagToSave);
}
