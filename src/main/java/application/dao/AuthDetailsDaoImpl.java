package application.dao;

import application.domain.AuthDetailsEntity;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;

public class AuthDetailsDaoImpl extends AbstractDao implements AuthDetailsDao {
    public AuthDetailsDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public AuthDetailsEntity createUserPassword(String login, String password) {
        return runInTransaction(session -> {
            AuthDetailsEntity userPassword = new AuthDetailsEntity();
            userPassword.setLogin(login);
            userPassword.setPassword(password);
            session.persist(userPassword);
            return userPassword;
        });
    }

    @Override
    public AuthDetailsEntity findUserByLogin(String login) {
        return  runWithoutTransaction(session -> session.createQuery("from AuthDetailsEntity where login =: login", AuthDetailsEntity.class )
                .setParameter("login", login)
                .getSingleResult());
    }




    /*Про метод get\load.
    Тот что помечено @Id или @Embeded id*/

}
