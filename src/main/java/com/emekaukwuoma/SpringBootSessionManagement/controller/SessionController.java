package com.emekaukwuoma.SpringBootSessionManagement.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
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

    private static void setSessionAttritube(HttpServletRequest request, List<String> note_session) {
        request.getSession().setAttribute("NOTE_SESSION", note_session);
    }
}
