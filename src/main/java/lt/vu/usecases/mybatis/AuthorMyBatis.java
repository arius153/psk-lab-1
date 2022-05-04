package lt.vu.usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.AuthorMapper;
import lt.vu.mybatis.model.Author;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class AuthorMyBatis {

    @Inject
    private AuthorMapper authorMapper;

    @Getter
    private List<Author> allAuthors;

    @Getter @Setter
    private Author authorToCreate = new Author();

    @PostConstruct
    public void init() {
        allAuthors = authorMapper.selectAll();
    }

    @Transactional
    public String createAuthor() {
        authorMapper.insert(authorToCreate);
        return "/mybatis/authors?faces-redirect=true";
    }


}
