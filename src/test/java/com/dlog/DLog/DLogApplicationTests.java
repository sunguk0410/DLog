package com.dlog.DLog;

import com.dlog.DLog.diary.Diary;
import com.dlog.DLog.diary.DiaryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class DLogApplicationTests {

	@Autowired
	private DiaryRepository diaryRepository;

	@Test
	void textJpa() {
		Diary d1 = new Diary();
		d1.setTitle("스프링을 공부한 날");
		d1.setContent("오늘은 스프링 공부를 시작한 날이다.");
		d1.setCreateDate(LocalDateTime.now());
		this.diaryRepository.save(d1);

		Diary d2 = new Diary();
		d2.setTitle("친구들과 여행을 갔다");
		d2.setContent("정말 재밌는 하루였다.");
		d2.setCreateDate(LocalDateTime.now());
		this.diaryRepository.save(d2);
	}

}
