package fr.inria.diverse.expression.visitor;

import java.util.Optional;

import expression.ConstExpr;
import expression.IntAdd;
import expression.IntMult;
import expression.IntNeg;
import expression.IntVarRef;
import expression.util.ExpressionSwitch;
import fr.inria.diverse.visitor.expressions.CtxEvalExp;

public class EvalExpressionVisitorCtxEvalExpIntegerInteger extends ExpressionSwitch<CtxEvalExp<Integer, Integer>> {

	@Override
	public CtxEvalExp<Integer, Integer> caseIntVarRef(IntVarRef intVarRef) {
		return ctx -> {
			Optional<Integer> ret;
			if (ctx.containsKey(intVarRef.getName())) {
				ret = Optional.of(ctx.get(intVarRef.getName()));
			} else {
				ret = Optional.empty();
			}
			return ret;
		};
	}

	@Override
	public CtxEvalExp<Integer, Integer> caseIntNeg(IntNeg intNeg) {
		return ctx -> {
			return this.doSwitch(intNeg.getExpression()).result(ctx).map(e -> -(Integer) e);
		};
	}

	@Override
	public CtxEvalExp<Integer, Integer> caseIntAdd(IntAdd intAdd) {
		return ctx -> this.doSwitch(intAdd.getExpression1()).result(ctx).flatMap(
				e1 -> this.doSwitch(intAdd.getExpression2()).result(ctx).map(e2 -> (Integer) e1 + (Integer) e2));
	}

	@Override
	public CtxEvalExp<Integer, Integer> caseIntMult(IntMult intMult) {
		return ctx -> this.doSwitch(intMult.getExpression1()).result(ctx).flatMap(
				e1 -> this.doSwitch(intMult.getExpression2()).result(ctx).map(e2 -> (Integer) e1 * (Integer) e2));
	}

	@Override
	public CtxEvalExp<Integer, Integer> caseConstExpr(ConstExpr constExpr) {
		return ctx -> Optional.of(constExpr.getValue());
	}

}
