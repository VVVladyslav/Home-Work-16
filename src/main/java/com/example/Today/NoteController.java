package com.example.Today;


import com.example.Today.CRUD.NoteService;
import com.example.Today.Entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/note")
//@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("/list")
    public String showNoteList(Model model) {
        List<Note> notes = noteService.listAll();
        model.addAttribute("notes", notes);
        return "note-list";
    }

    @PostMapping("/delete")
    public String deleteNoteById(@RequestParam("id") Long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String showEditNoteForm(@RequestParam("id") Long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "edit-note";
    }

    @PostMapping("/edit")
    public String editNote(@ModelAttribute("note") Note note) {
        noteService.update(note);
        return "redirect:/note/list";
    }
}