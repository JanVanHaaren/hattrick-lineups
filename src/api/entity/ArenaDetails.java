package api.entity;

import api.entity.arenadetails.Arena;


/**
 * @author	Jan Van Haaren
 * @date	28 June 2013
 */
public class ArenaDetails extends Entity {

	private Arena arena;

	public ArenaDetails() {
		super();
	}

	public ArenaDetails(String userID, String fetchedDate) {
		super(userID,fetchedDate);
	}

	public ArenaDetails(Arena arena) {
		this.setArena(arena);
	}

	public Arena getArena() {
		return this.arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

}
