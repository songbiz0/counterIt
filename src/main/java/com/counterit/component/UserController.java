package com.counterit.component;

import com.counterit.model.IntegerVO;
import com.counterit.model.UserEntity;
import com.counterit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public void login() {}

    @GetMapping("/join")
    public void join() {}

    @PostMapping("/join")
    public String join(UserEntity entity) {
        int result = userService.join(entity);
        return result == 1 ? "redirect:/" : "redirect:/user/join";
    }

    @GetMapping("/idChk/{uid}")
    @ResponseBody
    public IntegerVO idChk(@PathVariable String uid) {
        return new IntegerVO(userRepository.findByUid(uid) == null ? 1 : 0);
    }
}
