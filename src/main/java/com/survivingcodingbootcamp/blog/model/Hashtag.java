package com.survivingcodingbootcamp.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue
    private long id;
    private String tagName;

    @ManyToMany (mappedBy = "hashtags")
    private Collection<Post> post;

    protected Hashtag(Collection<Post> post) {
        this.post = post;
    }

    public Hashtag(String tagName, Post...post) {
        this.tagName = tagName;
        this.post = List.of(post);
        }

    public Hashtag() {

    }
    public String getTagName() {
        return tagName;
    }

    public Long getId() {
        return id;
    }

    public Collection<Post> getPost(){
        return post;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(id, hashtag.id) && Objects.equals(tagName, hashtag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName);
    }
}
