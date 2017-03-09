package fr.inria.diverse.visitor.expressions;

import java.util.Map;
import java.util.Optional;

public interface CtxEvalExp<C, R> {

	public Optional<R> result(Map<String, C> ctx);

}
