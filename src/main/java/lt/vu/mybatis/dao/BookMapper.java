package lt.vu.mybatis.dao;

import lt.vu.mybatis.model.Author;
import lt.vu.mybatis.model.Book;
import org.apache.ibatis.annotations.*;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    Book selectByPrimaryKey(Long id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);

    List<Book> getAllBooksWithAuthors();


    @Select("SELECT * FROM BOOK")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "author", column = "author_id", one=@One(select = "selectAuthor"))
    })
    List<Book> findAll();

    @Select("SELECT * FROM BOOK WHERE id = #{id}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "description", column = "description"),
            @Result(property = "name", column = "name"),
            @Result(property = "author", column = "author_id", one=@One(select = "selectAuthor"))
    })
    Book getBookWithAuthor(Long id);

    @Select("SELECT * FROM AUTHOR WHERE ID = #{authorId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "description", column = "description"),
            @Result(property = "fullname", column = "fullname")
    })
    Author selectAuthor(String authorId);
}
