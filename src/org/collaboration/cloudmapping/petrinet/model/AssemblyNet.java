package org.collaboration.cloudmapping.petrinet.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.collaboration.cloudmapping.model.Component;

import de.huberlin.informatik.pnk.kernel.Arc;
import de.huberlin.informatik.pnk.kernel.Graph;
import de.huberlin.informatik.pnk.kernel.Place;
import de.huberlin.informatik.pnk.kernel.Specification;
import de.huberlin.informatik.pnk.kernel.Transition;

public class AssemblyNet extends Graph {

	private Set<AssemblyPlace> places = new HashSet<AssemblyPlace>();

	private Set<AssemblyTransition> transitions = new HashSet<AssemblyTransition>();

	private Set<AssemblyArc> arcs = new HashSet<AssemblyArc>();

	/**
	 * 
	 */
	public AssemblyNet(Set<Component> components, Specification spec, String name) {
		super(spec, name);

		List<AssemblyPlace> places = new LinkedList<AssemblyPlace>();
		// int numberComponents = components.size();
		for (Component c : components) {
			AssemblyTransition currentTransition = new AssemblyTransition(this,
					c.getName());
			AssemblyPlace currentPlace = new AssemblyPlace(this, c.getName()
					+ "_p_initial");
			new AssemblyArc(this, currentTransition, currentPlace);
			places.add(currentPlace);
		}
		System.out.println("places: " + places + ", size: " + places.size());

		for (AssemblyPlace currentPlace : places) {
			System.out.println("place: " + currentPlace);

			Set<AssemblyTransition> unifyingTransitions = new HashSet<AssemblyTransition>();
			Iterator<AssemblyPlace> itp = places.iterator();
			while (itp.hasNext()) {
				AssemblyPlace itPlace = itp.next();
				if (!itPlace.equals(currentPlace) && !itPlace.getPredecessorPlaces().contains(currentPlace))
					unifyingTransitions.add(unifyPlaces(currentPlace, itPlace));
			}

			Iterator<AssemblyTransition> itt = unifyingTransitions.iterator();
			AssemblyPlace newPlace = currentPlace;
			while (itt.hasNext()) {
				AssemblyTransition currentTransition = itt.next();
				new AssemblyArc(this, newPlace, currentTransition);
				newPlace = new AssemblyPlace(this,
						currentTransition.getName() + "_place");
				
				new AssemblyArc(this, currentTransition, newPlace);
			}
		}

	}

	private AssemblyTransition unifyPlaces(AssemblyPlace p1, AssemblyPlace p2) {
		AssemblyTransition resultTransition = new AssemblyTransition(this, p1
				.getName()
				+ "_" + p2.getName() + "_unifyTrans");
		new AssemblyArc(this, p1, resultTransition);
		new AssemblyArc(this, p2, resultTransition);

		return resultTransition;
	}

	/**
	 * Gets a Vector of all {@link Place places} of this net.
	 */
	public Set<AssemblyPlace> getPlaces() {
		return places;
	}

	/**
	 * Gets a Vector of all {@link Transition transitions} of this net.
	 */
	public Set<AssemblyTransition> getTransitions() {
		return transitions;
	}

	/**
	 * Gets a Vector of all {@link Arc arcs} of this net.
	 */
	public Set<AssemblyArc> getArcs() {
		return arcs;
	}

	/**
	 * Registers a new {@link Place place} of this net.
	 **/
	public void registerPlace(AssemblyPlace p) {
		places.add(p);
	}

	/**
	 * Registers a new {@link Transition transition} of this net.
	 **/
	public void registerTransition(AssemblyTransition t) {
		transitions.add(t);
	}

}
