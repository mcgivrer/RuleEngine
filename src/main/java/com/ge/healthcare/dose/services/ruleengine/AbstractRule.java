package com.ge.healthcare.dose.services.ruleengine;

/**
 * This abstract class must be extended for any {@link Rule} implementation.
 * It provides the basics for any Rule managed by the {@link RuleProcessor}.
 *
 * @author Frédéric Delorme<frederic.delorme@ge.com>
 */
public abstract class AbstractRule implements Rule {
    private String name;

    /**
     * Create a new AbstractRule with a <code>name</code>
     *
     * @param name the name of this Rule. This name will be used by the engine to identify and retrieve this Rule.
     */
    public AbstractRule(String name) {
        this.name = name;
    }

    /**
     * return the name of this Rule.
     *
     * @return the name of this rule.
     */
    @Override
    public String getName() {
        return name;
    }
}
