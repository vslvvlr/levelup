package application;

import application.dao.*;
import application.domain.AuthDetailsEntity;
import application.domain.UserEntity;
import hibernate.JobSessionFactoryConfiguration;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JobApplication {
    public static void main(String[] args) throws IOException {
        SessionFactory factory = new JobSessionFactoryConfiguration().configure();

        /*CompanyDao companyDao = new CompanyDaoImpl(factory);
        CompanyLegalDetailsDaoImpl legalDetailsDao = new CompanyLegalDetailsDaoImpl(factory);

        companyDao.create("BP", "112233", "MSK");
        Integer companyId = companyDao.findByEin("112233").getId();

        UserDao userDao = new UserDaoImpl(factory);
        UserEntity user = userDao.createUser("Bob", "Green", "777778", new ArrayList<>(Arrays.asList(
                "address 1",
                "address 2",
                "address 3"
        )));

        PositionDao positionDao = new PositionDaoImpl(factory);
        Integer positionId = positionDao.createPosition("PM").getId();

        JobListDao jobListDao = new JobListDaoImpl(factory);
        jobListDao.createJobRecord(companyId, user.getId(), positionId, LocalDate.of(2019, 12, 4), null);

        JobListEntity jobRecord = jobListDao.findJobRecord(companyId, user.getId(), positionId);

        System.out.println(jobRecord.getCompany());
        System.out.println(jobRecord.getPosition());
        System.out.println(jobRecord.getUser());
        System.out.println(jobRecord.getStartDate());
        System.out.println(jobRecord.getEndDate());*/

        AuthDetailsDao userPasswordDao = new AuthDetailsDaoImpl(factory);
        //userPasswordDao.createUserPassword("pug", "feedthepug");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите логин");
        String loginFromUser = reader.readLine();
        System.out.println("Введите пароль");
        String PasswordFromUser = reader.readLine();

        try {
            String passwordFromDB = userPasswordDao.findUserByLogin(loginFromUser).getPassword();
            if (PasswordFromUser.equals(passwordFromDB)) {
                System.out.println("Авторизация прошла успешно");
                Integer id = userPasswordDao.findUserByLogin(loginFromUser).getId(); // получение id из табл auth_details
                UserDao userDao = new UserDaoImpl(factory);
                UserEntity userEntity = userDao.findUserById(id); //поиск в по id в табл users
                System.out.println("Добрый день, " + userEntity.getName() + " " + userEntity.getLastName() + "!");
            } else throw new NoResultException();
        } catch (NoResultException e) {
            System.out.println("Введен неверный логин/пароль. Попроуйте еще раз");
        }

        factory.close();
    }
}
