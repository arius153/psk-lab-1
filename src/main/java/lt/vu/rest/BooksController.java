package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Author;
import lt.vu.entities.Book;
import lt.vu.persistence.AuthorDAO;
import lt.vu.persistence.BookDAO;
import lt.vu.rest.models.BookDto;
import lt.vu.rest.models.BookListViewDto;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/books")
public class BooksController {

    @Inject
    @Getter
    @Setter
    private BookDAO bookDAO;

    @Inject
    @Getter
    @Setter
    private AuthorDAO authorDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        List<BookListViewDto> books = bookDAO.findAll()
                .stream()
                .map(BookListViewDto::new)
                .collect(Collectors.toList());
        return Response.ok(books).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Book book = bookDAO.findOne(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new BookDto(book)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addBook(BookDto bookDto) {
        if (bookDto.getName() == null || bookDto.getDescription() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Author author = new Author();
        author.setFullName(bookDto.getAuthorName());
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setDescription(bookDto.getDescription());
        bookDAO.persist(book);
        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") Long id, BookDto bookDto) {
        if (bookDto.getName() == null || bookDto.getDescription() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            Book existingBook = bookDAO.findOne(id);
            if (existingBook == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingBook.setName(bookDto.getName());
            existingBook.setDescription(bookDto.getDescription());
            existingBook.setVersion(bookDto.getVersion());
            bookDAO.update(existingBook);
            return Response.ok().build();
        } catch (Exception exception) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
