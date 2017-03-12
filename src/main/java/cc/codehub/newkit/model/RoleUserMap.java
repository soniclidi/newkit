package cc.codehub.newkit.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the role_user_map database table.
 * 
 */
@Entity
@Table(name="role_user_map")
public class RoleUserMap implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="map_id")
	private Integer mapId;

	@Column(name="role_id")
	private Integer roleId;

	private Integer uid;

	public RoleUserMap() {
	}

	public Integer getMapId() {
		return this.mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

}