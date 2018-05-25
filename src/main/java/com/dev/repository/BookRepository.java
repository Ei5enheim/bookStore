package com.dev.repository;

import com.dev.model.Book;
import com.dev.util.NumGenerator;
import com.dev.util.TextUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Transactional(SUPPORTS)
public class BookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager em;

    @Inject
    private TextUtil txtUtil;

    @Inject
    private NumGenerator gen;

    public Book find (@NotNull Long id) {
        return em.find(Book.class, id);
    }

    @Transactional(REQUIRED)
    public Book create (@NotNull Book book) {
        book.setTitle(txtUtil.sanitize(book.getTitle()));
        book.setIsbn(gen.genNumber());
        em.persist(book);

        return book;
    }

    @Transactional(REQUIRED)
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
