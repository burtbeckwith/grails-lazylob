package grails.plugin.lazylob

class Blobber {
	String name
	LazyBlob theBlob

	static mapping = {
		table 'nonstandard_blob_table'
		id column: 'nonstandard_id'
		theBlob column: 'nonstandard_column', type: LazyBlobType, params: [propertyName: 'theBlob']
	}
}
