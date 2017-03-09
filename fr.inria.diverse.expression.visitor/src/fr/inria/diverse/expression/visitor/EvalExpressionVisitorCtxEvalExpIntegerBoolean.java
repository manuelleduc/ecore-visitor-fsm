package fr.inria.diverse.expression.visitor;

import java.util.Optional;

import com.google.inject.Inject;

import expression.BooleanAnd;
import expression.BooleanEqual;
import expression.BooleanGreaterThan;
import expression.BooleanOr;
import expression.IntExpression;
import expression.util.ExpressionSwitch;
import fr.inria.diverse.visitor.expressions.CtxEvalExp;

public class EvalExpressionVisitorCtxEvalExpIntegerBoolean extends ExpressionSwitch<CtxEvalExp<Integer, Boolean>> {

	private EvalExpressionVisitorCtxEvalExpIntegerInteger eval;

	@Inject
	public EvalExpressionVisitorCtxEvalExpIntegerBoolean(EvalExpressionVisitorCtxEvalExpIntegerInteger eval) {
		super();
		this.eval = eval;
	}

	@Override
	public CtxEvalExp<Integer, Boolean> caseBooleanEqual(BooleanEqual booleanEqual) {
		return ctx -> {
			IntExpression expression1 = booleanEqual.getExpression1();
			CtxEvalExp<Integer, Integer> doSwitch = eval.doSwitch(expression1);
			Optional<Integer> result = doSwitch.result(ctx);
			return result.flatMap(e1 -> {
				IntExpression expression2 = booleanEqual.getExpression2();
				CtxEvalExp<Integer, Integer> doSwitch2 = eval.doSwitch(expression2);
				Optional<Integer> result2 = doSwitch2.result(ctx);
				return result2.map(e2 -> e1.equals(e2));
			});
		};
	}

	@Override
	public CtxEvalExp<Integer, Boolean> caseBooleanOr(BooleanOr booleanOr) {
		return ctx -> this.doSwitch(booleanOr.getBoolExpression1()).result(ctx)
				.flatMap(e1 -> this.doSwitch(booleanOr.getBoolExpression2()).result(ctx).map(e2 -> e1 || e2));
	}

	@Override
	public CtxEvalExp<Integer, Boolean> caseBooleanAnd(BooleanAnd booleanAnd) {
		return ctx -> this.doSwitch(booleanAnd.getBoolExpression1()).result(ctx)
				.flatMap(e1 -> this.doSwitch(booleanAnd.getBoolExpression2()).result(ctx).map(e2 -> e1 && e2));
	}

	@Override
	public CtxEvalExp<Integer, Boolean> caseBooleanGreaterThan(BooleanGreaterThan booleanGreaterThan) {
		return ctx -> eval.doSwitch(booleanGreaterThan.getExpression1()).result(ctx)
				.flatMap(e1 -> eval.doSwitch(booleanGreaterThan.getExpression2()).result(ctx).map(e2 -> e1 > e2));
	}

}
