package com.core.api.batch.scheduler;

import com.core.api.batch.job.ItemTypeUpdateJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemScheduler {
    private final ItemTypeUpdateJob itemTypeUpdateJob;

    /**
     * 데이터 보정용
     */
    @Scheduled(fixedRate = 60000)
    public void runItemUpgrade() {
        log.info("item type upgrade start");
        itemTypeUpdateJob.run();
        log.info("item type upgrade end");
    }

    @Scheduled(fixedRate = 600000)
    public void runItemRank() {
        log.info("item rank update start");
        itemTypeUpdateJob.rankRun();
        log.info("item rank update end");
    }
}
