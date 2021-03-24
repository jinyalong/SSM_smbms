import com.codefriday.pojo.Bill;
import com.codefriday.pojo.Role;
import com.codefriday.pojo.User;
import com.codefriday.service.bill.BillService;
import com.codefriday.service.bill.BillServiceImpl;
import com.codefriday.service.role.RoleService;
import com.codefriday.service.role.RoleServiceImpl;
import com.codefriday.service.user.UserService;
import com.codefriday.service.user.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.Date;



public class Test {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BillService billServiceImpl = context.getBean("BillServiceImpl", BillServiceImpl.class);
//        List<User> userList = userServiceImpl.getUserList(null, 0, 1, 50);
//        for (User user : userList) {
//            user.setBirthday("2000-2-25");
//            System.out.println(user);
//        }
        System.out.println(billServiceImpl.getBillCountByProviderId("1"));
    }
}
