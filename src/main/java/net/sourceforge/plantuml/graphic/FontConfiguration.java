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
package net.sourceforge.plantuml.graphic;

import java.util.EnumSet;
import java.util.Map;

import net.sourceforge.plantuml.FontParam;
import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.SkinParamUtils;
import net.sourceforge.plantuml.cucadiagram.Stereotype;
import net.sourceforge.plantuml.ugraphic.UFont;

public class FontConfiguration {

	private final EnumSet<FontStyle> styles;
	private final UFont currentFont;
	private final UFont motherFont;
	private final HtmlColor motherColor;
	private final HtmlColor hyperlinkColor;
	private final HtmlColor currentColor;
	private final HtmlColor extendedColor;
	private final FontPosition fontPosition;
	private final SvgAttributes svgAttributes;
	private final boolean hyperlink;
	private final boolean useUnderlineForHyperlink;

	public FontConfiguration(UFont font, HtmlColor color, HtmlColor hyperlinkColor, boolean useUnderlineForHyperlink) {
		this(getStyles(font), font, color, font, color, null, FontPosition.NORMAL, new SvgAttributes(), false,
				hyperlinkColor, useUnderlineForHyperlink);
	}

	public FontConfiguration(ISkinParam skinParam, FontParam fontParam, Stereotype stereo) {
		this(SkinParamUtils.getFont(skinParam, fontParam, stereo), SkinParamUtils.getFontColor(skinParam, fontParam,
				stereo), skinParam.getHyperlinkColor(), skinParam.useUnderlineForHyperlink());
	}
	
	//---
	
	public final boolean useUnderlineForHyperlink() {
		return useUnderlineForHyperlink;
	}

	public final HtmlColor getHyperlinkColor() {
		return hyperlinkColor;
	}

	//---

	private static EnumSet<FontStyle> getStyles(UFont font) {
		final boolean bold = font.isBold();
		final boolean italic = font.isItalic();
		if (bold && italic) {
			return EnumSet.of(FontStyle.ITALIC, FontStyle.BOLD);
		}
		if (bold) {
			return EnumSet.of(FontStyle.BOLD);
		}
		if (italic) {
			return EnumSet.of(FontStyle.ITALIC);
		}
		return EnumSet.noneOf(FontStyle.class);
	}

	@Override
	public String toString() {
		return styles.toString() + " " + currentColor;
	}

	private FontConfiguration(EnumSet<FontStyle> styles, UFont motherFont, HtmlColor motherColor, UFont currentFont,
			HtmlColor currentColor, HtmlColor extendedColor, FontPosition fontPosition, SvgAttributes svgAttributes,
			boolean hyperlink, HtmlColor hyperlinkColor, boolean useUnderlineForHyperlink) {
		this.styles = styles;
		this.currentFont = currentFont;
		this.motherFont = motherFont;
		this.currentColor = currentColor;
		this.motherColor = motherColor;
		this.extendedColor = extendedColor;
		this.fontPosition = fontPosition;
		this.svgAttributes = svgAttributes;
		this.hyperlink = hyperlink;
		this.hyperlinkColor = hyperlinkColor;
		this.useUnderlineForHyperlink = useUnderlineForHyperlink;
	}

	public FontConfiguration forceFont(UFont newFont, HtmlColor htmlColorForStereotype) {
		if (newFont == null) {
			return add(FontStyle.ITALIC);
		}
		FontConfiguration result = new FontConfiguration(styles, newFont, motherColor, newFont, currentColor,
				extendedColor, fontPosition, svgAttributes, hyperlink, hyperlinkColor, useUnderlineForHyperlink);
		if (htmlColorForStereotype != null) {
			result = result.changeColor(htmlColorForStereotype);
		}
		return result;
	}

	public FontConfiguration changeAttributes(SvgAttributes toBeAdded) {
		return new FontConfiguration(styles, motherFont, motherColor, currentFont, currentColor, extendedColor,
				fontPosition, svgAttributes.add(toBeAdded), hyperlink, hyperlinkColor, useUnderlineForHyperlink);
	}

	private FontConfiguration withHyperlink() {
		return new FontConfiguration(styles, motherFont, motherColor, currentFont, currentColor, extendedColor,
				fontPosition, svgAttributes, true, hyperlinkColor, useUnderlineForHyperlink);
	}

	public FontConfiguration changeColor(HtmlColor htmlColor) {
		return new FontConfiguration(styles, motherFont, motherColor, currentFont, htmlColor, extendedColor,
				fontPosition, svgAttributes, hyperlink, hyperlinkColor, useUnderlineForHyperlink);
	}

	FontConfiguration changeExtendedColor(HtmlColor newExtendedColor) {
		return new FontConfiguration(styles, motherFont, motherColor, currentFont, currentColor, newExtendedColor,
				fontPosition, svgAttributes, hyperlink, hyperlinkColor, useUnderlineForHyperlink);
	}

	public FontConfiguration changeSize(float size) {
		return new FontConfiguration(styles, motherFont, motherColor, currentFont.deriveSize(size), currentColor,
				extendedColor, fontPosition, svgAttributes, hyperlink, hyperlinkColor, useUnderlineForHyperlink);
	}

	public FontConfiguration bigger(double delta) {
		return changeSize((float) (currentFont.getSize() + delta));
	}

	public FontConfiguration changeFontPosition(FontPosition fontPosition) {
		return new FontConfiguration(styles, motherFont, motherColor, currentFont, currentColor, extendedColor,
				fontPosition, svgAttributes, hyperlink, hyperlinkColor, useUnderlineForHyperlink);
	}

	public FontConfiguration changeFamily(String family) {
		return new FontConfiguration(styles, motherFont, motherColor, new UFont(family, currentFont.getStyle(),
				currentFont.getSize()), currentColor, extendedColor, fontPosition, svgAttributes, hyperlink,
				hyperlinkColor, useUnderlineForHyperlink);
	}

	public FontConfiguration resetFont() {
		return new FontConfiguration(styles, motherFont, motherColor, motherFont, motherColor, null,
				FontPosition.NORMAL, new SvgAttributes(), hyperlink, hyperlinkColor, useUnderlineForHyperlink);
	}

	FontConfiguration add(FontStyle style) {
		final EnumSet<FontStyle> r = styles.clone();
		r.add(style);
		return new FontConfiguration(r, motherFont, motherColor, currentFont, currentColor, extendedColor,
				fontPosition, svgAttributes, hyperlink, hyperlinkColor, useUnderlineForHyperlink);
	}

	public FontConfiguration italic() {
		return add(FontStyle.ITALIC);
	}

	public FontConfiguration bold() {
		return add(FontStyle.BOLD);
	}

	public FontConfiguration underline() {
		return add(FontStyle.UNDERLINE);
	}

	public FontConfiguration hyperlink() {
		if (useUnderlineForHyperlink) {
			return add(FontStyle.UNDERLINE).withHyperlink();
		}
		return withHyperlink();
	}

	FontConfiguration remove(FontStyle style) {
		final EnumSet<FontStyle> r = styles.clone();
		r.remove(style);
		return new FontConfiguration(r, motherFont, motherColor, currentFont, currentColor, extendedColor,
				fontPosition, svgAttributes, hyperlink, hyperlinkColor, useUnderlineForHyperlink);
	}

	public UFont getFont() {
		UFont result = currentFont;
		for (FontStyle style : styles) {
			result = style.mutateFont(result);
		}
		return fontPosition.mute(result);
	}

	public HtmlColor getColor() {
		if (hyperlink) {
			return hyperlinkColor;
		}
		return currentColor;
	}

	public HtmlColor getExtendedColor() {
		return extendedColor;
	}

	public boolean containsStyle(FontStyle style) {
		return styles.contains(style);
	}

	public int getSpace() {
		return fontPosition.getSpace();
	}

	public Map<String, String> getAttributes() {
		return svgAttributes.attributes();
	}

	public double getSize2D() {
		return currentFont.getSize2D();
	}

}
