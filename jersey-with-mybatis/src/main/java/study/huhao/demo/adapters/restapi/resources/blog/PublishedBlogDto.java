package study.huhao.demo.adapters.restapi.resources.blog;

import study.huhao.demo.adapters.restapi.resources.ResponseDto;
import study.huhao.demo.domain.contexts.blogcontext.blog.PublishedBlog;

import java.time.Instant;

class PublishedBlogDto implements ResponseDto {
    private String title;
    private String body;
    private Instant publishedAt;

    private PublishedBlogDto(String title, String body, Instant publishedAt) {
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

    static PublishedBlogDto of(PublishedBlog published) {
        return published == null ? null : new PublishedBlogDto(
                published.getTitle(),
                published.getBody(),
                published.getPublishedAt());
    }
}
