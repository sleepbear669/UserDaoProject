package gom.cave.sleep;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Created by sleepbear on 2016. 3. 25..
 */
public class UserDaoTest {

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("DaoFactory.xml");
        userDao = (UserDao) context.getBean("userDao");
//        userDao = new DaoFactory().getUserDao();
    }

    @Test
    public void testUserGet() throws Exception {
        // Given
        final long id = 1L;
        final String name = "kim";
        final String password= "gom";

        // When
        User user = userDao.get(id);

        // Then
        resultUserMatchCheck(id, name, password, user);

    }

    private void resultUserMatchCheck(long id, String name, String password, User user) {
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testUserAdd() throws Exception {
        // Given
        final String name = "kr";
        final String password = "1234";
        final User user = new User();
        user.setName(name);
        user.setPassword(password);

        // When
        Long resultId = userDao.add(user);
        final User resultUser = userDao.get(resultId);
        // Then
        resultUserMatchCheck(resultId, name, password, resultUser);
    }

    @Test
    public void testUserDelete() throws Exception {
        // Given
        final String name = "kr";
        final String password = "1234";
        final User user = new User();
        user.setName(name);
        user.setPassword(password);

        // When
        Long resultId = userDao.add(user);
        final User resultUser = userDao.get(resultId);
        userDao.delete(resultId);
        final User deletedUser = userDao.get(resultId);

        assertThat(deletedUser, nullValue());
    }

    @Test
    public void testUserUpdate() throws Exception {
        // Given
        String name = "kr";
        String password = "1234";
        final User user = new User();
        user.setName(name);
        user.setPassword(password);

        // When
        Long resultId = userDao.add(user);
        final User resultUser = userDao.get(resultId);

        name = "roo";
        password = "0234";
        resultUser.setName(name);
        resultUser.setPassword(password);

        userDao.update(resultUser);
        final User updatedUser = userDao.get(resultId);

        // Then
        resultUserMatchCheck(resultId, name, password, updatedUser);
    }
}
