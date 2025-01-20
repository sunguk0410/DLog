package com.dlog.DLog.diary;

import com.dlog.DLog.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public List<Diary> getList() {
        return this.diaryRepository.findAll();
    }

    public Diary getDiary(Long id) {
        Optional<Diary> diary = this.diaryRepository.findById(id);

        if (diary.isPresent()) {
            return diary.get();
        } else {
            throw new DataNotFoundException("diary not found");
        }
    }

    public void create(String title, String content) {
        Diary diary = new Diary();
        diary.setTitle(title);
        diary.setContent(content);
        diary.setCreateDate(LocalDateTime.now());
        this.diaryRepository.save(diary);
    }
}
