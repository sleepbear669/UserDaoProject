package gom.cave.sleep;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by sleepbear on 2016. 3. 25..
 */
public class UserDaoTest {
    @Test
    public void testUserGet() throws Exception {
        // Given
        final UserDao userDao = new DaoFactory().getUserDao();
        final long id = 1L;
        final String name = "kim";
        final String password= "gom";

        // When
        User user =userDao.get(id);

        // Then
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
        final UserDao userDao = new DaoFactory().getUserDao();

        // When
        Long resultId = userDao.add(user);
        final User resultUser = userDao.get(resultId);
        // Then
        assertThat(resultUser.getId(), is(resultId));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }


}
