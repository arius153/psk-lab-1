package lt.vu.decorators;

import lt.vu.entities.Author;
import lt.vu.persistence.interfaces.AuthorDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class AuthorDecorator implements AuthorDAO {

    @Inject
    @Delegate
    @Any
    AuthorDAO authorDAO;

    public Author findOne(Long id) {
        Author author = authorDAO.findOne(id);
        if (author.getDescription() == null || author.getDescription().isEmpty()) {
            author.setDescription("<a href='https://en.wikipedia.org/w/index.php?search=" + author.getFullName() + "'>Info about this author</a>");
        } else {
            author.setDescription(author.getDescription() +
                    "<br> <a href='https://en.wikipedia.org/w/index.php?search=" + author.getFullName() + "'>More info about this author</a>");
        }
        return author;

    }
}
