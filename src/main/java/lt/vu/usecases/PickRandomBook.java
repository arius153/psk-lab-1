package lt.vu.usecases;

import lt.vu.entities.Book;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.BookDAO;
import lt.vu.services.RandomBookPicker;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class PickRandomBook implements Serializable {

    @Inject
    RandomBookPicker randomBookPicker;

    @Inject
    BookDAO bookDAO;

    private CompletableFuture<Book> pickerTask = null;

    @LoggedInvocation
    public String pickRandomBook() {
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        List<Book> allBooks = bookDAO.findAll();
        pickerTask = CompletableFuture.supplyAsync(() -> randomBookPicker.getRandomBookId(allBooks));

        return "books?faces-redirect=true";
    }

    public boolean isRunning() {
        return pickerTask != null && !pickerTask.isDone();
    }

    public String getBookLink() throws ExecutionException, InterruptedException {
        if (pickerTask == null) {
            return null;
        }
        if (isRunning()) {
            return "Fetching data!";
        }
        Book pickedBook = pickerTask.get();

        return "<a href='bookDetailed.xhtml?bookId="+ pickedBook.getId() +"'>" + pickedBook.getName() + " | " + pickedBook.getAuthor().getFullName() + "'  </a>";
    }

}
