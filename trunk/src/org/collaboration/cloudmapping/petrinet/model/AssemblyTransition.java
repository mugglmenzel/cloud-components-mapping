package org.collaboration.cloudmapping.petrinet.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import de.huberlin.informatik.pnk.kernel.Edge;
import de.huberlin.informatik.pnk.kernel.Node;

public class AssemblyTransition extends Node implements ITraversableNode {

	public AssemblyTransition(AssemblyNet assemblyNet, String name) {
		super(assemblyNet, name);
		assemblyNet.registerTransition(this);
	}

	@SuppressWarnings("unchecked")
	public Set<Node> getPredecessors() {
		Set<Node> resultNodes = new HashSet<Node>();
		for (Edge e : (Vector<Edge>) getIncomingEdges()) {
			resultNodes.add(e.getSource());
			if (e.getSource() instanceof ITraversableNode)
				resultNodes.addAll(((ITraversableNode) e.getSource())
						.getPredecessors());
		}
		return resultNodes;
	}

	public Set<AssemblyTransition> getPredecessorTransitions() {
		Set<AssemblyTransition> resultTransitions = new HashSet<AssemblyTransition>();
		Iterator<Node> itn = getPredecessors().iterator();
		while (itn.hasNext()) {
			Node n = itn.next();
			if (n instanceof AssemblyTransition)
				resultTransitions.add((AssemblyTransition) n);
		}
		return resultTransitions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}

}
