package com.ge.healthcare.dose.services.ruleengine;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * A RuleEngine to process RuleProcess and Rules.
 *
 * @author Frédéric Delorme<frederic.delorme@gmail.com>
 */
@Slf4j
public class RuleEngine {

    /**
     * Internal ThreadPool executor to process any Rules on  any data in a distributed and parallel way.
     */
    private ThreadPoolExecutor pool;

    /**
     * Data Store manager .
     */
    private OutputDataWriter dataWriter;

    /**
     * Create the RuleEngine with a pool of executor size of <code>maxThreadPoolSize</code>.
     *
     * @param maxThreadPoolSize the size of the pool executor to process the Rules.
     */
    public RuleEngine(int maxThreadPoolSize) {
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(maxThreadPoolSize);
        log.debug("nw RuleEngine initialize with a pool of {} slots", maxThreadPoolSize);
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
            log.debug("RuleProcessor Added to the thread pool");
        } else {
            log.error("Unable to add the RuleProcessor to the thread pool, no more slot !");
            throw new NoMoreExecutorPoolSlotException();
        }
    }

    /**
     * Define the OutputDataWriter to be used by te RuleEngine instance.
     *
     * @param writer
     */
    public void setOutputDataWrite(OutputDataWriter writer) {
        dataWriter = writer;
    }

    /**
     * Retrieve the OutputDataWriter for this RuleEngine instance.
     *
     * @return
     */
    public OutputDataWriter getDataWriter() {
        return dataWriter;
    }
}
