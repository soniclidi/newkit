package cc.codehub.newkit.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the access_role_map database table.
 * 
 */
@Entity
@Table(name="access_role_map")
public class AccessRoleMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="map_id")
	private Integer mapId;

	@Column(name="access_id")
	private Integer accessId;

	@Column(name="role_id")
	private Integer roleId;

	public AccessRoleMap() {
	}

	public Integer getMapId() {
		return this.mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public Integer getAccessId() {
		return this.accessId;
	}

	public void setAccessId(Integer accessId) {
		this.accessId = accessId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}