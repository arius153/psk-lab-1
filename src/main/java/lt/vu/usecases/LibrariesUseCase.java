package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Library;
import lt.vu.entities.LibraryUser;
import lt.vu.persistence.LibraryDAO;
import lt.vu.persistence.LibraryUserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class LibrariesUseCase {

    @Inject
    LibraryDAO libraryDAO;

    @Getter
    @Setter
    private Library libraryToAdd = new Library();

    @Getter
    private List<Library> allLibraries;

    @PostConstruct
    public void init() {
        allLibraries = libraryDAO.findAll();
    }

    @Transactional
    public void addLibrary() {
        libraryDAO.persist(libraryToAdd);
    }

}
