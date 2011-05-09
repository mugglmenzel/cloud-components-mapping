package org.collaboration.cloudmapping.petrinet.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import de.huberlin.informatik.pnk.kernel.Edge;
import de.huberlin.informatik.pnk.kernel.Net;
import de.huberlin.informatik.pnk.kernel.Node;

public class AssemblyPlace extends Node implements ITraversableNode {

	/**
	 * 
	 */
	public AssemblyPlace(AssemblyNet assemblyNet, String name) {
		super(assemblyNet, name);
		assemblyNet.registerPlace(this);
	}

	public AssemblyNet getAssemblyNet() {
		return (AssemblyNet) getGraph();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.huberlin.informatik.pnk.kernel.Extendable#getNet()
	 */
	@Override
	public Net getNet() {
		return null;
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

	public Set<AssemblyPlace> getPredecessorPlaces() {
		Set<AssemblyPlace> resultPlaces = new HashSet<AssemblyPlace>();
		Iterator<Node> itn = getPredecessors().iterator();
		while (itn.hasNext()) {
			Node n = itn.next();
			if (n instanceof AssemblyPlace)
				resultPlaces.add((AssemblyPlace) n);
		}
		return resultPlaces;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}
	
	
}
