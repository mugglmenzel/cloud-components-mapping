package org.collaboration.cloudmapping.petrinet;

import java.util.HashSet;
import java.util.Set;

import org.collaboration.cloudmapping.model.Component;
import org.collaboration.cloudmapping.petrinet.model.AssemblyNet;

import de.huberlin.informatik.pnk.appControl.ApplicationControl;
import de.huberlin.informatik.pnk.editor.Editor;
import de.huberlin.informatik.pnk.kernel.Arc;
import de.huberlin.informatik.pnk.kernel.Graph;
import de.huberlin.informatik.pnk.kernel.Net;
import de.huberlin.informatik.pnk.kernel.Place;
import de.huberlin.informatik.pnk.kernel.Specification;
import de.huberlin.informatik.pnk.kernel.Transition;

public class PetriNetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Graph g = new Graph(new Specification(
		// "\"de.huberlin.informatik.pnk.kernel.Graph\" : (\"name\": \"testGraph\")"));
		Net n = new Net(
				new Specification(
						"\"de.huberlin.informatik.pnk.kernel.Net\" : (\"name\" : \"testPetriNet\") \"de.huberlin.informatik.pnk.kernel.Place\" : () \"de.huberlin.informatik.pnk.kernel.Transition\" : () \"de.huberlin.informatik.pnk.kernel.Arc\" : ()"),
				"testPetriNet");

		Place p1 = new Place(n, "P1", null);
		Place p2 = new Place(n, "P2", null);
		Place p3 = new Place(n, "P3", null);

		Transition t1 = new Transition(n, "T1", null);
		Transition t2 = new Transition(n, "T2", null);

		new Arc(n, p1, t1, null);
		new Arc(n, p2, t2, null);
		new Arc(n, t1, p3, null);
		new Arc(n, t2, p3, null);

		
		
		
		System.out.println("created net: " + n.getName() + ", places: "
				+ n.getPlaces().size() + ", transitions: "
				+ n.getTransitions().size());
		

		Set<Component> comps = new HashSet<Component>();
		comps.add(new Component("DS"));
		comps.add(new Component("DS2"));
		comps.add(new Component("MS"));
		comps.add(new Component("WS"));

		AssemblyNet an = new AssemblyNet(
				comps,
				new Specification(
						"\"org.collaboration.cloudmapping.petrinet.model.AssemblyNet\" : (\"name\" : \"testAssemblyNet\") \"org.collaboration.cloudmapping.petrinet.model.AssemblyPlace\" : () \"org.collaboration.cloudmapping.petrinet.model.AssemblyTransition\" : () \"org.collaboration.cloudmapping.petrinet.model.AssemblyArc\" : ()"), "testAssemblyNet");

		System.out.println("created net: " + an.getName() + ", places: "
				+ an.getPlaces().size() + ", transitions: "
				+ an.getTransitions().size());
		
		ApplicationControl ac = new ApplicationControl("file:toolSpecifications/toolSpecification.xml");
		Graph g = ac.getNewNet("PTNet");
		ac.setActiveNet(g);
		ac.getNewStandardApp(g);
		Editor e = ((Editor) ac.getActiveApplications(g).elementAt(0));
		//e.setNet(n);
		e.newNet(an);
		//DoNetLayout dnl = (DoNetLayout) ac.getNewApp("DoNetLayout", g);
		//dnl.startApp();
		//Editor e = new Editor(ac);
		//ac.setActiveNet(n);

	}
}
