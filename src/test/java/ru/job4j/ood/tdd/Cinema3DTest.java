package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyOnZeroColumnThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, 1, 0, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyTicketWithNullDateThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        assertThatThrownBy(() -> cinema.buy(account, 1, 0, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyTicketYesterdayThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, -1);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyTicketWithoutAccountThenException() {
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(null, 1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenBuyOneTicketTwiceThenException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void whenBuyOneTicketByDifferentAccountThenException() {
        Account firstAccount = new AccountCinema();
        Account secondAccount = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(firstAccount, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(secondAccount, 1, 1, date))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void whenFindFilterFalseThenSessionListIsEmpty() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(date -> false);
        assertThat(sessions).isEmpty();
    }

    @Test
    public void whenAddNullSessionThenException() {
        Cinema cinema = new Cinema3D();
        assertThatThrownBy(() -> cinema.add(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
