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
        final UserDao userDao = new UserDao();
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
}
