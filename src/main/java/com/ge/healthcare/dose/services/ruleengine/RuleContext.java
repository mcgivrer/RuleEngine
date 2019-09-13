package com.ge.healthcare.dose.services.ruleengine;

import java.util.HashMap;

/**
 * This map contains any element that can contribute to Rules Evaluation.
 * This RuleContext object will be pass through all rules to input/output contextual information.
 *
 * @param <K> must be the RuleContextKey (mainly String)
 * @param <V> must be the RuleContext va:lue type to be stored (mainly JSONObject).
 */
public class RuleContext<K, V> extends HashMap<K, V> {
}
