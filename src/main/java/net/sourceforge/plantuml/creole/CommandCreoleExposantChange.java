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
package net.sourceforge.plantuml.creole;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.plantuml.command.regex.MyPattern;
import net.sourceforge.plantuml.graphic.FontConfiguration;
import net.sourceforge.plantuml.graphic.FontPosition;

public class CommandCreoleExposantChange implements Command {

	private final Pattern pattern;
	private final FontPosition position;

	private CommandCreoleExposantChange(String p, FontPosition position) {
		this.pattern = MyPattern.cmpile(p);
		this.position = position;
	}

	public static Command create(FontPosition position) {
		return new CommandCreoleExposantChange("^(?i)(" + "\\<" + position.getHtmlTag() + "\\>" + "(.*?)\\</"
				+ position.getHtmlTag() + "\\>)", position);
	}

	// public static Command createLegacyEol(FontStyle style) {
	// return new CommandCreoleExposantChange("^(" + style.getActivationPattern() + "(.+))$", style);
	// }

	public int matchingSize(String line) {
		final Matcher m = pattern.matcher(line);
		if (m.find() == false) {
			return 0;
		}
		return m.group(2).length();
	}

	public String executeAndGetRemaining(String line, StripeSimple stripe) {
		final Matcher m = pattern.matcher(line);
		if (m.find() == false) {
			throw new IllegalStateException();
		}
		final FontConfiguration fc1 = stripe.getActualFontConfiguration();
		final FontConfiguration fc2 = fc1.changeFontPosition(position);

		stripe.setActualFontConfiguration(fc2);
		stripe.analyzeAndAdd(m.group(2));
		stripe.setActualFontConfiguration(fc1);
		return line.substring(m.group(1).length());
	}

}
