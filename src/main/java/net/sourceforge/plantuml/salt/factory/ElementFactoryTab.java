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
package net.sourceforge.plantuml.salt.factory;

import java.awt.Font;

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.Dictionary;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.Terminator;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementTabBar;
import net.sourceforge.plantuml.ugraphic.UFont;

public class ElementFactoryTab extends AbstractElementFactoryComplex {

	public ElementFactoryTab(DataSource dataSource, Dictionary dictionary) {
		super(dataSource, dictionary);
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final String header = getDataSource().next().getElement();
		assert header.startsWith("{/");

		final UFont font = new UFont("Default", Font.PLAIN, 12);
		final ElementTabBar result = new ElementTabBar(font, getDictionary());

		while (getDataSource().peek(0).getElement().equals("}") == false) {
			final Terminated<String> t = getDataSource().next();
			result.addTab(t.getElement());
			if (t.getTerminator() == Terminator.NEWLINE) {
				result.setVertical(true);
			}
		}
		final Terminated<String> next = getDataSource().next();
		return new Terminated<Element>(result, next.getTerminator());
	}

	public boolean ready() {
		final String text = getDataSource().peek(0).getElement();
		if (text.equals("{/")) {
			return true;
		}
		return false;
	}
}
