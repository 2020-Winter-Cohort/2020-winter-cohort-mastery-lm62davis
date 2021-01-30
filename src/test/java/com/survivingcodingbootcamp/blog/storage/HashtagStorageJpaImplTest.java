package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.storage.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.storage.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class HashtagStorageJpaImplTest {

    private HashtagRepository hashtagRepo;
    private HashtagStorage underTest;
    private Hashtag testHashtag;

    @BeforeEach
    void setup() {
        hashtagRepo = mock(HashtagRepository.class);
        underTest = new HashtagStorageJpaImpl(hashtagRepo);
        testHashtag = new Hashtag("Testing");
    }

    @Test
    public void retrieveAllHashtagsShouldFindAllHashtagsFromRepo() {
        List retrievedHashtags = Collections.EMPTY_LIST;
        when(hashtagRepo.findAll()).thenReturn(retrievedHashtags);

        assertThat(underTest.retrieveAllHashtags()).isEmpty();
        verify(hashtagRepo).findAll();
    }
    @Test
    public void saveShouldSaveHashtagToRepo(){
        underTest.save(testHashtag);
        verify(hashtagRepo).save(testHashtag);
    }
    @Test
    public void retrieveSingleHashtagShouldRetrieveSingleHashtagByIdFromRepo(){
        when(hashtagRepo.findById(1L)).thenReturn(Optional.of(testHashtag));
        assertThat(underTest.retrieveSingleHashtag(1l)).isEqualTo(testHashtag);
    }
}
