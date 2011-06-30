package org.collaboration.cloudmapping.petrinet.model;

import java.util.Set;

import de.huberlin.informatik.pnk.kernel.Node;

public interface ITraversableNode {

	public Set<Node> getPredecessors();
	
}
