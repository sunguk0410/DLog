package com.dlog.DLog.comment;

import com.dlog.DLog.diary.Diary;
import com.dlog.DLog.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment create(Diary diary, String content, SiteUser author) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setDiary(diary);
        comment.setAuthor(author);
        this.commentRepository.save(comment);
        return comment;
    }
}
