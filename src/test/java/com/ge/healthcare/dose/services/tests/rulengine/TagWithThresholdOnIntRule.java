package com.ge.healthcare.dose.services.tests.rulengine;

import com.ge.healthcare.dose.services.ruleengine.AbstractRule;
import com.ge.healthcare.dose.services.ruleengine.Rule;
import com.ge.healthcare.dose.services.ruleengine.RuleContext;
import com.google.gson.JsonObject;

/**
 * <p>A Rule to verify a Threshold regarding an integer attribute of the JsonObject data and then mark the object with a specific tag.
 * All te is Rule metada are extracted from the RuleContext :</p>
 * <p>example of JsonObject in the RuleConext :</p>
 * <pre>{'id':1000,'field-name':'height','comparator':'greaterOrEqual','threshold':150,'tag-marker':'height-normal'}</pre>
 *
 * <p>So the attribute with <code>fieldname</code> from the JsonObject data is compared according to the set <code>comparator</code> with the
 * <code>threshold</code> value, and if true, the <code>tag-marker</code> is added to the JsonObject 'tags' attribute data.</p>
 *
 * @author Frédéric Delorme
 * @since 2019
 */
public class TagWithThresholdOnIntRule extends AbstractRule implements Rule {

    /**
     * Context Object to be retrieve to execute this rule
     */
    private JsonObject context;

    /**
     * Create a new AbstractRule with a <code>name</code>
     *
     * @param name the name of this Rule. This name will be used by the engine to identify and retrieve this Rule.
     */
    public TagWithThresholdOnIntRule(String name) {
        super(name);
    }

    /**
     * Evaluate the ThresholdOnInt rule
     *
     * @param rc  the RuleContext to help and feed evaluate the trigger
     * @param obj the object to be evaluated regarding rule context.
     * @return
     */
    @Override
    public boolean evaluate(RuleContext rc, JsonObject obj) {
        context = (JsonObject) rc.get("TagHeightOnThreshold");
        String fieldName = context.get("field-name").getAsString();
        int threshold = context.get("threshold").getAsInt();
        String comparator = context.get("comparator").getAsString();

        switch (comparator) {
            case "greater":
                return (obj.get(fieldName).getAsInt() > threshold);
            case "lower":
                return (obj.get(fieldName).getAsInt() < threshold);
            case "greaterOrEqual":
                return (obj.get(fieldName).getAsInt() >= threshold);
            case "lowerOrEqual":
                return (obj.get(fieldName).getAsInt() <= threshold);
            case "equal":
                return (obj.get(fieldName).getAsInt() == threshold);
            default:
                return false;
        }
    }

    /**
     * As this rule is valid one for JsonObject, we add the tag-marker to the JsonObject.
     *
     * @param rc  the execution RuleContext to be used as an input/output gateway.
     * @param obj the object to be processed.
     * @return
     */
    @Override
    public JsonObject process(RuleContext rc, JsonObject obj) {
        JsonObject common = (JsonObject) rc.get("common");
        String tagAttribute = common.get("tags-attribute").getAsString();
        String tag = context.get("tag-marker").getAsString();
        obj.addProperty(tagAttribute, tag);
        return obj;
    }
}
