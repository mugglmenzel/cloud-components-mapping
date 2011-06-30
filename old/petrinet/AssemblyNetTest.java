/**
 * 
 */
package org.collaboration.cloudmapping.petrinet;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.collaboration.cloudmapping.model.Component;
import org.collaboration.cloudmapping.petrinet.model.ITraversableNode;

import de.huberlin.informatik.pnk.appControl.ApplicationControl;
import de.huberlin.informatik.pnk.editor.Editor;
import de.huberlin.informatik.pnk.kernel.Arc;
import de.huberlin.informatik.pnk.kernel.Edge;
import de.huberlin.informatik.pnk.kernel.Graph;
import de.huberlin.informatik.pnk.kernel.Net;
import de.huberlin.informatik.pnk.kernel.Node;
import de.huberlin.informatik.pnk.kernel.Place;
import de.huberlin.informatik.pnk.kernel.Specification;
import de.huberlin.informatik.pnk.kernel.Transition;

/**
 * @author menzel
 * 
 */
public class AssemblyNetTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Component> comps = new ArrayList<Component>();
		comps.add(new Component("DS"));
		comps.add(new Component("DS2"));
		comps.add(new Component("MS"));
		comps.add(new Component("WS"));

		Net n = new Net(
				new Specification(
						"\"de.huberlin.informatik.pnk.kernel.Net\" : (\"name\" : \"testPetriNet\") \"de.huberlin.informatik.pnk.kernel.Place\" : () \"de.huberlin.informatik.pnk.kernel.Transition\" : () \"de.huberlin.informatik.pnk.kernel.Arc\" : ()"),
				"testPetriNet");

		List<Place> places = new LinkedList<Place>();
		// int numberComponents = components.size();
		for (Component c : comps) {
			Transition currentTransition = new Transition(n, c.getName(), null);
			currentTransition.setPosition(new Point(0, comps.indexOf(c) * 50),
					1);
			Place currentPlace = new Place(n, c.getName() + "_p_initial", null);
			currentPlace.setPosition(new Point(100, comps.indexOf(c) * 50), 1);
			new Arc(n, currentTransition, currentPlace, null);
			places.add(currentPlace);
		}
		System.out.println("places: " + places + ", size: " + places.size());

		while (places.size() > 1)
			places = connectAssembly(n, places);

		ApplicationControl ac = new ApplicationControl(
				"file:toolSpecifications/toolSpecification.xml");
		Graph g = ac.getNewNet("PTNet");
		ac.setActiveNet(g);
		ac.getNewStandardApp(g);
		Editor e = ((Editor) ac.getActiveApplications(g).elementAt(0));
		// e.setNet(n);
		e.newNet(n);

	}

	private static List<Place> connectAssembly(Net n, List<Place> places) {
		List<Place> newPlaces = new LinkedList<Place>();
		Set<Set<Place>> placeCombos = new HashSet<Set<Place>>();
		Set<Transition> unifyingTransitions = new HashSet<Transition>();

		for (Place currentPlace : places) {

			System.out.println("place: " + currentPlace);

			Iterator<Place> itp = places.iterator();
			while (itp.hasNext()) {
				Place itPlace = itp.next();
				if (!itPlace.equals(currentPlace)
						&& !getPredecessorPlaces(itPlace)
								.contains(currentPlace)
						&& !placeComboExists(placeCombos, currentPlace, itPlace)) {
					Set<Place> unifyPlaces = new HashSet<Place>();
					unifyPlaces.add(currentPlace);
					unifyPlaces.add(itPlace);
					unifyingTransitions.add(unifyPlaces(n, unifyPlaces));
					placeCombos.add(unifyPlaces);
				}
			}

			// Iterator<Transition> itt = unifyingTransitions.iterator();
			// Place newPlace = currentPlace;
			// while (itt.hasNext()) {
			// Transition currentTransition = itt.next();
			// new Arc(n, newPlace, currentTransition, null);
			// newPlace = new Place(n, currentTransition.getName() + "_place",
			// null);
			// newPlace
			// .setPosition(new Point(new Double(currentTransition
			// .getPosition(1).getX()).intValue() + 100,
			// new Double(currentTransition.getPosition(1)
			// .getY()).intValue()), 1);
			// new Arc(n, currentTransition, newPlace, null);
			// newPlaces.add(newPlace);
			// }
		}

		newPlaces.add(unifyTransitions(n, unifyingTransitions));
		return newPlaces;
	}

	private static boolean placeComboExists(Set<Set<Place>> placeCombos,
			Place p1, Place p2) {
		for (Set<Place> c : placeCombos)
			if (c.contains(p1) && c.contains(p2))
				return true;

		return false;
	}

	private static Transition unifyPlaces(Net net, Set<Place> places) {
		Set<Node> nodes = new HashSet<Node>(places);
		Transition resultTransition = new Transition(net, join(nodes, "_")
				.substring(0, 20)
				+ "_unifyTrans", null);
		resultTransition.setPosition(new Point(maxX(nodes) + 100, maxY(nodes)),
				1);
		for (Place p : places)
			new Arc(net, p, resultTransition, null);

		return resultTransition;
	}

	private static Place unifyTransitions(Net net, Set<Transition> trans) {
		Set<Node> nodes = new HashSet<Node>(trans);
		Place resultPlace = new Place(net, join(nodes, "_").substring(0, 20)
				+ "_unifyPlace", null);
		resultPlace.setPosition(new Point(maxX(nodes) + 100, maxY(nodes)), 1);
		for (Transition t : trans)
			new Arc(net, t, resultPlace, null);

		return resultPlace;
	}

	@SuppressWarnings("unchecked")
	private static Set<Node> getPredecessors(Node n) {
		Set<Node> resultNodes = new HashSet<Node>();
		for (Edge e : (Vector<Edge>) n.getIncomingEdges()) {
			resultNodes.add(e.getSource());
			if (e.getSource() instanceof ITraversableNode)
				resultNodes.addAll(((ITraversableNode) e.getSource())
						.getPredecessors());
		}
		return resultNodes;
	}

	private static Set<Place> getPredecessorPlaces(Place p) {
		Set<Place> resultPlaces = new HashSet<Place>();
		Iterator<Node> itn = getPredecessors(p).iterator();
		while (itn.hasNext()) {
			Node n = itn.next();
			if (n instanceof Place)
				resultPlaces.add((Place) n);
		}
		return resultPlaces;
	}

	private static int maxX(Set<Node> nodes) {
		int maxX = 0;
		for (Node p : nodes)
			maxX = p.getPosition(1).getX() > maxX ? new Double(p.getPosition(1)
					.getX()).intValue() : maxX;

		return maxX;
	}

	private static int maxY(Set<Node> nodes) {
		int maxY = 0;
		for (Node p : nodes)
			maxY = p.getPosition(1).getY() > maxY ? new Double(p.getPosition(1)
					.getY()).intValue() : maxY;

		return maxY;
	}

	private static String join(Set<Node> nodes, String delimiter) {
		List<String> names = new LinkedList<String>();
		for (Node p : nodes)
			names.add(p.getName());

		return join(names, delimiter);
	}

	private static String join(List<? extends CharSequence> s, String delimiter) {
		int capacity = 0;
		int delimLength = delimiter.length();
		Iterator<? extends CharSequence> iter = s.iterator();
		if (iter.hasNext()) {
			capacity += iter.next().length() + delimLength;
		}

		StringBuilder buffer = new StringBuilder(capacity);
		iter = s.iterator();
		if (iter.hasNext()) {
			buffer.append(iter.next());
			while (iter.hasNext()) {
				buffer.append(delimiter);
				buffer.append(iter.next());
			}
		}
		return buffer.toString();
	}
}
