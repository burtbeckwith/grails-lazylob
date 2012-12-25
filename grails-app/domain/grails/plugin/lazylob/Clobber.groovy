package grails.plugin.lazylob

class Clobber {
	String name
	LazyClob theClob

	static mapping = {
		table 'nonstandard_clob_table'
		id column: 'nonstandard_id'
		theClob column: 'nonstandard_column', type: LazyClobType, params: [propertyName: 'theClob']
	}
}
