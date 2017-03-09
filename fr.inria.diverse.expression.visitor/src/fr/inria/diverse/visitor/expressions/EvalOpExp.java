package fr.inria.diverse.visitor.expressions;

import java.util.Map;

public interface EvalOpExp<T> {
	boolean eval(Map<String, T> ctx);
}
