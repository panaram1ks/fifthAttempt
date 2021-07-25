package parominsky.evgeny.belbanka.controllers.admin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import parominsky.evgeny.belbanka.service.UserService;

@RestController
public class CheckUserNameController {
    private UserService userService;

    @Autowired
    private CheckUserNameController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/check", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkName(@RequestBody String nameJson) {
        JsonObject jsonObject = new Gson().fromJson(nameJson, JsonObject.class);
        String name = jsonObject.get("name").getAsString();
        String result = userService.getUser(name) == null ? "ok" : "wrong";
        return ResponseEntity.ok(result);
    }
}
