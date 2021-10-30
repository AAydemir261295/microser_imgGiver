package qk.mygroup.work.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qk.mygroup.work.Engine.LinkOperations;

import java.io.IOException;

@Controller
public class MainController {

    @GetMapping("/get")
    public String getHomePage(){

        return "home";
    }

    @PostMapping("/get")
    public String getImg(@RequestParam String img, Model model) {

        LinkOperations linkOperations = new LinkOperations(img);
        String imgSrc = linkOperations.getImgSrc();
        model.addAttribute("image", imgSrc);

        return "imgSrc";

    }
}
