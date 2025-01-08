package io.gameservice.api.submission.domain;

import io.gameservice.api.submission.domain.dto.in.SubmissionCommand;
import io.gameservice.api.submission.domain.dto.out.FullSubmissionInfo;
import io.gameservice.api.submission.domain.dto.out.MiniSubmissionInfos;
import io.gameservice.api.submission.infrastructure.SubmissionRepository;
import io.gameservice.common.exception.ServerException;
import io.gameservice.common.exception.dto.ServerResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public FullSubmissionInfo findById(long partyId, long roundCount, long userId, int submissionCount) {
        return FullSubmissionInfo.from(submissionRepository.findById(partyId, roundCount, userId, submissionCount)
                .orElseThrow(() -> new ServerException(ServerResponseCode.SUBMISSION_NOT_FOUND)));
    }

    @CacheEvict(cacheNames = "submissionInfo", key = "#command.userId")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FullSubmissionInfo handFromParticipants(SubmissionCommand command, long roundId) {
        return FullSubmissionInfo.from(command.partyId(),
                submissionRepository.save(FullSubmissionInfo.toEntity(command, roundId)));
    }

    @Transactional
    public MiniSubmissionInfos check(long partyId, long roundCount) {
        return MiniSubmissionInfos.from(submissionRepository.findLastestByPartyIdAndRoundCount(partyId, roundCount));
    }

    @Transactional
    public MiniSubmissionInfos check(long partyId) {
        return MiniSubmissionInfos.from(submissionRepository.findLatestByPartyId(partyId));
    }

    @Cacheable(cacheNames = "submissionInfo", key = "#userId")
    public FullSubmissionInfo findLatestByUserId(long userId) {
        return FullSubmissionInfo.from(submissionRepository.findLastestByUserId(userId)
                .orElseThrow(() -> new ServerException(ServerResponseCode.SUBMISSION_NOT_FOUND)));
    }


}
