package grails.plugin.lazylob;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.type.BlobType;
import org.springframework.util.Assert;

/**
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 */
public class LazyBlobType extends LazyLobType {

	protected static final int[] TYPES = { Types.BLOB };

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		Assert.notNull(name, "propertyName must be set in params");
		return new LazyBlob(getCurrentSession(), owner, name);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		BlobType.INSTANCE.nullSafeSet(st, value, index, getCurrentSession());
	}

	public int[] sqlTypes() {
		return TYPES;
	}

	public Class<LazyBlob> returnedClass() {
		return LazyBlob.class;
	}
}
