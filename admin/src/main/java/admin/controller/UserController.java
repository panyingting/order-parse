package admin.controller;

import com.jiao.order.parse.entity.User;
import com.jiao.order.parse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public User add(User user){

        return userService.save(user);
    }

    @RequestMapping("/add/{username}")
    public User add(@PathVariable String username){
        User user = new User();
        user.setAge((int)(Math.random()*100));
        user.setUsername(username);
        user.setDesc("描述信息：滴滴答答");
        return userService.save(user);
    }

    @RequestMapping("/findAll")
    public List<User> find(){

        return userService.findAll();
    }
}
