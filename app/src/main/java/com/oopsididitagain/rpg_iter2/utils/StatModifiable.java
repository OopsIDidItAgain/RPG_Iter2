package com.oopsididitagain.rpg_iter2.utils;

public interface StatModifiable extends StatBlobHolder {
	public void visit(InstantStatModifier statModifier);
}
