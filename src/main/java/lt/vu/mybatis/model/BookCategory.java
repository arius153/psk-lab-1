package lt.vu.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookCategory {

    private Long id;

    private String category;

    private List<Book> books;
}