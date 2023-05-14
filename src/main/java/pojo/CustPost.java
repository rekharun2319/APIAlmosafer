package pojo;

import java.util.List;

public class CustPost {
	
	private List<Leg> leg;
	private String cabin;
	private Pax pax;	
	private List stops;
	private List airline;
	private Timeslots timeSlots;
	private Airports airports;
	
	
	public List<Leg> getLeg() {
		return leg;
	}
	public void setLeg(List<Leg> leg) {
		this.leg = leg;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public Pax getPax() {
		return pax;
	}
	public void setPax(Pax pax) {
		this.pax = pax;
	}
	public List getStops() {
		return stops;
	}
	public void setStops(List stops) {
		this.stops = stops;
	}
	public List getAirline() {
		return airline;
	}
	public void setAirline(List airline) {
		this.airline = airline;
	}
	public Timeslots getTimeSlots() {
		return timeSlots;
	}
	public void setTimeSlots(Timeslots timeSlots) {
		this.timeSlots = timeSlots;
	}
	public Airports getAirports() {
		return airports;
	}
	public void setAirports(Airports airports) {
		this.airports = airports;
	}
	
	

}
