import com.javakc.ssm.user.entity.User;
import com.javakc.ssm.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring_ioc_config.xml")
public class CrudJunit {

    @Autowired
    private UserService userService;


    @Test
    @Transactional
    public void insert(){
        User user = new User();
        user.setId(1);
        user.setAddress("北京海淀区");
        user.setName("W程成W");
        user.setBirthday(new Date());
        user.setGender(2);
        userService.insert(user);
    }
//    @Test
//    public void insert(){
//        User user = new User();
//        user.setId(1);
//        user.setAddress("北京海淀区");
//        user.setName("麒玖W");
//        user.setBirthday(new Date());
//        user.setGender(0);
//        userService.update(user);
//    }
}
