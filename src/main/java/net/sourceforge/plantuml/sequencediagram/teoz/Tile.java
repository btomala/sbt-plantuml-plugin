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
package net.sourceforge.plantuml.sequencediagram.teoz;

import net.sourceforge.plantuml.graphic.StringBounder;
import net.sourceforge.plantuml.graphic.UDrawable;
import net.sourceforge.plantuml.real.Real;
import net.sourceforge.plantuml.sequencediagram.Event;
import net.sourceforge.plantuml.ugraphic.UShape;

public interface Tile extends UDrawable, UShape {

	public double getPreferredHeight(StringBounder stringBounder);

	public void addConstraints(StringBounder stringBounder);

	public Real getMinX(StringBounder stringBounder);

	public Real getMaxX(StringBounder stringBounder);

	public Event getEvent();

}
