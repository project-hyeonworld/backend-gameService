package io.gameservice.api.submission.domain.dto.out;

import io.gameservice.api.submission.infrastructure.entity.Submission;
import io.gameservice.common.mapper.ObjectrMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
@Getter
@NoArgsConstructor
public class MiniSubmissionInfo {

    private long userId;
    private String name;
    private String text;
    private long number;

    private MiniSubmissionInfo(long userId, String userName, String text, Long number) {
        this.userId = userId;
        this.name = userName;
        this.text = text;
        this.number = number;
    }

    public static MiniSubmissionInfo from(Submission submission) {
        return ObjectrMapper.convert(submission, MiniSubmissionInfo.class);
    }

    public static MiniSubmissionInfo from(long userId, String userName, String text, Long number) {
        return new MiniSubmissionInfo(userId, userName, text, number);
    }

    public void complete(String userName) {
        this.name = userName;
    }
}
