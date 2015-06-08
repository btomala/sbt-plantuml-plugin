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

import net.sourceforge.plantuml.salt.DataSource;
import net.sourceforge.plantuml.salt.Terminated;
import net.sourceforge.plantuml.salt.element.Element;
import net.sourceforge.plantuml.salt.element.ElementLine;

public class ElementFactoryLine implements ElementFactory {

	final private DataSource dataSource;

	public ElementFactoryLine(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Terminated<Element> create() {
		if (ready() == false) {
			throw new IllegalStateException();
		}
		final Terminated<String> next = dataSource.next();
		final String text = next.getElement();
		return new Terminated<Element>(new ElementLine(text.charAt(0)), next.getTerminator());
	}

	public boolean ready() {
		final String text = dataSource.peek(0).getElement();
		if (isLine(text, '-')) {
			return true;
		}
		if (isLine(text, '=')) {
			return true;
		}
		if (isLine(text, '~')) {
			return true;
		}
		if (isLine(text, '.')) {
			return true;
		}
		return false;
	}

	private boolean isLine(String text, char c) {
		final String s = "" + c + c;
		return text.startsWith(s) && text.endsWith(s);
	}
}
