package io.gameservice.api.game.strategy;

import io.gameservice.api.answerSubmission.domain.AnswerSubmissionService;
import io.gameservice.api.answerSubmission.domain.out.AnswerSubmissionInfos;
import io.gameservice.api.game.client.user.UserClient;
import io.gameservice.api.game.domain.dto.in.CheckConfirmCommand;
import io.gameservice.api.game.domain.dto.out.check.SubmissionInfo;
import io.gameservice.api.submission.domain.SubmissionService;
import io.gameservice.api.submission.domain.dto.out.FullSubmissionInfo;
import io.gameservice.api.submission.domain.dto.out.MiniSubmissionInfo;
import io.gameservice.api.submission.domain.dto.out.MiniSubmissionInfos;
import io.gameservice.common.annotation.Strategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
@Strategy(0)
@RequiredArgsConstructor
public class TruthOrFalseStrategy implements GameStrategy<Long, SubmissionInfo> {

    private final String HTML_LINE_SEPARATOR = "<br />";
    //private final RoundService roundService;
    private final SubmissionService submissionService;
    private final AnswerSubmissionService answerSubmissionService;
    private final UserClient userClient;

    @Override
    public long getGameId() {
        return 0L;
    }

    @Override
    public long checkConfirm(CheckConfirmCommand command) {
        return 1L;
    }

    @Override
    public long updateTargetId(long partyId, long roundCount, long targetId) {
        return 1L;
    }

    @Override
    public void tutorialStage() {

    }

    private String formatContentText(MiniSubmissionInfo info) {
        StringBuilder formattedText = new StringBuilder();
        formattedText.append(info.getName()).append("님의 진실 혹은 거짓 :");
        formattedText.append(HTML_LINE_SEPARATOR);
        String[] statements = info.getText().split(",");
        for (int i = 0; i < statements.length; i++) {
            formattedText.append(i + 1)
                    .append(". ")
                    .append(statements[i].trim())
                    .append(HTML_LINE_SEPARATOR);
        }
        return formattedText.toString();
    }

    @Override
    public String showStage(long partyId, long roundCount, long targetId) {
        FullSubmissionInfo submissionInfo = submissionService.findLatestByUserId(targetId);
        String userName = userClient.getNameById(targetId);
        return formatContentText(submissionInfo.patchUserName(userName));
    }

    @Override
    public Long playStage(long roundCount, long userId, String answer) {
        answerSubmissionService.submitAnswer(roundCount,
                userId, answer);
        return Long.parseLong(answer);
    }

    @Override
    public SubmissionInfo checkStage(long partyId, long roundCount) {
        MiniSubmissionInfos miniSubmissionInfos = submissionService.check(partyId);
        for (MiniSubmissionInfo roundSubmissionInfo : miniSubmissionInfos) {
            String userName = userClient.getNameById(roundSubmissionInfo.getUserId());
            roundSubmissionInfo.complete(userName);
        }
        return SubmissionInfo.from(miniSubmissionInfos);
    }

    @Override
    public String getAnswer(long partyId, long roundCount, long targetId) {
        FullSubmissionInfo submissionInfo = submissionService.findLatestByUserId(targetId);
        return String.valueOf(submissionInfo.getNumber());
    }

    @Override
    public Map<Long, String> resultStage(String answer, long partyId, long roundCount) {
        AnswerSubmissionInfos answerSubmissionInfos = answerSubmissionService.findByAnswer(roundCount, answer);

        List<Long> winnerIds = answerSubmissionInfos.getUserIds();

        HashMap<Long, String> usersIdName = new HashMap<>();
        for (long id : winnerIds) {
            String name = userClient.getNameById(id);
            usersIdName.put(id, name);
        }
        return usersIdName;
    }
}
