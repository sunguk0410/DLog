package com.dlog.DLog.comment;

import com.dlog.DLog.diary.Diary;
import com.dlog.DLog.diary.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final DiaryService diaryService;
    private final CommentService commentService;

    @PostMapping("/comment/create/{id}")
    public String createComment(Model model, @PathVariable("id") Long id, @RequestParam(value = "content") String content) {
        Diary diary = this.diaryService.getDiary(id);
        this.commentService.create(diary, content);
        return String.format("redirect:/diary/detail/%s", id);
    }
}
