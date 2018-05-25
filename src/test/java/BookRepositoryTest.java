

import com.dev.model.Book;
import com.dev.model.Language;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import com.dev.repository.BookRepository;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class BookRepositoryTest {

    @Inject
    private BookRepository bookRepo;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
    }

    @org.junit.Test
    public void create() {
        assertEquals(Long.valueOf(0), bookRepo.countAll());
        assertEquals(0, bookRepo.findAll().size());

        Book b = new Book("isbn", "title", 12F, 123, Language.ENGLISH, "description");
        bookRepo.create(b);
        Long id = b.getId();

        assertNotNull(id);

        Book bFind = bookRepo.find(id);
        assertEquals(b.getTitle(), bFind.getTitle());

        assertEquals(Long.valueOf(1), bookRepo.countAll());
        assertEquals(1, bookRepo.findAll().size());

        bookRepo.delete(id);

        assertEquals(Long.valueOf(0), bookRepo.countAll());
        assertEquals(0, bookRepo.findAll().size());
    }
}