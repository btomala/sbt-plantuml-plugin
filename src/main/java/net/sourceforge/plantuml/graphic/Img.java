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

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import net.sourceforge.plantuml.FileSystem;
import net.sourceforge.plantuml.StringUtils;
import net.sourceforge.plantuml.command.regex.MyPattern;

public class Img implements HtmlCommand {

	final static private Pattern srcPattern = MyPattern.cmpile("(?i)src[%s]*=[%s]*[\"%q]?([^%s\">]+)[\"%q]?");
	final static private Pattern vspacePattern = MyPattern.cmpile("(?i)vspace[%s]*=[%s]*[\"%q]?(\\d+)[\"%q]?");
	final static private Pattern valignPattern = MyPattern.cmpile("(?i)valign[%s]*=[%s]*[\"%q]?(top|bottom|middle)[\"%q]?");
	final static private Pattern noSrcColonPattern = MyPattern.cmpile("(?i)" + Splitter.imgPatternNoSrcColon);

	private final TextBlock tileImage;

	private Img(TextBlock image) {
		this.tileImage = image;
	}

	static int getVspace(String html) {
		final Matcher m = vspacePattern.matcher(html);
		if (m.find() == false) {
			return 0;
		}
		return Integer.parseInt(m.group(1));
	}

	static ImgValign getValign(String html) {
		final Matcher m = valignPattern.matcher(html);
		if (m.find() == false) {
			return ImgValign.TOP;
		}
		return ImgValign.valueOf(StringUtils.goUpperCase(m.group(1)));
	}

	static HtmlCommand getInstance(String html, boolean withSrc) {
		if (withSrc) {
			final Matcher m = srcPattern.matcher(html);
			final int vspace = getVspace(html);
			final ImgValign valign = getValign(html);
			return build(m, valign, vspace);
		}
		final Matcher m = noSrcColonPattern.matcher(html);
		return build(m, ImgValign.TOP, 0);
	}

	private static HtmlCommand build(final Matcher m, final ImgValign valign, final int vspace) {
		if (m.find() == false) {
			return new Text("(SYNTAX ERROR)");
		}
		final String src = m.group(1);
		try {
			final File f = FileSystem.getInstance().getFile(src);
			if (f.exists() == false) {
				// Check if valid URL
				if (src.startsWith("http:") || src.startsWith("https:")) {
					final byte image[] = getFile(src);
					final BufferedImage read = ImageIO.read(new ByteArrayInputStream(image));
					if (read == null) {
						return new Text("(Cannot decode: " + src + ")");
					}
					return new Img(new TileImage(read, valign, vspace));
				}
				return new Text("(File not found: " + f + ")");
			}
			if (f.getName().endsWith(".svg")) {
				return new Img(new TileImageSvg(f));
			}
			final BufferedImage read = ImageIO.read(f);
			if (read == null) {
				return new Text("(Cannot decode: " + f + ")");
			}
			return new Img(new TileImage(ImageIO.read(f), valign, vspace));
		} catch (IOException e) {
			return new Text("ERROR " + e.toString());
		}
	}

	public TextBlock createMonoImage() {
		return tileImage;
	}

	// Added by Alain Corbiere
	static byte[] getFile(String host) throws IOException {
		final ByteArrayOutputStream image = new ByteArrayOutputStream();
		InputStream input = null;
		try {
			final URL url = new URL(host);
			final URLConnection connection = url.openConnection();
			input = connection.getInputStream();
			final byte[] buffer = new byte[1024];
			int read;
			while ((read = input.read(buffer)) > 0) {
				image.write(buffer, 0, read);
			}
			image.close();
			return image.toByteArray();
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
	// End

}
