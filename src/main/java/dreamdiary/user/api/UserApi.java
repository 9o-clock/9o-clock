package dreamdiary.user.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dreamdiary.user.app.IUserService;
import dreamdiary.user.app.UserValidator;
import dreamdiary.user.domain.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(UserApi.PATH)
@RequiredArgsConstructor
public class UserApi {
    public static final String PATH = "user";

    private final IUserService userService;
    private final UserValidator userValidator;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @PostMapping
    public String register(@Validated @RequestBody UserDto userDto, BindingResult bindingResult) throws JsonProcessingException {
        log.info("[USER] 회원가입 요청");
        
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getAllErrors().get(0).toString());
        }

        UserDto result = userService.register(userDto);

        return objectMapper.writeValueAsString(result);
    }

}
