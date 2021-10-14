package view;

import java.awt.Graphics;

import model.Tree;

public abstract class TreePainter {

	protected final int XXLWidth = 20;
	protected final int XLWidth = 18;
	protected final int LWidth = 15;
	protected final int MWidth = 10;
	protected final int SWidth = 4;
	protected final int XXLHeight = 120;
	protected final int XLHeight = 90;
	protected final int LHeight = 70;
	protected final int MHeight = 50;
	protected final int SHeight = 30;
	protected final int XXLDiameter = 130;
	protected final int XLDiameter = 100;
	protected final int LDiameter = 80;
	protected final int MDiameter = 50;
	protected final int SDiameter = 30;
	protected final float XXLStroke = 3;
	protected final float XLStroke = 2.5f;
	protected final float LStroke = 2.0f;
	protected final float MStroke = 1.5f;
	protected final float SStroke = 1;
	
	
	abstract void paintTree(Tree t, Graphics g, PaintingPanel p);
	
}
