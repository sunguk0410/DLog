package com.dlog.DLog.diary;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/diary")
    public String index(Model model) {
        List<Diary> diaryList = this.diaryService.getList();
        model.addAttribute("diaryList", diaryList);
        return "diary/index";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/diary";
    }

    @GetMapping("/diary/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Diary diary = this.diaryService.getDiary(id);
        model.addAttribute("diary", diary);
        return "diary/detail";
    }

    @GetMapping("/diary/create")
    public String createDiary() {
        return "diary/form";
    }

    @PostMapping("/diary/create")
    public String createDiary(@Valid DiaryForm diaryForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "diary/form";
        }
        this.diaryService.create(diaryForm.getTitle(), diaryForm.getContent());
        return "redirect:/diary";
    }
}
