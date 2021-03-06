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
package net.sourceforge.plantuml;

import java.awt.Font;

import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.skin.rose.Rose;
import net.sourceforge.plantuml.ugraphic.UFont;

interface FontParamConstant {
	String FAMILY = "SansSerif";
	String COLOR = "black";
}

public enum FontParam {
	ACTIVITY(12, Font.PLAIN), //
	ACTIVITY_DIAMOND(11, Font.PLAIN), //
	ACTIVITY_ARROW(11, Font.PLAIN), //
	GENERIC_ARROW(13, Font.PLAIN), //
	CIRCLED_CHARACTER(17, Font.BOLD, FontParamConstant.COLOR, "Monospaced"), //
	OBJECT_ATTRIBUTE(10, Font.PLAIN), //
	OBJECT(12, Font.PLAIN), //
	OBJECT_STEREOTYPE(12, Font.ITALIC), //
	CLASS_ATTRIBUTE(10, Font.PLAIN), //
	CLASS(12, Font.PLAIN), //
	CLASS_STEREOTYPE(12, Font.ITALIC), //
	COMPONENT(14, Font.PLAIN), //
	INTERFACE(14, Font.PLAIN), //
	INTERFACE_STEREOTYPE(14, Font.ITALIC), //
	COMPONENT_STEREOTYPE(14, Font.ITALIC), //
	NOTE(13, Font.PLAIN), //
	PACKAGE(14, Font.PLAIN), //
	ACTOR(14, Font.PLAIN), //
	ARTIFACT(14, Font.PLAIN), //
	CLOUD(14, Font.PLAIN), //
	FOLDER(14, Font.PLAIN), //
	FRAME(14, Font.PLAIN), //
	STORAGE(14, Font.PLAIN), //
	BOUNDARY(14, Font.PLAIN), //
	CONTROL(14, Font.PLAIN), //
	ENTITY(14, Font.PLAIN), //
	AGENT(14, Font.PLAIN), //
	RECTANGLE(14, Font.PLAIN), //
	NODE(14, Font.PLAIN), //
	DATABASE(14, Font.PLAIN), //
	QUEUE(14, Font.PLAIN), //
	SEQUENCE_ARROW(13, Font.PLAIN), //
	SEQUENCE_BOX(13, Font.BOLD), //
	SEQUENCE_DIVIDER(13, Font.BOLD), //
	SEQUENCE_REFERENCE(13, Font.PLAIN), //
	SEQUENCE_DELAY(11, Font.PLAIN), //
	SEQUENCE_GROUP(11, Font.BOLD), //
	SEQUENCE_GROUP_HEADER(13, Font.BOLD), //
	PARTICIPANT(14, Font.PLAIN), //
	SEQUENCE_TITLE(14, Font.BOLD), //
	STATE(14, Font.PLAIN), //
	STATE_ATTRIBUTE(12, Font.PLAIN), //
	LEGEND(14, Font.PLAIN), //
	TITLE(18, Font.PLAIN), //
	FOOTER(10, Font.PLAIN, "#888888", FontParamConstant.FAMILY), //
	HEADER(10, Font.PLAIN, "#888888", FontParamConstant.FAMILY), //
	USECASE(14, Font.PLAIN), //
	USECASE_STEREOTYPE(14, Font.ITALIC), //
	ARTIFACT_STEREOTYPE(14, Font.ITALIC), //
	CLOUD_STEREOTYPE(14, Font.ITALIC), //
	STORAGE_STEREOTYPE(14, Font.ITALIC), //
	BOUNDARY_STEREOTYPE(14, Font.ITALIC), //
	CONTROL_STEREOTYPE(14, Font.ITALIC), //
	ENTITY_STEREOTYPE(14, Font.ITALIC), //
	AGENT_STEREOTYPE(14, Font.ITALIC), //
	RECTANGLE_STEREOTYPE(14, Font.ITALIC), //
	NODE_STEREOTYPE(14, Font.ITALIC), //
	FOLDER_STEREOTYPE(14, Font.ITALIC), //
	FRAME_STEREOTYPE(14, Font.ITALIC), //
	DATABASE_STEREOTYPE(14, Font.ITALIC), //
	QUEUE_STEREOTYPE(14, Font.ITALIC), //
	ACTOR_STEREOTYPE(14, Font.ITALIC), //
	SEQUENCE_STEREOTYPE(14, Font.ITALIC); //

	private final int defaultSize;
	private final int fontStyle;
	private final String defaultColor;
	private final String defaultFamily;

	private FontParam(int defaultSize, int fontStyle, String defaultColor, String defaultFamily) {
		this.defaultSize = defaultSize;
		this.fontStyle = fontStyle;
		this.defaultColor = defaultColor;
		this.defaultFamily = defaultFamily;
	}

	private FontParam(int defaultSize, int fontStyle) {
		this(defaultSize, fontStyle, FontParamConstant.COLOR, FontParamConstant.FAMILY);
	}

	public final int getDefaultSize(ISkinParam skinParam) {
		if (this == CLASS_ATTRIBUTE) {
			return 11;
		}
		return defaultSize;
	}

	public final int getDefaultFontStyle(ISkinParam skinParam, boolean inPackageTitle) {
		if (this == STATE) {
			return fontStyle;
		}
		if (inPackageTitle || this == PACKAGE) {
			return Font.BOLD;
		}
		return fontStyle;
	}

	public final String getDefaultColor() {
		return defaultColor;
	}

	public String getDefaultFamily() {
		return defaultFamily;
	}

	public FontConfiguration getFontConfiguration(ISkinParam skinParam) {
		final UFont font = skinParam.getFont(this, null, false);
		final HtmlColor color = new Rose().getFontColor(skinParam, this);
		final HtmlColor hyperlinkColor = skinParam.getHyperlinkColor();
		final boolean useUnderlineForHyperlink = skinParam.useUnderlineForHyperlink();
		return new FontConfiguration(font, color, hyperlinkColor, useUnderlineForHyperlink);
	}

}
