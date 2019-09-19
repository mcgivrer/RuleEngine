package com.ge.healthcare.dose.services.ruleengine;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * A RuleEngine to process RuleProcess and Rules.
 *
 * @author Frédéric Delorme<frederic.delorme@gmail.com>
 */
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
