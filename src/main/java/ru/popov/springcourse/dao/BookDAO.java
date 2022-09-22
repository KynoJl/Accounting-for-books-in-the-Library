package ru.popov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.popov.springcourse.model.Book;
import ru.popov.springcourse.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
       return jdbcTemplate.query("SELECT * FROM Book",new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(name,author,age)VALUES (?,?,?)",book.getName(),book.getAuthor(),book.getAge());
    }
    public void update(int id,Book updateBook){
        jdbcTemplate.update("UPDATE Book SET name=?,author=?,age=? WHERE id=?",updateBook.getName(),updateBook.getAuthor(),updateBook.getAge(),id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE * FROM Book WHERE id=?",id);
    }

    // Получаем человека которому принадлежат книги с указанным id
    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id= Person.id WHERE Book.id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    // Освобождаем книгу с конкретным id
    public void release(int id) {
       jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE id = ?",id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE id = ?",selectedPerson.getId(),id );
    }
}
