package src.main.java.com.dev.repository;

import src.main.java.com.dev.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;

public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager em;

    public Book find (Long id) {
        return em.find(Book.class, id);
    }

    public Book create (Book book) {
        em.persist(book);
        return book;
    }

    public void delete (Long id) {
        em.remove(em.getReference(Book.class, id));
    }

    public List<Book> findAll () {
        TypedQuery<Book> query = em.createQuery("Select b FROM Book b ORDER BY b.title DESC ", Book.class);
        return query.getResultList();
    }


    public Long countAll() {
        TypedQuery<Long> query = em.createQuery("Select count(b) FROM Book b", Long.class);
        return  query.getSingleResult();
    }
}
