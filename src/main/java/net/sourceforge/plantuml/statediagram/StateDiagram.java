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
package net.sourceforge.plantuml.statediagram;

import net.sourceforge.plantuml.UmlDiagramType;
import net.sourceforge.plantuml.classdiagram.AbstractEntityDiagram;
import net.sourceforge.plantuml.cucadiagram.Code;
import net.sourceforge.plantuml.cucadiagram.Display;
import net.sourceforge.plantuml.cucadiagram.EntityUtils;
import net.sourceforge.plantuml.cucadiagram.GroupType;
import net.sourceforge.plantuml.cucadiagram.IEntity;
import net.sourceforge.plantuml.cucadiagram.IGroup;
import net.sourceforge.plantuml.cucadiagram.LeafType;
import net.sourceforge.plantuml.cucadiagram.Rankdir;
import net.sourceforge.plantuml.graphic.USymbol;
import net.sourceforge.plantuml.utils.UniqueSequence;

public class StateDiagram extends AbstractEntityDiagram {

	public boolean checkConcurrentStateOk(Code code) {
		if (leafExist(code) == false) {
			return true;
		}
		final IEntity existing = this.getLeafsget(code);
		if (getCurrentGroup().getGroupType() == GroupType.CONCURRENT_STATE
				&& getCurrentGroup() != existing.getParentContainer()) {
			return false;
		}
		if (existing.getParentContainer().getGroupType() == GroupType.CONCURRENT_STATE
				&& getCurrentGroup() != existing.getParentContainer()) {
			return false;
		}
		return true;
	}

	@Override
	public IEntity getOrCreateLeaf(Code code, LeafType type, USymbol symbol) {
		if (checkConcurrentStateOk(code) == false) {
			throw new IllegalStateException("Concurrent State " + code);
		}
		if (type == null) {
			if (code.getFullName().startsWith("[*]")) {
				throw new IllegalArgumentException();
			}
			if (isGroup(code)) {
				return getGroup(code);
			}
			return getOrCreateLeafDefault(code, LeafType.STATE, null);
		}
		return getOrCreateLeafDefault(code, type, symbol);
	}

	public IEntity getStart() {
		final IGroup g = getCurrentGroup();
		if (EntityUtils.groupRoot(g)) {
			return getOrCreateLeaf(Code.of("*start"), LeafType.CIRCLE_START, null);
		}
		return getOrCreateLeaf(Code.of("*start*" + g.getCode().getFullName()), LeafType.CIRCLE_START, null);
	}

	public IEntity getEnd() {
		final IGroup p = getCurrentGroup();
		if (EntityUtils.groupRoot(p)) {
			return getOrCreateLeaf(Code.of("*end"), LeafType.CIRCLE_END, null);
		}
		return getOrCreateLeaf(Code.of("*end*" + p.getCode().getFullName()), LeafType.CIRCLE_END, null);
	}

	public IEntity getHistorical() {
		final IGroup g = getCurrentGroup();
		if (EntityUtils.groupRoot(g)) {
			return getOrCreateLeaf(Code.of("*historical"), LeafType.PSEUDO_STATE, null);
		}
		return getOrCreateLeaf(Code.of("*historical*" + g.getCode().getFullName()), LeafType.PSEUDO_STATE, null);
	}

	public IEntity getHistorical(Code codeGroup) {
		final IEntity g = getOrCreateGroup(codeGroup, Display.getWithNewlines(codeGroup), GroupType.STATE,
				getRootGroup());
		final IEntity result = getOrCreateLeaf(Code.of("*historical*" + g.getCode().getFullName()),
				LeafType.PSEUDO_STATE, null);
		endGroup();
		return result;
	}

	public boolean concurrentState(char direction) {
		final IGroup cur = getCurrentGroup();
		// printlink("BEFORE");
		if (EntityUtils.groupRoot(cur) == false && cur.getGroupType() == GroupType.CONCURRENT_STATE) {
			super.endGroup();
		}
		getCurrentGroup().setConcurrentSeparator(direction);
		final IGroup conc1 = getOrCreateGroup(UniqueSequence.getCode("CONC"), Display.create(""),
				GroupType.CONCURRENT_STATE, getCurrentGroup());
		if (EntityUtils.groupRoot(cur) == false && cur.getGroupType() == GroupType.STATE) {
			cur.moveEntitiesTo(conc1);
			super.endGroup();
			getOrCreateGroup(UniqueSequence.getCode("CONC"), Display.create(""), GroupType.CONCURRENT_STATE,
					getCurrentGroup());
		}
		// printlink("AFTER");
		return true;
	}

	// private void printlink(String comment) {
	// Log.println("COMMENT="+comment);
	// for (Link l : getLinks()) {
	// Log.println(l);
	// }
	// }

	@Override
	public void endGroup() {
		final IGroup cur = getCurrentGroup();
		if (EntityUtils.groupRoot(cur) == false && cur.getGroupType() == GroupType.CONCURRENT_STATE) {
			super.endGroup();
		}
		super.endGroup();
	}

	@Override
	public UmlDiagramType getUmlDiagramType() {
		return UmlDiagramType.STATE;
	}

	private boolean hideEmptyDescription = false;

	public final void setHideEmptyDescription(boolean hideEmptyDescription) {
		this.hideEmptyDescription = hideEmptyDescription;
	}

	public final boolean isHideEmptyDescriptionForState() {
		return hideEmptyDescription;
	}

	// public Link isEntryPoint(IEntity ent) {
	// final Stereotype stereotype = ent.getStereotype();
	// if (stereotype == null) {
	// return null;
	// }
	// final String label = stereotype.getLabel();
	// if ("<<entrypoint>>".equalsIgnoreCase(label) == false) {
	// return null;
	// }
	// Link inLink = null;
	// Link outLink = null;
	// for (Link link : getLinks()) {
	// if (link.getEntity1() == ent) {
	// if (outLink != null) {
	// return null;
	// }
	// outLink = link;
	// }
	// if (link.getEntity2() == ent) {
	// if (inLink != null) {
	// return null;
	// }
	// inLink = link;
	// }
	// }
	// if (inLink == null || outLink == null) {
	// return null;
	// }
	// final Link result = Link.mergeForEntryPoint(inLink, outLink);
	// result.setEntryPoint(ent.getContainer());
	// return result;
	// }
	//
	// public void manageExitAndEntryPoints() {
	// for (IEntity ent : getEntities().values()) {
	// final Link entryPointLink = isEntryPoint(ent);
	// if (entryPointLink != null) {
	// addLink(entryPointLink);
	// for (Link link : new ArrayList<Link>(getLinks())) {
	// if (link.contains(ent)) {
	// removeLink(link);
	// }
	// }
	// }
	// }
	//
	// }

}
