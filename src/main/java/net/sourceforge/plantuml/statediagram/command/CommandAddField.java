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
package net.sourceforge.plantuml.statediagram.command;

import net.sourceforge.plantuml.command.CommandExecutionResult;
import net.sourceforge.plantuml.command.SingleLineCommand2;
import net.sourceforge.plantuml.command.regex.RegexConcat;
import net.sourceforge.plantuml.command.regex.RegexLeaf;
import net.sourceforge.plantuml.command.regex.RegexOr;
import net.sourceforge.plantuml.command.regex.RegexResult;
import net.sourceforge.plantuml.cucadiagram.Code;
import net.sourceforge.plantuml.cucadiagram.IEntity;
import net.sourceforge.plantuml.statediagram.StateDiagram;

public class CommandAddField extends SingleLineCommand2<StateDiagram> {

	public CommandAddField() {
		super(getRegexConcat());
	}

	private static RegexConcat getRegexConcat() {
		return new RegexConcat(new RegexLeaf("^"), //
				new RegexOr( //
						new RegexLeaf("CODE3", "([\\p{L}0-9_.]+)"), //
						new RegexLeaf("CODE4", "[%g]([^%g]+)[%g]")), //
				new RegexLeaf("[%s]*:[%s]*"), //
				new RegexLeaf("FIELD", "(.*)"), //
				new RegexLeaf("$"));
	}

	@Override
	protected CommandExecutionResult executeArg(StateDiagram diagram, RegexResult arg) {
		final String code = arg.getLazzy("CODE", 0);
		final String field = arg.get("FIELD", 0);

		final IEntity entity = diagram.getOrCreateLeaf(Code.of(code), null, null);

		entity.getBodier().addFieldOrMethod(field);
		return CommandExecutionResult.ok();
	}

}
