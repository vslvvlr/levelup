package application.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class AbstractDao {

    protected final SessionFactory factory;

    protected <T> T runInTransaction(Function<Session, T> function) {
        try(Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            T result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    protected void runInTransaction(Consumer<Session> consumer) {
        try (Session session = factory.openSession()) {
            Transaction  transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        }
    }

    protected <T> T runWithoutTransaction(Function<Session, T> function) {  //если нужно только читать
        try (Session session = factory.openSession()) {
            return function.apply(session);
        }
    }

    // ACID
    // A - Atomicity
    // C - Сonsistency (на основе ограничений и связей кот были до начала - д.б. согласованы)
    // I - Isolation
    // D - Durability (если транзкация успешно завершена, данные 100% попадут в базу, даже если сразу после все упало WAL - write ahead log)
    // CAS - compare & swap

}
