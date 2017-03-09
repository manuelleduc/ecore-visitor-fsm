package expression.algebra;

import expression.BooleanAnd;
import expression.BooleanEqual;
import expression.BooleanExpression;
import expression.BooleanGreaterThan;
import expression.BooleanOr;
import expression.ConstExpr;
import expression.ExpressionPackage;
import expression.IntAdd;
import expression.IntBlock;
import expression.IntExpression;
import expression.IntMult;
import expression.IntNeg;
import expression.IntOperation;
import expression.IntVarAssign;
import expression.IntVarRef;
import java.util.Map;

public interface ExpressionAlgebra<A, B, C> {

	A booleanAnd(final BooleanAnd booleanAnd);

	A booleanEqual(final BooleanEqual booleanEqual);

	A booleanGreaterThan(final BooleanGreaterThan booleanGreaterThan);

	A booleanOr(final BooleanOr booleanOr);

	B constExpr(final ConstExpr constExpr);

	B intAdd(final IntAdd intAdd);

	C intBlock(final IntBlock intBlock);

	B intMult(final IntMult intMult);

	B intNeg(final IntNeg intNeg);

	C intVarAssign(final IntVarAssign intVarAssign);

	B intVarRef(final IntVarRef intVarRef);

	public default A $(final BooleanExpression booleanExpression) {
		final A ret;
		if (booleanExpression.eClass().getClassifierID() == ExpressionPackage.BOOLEAN_AND) {
			ret = this.booleanAnd((BooleanAnd) booleanExpression);
		} else if (booleanExpression.eClass().getClassifierID() == ExpressionPackage.BOOLEAN_EQUAL) {
			ret = this.booleanEqual((BooleanEqual) booleanExpression);
		} else if (booleanExpression.eClass().getClassifierID() == ExpressionPackage.BOOLEAN_GREATER_THAN) {
			ret = this.booleanGreaterThan((BooleanGreaterThan) booleanExpression);
		} else if (booleanExpression.eClass().getClassifierID() == ExpressionPackage.BOOLEAN_OR) {
			ret = this.booleanOr((BooleanOr) booleanExpression);
		} else {
			throw new RuntimeException("Unknow BooleanExpression " + booleanExpression);
		}
		return ret;
	}
	public default B $(final IntExpression intExpression) {
		final B ret;
		if (intExpression.eClass().getClassifierID() == ExpressionPackage.CONST_EXPR) {
			ret = this.constExpr((ConstExpr) intExpression);
		} else if (intExpression.eClass().getClassifierID() == ExpressionPackage.INT_ADD) {
			ret = this.intAdd((IntAdd) intExpression);
		} else if (intExpression.eClass().getClassifierID() == ExpressionPackage.INT_MULT) {
			ret = this.intMult((IntMult) intExpression);
		} else if (intExpression.eClass().getClassifierID() == ExpressionPackage.INT_NEG) {
			ret = this.intNeg((IntNeg) intExpression);
		} else if (intExpression.eClass().getClassifierID() == ExpressionPackage.INT_VAR_REF) {
			ret = this.intVarRef((IntVarRef) intExpression);
		} else {
			throw new RuntimeException("Unknow IntExpression " + intExpression);
		}
		return ret;
	}
	public default C $(final IntOperation intOperation) {
		final C ret;
		if (intOperation.eClass().getClassifierID() == ExpressionPackage.INT_BLOCK) {
			ret = this.intBlock((IntBlock) intOperation);
		} else if (intOperation.eClass().getClassifierID() == ExpressionPackage.INT_VAR_ASSIGN) {
			ret = this.intVarAssign((IntVarAssign) intOperation);
		} else {
			throw new RuntimeException("Unknow IntOperation " + intOperation);
		}
		return ret;
	}
}