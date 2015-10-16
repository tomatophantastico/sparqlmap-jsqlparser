package net.sf.jsqlparser.expression;

import java.util.Arrays;

import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

/**
 * Extract value from date/time expression. The name stores the part - name 
 * to get from the following date/time expression.
 * @author tw
 */
public class ExtractExpression extends Function {
	private String name;
	
	public ExtractExpression(String name, Expression expression) {
		super();
		this.name = name;
		this.expression = expression;
		this.setName("EXTRACT");
		Expression ews = new ExpressionWithString(expression," FROM " + name);
		ExpressionList exprList = new ExpressionList(Arrays.asList(ews));
		this.setParameters(exprList);
	}

	private Expression expression;
	
	@Override
	public void accept(ExpressionVisitor expressionVisitor) {
		expressionVisitor.visit(this);
	}



	

	public Expression getExpression() {
		return expression;
	}

	

	@Override
	public String toString() {
		return "EXTRACT(" + name + " FROM " + expression + ')';
	}
}
