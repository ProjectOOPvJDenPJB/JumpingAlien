package jumpingalien.model;

public enum TileType {
	
	AIR {
		/**
		 * Return whether air is passable or not.
		 * @return	TODO
		 * 			| result == true
		 */
		public boolean getPassable() {
			return true;
		}
	},
	
	WATER {
		/**
		 * Return whether water is passable or not.
		 * @return	TODO
		 * 			| result == true
		 */
		public boolean getPassable() {
			return true;
		}
	},
	
	MAGMA {
		/**
		 * Return whether magma is passable or not.
		 * @return	TODO
		 * 			| result == true
		 */
		public boolean getPassable() {
			return true;
		}
	},
	
	GROUND {
		/**
		 * Return whether ground is passable or not.
		 * @return	TODO
		 * 			| result == false
		 */
		public boolean getPassable() {
			return false;
		}
	};
	
	
	public abstract boolean getPassable();

}
