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
package net.sourceforge.plantuml.postit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.plantuml.EmptyImageBuilder;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.UmlDiagram;
import net.sourceforge.plantuml.UmlDiagramType;
import net.sourceforge.plantuml.api.ImageDataSimple;
import net.sourceforge.plantuml.core.DiagramDescription;
import net.sourceforge.plantuml.core.DiagramDescriptionImpl;
import net.sourceforge.plantuml.core.ImageData;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.graphic.TextBlockUtils;
import net.sourceforge.plantuml.png.PngIO;
import net.sourceforge.plantuml.ugraphic.ColorMapperIdentity;
import net.sourceforge.plantuml.ugraphic.UGraphic;
import net.sourceforge.plantuml.ugraphic.eps.UGraphicEps;
import net.sourceforge.plantuml.ugraphic.g2d.UGraphicG2d;
import net.sourceforge.plantuml.ugraphic.svg.UGraphicSvg;

public class PostItDiagram extends UmlDiagram {

	private final Area defaultArea = new Area('\0', null);

	private final Map<String, PostIt> postIts = new HashMap<String, PostIt>();

	@Override
	public UmlDiagramType getUmlDiagramType() {
		return null;
	}

	@Override
	final protected ImageData exportDiagramInternal(OutputStream os, int index, FileFormatOption fileFormatOption) throws IOException {
		final UGraphic ug = createImage(fileFormatOption);
		drawU(ug);
		if (ug instanceof UGraphicG2d) {
			final BufferedImage im = ((UGraphicG2d) ug).getBufferedImage();
			PngIO.write(im, os, fileFormatOption.isWithMetadata() ? getMetadata() : null, this.getDpi(fileFormatOption));
		} else if (ug instanceof UGraphicSvg) {
			final UGraphicSvg svg = (UGraphicSvg) ug;
			svg.createXml(os);
		} else if (ug instanceof UGraphicEps) {
			final UGraphicEps eps = (UGraphicEps) ug;
			os.write(eps.getEPSCode().getBytes());
		}
		return new ImageDataSimple();
	}

	public DiagramDescription getDescription() {
		return new DiagramDescriptionImpl("Board of post-it", getClass());
	}

	public Area getDefaultArea() {
		return defaultArea;
	}

	public Area createArea(char id) {
		throw new UnsupportedOperationException();
	}

	public PostIt createPostIt(String id, Display text) {
		if (postIts.containsKey(id)) {
			throw new IllegalArgumentException();
		}
		final PostIt postIt = new PostIt(id, text);
		postIts.put(id, postIt);
		getDefaultArea().add(postIt);
		return postIt;
	}

	void drawU(UGraphic ug) {
		getDefaultArea().drawU(ug, width);
	}

	private UGraphic createImage(FileFormatOption fileFormatOption) {
		final Color backColor = getSkinParam().getColorMapper()
				.getMappedColor(this.getSkinParam().getBackgroundColor());
		final FileFormat fileFormat = fileFormatOption.getFileFormat();
		if (fileFormat == FileFormat.PNG) {
			final double height = getDefaultArea().heightWhenWidthIs(width, TextBlockUtils.getDummyStringBounder());
			final EmptyImageBuilder builder = new EmptyImageBuilder(width, height, backColor);

			final Graphics2D graphics2D = builder.getGraphics2D();
			final double dpiFactor = this.getDpiFactor(fileFormatOption);
			final UGraphicG2d result = new UGraphicG2d(new ColorMapperIdentity(), graphics2D, dpiFactor);
			result.setBufferedImage(builder.getBufferedImage());
			return result;
		}
		throw new UnsupportedOperationException();
	}

	private int width = 800;

	public void setWidth(int width) {
		this.width = width;
	}

}
