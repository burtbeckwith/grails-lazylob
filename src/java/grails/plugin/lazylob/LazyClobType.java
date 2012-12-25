package grails.plugin.lazylob;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.type.ClobType;
import org.springframework.util.Assert;

/**
 * @author <a href='mailto:burt@burtbeckwith.com'>Burt Beckwith</a>
 */
public class LazyClobType extends LazyLobType {

	protected static final int[] TYPES = { Types.BLOB };

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		Assert.notNull(name, "propertyName must be set in params");
		return new LazyClob(getCurrentSession(), owner, name);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		ClobType.INSTANCE.nullSafeSet(st, value, index, getCurrentSession());
	}

	public int[] sqlTypes() {
		return TYPES;
	}

	public Class<LazyClob> returnedClass() {
		return LazyClob.class;
	}
}
