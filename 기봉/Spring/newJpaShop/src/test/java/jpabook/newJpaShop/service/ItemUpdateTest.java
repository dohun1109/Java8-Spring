package jpabook.newJpaShop.service;

import jakarta.persistence.EntityManager;
import jpabook.newJpaShop.domain.Item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateItemTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        // TX
        book.setName("asdfasdf");

        // 변경감지 == dirty checking
        // TX commit
    }
}
