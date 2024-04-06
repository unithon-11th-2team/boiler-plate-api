package com.core.api.batch.scheduler;

import com.core.api.batch.job.RankAddressUpdateJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RankScheduler {
    private final RankAddressUpdateJob rankAddressUpdateJob;

    @Scheduled(fixedRate = 600000)
    public void runItemRank() {
        log.info("item rank update start");
        rankAddressUpdateJob.rankRun();
        log.info("item rank update end");
    }
}
