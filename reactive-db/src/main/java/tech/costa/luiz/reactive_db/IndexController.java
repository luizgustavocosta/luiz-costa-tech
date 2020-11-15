package tech.costa.luiz.reactive_db;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Index controller.
 */
@Controller
public class IndexController {

    /**
     * Index string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
