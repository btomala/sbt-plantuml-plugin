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
package net.sourceforge.plantuml.ugraphic.svg;

import net.sourceforge.plantuml.graphic.HtmlColor;
import net.sourceforge.plantuml.graphic.HtmlColorGradient;
import net.sourceforge.plantuml.svg.SvgGraphics;
import net.sourceforge.plantuml.ugraphic.ClipContainer;
import net.sourceforge.plantuml.ugraphic.ColorMapper;
import net.sourceforge.plantuml.ugraphic.UDriver;
import net.sourceforge.plantuml.ugraphic.UParam;
import net.sourceforge.plantuml.ugraphic.UPath;
import net.sourceforge.plantuml.ugraphic.UShape;
import net.sourceforge.plantuml.ugraphic.g2d.DriverShadowedG2d;
import net.sourceforge.plantuml.StringUtils;

public class DriverPathSvg extends DriverShadowedG2d implements UDriver<SvgGraphics> {

	private final ClipContainer clipContainer;

	public DriverPathSvg(ClipContainer clipContainer) {
		this.clipContainer = clipContainer;
	}

	public void draw(UShape ushape, double x, double y, ColorMapper mapper, UParam param, SvgGraphics svg) {
		final UPath shape = (UPath) ushape;

		final String color = StringUtils.getAsSvg(mapper, param.getColor());
		if (shape.isOpenIconic()) {
			svg.setFillColor(color);
			svg.setStrokeColor("");
			svg.setStrokeWidth(0, "");
		} else {
			final HtmlColor back = param.getBackcolor();
			if (back instanceof HtmlColorGradient) {
				final HtmlColorGradient gr = (HtmlColorGradient) back;
				final String id = svg.createSvgGradient(StringUtils.getAsHtml(mapper.getMappedColor(gr.getColor1())),
						StringUtils.getAsHtml(mapper.getMappedColor(gr.getColor2())), gr.getPolicy());
				svg.setFillColor("url(#" + id + ")");
			} else {
				final String backcolor = StringUtils.getAsSvg(mapper, back);
				svg.setFillColor(backcolor);
			}
			svg.setStrokeColor(color);
			svg.setStrokeWidth(param.getStroke().getThickness(), param.getStroke().getDasharraySvg());
		}

		svg.svgPath(x, y, shape, shape.getDeltaShadow());

	}
}
