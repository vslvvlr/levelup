package application.dao;

import application.domain.AuthDetailsEntity;
import streams.CollectionUtils;

import java.util.Collection;
import java.util.List;

public interface AuthDetailsDao {

    AuthDetailsEntity createUserPassword (String login, String password);

    AuthDetailsEntity findUserByLogin (String login);

}
