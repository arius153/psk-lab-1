package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.LibraryUser;
import lt.vu.persistence.LibraryUserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class UserUseCase {

    @Inject
    LibraryUserDAO libraryUserDAO;

    @Getter
    @Setter
    private LibraryUser userToAdd = new LibraryUser();

    @Getter
    private List<LibraryUser> allLibrariesUsers;

    @PostConstruct
    public void init() {
        allLibrariesUsers = libraryUserDAO.findAll();
    }

    @Transactional
    public void addUser() {
        libraryUserDAO.persist(userToAdd);
    }

}
