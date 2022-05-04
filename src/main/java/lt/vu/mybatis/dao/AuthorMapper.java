package lt.vu.mybatis.dao;

import lt.vu.mybatis.model.Author;
import lt.vu.mybatis.model.Book;
import org.apache.ibatis.annotations.*;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface AuthorMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Author record);

    Author selectByPrimaryKey(Long id);

    List<Author> selectAll();

    int updateByPrimaryKey(Author record);

    @Select("SELECT id, description, fullname FROM AUTHOR WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "description", column = "description"),
            @Result(property = "books", column = "id", javaType = List.class, many = @Many(select = "selectBooks"))
    })
    Author getAuthorWithBooks(Long id);

    @Select("SELECT * FROM BOOK WHERE AUTHOR_ID = #{authorId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name")
    })
    List<Book> selectBooks(String authorId);
}
