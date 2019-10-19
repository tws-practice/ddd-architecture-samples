package study.huhao.demo.adapters.restapi.resources.blog;

import study.huhao.demo.adapters.restapi.resources.ResponseDto;
import study.huhao.demo.domain.contexts.blogcontext.blog.Blog;

import java.time.Instant;
import java.util.UUID;

class BlogDto implements ResponseDto {
    private UUID id;
    private String title;
    private String body;
    private UUID authorId;
    private Blog.Status status;
    private Instant createdAt;
    private Instant savedAt;
    private PublishedBlogDto published;

    private BlogDto(UUID id, String title, String body, UUID authorId, Blog.Status status, Instant createdAt, Instant savedAt, PublishedBlogDto published) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorId = authorId;
        this.status = status;
        this.createdAt = createdAt;
        this.savedAt = savedAt;
        this.published = published;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public Blog.Status getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getSavedAt() {
        return savedAt;
    }

    public PublishedBlogDto getPublished() {
        return published;
    }

    static BlogDto of(Blog blog) {
        return new BlogDto(
                blog.getId(),
                blog.getTitle(),
                blog.getBody(),
                blog.getAuthorId(),
                blog.getStatus(),
                blog.getCreatedAt(),
                blog.getSavedAt(),
                PublishedBlogDto.of(blog.getPublished()));
    }
}
