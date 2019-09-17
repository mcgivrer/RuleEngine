package com.ge.healthcare.dose.services.ruleengine;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * A RuleEngine to process RuleProcess and Rules.
 *
 * @author Frédéric Delorme<frederic.delorme@gmail.com>
 */
public class RuleEngine{

    private ThreadPoolExecutor pool;

    private OutputDataWriter dataWriter;

    public RuleEngine(int maxThreadPoolSize) {
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxThreadPoolSize);
    }

    /**
     * Add a Rule processor
     *
     * @param rp
     * @throws NoMoreExecutorPoolSlotException
     */
    public void addRuleProcessor(RuleProcessor rp) throws NoMoreExecutorPoolSlotException {
        if (!pool.isTerminating() || !pool.isTerminated() || !pool.isShutdown()) {
            rp.setDataWriter(dataWriter);
            pool.execute(rp);
        } else {
            throw new NoMoreExecutorPoolSlotException();
        }
    }

    public void setOutputDataWrite(OutputDataWriter writer) {
        dataWriter = writer;
    }

    public OutputDataWriter getDataWriter() {
        return dataWriter;
    }
}
