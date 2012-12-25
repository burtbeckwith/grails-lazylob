package grails.plugin.lazylob

import org.hibernate.Hibernate;

class PersistenceTests extends GroovyTestCase {

	def sessionFactory

	private String content = 'testing, testing'
	private bytes = content.bytes
	private name = 'the lob'

	void testBlobFromBytes() {
		def b = new Blobber(name: name)
		b.theBlob = new LazyBlob(bytes, b)
		assert b.save()
		assert !b.hasErrors()

		flushAndClear()

		Blobber reloaded = Blobber.get(b.id)
		assert !reloaded.is(b)

		assert !reloaded.theBlob.isInitialized()

		assert name == reloaded.name
		assert !reloaded.theBlob.isInitialized()

		assert content == new String(reloaded.theBlob.getBytes(1, (int)reloaded.theBlob.length()))
		assert reloaded.theBlob.isInitialized()
	}

	void testBlobFromBlob() {
		def b = new Blobber(name: name)
		b.theBlob = new LazyBlob(Hibernate.createBlob(bytes), b)
		assert b.save()
		assert !b.hasErrors()

		flushAndClear()

		Blobber reloaded = Blobber.get(b.id)
		assert !reloaded.is(b)

		assert !reloaded.theBlob.isInitialized()

		assert name == reloaded.name
		assert !reloaded.theBlob.isInitialized()

		assert content == new String(reloaded.theBlob.getBytes(1, (int)reloaded.theBlob.length()))
		assert reloaded.theBlob.isInitialized()
	}

	void testClobFromString() {
		def c = new Clobber(name: name)
		c.theClob = new LazyClob(content, c)
		assert c.save()
		assert !c.hasErrors()

		flushAndClear()

		Clobber reloaded = Clobber.get(c.id)
		assert !reloaded.is(c)

		assert !reloaded.theClob.isInitialized()

		assert name == reloaded.name
		assert !reloaded.theClob.isInitialized()

		assert content == new String(reloaded.theClob.getSubString(1, (int)reloaded.theClob.length()))
		assert reloaded.theClob.isInitialized()
	}

	void testClobFromClob() {
		def c = new Clobber(name: name)
		c.theClob = new LazyClob(Hibernate.createClob(content), c)
		assert c.save()
		assert !c.hasErrors()

		flushAndClear()

		Clobber reloaded = Clobber.get(c.id)
		assert !reloaded.is(c)

		assert !reloaded.theClob.isInitialized()

		assert name == reloaded.name
		assert !reloaded.theClob.isInitialized()

		assert content == new String(reloaded.theClob.getSubString(1, (int)reloaded.theClob.length()))
		assert reloaded.theClob.isInitialized()
	}

	private void flushAndClear() {
		sessionFactory.currentSession.flush()
		sessionFactory.currentSession.clear()
	}
}
