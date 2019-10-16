package study.huhao.demo.infrastructure.persistence.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.huhao.demo.domain.contexts.blogcontext.blog.Blog;
import study.huhao.demo.domain.contexts.blogcontext.blog.BlogCriteria;
import study.huhao.demo.domain.contexts.blogcontext.blog.BlogRepository;
import study.huhao.demo.domain.core.common.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;


@Component
public class BlogRepositoryImpl implements BlogRepository {

    private final BlogMapper blogMapper;

    @Autowired
    public BlogRepositoryImpl(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    @Override
    public void save(Blog blog) {
        BlogPO blogPO = BlogPO.of(blog);

        Optional<BlogPO> existedBlog = blogMapper.findById(blog.getId().toString());
        if (existedBlog.isPresent()) {
            blogMapper.update(blogPO);
        } else {
            blogMapper.insert(blogPO);
        }

    }

    @Override
    public Optional<Blog> findById(UUID id) {
        return blogMapper.findById(id.toString()).map(BlogPO::toDomainModel);
    }

    @Override
    public boolean existsById(UUID id) {
        return blogMapper.existsById(id.toString());
    }

    @Override
    public void deleteById(UUID id) {
        blogMapper.deleteById(id.toString());
    }

    @Override
    public Page<Blog> findAllWithPagination(BlogCriteria criteria) {
        long total = blogMapper.countTotalByCriteria(criteria);

        List<Blog> pagedBlog = blogMapper.selectAllByCriteria(criteria).stream().map(BlogPO::toDomainModel).collect(toList());

        return new Page<>(
                pagedBlog,
                criteria.getLimit(),
                criteria.getOffset(),
                total
        );
    }
}

