package fr.inria.diverse.expression.visitor;

import java.util.Optional;

import com.google.inject.Inject;

import expression.IntBlock;
import expression.IntVarAssign;
import expression.util.ExpressionSwitch;
import fr.inria.diverse.visitor.expressions.CtxEvalExp;
import fr.inria.diverse.visitor.expressions.EvalOpExp;

public class EvalExpressionVisitorEvalOpExpInteger extends ExpressionSwitch<EvalOpExp<Integer>> {

	private EvalExpressionVisitorCtxEvalExpIntegerInteger evalExpressionVisitor;
	
	

	@Inject
	public EvalExpressionVisitorEvalOpExpInteger(EvalExpressionVisitorCtxEvalExpIntegerInteger evalExpressionVisitor) {
		super();
		this.evalExpressionVisitor = evalExpressionVisitor;
	}

	@Override
	public EvalOpExp<Integer> caseIntVarAssign(final IntVarAssign intVarAssign) {
		return ctx -> {
			final CtxEvalExp<Integer, Integer> doSwitch = evalExpressionVisitor.doSwitch(intVarAssign.getExpression());
			final Optional<Integer> result = doSwitch.result(ctx);
			result.ifPresent(res -> {
				final String name = intVarAssign.getName();
				ctx.put(name, res);
			});
			return result.isPresent();
		};
	}

	@Override
	public EvalOpExp<Integer> caseIntBlock(IntBlock intBlock) {
		return ctx -> !intBlock.getOperations().stream().map(operation -> this.doSwitch(operation).eval(ctx))
				.filter(x -> !x).findAny().isPresent();
	}

}
