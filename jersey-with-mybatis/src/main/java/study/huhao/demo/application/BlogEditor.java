package study.huhao.demo.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.huhao.demo.domain.contexts.blogcontext.blog.Blog;
import study.huhao.demo.domain.contexts.blogcontext.blog.BlogRepository;
import study.huhao.demo.domain.contexts.blogcontext.blog.BlogService;

import java.util.UUID;

@Service
public class BlogEditor {

    private final BlogService blogService;

    @Autowired
    public BlogEditor(BlogRepository blogRepository) {
        this.blogService = new BlogService(blogRepository);
    }

    @Transactional
    public Blog create(String title, String body, UUID authorId) {
        return blogService.createBlog(title, body, authorId);
    }

    @Transactional
    public void delete(UUID id) {
        blogService.deleteBlog(id);
    }

    @Transactional
    public void save(UUID id, String title, String body) {
        blogService.saveBlog(id, title, body);
    }

    @Transactional
    public void publish(UUID id) {
        blogService.publishBlog(id);
    }
}
