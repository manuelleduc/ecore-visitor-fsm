package fr.inria.diverse.expression.visitor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.google.inject.Guice;

import expression.BooleanExpression;
import expression.ExpressionPackage;
import expression.IntExpression;
import expression.IntOperation;

public class Program1 {

	public static void main(final String[] args) {
		new Program1().executeIntExpression("Expression1.expression");
		System.out.println("----------");
		new Program1().executeIntOperation("Expression2.expression");
		System.out.println("----------");
		new Program1().executeBoolExpression("Expression3.expression");

	}

	private void executeBoolExpression(final String progName) {
		EvalExpressionVisitorCtxEvalExpIntegerBoolean instance = Guice.createInjector()
				.getInstance(EvalExpressionVisitorCtxEvalExpIntegerBoolean.class);
		final BooleanExpression model = (BooleanExpression) this.createModel(progName);
		// System.out.println(new
		// PrettyPrintExpressionAlgebraImplementation().$(model).result());
		final Map<String, Integer> ctx = new HashMap<>();
		ctx.put("a", 200);
		ctx.put("b", 200);
		System.out.println(instance.doSwitch(model).result(ctx));
		System.out.println(ctx);

	}

	private void executeIntOperation(final String progName) {

		EvalExpressionVisitorEvalOpExpInteger instance = Guice.createInjector()
				.getInstance(EvalExpressionVisitorEvalOpExpInteger.class);
		final IntOperation model = (IntOperation) this.createModel(progName);
		// System.out.println(new
		// PrettyPrintExpressionAlgebraImplementation().$(model).result());
		final Map<String, Integer> ctx = new HashMap<>();
		ctx.put("a", 200);
		ctx.put("b", 200);
		System.out.println(instance.doSwitch(model).eval(ctx));
		System.out.println(ctx);
	}

	private void executeIntExpression(final String progName) {
		EvalExpressionVisitorCtxEvalExpIntegerInteger instance = Guice.createInjector()
				.getInstance(EvalExpressionVisitorCtxEvalExpIntegerInteger.class);
		final IntExpression model = (IntExpression) this.createModel(progName);
		// System.out.println(new
		// PrettyPrintExpressionAlgebraImplementation().$(model).result());
		final Map<String, Integer> ctx = new HashMap<>();
		ctx.put("a", 200);
		ctx.put("b", 200);
		System.out.println(instance.doSwitch(model).result(ctx));
		System.out.println(ctx);
	}

	private Object createModel(final String progName) {
		final ResourceSetImpl resSet = new ResourceSetImpl();
		resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("expression", new XMIResourceFactoryImpl());
		final ExpressionPackage fsmPackage = ExpressionPackage.eINSTANCE;
		// TODO: Replacing with System.getProperty("user.dir")
		final URI createURI = URI
				.createURI("/home/mleduc/dev/ecore/ecore-oa/fsm/fr.inria.diverse.expression.algebra/model/" + progName);
		final Resource resource = resSet.getResource(createURI, true);
		final EList<EObject> contents = resource.getContents();
		final EObject head = contents.get(0);
		return head;
	}

}
