package lt.vu.services;

import lt.vu.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class RandomBookPicker implements Serializable {

    public Book getRandomBookId(List<Book> allBooks) {
        try {
            Thread.sleep(1000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Random r = new Random();
        return allBooks.get(r.nextInt(allBooks.size()));
    }
}
