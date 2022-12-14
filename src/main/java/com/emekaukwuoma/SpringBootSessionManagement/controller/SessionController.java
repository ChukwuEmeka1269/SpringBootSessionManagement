package com.emekaukwuoma.SpringBootSessionManagement.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class SessionController {

    @PostMapping("/addNote")
    public String addNote(@RequestParam("note") String note, HttpServletRequest request){
        List<String> note_session = (List<String>) request.getSession().getAttribute("NOTE_SESSION");
        if(Objects.isNull(note_session)){
            note_session = new ArrayList<>();
            setSessionAttritube(request, note_session);
        }
        note_session.add(note);
        setSessionAttritube(request, note_session);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session){
        List<String> notes = (List<String>) session.getAttribute("NOTE_SESSION");
        model.addAttribute("noteSession", Objects.nonNull(notes) ? notes:new ArrayList<>());
        return "home";
    }

    @PostMapping("/invalidate/session")
    public String destroySession(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/home";
    }

    private static void setSessionAttritube(HttpServletRequest request, List<String> note_session) {
        request.getSession().setAttribute("NOTE_SESSION", note_session);
    }
}
