package net.sf.jsqlparser.expression;

import java.util.Arrays;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import net.sf.jsqlparser.expression.operators.relational.ExpressionList;

/**
 *  
 * @author tw
 * @author ju
 */
public class CastExpression extends Function {
	private Expression leftExpression;
	private String typeName;
	
	
	public CastExpression(Expression leftExpression, String typeName) {
		super();
		this.leftExpression = leftExpression;
		this.typeName = typeName;
		this.setName("CAST");
		
		Expression ews = new ExpressionWithString(leftExpression, " AS " + typeName);
		
		ExpressionList explist = new ExpressionList();
		explist.setExpressions(Arrays.asList(ews));
		
		this.setParameters(explist);
		
	}

	

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Expression getCastedExpression() {
		return leftExpression;
	}


	public void accept(ExpressionVisitor expressionVisitor) {
		expressionVisitor.visit(this);
	}

	public String toString() {
        return "CAST(" + leftExpression + " AS " + typeName + ")";
    }
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(leftExpression).append(typeName).build();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CastExpression other = (CastExpression) obj;
		 return new EqualsBuilder()
		                 .append(leftExpression,other.leftExpression)
		                 .append(typeName, other.typeName)
		                 .isEquals();
		
	
	}
}
