package org.collaboration.cloudmapping.petrinet.model;

import de.huberlin.informatik.pnk.kernel.Edge;
import de.huberlin.informatik.pnk.kernel.Graph;
import de.huberlin.informatik.pnk.kernel.Node;

public class AssemblyArc extends Edge {

	public AssemblyArc(Graph graph, Node source, Node target) {
		super(graph, source, target);
		// TODO Auto-generated constructor stub
	}

	public AssemblyArc(Graph graph, String sourceId, String targetId,
			Object initiator, String arcId) {
		super(graph, sourceId, targetId, initiator, arcId);
		// TODO Auto-generated constructor stub
	}

}
