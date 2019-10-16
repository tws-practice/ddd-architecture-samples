package study.huhao.demo.domain.blogcontext.blog;

import study.huhao.demo.domain.core.ValueObject;

import java.time.Instant;
import java.util.Objects;

public class PublishedBlog implements ValueObject {
    private String title;
    private String body;
    private Instant publishedAt;

    public PublishedBlog(String title, String body, Instant publishedAt) {
        this.title = title;
        this.body = body;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishedBlog that = (PublishedBlog) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(body, that.body) &&
                Objects.equals(publishedAt, that.publishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, body, publishedAt);
    }
}

