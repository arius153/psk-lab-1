package lt.vu.usecases;

import lt.vu.entities.Author;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Specializes;
import javax.transaction.Transactional;
import java.util.List;

@Specializes
@Model
public class SpecializedAuthorUseCase extends AuthorUseCase{

    @Override
    public List<Author> getAllAuthors() {
        System.out.println("\n\n Here is the specialized Author use case! \n\n");
        return super.getAllAuthors();
    }

}
