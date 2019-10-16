package study.huhao.demo.domain.blogcontext.blog;

import study.huhao.demo.domain.core.common.Page;
import study.huhao.demo.domain.core.concepts.Service;
import study.huhao.demo.domain.core.common.excpetions.EntityNotFoundException;

import java.util.UUID;

public class BlogService implements Service {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog createBlog(String title, String body, UUID authorId) {
        Blog blog = new Blog(title, body, authorId);
        blogRepository.save(blog);
        return blog;
    }

    public Blog getBlog(UUID id) {
        return blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Blog.class, id));
    }

    public void saveBlog(UUID id, String title, String body) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Blog.class, id));
        blog.save(title, body);
        blogRepository.save(blog);
    }

    public void deleteBlog(UUID id) {
        boolean existed = blogRepository.existsById(id);
        if (!existed) throw new EntityNotFoundException(Blog.class, id);
        blogRepository.deleteById(id);
    }

    public void publishBlog(UUID id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Blog.class, id));
        blog.publish();
        blogRepository.save(blog);
    }

    public Page<Blog> getAllBlog(BlogCriteria criteria) {
        return blogRepository.findAllWithPagination(criteria);
    }
}
