package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Book TheBookThief = new Book(1, "Книжный вор", 300, "Маркус Зусак", 300, 2005);
    private Book NightInLisbon = new Book(2, "Ночь в Лиссабоне", 350, "Эрих Мария Ремарк", 250, 1962);
    private Book TimeToLiveAndTimeToDie = new Book(3, "Время жить и время умирать", 350, "Эрих Мария Ремарк", 400, 1954);
    private TShirt Polo = new TShirt(4, "Polo", 1000, "green", "46");
    private Product ASICS = new Product(5, "Кроссовки ASICS", 5000);

    @BeforeEach
    public void setUp() {
        repository.save(TheBookThief);
        repository.save(NightInLisbon);
        repository.save(TimeToLiveAndTimeToDie);
        repository.save(Polo);
        repository.save(ASICS);
    }

    @Test
    void shouldRemoveById() {
        int idToRemove = 4;
        repository.removeById(idToRemove);

        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{TheBookThief, NightInLisbon, TimeToLiveAndTimeToDie, ASICS};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowAnException() {
        assertThrows(NotFoundException.class, () -> repository.removeById(10));
    }
}