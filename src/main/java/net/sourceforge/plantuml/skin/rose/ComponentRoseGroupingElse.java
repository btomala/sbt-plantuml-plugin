/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2014, Arnaud Roques
 *
 * Project Info:  http://plantuml.sourceforge.net
 * 
 * This file is part of PlantUML.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * Original Author:  Arnaud Roques
 */
package net.sourceforge.plantuml.skin.rose;

import java.awt.geom.Dimension2D;

import net.sourceforge.plantuml.ISkinSimple;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HorizontalAlignment;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.skin.AbstractTextualComponent;
import net.sourceforge.plantuml.skin.Area;
import net.sourceforge.plantuml.ugraphic.UChangeBackColor;
import net.sourceforge.plantuml.ugraphic.UChangeColor;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.ULine;
import net.sourceforge.plantuml.ugraphic.URectangle;
import net.sourceforge.plantuml.ugraphic.UStroke;
import net.sourceforge.plantuml.ugraphic.UTranslate;

public class ComponentRoseGroupingElse extends AbstractTextualComponent {

	private final HtmlColor groupBorder;
	private final HtmlColor backgroundColor;

	// private final UStroke stroke;

	public ComponentRoseGroupingElse(HtmlColor groupBorder, FontConfiguration smallFont, CharSequence comment,
			ISkinSimple spriteContainer, HtmlColor backgroundColor) {
		super(comment == null ? null : "[" + comment + "]", smallFont, HorizontalAlignment.LEFT, 5, 5, 1,
				spriteContainer, 0, null, null);
		this.groupBorder = groupBorder;
		this.backgroundColor = backgroundColor;
		// this.stroke = stroke;
	}

	@Override
	protected void drawBackgroundInternalU(UGraphic ug, Area area) {
		final Dimension2D dimensionToUse = area.getDimensionToUse();
		final URectangle rect = new URectangle(dimensionToUse.getWidth(), dimensionToUse.getHeight());
		ug.apply(new UChangeColor(null)).apply(new UChangeBackColor(backgroundColor)).draw(rect);
	}

	@Override
	protected void drawInternalU(UGraphic ug, Area area) {
		final Dimension2D dimensionToUse = area.getDimensionToUse();
		ug = stroke(ug, 2, 2).apply(new UChangeColor(groupBorder));
		ug.apply(new UTranslate(0, 1)).draw(new ULine(dimensionToUse.getWidth(), 0));
		ug = ug.apply(new UStroke());
		getTextBlock().drawU(ug.apply(new UTranslate(getMarginX1(), getMarginY())));
	}

	@Override
	public double getPreferredHeight(StringBounder stringBounder) {
		return getTextHeight(stringBounder);
	}

	@Override
	public double getPreferredWidth(StringBounder stringBounder) {
		return getTextWidth(stringBounder);
	}

}
