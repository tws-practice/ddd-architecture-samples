package study.huhao.demo.infrastructure.persistence.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study.huhao.demo.domain.contexts.blogcontext.blog.Blog;
import study.huhao.demo.domain.contexts.blogcontext.blog.BlogCriteria;
import study.huhao.demo.domain.contexts.blogcontext.blog.BlogRepository;
import study.huhao.demo.domain.contexts.blogcontext.blog.BlogService;
import study.huhao.demo.domain.core.common.Page;
import study.huhao.demo.domain.core.common.excpetions.EntityNotFoundException;
import study.huhao.demo.infrastructure.persistence.RepositoryTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlogRepositoryTest extends RepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    private BlogService blogService;

    @BeforeEach
    void setUp() {
        blogService = new BlogService(blogRepository);
    }

    @Test
    void findById() {
        Blog blog = blogService
                .create("Test Blog", "Something...", UUID.randomUUID());

        Blog foundBlog = blogService.get(blog.getId());

        assertThat(foundBlog.getId()).isEqualTo(blog.getId());
        assertThat(foundBlog.getTitle()).isEqualTo("Test Blog");
        assertThat(foundBlog.getBody()).isEqualTo("Something...");
    }

    @Test
    void save_updated_blog() {
        Blog blog = blogService
                .create("Test Blog", "Something...", UUID.randomUUID());

        blogService.saveDraft(blog.getId(), "Updated Title", "Updated...");

        Blog foundBlog = blogService.get(blog.getId());
        assertThat(foundBlog.getId()).isEqualTo(blog.getId());
        assertThat(foundBlog.getTitle()).isEqualTo("Updated Title");
        assertThat(foundBlog.getBody()).isEqualTo("Updated...");
    }

    @Test
    void delete_blog() {
        Blog blog = blogService
                .create("Test Blog", "Something...", UUID.randomUUID());

        blogService.delete(blog.getId());

        assertThatThrownBy(() -> blogService.get(blog.getId()))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void publish_blog() {
        Blog blog = blogService
                .create("Test Blog", "Something...", UUID.randomUUID());

        blogService.publish(blog.getId());

        Blog foundBlog = blogService.get(blog.getId());
        assertThat(foundBlog.getId()).isEqualTo(blog.getId());
        assertThat(foundBlog.getStatus()).isEqualTo(Blog.Status.Published);
        assertThat(foundBlog.getPublished()).isNotNull();
        assertThat(foundBlog.getPublished().getTitle()).isEqualTo("Test Blog");
        assertThat(foundBlog.getPublished().getBody()).isEqualTo("Something...");
        assertThat(foundBlog.getPublished().getPublishedAt()).isNotNull();
    }

    @Test
    void get_all_blog() {
        UUID authorId = UUID.randomUUID();
        for (int i = 0; i < 5; i++) {
            blogService.create("Test Blog " + (i + 1), "Something...", authorId);
        }
        BlogCriteria criteria = new BlogCriteria(3, 3);

        Page<Blog> pagedBlog = blogService.query(criteria);

        assertThat(pagedBlog.getResults()).hasSize(2);
        assertThat(pagedBlog.getLimit()).isEqualTo(3);
        assertThat(pagedBlog.getOffset()).isEqualTo(3);
        assertThat(pagedBlog.getTotal()).isEqualTo(5);
    }
}
