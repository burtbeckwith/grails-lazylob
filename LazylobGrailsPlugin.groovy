import grails.plugin.lazylob.ApplicationContextHolder

class LazylobGrailsPlugin {
	String version = '0.1'
	String grailsVersion = '2.0 > *'
	String title = 'Lazylob Plugin'
	String author = 'Burt Beckwith'
	String authorEmail = 'burt@burtbeckwith.com'
	String description = 'Adds support for lazy-loaded Blobs and Clobs'
	String documentation = 'http://grails.org/plugin/lazylob'
	List pluginExcludes = [
		'grails-app/domain/**'
	]

	String license = 'APACHE'
	def issueManagement = [system: 'Github', url: 'https://github.com/burtbeckwith/grails-lazylob/issues']
	def scm = [url: 'https://github.com/burtbeckwith/grails-lazylob']

	def doWithApplicationContext = { ctx ->
		ApplicationContextHolder.applicationContext = ctx
	}
}
