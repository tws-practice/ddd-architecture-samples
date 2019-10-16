package study.huhao.demo.infrastructure.persistence.blog;

import study.huhao.demo.domain.blogcontext.blog.PublishedBlog;
import study.huhao.demo.infrastructure.persistence.PersistenceObject;

import java.time.Instant;

public class PublishedBlogPO implements PersistenceObject<PublishedBlog> {
    private String publishedTitle;
    private String publishedBody;
    private Instant publishedAt;

    PublishedBlogPO() {
    }

    PublishedBlogPO(String publishedTitle, String publishedBody, Instant publishedAt) {
        this.publishedTitle = publishedTitle;
        this.publishedBody = publishedBody;
        this.publishedAt = publishedAt;
    }

    public String getPublishedTitle() {
        return publishedTitle;
    }

    public String getPublishedBody() {
        return publishedBody;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    // The persistence object needs to reflect the table structure.
    // The domain model and persistence object may have much different.
    // So, manual to convert between them is better than use object mapper like Orika.
    @Override
    public PublishedBlog toDomainModel() {
        return new PublishedBlog(publishedTitle, publishedBody, publishedAt);
    }

    // The persistence object needs to reflect the table structure.
    // The domain model and persistence object may have much different.
    // So, manual to convert between them is better than use object mapper like Orika.
    static PublishedBlogPO of(PublishedBlog published) {
        if (published == null) return null;
        return new PublishedBlogPO(
                published.getTitle(),
                published.getBody(),
                published.getPublishedAt());
    }
}
