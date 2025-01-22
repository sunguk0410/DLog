package com.dlog.DLog.comment;

import com.dlog.DLog.diary.Diary;
import com.dlog.DLog.diary.DiaryService;
import com.dlog.DLog.user.SiteUser;
import com.dlog.DLog.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final DiaryService diaryService;
    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/comment/create/{id}")
    public String createComment(Model model, @PathVariable("id") Long id, @Valid CommentForm commentForm, BindingResult bindingResult, Principal principal) {
        Diary diary = this.diaryService.getDiary(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("diary", diary);
            return "diary/detail";
        }
        this.commentService.create(diary, commentForm.getContent(), siteUser);
        return String.format("redirect:/diary/detail/%s", id);
    }
}
